package ua.vn.talkos.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

/**
 * @author oleg.sukhov
 */
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends PersistableEntity<Long> implements UserDetails {

    @Column(unique = true, nullable = false, length = 100)
    private String login;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(unique = true, length = 100)
    private String email;

    @Column(name = "avatar_path", length = 300)
    private String avatarPath;

    @Column
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
