package webgr7.hotelbk.service;
import webgr7.hotelbk.dto.RoomDTO;
import webgr7.hotelbk.model.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface RoomService {

    //retrieve all rooms by hotel id
    public List<Room> getAllRoomsByHotelId(Long hotelId);

    //get room available by checkin, checkout and pnum
    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, int pnum) throws SQLException;

    public Room createRoom(Long hotelId, RoomDTO roomDTO) throws SQLException, IOException;

    public Room getRoomById(Long roomId);

    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException ;

    public Room updateRoomById(Long roomId, RoomDTO roomDTO) throws SQLException, IOException;

    public void deleteRoom(Long roomId);
}
