package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.ReviewDao;
import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.ReviewInfoDto;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
import kr.or.connect.reservation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public Integer createReview(Review review) {
        return reviewDao.insert(review);
    }

    @Override
    public List<UserCommentDto> getUserComment(Integer id) {
        return reviewDao.selectComment(id);
    }

    @Override
    public List<CommentImage> getUserCommentImage(Integer id) {
        return reviewDao.selectCommentImg(id);
    }

    @Override
    public ReviewInfoDto getReviewCountInfo(Integer id) {
        return reviewDao.selectCountInfo(id);
    }
}
