package kr.or.connect.reservation.service;

import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.ReviewInfoDto;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
import java.util.List;

public interface ReviewService {
    List<UserCommentDto> getUserComment(Integer id, Integer start);
    List<CommentImage> getUserCommentImage(Integer id);
    ReviewInfoDto getReviewCountInfo(Integer id);
    Integer addReview(Review review);
    Integer addReviewWithFiles(Review review, List<Integer> fileIdList);
}
