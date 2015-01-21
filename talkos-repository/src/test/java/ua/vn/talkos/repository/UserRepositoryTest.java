package ua.vn.talkos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.vn.talkos.config.RepositoryTestConfig;
import ua.vn.talkos.persistence.UserRepository;

/**
 * @author oleg.sukhov
 */
@ContextConfiguration(classes = RepositoryTestConfig.class)
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeClass
    public void setup() {
        System.out.println(userRepository);

    }

    @Test
    public void test() {

    }
}
