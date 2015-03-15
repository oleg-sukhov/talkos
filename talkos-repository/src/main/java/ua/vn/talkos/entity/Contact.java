package ua.vn.talkos.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.vn.talkos.util.contact.ContactStateEnum;

import javax.persistence.*;

/**
 * @author oleg.sukhov
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Contact extends PersistableEntity {

    @Enumerated(EnumType.STRING)
    private ContactStateEnum state;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
