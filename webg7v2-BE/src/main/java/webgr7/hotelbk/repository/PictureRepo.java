package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.Picture;

import java.util.List;
@Repository
public interface PictureRepo  extends JpaRepository<Picture, Long> {
    List<Picture> findByHotelId(Long hotelId);
}
