package top.threshold.aphrodite.pkg.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;
import top.threshold.aphrodite.pkg.utils.DbUtils;

public class MigrationVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) {
        final Configuration config = DbUtils.buildMigrationsConfiguration();
        final Flyway flyway = new Flyway(config);

        flyway.migrate();

        promise.complete();
    }

}
