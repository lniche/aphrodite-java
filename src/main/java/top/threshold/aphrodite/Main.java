package top.threshold.aphrodite;

import io.vertx.core.Vertx;
import top.threshold.aphrodite.pkg.verticle.MainVerticle;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(MainVerticle.class.getName())
            .onFailure(throwable -> System.exit(-1));
    }
}
