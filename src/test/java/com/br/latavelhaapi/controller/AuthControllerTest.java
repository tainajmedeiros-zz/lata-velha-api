package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.payload.LoginRequest;
import com.br.latavelhaapi.payload.SignUpRequest;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

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
    }

    @Test
    public void successSignUpUserTest() throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signUp")
                .content(asJsonString(
                        new SignUpRequest("tata", "tata@email.com", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
    }

    @Test
    public void existsByEmailSignUpUserTest() throws Exception {
        generaterUser();
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signUp")
                .content(asJsonString(
                        new SignUpRequest("tainazinha", "taina@email.com", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));
    }

    @Test
    public void successAuthenticateUserTest() throws Exception {
        generaterUser();
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signIn")
                .content(asJsonString(
                        new LoginRequest("taina@email.com", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("jwtAuthenticationResponse.tokenType").value("Bearer"))
                .andExpect(MockMvcResultMatchers.jsonPath("jwtAuthenticationResponse.accessToken").exists());
    }

    @Test
    public void notSuccessUnregisteredUserAuthenticateUserTest() throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signIn")
                .content(asJsonString(
                        new LoginRequest("taina@email.com", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void userCorrectPasswordWrongAuthenticateUserTest() throws Exception {
        generaterUser();
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signIn")
                .content(asJsonString(
                        new LoginRequest("taina@email.com", "senhaerrada")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void userWrongPasswordCorrectAuthenticateUserTest() throws Exception {
        generaterUser();
        this.mvc.perform( MockMvcRequestBuilders
                .post("/auth/signIn")
                .content(asJsonString(
                        new LoginRequest("usererrado", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
