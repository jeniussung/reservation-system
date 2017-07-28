package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.config.RootApplicationContextConfig;
import kr.or.connect.reservation.domain.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ReviewDaoTest {

    @Autowired
    private ReviewDao reviewDao;

    @Test
    public void shouldInsert() {
        Review review = new Review();
        review.setProductId(1);
        review.setUserId(1);
        review.setScore(BigDecimal.ONE);
        review.setComment("test");
        Integer returnKey = reviewDao.insert(review);
        Assert.assertNotNull(returnKey);
    }
}
