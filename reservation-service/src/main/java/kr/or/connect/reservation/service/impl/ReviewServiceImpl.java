package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.ReviewDao;
import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public Integer createReview(Review review) {
        return reviewDao.insert(review);
    }
}
