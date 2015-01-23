package ua.vn.talkos.repository;

import org.dbunit.IDatabaseTester;
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
import java.io.FileInputStream;
import java.util.List;

/**
 * @author oleg.sukhov
 */
@ContextConfiguration(classes = RepositoryTestConfig.class)
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

    @Resource
    private UserRepository userRepository;

    @Autowired
    private IDatabaseTester databaseTester;

    @BeforeClass
    public void setUp() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("ua.vn.talkos.persistence.dataset.UserRepositoryDataSet.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @AfterClass
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    @Test
    public void testLoadUsers() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(4, users.size());
    }
}
