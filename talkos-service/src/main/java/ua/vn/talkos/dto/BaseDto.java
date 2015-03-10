package ua.vn.talkos.dto;

import ua.vn.talkos.entity.PersistableEntity;

/**
 * @author oleg.sukhov
 */
public interface BaseDto<T extends PersistableEntity> {
    public T toEntity();
}
