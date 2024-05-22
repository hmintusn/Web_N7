package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.location = :location")
    List<Room> findRoomsByHotelLocation(@Param("location") String location);

    Optional<Room>findById(Long id);
    List<Room> findByHotelId(Long hotelId);

    @Query("SELECT r FROM Room r " +
            "WHERE r.pnum >= :pnum AND r.id NOT IN (" +
            "SELECT br.room.id FROM BookedRoom br " +
            "WHERE (br.checkIn <= :checkOut AND br.checkOut >= :checkIn))")
    List<Room> findAvailableRoomsByDatesAndPnum(@Param("checkIn") Date checkIn,
                                                @Param("checkOut") Date checkOut,
                                                @Param("pnum") int pnum);
}
