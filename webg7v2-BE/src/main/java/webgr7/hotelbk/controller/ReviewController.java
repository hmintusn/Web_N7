package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webgr7.hotelbk.dto.ReviewDTO;
import webgr7.hotelbk.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/hotel/{hotel_id}")
    public ResponseEntity<?> getHotelReview(@PathVariable Long hotel_id){
        return ResponseEntity.ok(reviewService.getReviewByHotel(hotel_id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO){
        return ResponseEntity.ok(reviewService.addReview(reviewDTO));
    }
}
