package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.ReviewDTO;
import webgr7.hotelbk.model.BookedRoom;
import webgr7.hotelbk.model.Review;
import webgr7.hotelbk.model.Slip;
import webgr7.hotelbk.repository.*;
import webgr7.hotelbk.service.ReviewService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public List<Review> getReviewByHotel(Long hotelId) {
        return reviewRepo.findByHotelId(hotelId);
    }

    @Override
    public Review addReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        if(billRepo.findById(reviewDTO.getBill_id()).isPresent() && clientRepo.findById(reviewDTO.getClient_id()).isPresent()) {
            review.setBill(billRepo.findById(reviewDTO.getBill_id()).get());
            review.setComment(reviewDTO.getComment());
            review.setRate(reviewDTO.getRate());
            review.setClient(clientRepo.findById(reviewDTO.getClient_id()).get());

            review.setHotel( review.getBill()
                    .getSlip()
                    .getBookedRooms().get(0)
                    .getRoom().getHotel());
            return reviewRepo.save(review);
        }
        return review;
    }
}
