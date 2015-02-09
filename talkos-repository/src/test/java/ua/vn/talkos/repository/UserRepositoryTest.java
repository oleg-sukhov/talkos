package ua.vn.talkos.repository;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.vn.talkos.config.RepositoryTestConfig;
import ua.vn.talkos.entity.User;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author oleg.sukhov
 */
@ContextConfiguration(classes = RepositoryTestConfig.class)
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

    @Resource
    private UserRepository userRepository;

    @Resource
    private IDatabaseTester databaseTester;

    @BeforeClass
    public void setUp() throws Exception {
        databaseTester.setDataSet(buildDataSet());
        databaseTester.onSetup();
    }

    private IDataSet buildDataSet() throws FileNotFoundException, DataSetException {
        URL url = this.getClass().getResource("/ua/vn/talkos/persistence/dataset/UserRepositoryDataSet.xml");
        FileInputStream fis = new FileInputStream(new File(url.getPath()));
        return new FlatXmlDataSetBuilder().build(fis);
    }

    @AfterClass
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertEquals(4, users.size());
    }

    @Test
    public void testCount() {
        assertEquals(4, userRepository.count());
    }

    @Test
    public void testFindOne() {
        User user = userRepository.findOne(Long.valueOf(4));

        assertNotNull(user);
        assertEquals(Long.valueOf(4), user.getId());
        assertEquals("BorisovE", user.getUsername());
        assertEquals("qwer5", user.getPassword());
        assertEquals(true, user.isEnabled());
    }

    @Test
    public void testFindByUserName() {
        User firstUser = userRepository.findByUsername("BorisovE");
        User secondUser = userRepository.findByUsername("JoshuaB");

        assertNotNull(firstUser);
        assertEquals(Long.valueOf(4), firstUser.getId());
        assertEquals("BorisovE", firstUser.getUsername());
        assertEquals("qwer5", firstUser.getPassword());
        assertEquals(true, firstUser.isEnabled());

        assertNotNull(secondUser);
        assertEquals(Long.valueOf(1), secondUser.getId());
        assertEquals("JoshuaB", secondUser.getUsername());
        assertEquals("qwer", secondUser.getPassword());
        assertEquals(false, secondUser.isEnabled());
    }
}
