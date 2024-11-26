package top.threshold.aphrodite.app.controller.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import top.threshold.aphrodite.app.entity.pojo.UserDO;
import top.threshold.aphrodite.app.repository.UserRepository;
import top.threshold.aphrodite.pkg.constant.CacheKey;
import top.threshold.aphrodite.pkg.util.RedisUtil;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RedisUtil mockRedisUtil;
    @MockBean
    private UserRepository mockUserRepository;

    @Test
    void testSendVerifyCode() throws Exception {
        // Setup
        when(mockRedisUtil.hasKey("key")).thenReturn(false);

        // Run the test and verify the results
        mockMvc.perform(post("/v1/send-code")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{}", true));
        verify(mockRedisUtil).setStr("key", "value", 60L);
    }

    @Test
    void testSendVerifyCode_RedisUtilHasKeyReturnsTrue() throws Exception {
        // Setup
        when(mockRedisUtil.hasKey("key")).thenReturn(true);

        // Run the test and verify the results
        mockMvc.perform(post("/v1/send-code")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{}", true));
    }

    @Test
    void testLogin() throws Exception {
        // Setup
        when(mockRedisUtil.getStr("key")).thenReturn("result");

        // Configure UserRepository.getByPhone(...).
        final UserDO userDO = new UserDO();
        userDO.setUserCode("userCode");
        userDO.setUserNo(0L);
        userDO.setNickname("nickname");
        userDO.setPhone("phone");
        userDO.setClientIp("clientIp");
        userDO.setLoginAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        userDO.setLoginToken("loginToken");
        when(mockUserRepository.getByPhone("phone")).thenReturn(userDO);

        when(mockRedisUtil.nextId(CacheKey.NEXT_UNO)).thenReturn(0L);

        // Run the test and verify the results
        mockMvc.perform(post("/v1/login")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{}", true));

        // Confirm UserRepository.save(...).
        final UserDO entity = new UserDO();
        entity.setUserCode("userCode");
        entity.setUserNo(0L);
        entity.setNickname("nickname");
        entity.setPhone("phone");
        entity.setClientIp("clientIp");
        entity.setLoginAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        entity.setLoginToken("loginToken");
        verify(mockUserRepository).save(entity);

        // Confirm UserRepository.updateById(...).
        final UserDO entity1 = new UserDO();
        entity1.setUserCode("userCode");
        entity1.setUserNo(0L);
        entity1.setNickname("nickname");
        entity1.setPhone("phone");
        entity1.setClientIp("clientIp");
        entity1.setLoginAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        entity1.setLoginToken("loginToken");
        verify(mockUserRepository).updateById(entity1);
        verify(mockRedisUtil).del("keys");
    }

    @Test
    void testLogout() throws Exception {
        // Setup
        // Configure UserRepository.getByCode(...).
        final UserDO userDO = new UserDO();
        userDO.setUserCode("userCode");
        userDO.setUserNo(0L);
        userDO.setNickname("nickname");
        userDO.setPhone("phone");
        userDO.setClientIp("clientIp");
        userDO.setLoginAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        userDO.setLoginToken("loginToken");
        when(mockUserRepository.getByCode("code")).thenReturn(userDO);

        // Run the test and verify the results
        mockMvc.perform(post("/v1/logout")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{}", true));

        // Confirm UserRepository.updateById(...).
        final UserDO entity = new UserDO();
        entity.setUserCode("userCode");
        entity.setUserNo(0L);
        entity.setNickname("nickname");
        entity.setPhone("phone");
        entity.setClientIp("clientIp");
        entity.setLoginAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        entity.setLoginToken("loginToken");
        verify(mockUserRepository).updateById(entity);
    }

    @Test
    void testLogout_UserRepositoryGetByCodeReturnsNull() throws Exception {
        // Setup
        when(mockUserRepository.getByCode("code")).thenReturn(null);

        // Run the test and verify the results
        mockMvc.perform(post("/v1/logout")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("", true));
    }
}
