package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.BookedRoom;

import java.util.Date;
import java.util.List;
@Repository
public interface BookedRoomRepo  extends JpaRepository<BookedRoom, Long> {
    @Query("SELECT br FROM BookedRoom br WHERE br.checkIn <= :endDate AND br.checkOut >= :startDate")
    List<BookedRoom> findBookedRoomsInDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
