#!/bin/bash
set -e

DATE=$(date +%Y%m%d%H%M)
# Base path
BASE_PATH=/work/projects/yudao-server
# The address of the compiled jar. During deployment, Jenkins will upload the jar package to this directory.
SOURCE_PATH=$BASE_PATH/build
# Service name. It is also agreed that the name of the deployed service's jar package is the same as it.
SERVER_NAME=yudao-server
# Environment
PROFILES_ACTIVE=development
# Health check URL
HEALTH_CHECK_URL=http://127.0.0.1:48080/actuator/health/

# Storage path for heapError
HEAP_ERROR_PATH=$BASE_PATH/heapError
# JVM parameters
JAVA_OPS="-Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$HEAP_ERROR_PATH"

# SkyWalking Agent configuration
#export SW_AGENT_NAME=$SERVER_NAME
#export SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.84:11800
#export SW_GRPC_LOG_SERVER_HOST=192.168.0.84
#export SW_AGENT_TRACE_IGNORE_PATH="Redisson/PING,/actuator/**,/admin/**"
#export JAVA_AGENT=-javaagent:/work/skywalking/apache-skywalking-apm-bin/agent/skywalking-agent.jar

# Backup
function backup() {
    # If it does not exist, no backup is required.
    if [ ! -f "$BASE_PATH/$SERVER_NAME.jar" ]; then
        echo "[backup] $BASE_PATH/$SERVER_NAME.jar does not exist, skipping backup"
    # If it exists, back it up to the backup directory with the time as the suffix.
    else
        echo "[backup] Starting to back up $SERVER_NAME ..."
        cp $BASE_PATH/$SERVER_NAME.jar $BASE_PATH/backup/$SERVER_NAME-$DATE.jar
        echo "[backup] Backup of $SERVER_NAME completed"
    fi
}

# Move the latest built code to the project environment
function transfer() {
    echo "[transfer] Starting to transfer $SERVER_NAME.jar"

    # Delete the original jar package
    if [ ! -f "$BASE_PATH/$SERVER_NAME.jar" ]; then
        echo "[transfer] $BASE_PATH/$SERVER_NAME.jar does not exist, skipping deletion"
    else
        echo "[transfer] Removal of $BASE_PATH/$SERVER_NAME.jar completed"
        rm $BASE_PATH/$SERVER_NAME.jar
    fi

    # Copy the new jar package
    echo "[transfer] Getting $SERVER_NAME.jar from $SOURCE_PATH and migrating it to $BASE_PATH ...."
    cp $SOURCE_PATH/$SERVER_NAME.jar $BASE_PATH

    echo "[transfer] Transfer of $SERVER_NAME.jar completed"
}

# Stop: Gracefully shut down the previously started service
function stop() {
    echo "[stop] Starting to stop $BASE_PATH/$SERVER_NAME"
    PID=$(ps -ef | grep $BASE_PATH/$SERVER_NAME | grep -v "grep" | awk '{print $2}')
    # If the Java service is running, shut it down.
    if [ -n "$PID" ]; then
        # Normal shutdown
        echo "[stop] $BASE_PATH/$SERVER_NAME is running, starting to kill [$PID]"
        kill -15 $PID
        # Wait for a maximum of 120 seconds until the shutdown is complete.
        for ((i = 0; i < 120; i++))
            do
                sleep 1
                PID=$(ps -ef | grep $BASE_PATH/$SERVER_NAME | grep -v "grep" | awk '{print $2}')
                if [ -n "$PID" ]; then
                    echo -e ".\c"
                else
                    echo "[stop] Stopping $BASE_PATH/$SERVER_NAME succeeded"
                    break
                fi
            done

        # If the normal shutdown fails, then perform a forced kill -9 to shut it down.
        if [ -n "$PID" ]; then
            echo "[stop] Stopping $BASE_PATH/$SERVER_NAME failed, forcing kill -9 $PID"
            kill -9 $PID
        fi
    # If the Java service is not started, no shutdown is required.
    else
        echo "[stop] $BASE_PATH/$SERVER_NAME is not started, no need to stop"
    fi
}

# Start: Start the backend project
function start() {
    # Before starting, print the startup parameters.
    echo "[start] Starting to start $BASE_PATH/$SERVER_NAME"
    echo "[start] JAVA_OPS: $JAVA_OPS"
    echo "[start] JAVA_AGENT: $JAVA_AGENT"
    echo "[start] PROFILES: $PROFILES_ACTIVE"

    # Start the service
    BUILD_ID=dontKillMe nohup java -server $JAVA_OPS $JAVA_AGENT -jar $BASE_PATH/$SERVER_NAME.jar --spring.profiles.active=$PROFILES_ACTIVE &
    echo "[start] Starting $BASE_PATH/$SERVER_NAME completed"
}

# Health check: Automatically determine whether the backend project has started normally.
function healthCheck() {
    # If the health check is configured, perform the health check.
    if [ -n "$HEALTH_CHECK_URL" ]; then
        # The health check takes a maximum of 120 seconds until it passes.
        echo "[healthCheck] Starting to perform a health check via the $HEALTH_CHECK_URL address";
        for ((i = 0; i < 120; i++))
            do
                # Request the health check address and only get the status code.
                result=`curl -I -m 10 -o /dev/null -s -w %{http_code} $HEALTH_CHECK_URL || echo "000"`
                # If the status code is 200, the health check passes.
                if [ "$result" == "200" ]; then
                    echo "[healthCheck] Health check passed";
                    break
                # If the status code is not 200, it means it failed. Sleep for 1 second and then retry.
                else
                    echo -e ".\c"
                    sleep 1
                fi
            done

        # If the health check fails, exit the shell script abnormally and do not continue the deployment.
        if [ ! "$result" == "200" ]; then
            echo "[healthCheck] Health check failed, the deployment may have failed. Check the logs to determine if it started successfully by yourself";
            tail -n 10 nohup.out
            exit 1;
        # If the health check passes, print the last 10 lines of the log. Maybe the deployer wants to check the log.
        else
            tail -n 10 nohup.out
        fi
    # If the health check is not configured, sleep for 120 seconds and manually check the logs to see if the deployment was successful.
    else
        echo "[healthCheck] HEALTH_CHECK_URL is not configured, starting to sleep for 120 seconds";
        sleep 120
        echo "[healthCheck] Sleeping for 120 seconds completed, check the logs to determine if it started successfully by yourself";
        tail -n 50 nohup.out
    fi
}

# Deployment
function deploy() {
    cd $BASE_PATH
    # Backup the original jar
    backup
    # Stop the Java service
    stop
    # Deploy the new jar
    transfer
    # Start the Java service
    start
    # Health check
    healthCheck
}

deploy
