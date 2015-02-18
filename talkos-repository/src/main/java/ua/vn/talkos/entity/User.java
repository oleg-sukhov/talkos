package ua.vn.talkos.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author oleg.sukhov
 */
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends AbstractPersistable<Long> {

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

    @Column(length = 300)
    private String avatarPath;

    @Column
    private boolean enabled;
}
