package kr.or.connect.reservation.service;

import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.UserCommentDto;

import java.util.Collection;
import java.util.List;

public interface ReviewService {
    Integer createReview(Review review);
    List<UserCommentDto> getUserComment(Integer id);
    List<CommentImage> getUserCommentImage(Integer id);
}
