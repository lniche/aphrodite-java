package top.threshold.aphrodite;

import io.vertx.core.Vertx;

public class AphroditeApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MainVerticle.class.getName());
    }
}
