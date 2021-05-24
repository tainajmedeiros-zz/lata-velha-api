package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.payload.LoginRequest;
import com.br.latavelhaapi.payload.SignUpRequest;
import com.br.latavelhaapi.repository.UserRepository;
import com.br.latavelhaapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generaterUser() throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signUp")
                .content(asJsonString(
                        new SignUpRequest("taina", "taina@email.com", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signUp")
                .content(asJsonString(
                        new SignUpRequest("tequila", "tequila@email.com", "TQ@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private String obtainAccessToken(String username, String password) throws Exception {
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signIn")
                .content(asJsonString(
                        new LoginRequest(username, password)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("jwtAuthenticationResponse.tokenType").value("Bearer"))
                .andExpect(MockMvcResultMatchers.jsonPath("jwtAuthenticationResponse.accessToken").exists());

        String resultString = result.andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map> map = mapper.readValue(resultString, Map.class);
        return map.get("jwtAuthenticationResponse").get("accessToken").toString();
    }

    @Test
    @Transactional
    public void successListAllUser() throws Exception {
        generaterUser();
        String token = obtainAccessToken("taina@email.com","TM@123");
        this.mvc.perform( MockMvcRequestBuilders
                .get("/users")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void unauthorizedGetAllUser() throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    public void deleteUser() throws Exception {
        generaterUser();
        String token = obtainAccessToken("taina@email.com","TM@123");
        Optional<User> user = userService.findByEmail("tequila@email.com");
        this.mvc.perform( MockMvcRequestBuilders
                .delete("/users/"+ user.get().getID())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        generaterUser();
        String token = obtainAccessToken("taina@email.com","TM@123");
        Optional<User> user = userService.findByEmail("taina@email.com");
        this.mvc.perform( MockMvcRequestBuilders
                .put("/users/" + user.get().getID())
                .content(asJsonString(
                        new User ("tai", "tai@email.com")))
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
