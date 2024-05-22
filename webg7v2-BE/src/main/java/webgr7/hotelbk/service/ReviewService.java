package webgr7.hotelbk.service;

import webgr7.hotelbk.dto.ReviewDTO;
import webgr7.hotelbk.model.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> getReviewByHotel(Long hotelId);

    public Review addReview(ReviewDTO reviewDTO);
}
