package top.threshold.aphrodite;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import top.threshold.aphrodite.app.router.AuthRouter;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    ConfigRetrieverOptions options = new ConfigRetrieverOptions()
      .addStore(new ConfigStoreOptions().setType("file").setConfig(new JsonObject().put("path", "application.conf")));
    ConfigRetriever retriever = ConfigRetriever.create(vertx, options);

    retriever.getConfig().onSuccess(config -> {
      // 配置加载成功
      vertx.exceptionHandler(err -> {
        System.err.println("Exception in verticle: " + err.getMessage());
      });

      Router router = Router.router(vertx);
      AuthRouter authRouter = new AuthRouter(vertx, config);
      authRouter.applyRoutes(router);

      vertx.createHttpServer().requestHandler(router).listen(config.getInteger("http.port", 8000)).onSuccess(http -> {
        // HTTP 服务器启动成功
        startPromise.complete();
        System.out.println("HTTP server started on port " + config.getInteger("http.port", 8000));
      }).onFailure(err -> {
        // HTTP 服务器启动失败
        startPromise.fail(err);
      });
    }).onFailure(err -> {
      // 配置加载失败
      startPromise.fail(err);
    });
  }


  public void main(String[] args) {
    Vertx.vertx().deployVerticle(new MainVerticle());
  }
}

