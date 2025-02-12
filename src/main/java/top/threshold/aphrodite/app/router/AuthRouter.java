package top.threshold.aphrodite.app.router;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import top.threshold.aphrodite.app.service.AuthService;
import top.threshold.aphrodite.app.service.impl.AuthServiceImpl;

public class AuthRouter {
  private final AuthService authService;

  public AuthRouter(Vertx vertx, JsonObject config) {
    this.authService = new AuthServiceImpl(vertx, config);
  }

  public void applyRoutes(Router router) {
    router.post("/login").handler(this::handleLogin);
  }

  private void handleLogin(RoutingContext context) {
    context.request().bodyHandler(buffer -> {
      try {
        JsonObject body = buffer.toJsonObject();
        authService.login(body);
      } catch (Exception e) {
        context.fail(e);
      }
    });
  }
}
