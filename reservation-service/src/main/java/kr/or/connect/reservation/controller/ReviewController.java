package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.ReviewService;
import kr.or.connect.reservation.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

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

    @GetMapping("/{id}")
    public List<UserCommentDto> getUserComments(@PathVariable Integer id)
    {
        return reviewService.getUserComment(id);
    }

    @GetMapping("/image/{id}")
    public List<CommentImage> getCommentImage(@PathVariable Integer id)
    {
        return reviewService.getUserCommentImage(id);
    }

}
