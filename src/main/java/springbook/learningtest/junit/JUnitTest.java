package springbook.learningtest.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.dao.UserDaoJdbc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/junit.xml")
public class JUnitTest {
//    @Autowired
//    ApplicationContext context;
//
//    @Autowired
//    UserDaoJdbc dao1;
//
//    @Autowired
//    UserDaoJdbc dao2;
//
//    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
//    static ApplicationContext contextObject = null;

//    @Test
//    public void test1() {
//        assertThat(testObjects, not(hasItem(this)));
//        testObjects.add(this);
//
//        assertThat(contextObject == null || contextObject == this.context, is(true));
//        contextObject = this.context;
//    }

//    @Test
//    public void test2() {
//        assertThat(testObjects, not(hasItem(this)));
//        testObjects.add(this);
//
//        assertTrue(contextObject == null || contextObject == this.context);
//        contextObject = this.context;
//    }
//
//    @Test
//    public void test3() {
//        assertThat(testObjects, not(hasItem(this)));
//        testObjects.add(this);
//
//        assertThat(contextObject, either(is(nullValue())).or(CoreMatchers.<Object>is(this.context)));
//        contextObject = this.context;
//    }

//    @Test
//    public void singletonBean() {
//        UserDaoJdbc dao3 = context.getBean("userDao", UserDaoJdbc.class);
//
//        assertThat(dao1 == dao2 && dao2 == dao3, is(true));
//    }
}
