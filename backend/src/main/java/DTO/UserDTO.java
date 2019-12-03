/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashSet;
import java.util.Set;
import entities.User;

/**
 *
 * @author ahmed
 */
@Schema(name ="User")
public class UserDTO {
    @Schema(required = true, example ="userMAN21")
    private String userName;
    @Schema(required = true, example="userman@mail.com")
    private String mail;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.mail = user.getEmail();
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }
    
    
    
    
}
