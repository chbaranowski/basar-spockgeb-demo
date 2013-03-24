package controller;

import data.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource userResource = new UserResource();
        userResource.name = user.getName();
        userResource.lastname = user.getLastname();
        userResource.email = user.getEmail();
        userResource.basarNumber = user.getBasarNumber();
        userResource.add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
        return userResource;
    }

    public User toUser(UserResource resource) {
        return toUser(new User(), resource);
    }

    public User toUser(User user, UserResource resource) {
        user.setName(resource.name);
        user.setLastname(resource.lastname);
        user.setEmail(resource.email);
        user.setBasarNumber(resource.getBasarNumber());
        return user;
    }

}
