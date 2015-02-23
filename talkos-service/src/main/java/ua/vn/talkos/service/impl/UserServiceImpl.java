package ua.vn.talkos.service.impl;

import org.springframework.stereotype.Service;
import ua.vn.talkos.entity.User;
import ua.vn.talkos.repository.UserRepository;
import ua.vn.talkos.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author oleg.sukhov
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> loadAll() {
        return userRepository.findAll();
    }

    @Override
    public User loadByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void save(User user) {
        Optional<User> optionalUser = Optional.ofNullable(user);

        if(!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User cannot be null");
        }

        userRepository.save(user);
    }
}
