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

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;
}
