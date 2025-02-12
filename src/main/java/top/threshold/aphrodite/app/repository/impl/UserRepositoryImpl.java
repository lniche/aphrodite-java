package top.threshold.aphrodite.app.repository.impl;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import top.threshold.aphrodite.app.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
  private Map<String, JsonObject> users = new HashMap<>();

  public UserRepositoryImpl(Vertx vertx, JsonObject config) {
    // 模拟用户数据
    users.put("admin", new JsonObject().put("id", "1").put("username", "admin").put("password", "password"));
  }

  @Override
  public Future<JsonObject> findByUsername(String username) {
    return Future.succeededFuture(users.get(username));
  }
}
