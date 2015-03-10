package ua.vn.talkos.entity;

/**
 * @author oleg.sukhov
 */

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class PersistableEntity<PK extends Serializable> implements Persistable<PK> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == id;
    }
}
