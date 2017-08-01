package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReviewDao;
import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public Integer addReview(Review review) {
    	Date currentDate = new Date();
    	review.setCreateDate(currentDate);
    	review.setModifyDate(currentDate);
        return reviewDao.insert(review);
    }

    @Override
    public Integer addReviewWithFiles(Review review, List<Integer> fileIdList) {
        return reviewDao.insertWithFiles(review, fileIdList);
    }
}
