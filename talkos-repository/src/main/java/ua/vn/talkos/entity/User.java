package ua.vn.talkos.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author oleg.sukhov
 */
@Entity
@Data
public class User {

    @Id
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;
}
