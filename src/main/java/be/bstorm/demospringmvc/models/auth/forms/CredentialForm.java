package be.bstorm.demospringmvc.models.auth.forms;

import be.bstorm.demospringmvc.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CredentialForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
