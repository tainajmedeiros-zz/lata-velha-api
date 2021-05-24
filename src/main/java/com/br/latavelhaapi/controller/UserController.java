package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.DTO.UserForm;
import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Finds a list users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list users", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping
    public ResponseEntity<?> list() {
        try {
            List<User> users = userService.list();
            if(users == null){
                return new ResponseEntity<>(
                        new Response(false, "Not found users"),
                        HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Response(false, "Bad request"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the deleted user", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            User findUser = userService.findById(id);
            if(findUser != null){
                userService.delete(id);
                return new ResponseEntity<User>(findUser, HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    new Response(false, "Not found user with id:" + id),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Response(false, "Bad request"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Edit user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the user who was modified", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") Long id) {
        try {
            User findUser = userService.findById(id);
            if(findUser != null){
                user.setID(findUser.getID());
                userService.update(user);
                return new ResponseEntity<User>(findUser, HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    new Response(false, "Not found user with id: " + id),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Response(false, "Bad request"),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
