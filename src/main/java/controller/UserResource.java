package controller;

import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    String basarNumber;

    String name;

    String email;

    String lastname;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBasarNumber() {
        return basarNumber;
    }

    public void setBasarNumber(String basarNumber) {
        this.basarNumber = basarNumber;
    }

}
