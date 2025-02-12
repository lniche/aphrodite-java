package top.threshold.aphrodite.app.service.impl;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import top.threshold.aphrodite.app.model.LoginRequest;
import top.threshold.aphrodite.app.model.LoginResponse;
import top.threshold.aphrodite.app.repository.UserRepository;
import top.threshold.aphrodite.app.repository.impl.UserRepositoryImpl;
import top.threshold.aphrodite.app.service.AuthService;

public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;

  public AuthServiceImpl(Vertx vertx, JsonObject config) {
    this.userRepository = new UserRepositoryImpl(vertx, config);
  }

  @Override
  public Future<JsonObject> login(JsonObject credentials) {
    return Future.future(promise -> {
      LoginRequest loginRequest = credentials.mapTo(LoginRequest.class);
      userRepository.findByUsername(loginRequest.getUsername())
        .onSuccess(user -> {
          if (user != null && user.getString("password").equals(loginRequest.getPassword())) {
            LoginResponse loginResponse = new LoginResponse();
            promise.complete(JsonObject.mapFrom(loginResponse));
          } else {
            promise.fail("Invalid username or password");
          }
        })
        .onFailure(promise::fail);
    });
  }
}
