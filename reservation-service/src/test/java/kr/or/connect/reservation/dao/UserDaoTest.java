package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.config.RootApplicationContextConfig;
import kr.or.connect.reservation.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldSelectUserById() {
        User user = userDao.selectUser("64310530");
        Assert.assertEquals("오석호", user.getUsername());
    }

    @Test
    public void shouldInsertUser() {
        User user = new User();
        user.setAdmin_flag(1);
        Assert.assertNotNull(userDao.insert(user));
    }


}
