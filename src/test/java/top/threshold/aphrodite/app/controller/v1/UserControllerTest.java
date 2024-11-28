package top.threshold.aphrodite.app.controller.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import top.threshold.aphrodite.app.entity.pojo.UserDO;
import top.threshold.aphrodite.app.repository.UserRepository;
import top.threshold.aphrodite.pkg.utils.RedisUtil;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository mockUserRepository;
    @MockBean
    private RedisUtil mockRedisUtil;

    @Test
    void testGetUser() throws Exception {
        // Setup
        // Configure RedisUtil.getObj(...).
        final UserController.GetUserResponse getUserResponse = new UserController.GetUserResponse();
        getUserResponse.setNickname("nickname");
        getUserResponse.setUserNo(0L);
        getUserResponse.setUserCode("userCode");
        getUserResponse.setEmail("email");
        getUserResponse.setPhone("phone");
        when(mockRedisUtil.getObj("key", UserController.GetUserResponse.class)).thenReturn(getUserResponse);

        // Run the test and verify the results
        mockMvc.perform(get("/v1/user/{userCode}", "userCode")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetUser_RedisUtilGetObjReturnsNull() throws Exception {
        // Setup
        when(mockRedisUtil.getObj("key", UserController.GetUserResponse.class)).thenReturn(null);

        // Configure UserRepository.getByCode(...).
        final UserDO userDO = new UserDO();
        userDO.setDeletedAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        userDO.setUserCode("userCode");
        userDO.setUserNo(0L);
        userDO.setUsername("username");
        userDO.setStatus(0);
        when(mockUserRepository.getByCode("code")).thenReturn(userDO);

        // Run the test and verify the results
        mockMvc.perform(get("/v1/user/{userCode}", "userCode")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true));

        // Confirm RedisUtil.set(...).
        final UserDO value = new UserDO();
        value.setDeletedAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        value.setUserCode("userCode");
        value.setUserNo(0L);
        value.setUsername("username");
        value.setStatus(0);
        verify(mockRedisUtil).set("key", value, 60L);
    }

    @Test
    void testGetUser_UserRepositoryReturnsNull() throws Exception {
        // Setup
        when(mockRedisUtil.getObj("key", UserController.GetUserResponse.class)).thenReturn(null);
        when(mockUserRepository.getByCode("code")).thenReturn(null);

        // Run the test and verify the results
        mockMvc.perform(get("/v1/user/{userCode}", "userCode")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testUpdateUser() throws Exception {
        // Setup
        // Configure UserRepository.getByCode(...).
        final UserDO userDO = new UserDO();
        userDO.setDeletedAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        userDO.setUserCode("userCode");
        userDO.setUserNo(0L);
        userDO.setUsername("username");
        userDO.setStatus(0);
        when(mockUserRepository.getByCode("code")).thenReturn(userDO);

        // Run the test and verify the results
        mockMvc.perform(put("/v1/user")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));

        // Confirm UserRepository.updateById(...).
        final UserDO entity = new UserDO();
        entity.setDeletedAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        entity.setUserCode("userCode");
        entity.setUserNo(0L);
        entity.setUsername("username");
        entity.setStatus(0);
        verify(mockUserRepository).updateById(entity);
    }

    @Test
    void testUpdateUser_UserRepositoryGetByCodeReturnsNull() throws Exception {
        // Setup
        when(mockUserRepository.getByCode("code")).thenReturn(null);

        // Run the test and verify the results
        mockMvc.perform(put("/v1/user")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true));
    }

    @Test
    void testDeleteUser() throws Exception {
        // Setup
        // Configure UserRepository.getByCode(...).
        final UserDO userDO = new UserDO();
        userDO.setDeletedAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        userDO.setUserCode("userCode");
        userDO.setUserNo(0L);
        userDO.setUsername("username");
        userDO.setStatus(0);
        when(mockUserRepository.getByCode("code")).thenReturn(userDO);

        // Run the test and verify the results
        mockMvc.perform(delete("/v1/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));

        // Confirm UserRepository.updateById(...).
        final UserDO entity = new UserDO();
        entity.setDeletedAt(OffsetDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), ZoneOffset.UTC));
        entity.setUserCode("userCode");
        entity.setUserNo(0L);
        entity.setUsername("username");
        entity.setStatus(0);
        verify(mockUserRepository).updateById(entity);
    }

    @Test
    void testDeleteUser_UserRepositoryGetByCodeReturnsNull() throws Exception {
        // Setup
        when(mockUserRepository.getByCode("code")).thenReturn(null);

        // Run the test and verify the results
        mockMvc.perform(delete("/v1/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("", true));
    }
}
