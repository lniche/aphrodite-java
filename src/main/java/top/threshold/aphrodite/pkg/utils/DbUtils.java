package top.threshold.aphrodite.pkg.utils;

import io.vertx.core.Vertx;
import io.vertx.pgclient.PgBuilder;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.util.Properties;

public class DbUtils {

    private static final String HOST_CONFIG = "datasource.host";
    private static final String PORT_CONFIG = "datasource.port";
    private static final String DATABASE_CONFIG = "datasource.database";
    private static final String USERNAME_CONFIG = "datasource.username";
    private static final String PASSWORD_CONFIG = "datasource.password";

    private DbUtils() {

    }

    /**
     * Build DB client that is used to manage a pool of connections
     *
     * @param vertx Vertx context
     * @return PostgreSQL pool
     */
    public static SqlClient buildDbClient(Vertx vertx) {
        final Properties properties = ConfigUtils.getInstance().getProperties();
        PgConnectOptions connectOptions = new PgConnectOptions()
            .setPort(Integer.parseInt(properties.getProperty(PORT_CONFIG)))
            .setHost(properties.getProperty(HOST_CONFIG))
            .setDatabase(properties.getProperty(DATABASE_CONFIG))
            .setUser(properties.getProperty(USERNAME_CONFIG))
            .setPassword(properties.getProperty(PASSWORD_CONFIG));
        PoolOptions poolOptions = new PoolOptions()
            .setMaxSize(5);

        return PgBuilder
            .client()
            .with(poolOptions)
            .connectingTo(connectOptions)
            .build();
    }

    /**
     * Build Flyway configuration that is used to run migrations
     *
     * @return Flyway configuration
     */
    public static Configuration buildMigrationsConfiguration() {
        final Properties properties = ConfigUtils.getInstance().getProperties();
        final String url = "jdbc:postgresql://" + properties.getProperty(HOST_CONFIG) + ":" + properties.getProperty(PORT_CONFIG) + "/" + properties.getProperty(DATABASE_CONFIG);

        return new FluentConfiguration().dataSource(url, properties.getProperty(USERNAME_CONFIG), properties.getProperty(PASSWORD_CONFIG));
    }

}
