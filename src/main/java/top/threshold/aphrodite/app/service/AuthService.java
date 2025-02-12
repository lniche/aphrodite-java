package top.threshold.aphrodite.app.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface AuthService {
  Future<JsonObject> login(JsonObject credentials);
}
