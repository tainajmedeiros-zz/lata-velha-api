package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class UserServiceTest {

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
    public void saveBrand() {
        User userSave = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );

        assertThat(userSave).isNotNull();
    }

    @Test
    public void getUserByIDSuccess() {
        User userSave = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );

        User found = userServiceTest.findById(userSave.getID());

        assertThat(found.getName())
                .isEqualTo(userSave.getName());
        assertThat(found.getEmail())
                .isEqualTo(userSave.getEmail());
    }


    @Test
    public void existsBrandWhitEmailSuccess(){
        User userSave = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );

        Boolean found = userServiceTest.existsByEmail(userSave.getEmail());

        assertThat(found).isTrue();
    }

    @Test
    public void existsBrandWhitEmailFalse(){
        User userSaveOne = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );
        User userSaveTwo = userServiceTest.add(
                createUser("Brenda", "bre@bre.com", "bre")
        );

        Boolean found = userServiceTest.existsByEmail("Kae");

        assertThat(found).isFalse();
    }

    @Test
    public void listUsers(){
        User userSaveOne = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );
        User userSavetwo = userServiceTest.add(
                createUser("Brenda", "bre@bre.com", "bre")
        );
        User userSaveTree = userServiceTest.add(
                createUser("Kae", "kae@kae.com", "kae")
        );

        List<User> findAll = userServiceTest.list();

        assertThat(findAll.get(0).getName()).isEqualTo(userSaveOne.getName());
        assertThat(findAll.get(1).getName()).isEqualTo(userSavetwo.getName());
        assertThat(findAll.get(2).getName()).isEqualTo(userSaveTree.getName());
    }

    @Test
    public void deleteUser(){
        User userSaveOne = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );
        User userSavetwo = userServiceTest.add(
                createUser("Brenda", "bre@bre.com", "bre")
        );
        User userSaveTree = userServiceTest.add(
                createUser("Kae", "kae@kae.com", "kae")
        );

        List<User> findAllBefore = userServiceTest.list();
        assertThat(findAllBefore.size()).isEqualTo(3);

        userServiceTest.delete(userSaveOne.getID());

        List<User> findAllAfter = userServiceTest.list();
        assertThat(findAllAfter.size()).isEqualTo(2);
    }

    @Test
    public void updateUser(){
        User userSave = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );

        userSave.setName("Tai");
        User newUser = userServiceTest.update(userSave);

        assertThat(userSave.getName()).isEqualTo(newUser.getName());
    }

    @Test
    public void findUserByEmailSuccess(){
        User userSaveOne = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );
        User userSavetwo = userServiceTest.add(
                createUser("Brenda", "bre@bre.com", "bre")
        );
        User userSaveTree = userServiceTest.add(
                createUser("Kae", "kae@kae.com", "kae")
        );

        Optional<User> foundOne = userServiceTest.findByEmail(userSaveOne.getEmail());
        Optional<User> foundTwo = userServiceTest.findByEmail(userSavetwo.getEmail());
        Optional<User> foundTree = userServiceTest.findByEmail(userSaveTree.getEmail());

        assertThat(userSaveOne.getEmail()).isEqualTo(foundOne.get().getEmail());
        assertThat(userSavetwo.getEmail()).isEqualTo(foundTwo.get().getEmail());
        assertThat(userSaveTree.getEmail()).isEqualTo(foundTree.get().getEmail());
    }

    @Test
    public void findUserByEmailFalse(){
        User userSaveOne = userServiceTest.add(
                createUser("Taina", "tai@tai.com", "tai")
        );

        Optional<User> foundOne = userServiceTest.findByEmail("bre@bre.com");

        assertThat(foundOne).isEmpty();
    }

}
