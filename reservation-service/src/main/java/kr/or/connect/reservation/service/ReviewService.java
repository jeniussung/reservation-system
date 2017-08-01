package kr.or.connect.reservation.service;

import kr.or.connect.reservation.domain.Review;

import java.util.List;

public interface ReviewService {
    Integer addReview(Review review);
    Integer addReviewWithFiles(Review review, List<Integer> fileIdList);
}
