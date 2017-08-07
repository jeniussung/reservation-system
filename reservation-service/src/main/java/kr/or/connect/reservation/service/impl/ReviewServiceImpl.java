package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReviewDao;
import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.ReviewInfoDto;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
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
    public List<UserCommentDto> getUserComment(Integer id, Integer start) {
        return reviewDao.selectComment(id,start);
    }

    @Override
    public List<CommentImage> getUserCommentImage(Integer id) {
        return reviewDao.selectCommentImg(id);
    }

    @Override
    public ReviewInfoDto getReviewCountInfo(Integer id) {
        return reviewDao.selectCountInfo(id);
    }

    @Override
    public Integer addReviewWithFiles(Review review, List<Integer> fileIdList) {
    	Date currentDate = new Date();
    	review.setCreateDate(currentDate);
    	review.setModifyDate(currentDate);
        return reviewDao.insertWithFiles(review, fileIdList);
    }
}
