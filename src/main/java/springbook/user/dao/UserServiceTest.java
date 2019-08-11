package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;
import static springbook.user.dao.UserService.MIN_LOGIN_FOR_SILVER;
import static springbook.user.dao.UserService.MIN_RECOMMEND_FOR_GOLD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Autowired
    PlatformTransactionManager transactionManager;

    private List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("aaa", "가가가", "p1", Level.BASIC, MIN_LOGIN_FOR_SILVER - 1, 0, "aaa@gmail.com"),
                new User("bbb", "나나나", "p2", Level.BASIC, MIN_LOGIN_FOR_SILVER, 0, "bbb@gmail.com"),
                new User("ccc", "다다다", "p3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1, "ccc@gmail.com"),
                new User("ddd", "라라라", "p4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD, "ddd@gmail.com"),
                new User("eee", "마마마", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "eee@gmail.com")
        );
    }

    @Test
    public void bean() {
        assertThat(this.userService, is(notNullValue()));
    }

    @Test
    public void upgradeLevels() throws Exception {
        userDao.deleteAll();

        for (User user: users) {
            userDao.add(user);
        }

        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);
    }

    private void checkLevelUpgraded(User user, boolean updated) {
        User userUpdate = userDao.get(user.getId());
        if (updated) {
            assertThat(userUpdate.getLevel(), is(user.getLevel().nexLevel()));
        } else {
            assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }

    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
        assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
    }

    @Test
    public void upgradeAllOrNothing() throws Exception {
        UserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(this.userDao);
        testUserService.setTransactionManager(transactionManager);

        userDao.deleteAll();

        for (User user: users) {
            userDao.add(user);
        }

        try {
            testUserService.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch(TestUserServiceException e) {
        }

        checkLevelUpgraded(users.get(1), false);
    }

    static class TestUserService extends UserService {
        private String id;

        private TestUserService(String id) {
            this.id = id;
        }

        @Override
        protected void upgradeUser(User user) {
            if (user.getId().equals(this.id)) {
                throw new TestUserServiceException();
            }
            super.upgradeUser(user);
        }

    }

    private static class TestUserServiceException extends RuntimeException {
    }
}
