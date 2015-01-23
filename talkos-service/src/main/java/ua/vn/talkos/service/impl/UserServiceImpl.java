package ua.vn.talkos.service.impl;

import org.springframework.stereotype.Service;
import ua.vn.talkos.entity.User;
import ua.vn.talkos.repository.UserRepository;
import ua.vn.talkos.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oleg.sukhov
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> loadUsers() {
        return userRepository.findAll();
    }
}
