package kr.or.connect.reservation.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.service.FileService;
import kr.or.connect.reservation.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FileService fileService;

    @PostMapping
    public Integer createReview(@RequestParam("review") String reviewString, @RequestParam("files") MultipartFile[] files) {
    	System.out.println("review : "+ reviewString);
    	Review review = null;
		try {
			review = new ObjectMapper().readValue(reviewString, Review.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
