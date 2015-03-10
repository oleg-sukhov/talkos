package ua.vn.talkos.dto;

import lombok.Data;
import ua.vn.talkos.entity.User;

/**
 * @author oleg.sukhov
 */
@Data
public class UserDto implements BaseDto<User> {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarPath;
    private boolean enabled;

    @Override
    public User toEntity() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAvatarPath(avatarPath);
        user.setEnabled(enabled);
        return user;
    }
}
