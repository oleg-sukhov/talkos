package ua.vn.talkos.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.vn.talkos.dto.UserDto;
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
    private PasswordEncoder passwordEncoder;

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
    public void save(UserDto userDto) {
        Optional<UserDto> optionalUser = Optional.ofNullable(userDto);

        if(!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User cannot be null");
        }

        User user = userDto.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadByLogin(username);
    }
}
