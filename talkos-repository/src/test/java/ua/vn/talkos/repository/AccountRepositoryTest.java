package ua.vn.talkos.repository;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.vn.talkos.config.RepositoryTestConfig;
import ua.vn.talkos.entity.Account;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author oleg.sukhov
 */
@ContextConfiguration(classes = RepositoryTestConfig.class)
public class AccountRepositoryTest extends AbstractTestNGSpringContextTests {

    @Resource
    private AccountRepository accountRepository;

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
        List<Account> accounts = accountRepository.findAll();
        assertNotNull(accounts);
        assertEquals(4, accounts.size());
    }

    @Test
    public void testCount() {
        assertEquals(4, accountRepository.count());
    }

    @Test
    public void testFindOne() {
        Account account = accountRepository.findOne(Long.valueOf(4));

        assertNotNull(account);
        assertEquals(Long.valueOf(4), account.getId());
        assertEquals("BorisovE", account.getLogin());
        assertEquals("qwer5", account.getPassword());
        assertEquals(true, account.isEnabled());
    }

    @Test
    public void testFindByUserName() {
        Account firstAccount = accountRepository.findByLogin("BorisovE");
        Account secondAccount = accountRepository.findByLogin("JoshuaB");

        assertNotNull(firstAccount);
        assertEquals(Long.valueOf(4), firstAccount.getId());
        assertEquals("BorisovE", firstAccount.getLogin());
        assertEquals("qwer5", firstAccount.getPassword());
        assertEquals(true, firstAccount.isEnabled());

        assertNotNull(secondAccount);
        assertEquals(Long.valueOf(1), secondAccount.getId());
        assertEquals("JoshuaB", secondAccount.getLogin());
        assertEquals("qwer", secondAccount.getPassword());
        assertEquals(false, secondAccount.isEnabled());
    }
}
