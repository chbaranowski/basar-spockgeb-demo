package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.Basar;
import data.User;

@Controller
@RequestMapping(value = "/users/", produces = "application/json")
public class UserController {

    @Autowired
    Basar basar;
    
    @Autowired
    UserResourceAssembler assembler;

    @RequestMapping(value = "/{id}",  method = RequestMethod.GET)
    @ResponseBody
    public UserResource findUser(@PathVariable Long id) {
        User user = basar.findUserWithId(id);
        return assembler.toResource(user);
    }

    @RequestMapping(value = "/",      method = RequestMethod.GET)
    @ResponseBody
    public Iterable<UserResource> listUsers() {
        Iterable<User> users = basar.findAllUsers();
        return assembler.toResources(users);
    }

    @RequestMapping(value = "/",      method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserResource> createUser(UserResource userResource) {
        User user = assembler.toUser(userResource);
        basar.saveUser(user);
        return new ResponseEntity<UserResource>(assembler.toResource(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",  method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus updateUser(@PathVariable Long id, UserResource userResource) {
        User user = assembler.toUser(basar.findUserWithId(id), userResource);
        basar.saveUser(user);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/{id}",  method = RequestMethod.DELETE)
    @ResponseBody
    public HttpStatus deleteUser(@PathVariable Long id) {
        basar.deleteUserWithId(id);
        return HttpStatus.OK;
    }
    
    @RequestMapping(value = "/",      method = RequestMethod.DELETE)
    @ResponseBody
    public HttpStatus deleteUsers() {
        Iterable<User> users = basar.findAllUsers();
        for (User user : users) {
          basar.deleteUserWithId(user.getId());
        }
        return HttpStatus.OK;
    }

}
