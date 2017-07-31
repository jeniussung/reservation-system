package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.service.FileService;
import kr.or.connect.reservation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FileService fileService;

    @PostMapping
    public Integer createReview(@RequestParam("review") Review review, @RequestParam("files") MultipartFile[] files) {
        Integer reviewId;
        if(files != null) {
            List<Integer> addedFileList = fileService.saveFiles(review.getUserId(), files);
            reviewId = reviewService.addReviewWithFiles(review, addedFileList);
        } else {
            reviewId = reviewService.addReview(review);
        }

        return reviewId;
    }


}
