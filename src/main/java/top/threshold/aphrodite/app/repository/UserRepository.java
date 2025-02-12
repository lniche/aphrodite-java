package top.threshold.aphrodite.app.repository;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface UserRepository {
  Future<JsonObject> findByUsername(String username);
}
