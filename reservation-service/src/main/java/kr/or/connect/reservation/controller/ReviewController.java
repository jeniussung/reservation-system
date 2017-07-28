package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Integer createReview(@RequestParam("review") Review review, @RequestParam("files") MultipartFile[] files) {
        if(files != null) {

        }
        return reviewService.createReview(review);
    }


}
