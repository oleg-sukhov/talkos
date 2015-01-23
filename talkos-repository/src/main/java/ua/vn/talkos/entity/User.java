package ua.vn.talkos.entity;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author oleg.sukhov
 */
@Entity
@Data
public class User extends AbstractPersistable<Long> {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;
}
