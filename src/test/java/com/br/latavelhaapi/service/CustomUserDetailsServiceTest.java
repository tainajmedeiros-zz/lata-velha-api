package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class CustomUserDetailsServiceTest {

    @Autowired
    CustomUserDetailsService customUserDetailsServiceTest;

    @Autowired
    UserService userServiceTest;

    private User createUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    @Test
    public void loadUserByEmailSuccess(){
        User userSave = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );

        UserDetails foundUser = customUserDetailsServiceTest.loadUserByUsername(userSave.getEmail());

        assertThat(foundUser).isNotNull();
    }

    @Test
    public void loadUserByIdSuccess(){
        User userSave = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );

        UserDetails foundUser = customUserDetailsServiceTest.loadUserById(userSave.getID());

        assertThat(foundUser).isNotNull();
    }
}
