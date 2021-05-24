package com.br.latavelhaapi.payload;

import com.br.latavelhaapi.model.User;

public class LoginResponse {
    public User user;
    public JwtAuthenticationResponse jwtAuthenticationResponse;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JwtAuthenticationResponse getJwtAuthenticationResponse() {
        return jwtAuthenticationResponse;
    }

    public void setJwtAuthenticationResponse(JwtAuthenticationResponse jwtAuthenticationResponse) {
        this.jwtAuthenticationResponse = jwtAuthenticationResponse;
    }
}
