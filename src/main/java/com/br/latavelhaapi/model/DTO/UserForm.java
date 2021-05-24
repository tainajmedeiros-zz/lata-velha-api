package com.br.latavelhaapi.model.DTO;

import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.repository.UserRepository;
import com.br.latavelhaapi.service.UserService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class UserForm {

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User convert(UserService userService){
        Optional<User> user = userService.findByEmail(email);
        return new User(name,email,password);
    }
}
