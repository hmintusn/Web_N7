package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.Hotel;

import java.util.List;
@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocation(String Location);
}
