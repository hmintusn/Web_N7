package webgr7.hotelbk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.RoomDTO;
import webgr7.hotelbk.model.Hotel;
import webgr7.hotelbk.model.Room;
import webgr7.hotelbk.repository.HotelRepo;
import webgr7.hotelbk.repository.RoomRepo;
import webgr7.hotelbk.service.RoomService;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    RoomRepo roomRepo;

    @Autowired
    HotelRepo hotelRepo;

    //retrieve all rooms by hotel id
    @Override
    public List<Room> getAllRoomsByHotelId(Long hotelId){
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if(hotel != null){
            return hotel.getRooms();
        }else{
            return new ArrayList<>();
        }
    }

    //get room available by checkin, checkout and pnum
    @Override
    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, int pnum) throws SQLException {
        return roomRepo.findAvailableRoomsByDatesAndPnum(checkInDate, checkOutDate, pnum);
    }
    @Override
    public Room createRoom(Long hotelId, RoomDTO roomDTO) throws SQLException, IOException {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if(hotel != null){
            List<Room> rooms = hotel.getRooms();
            Room room = new Room();
            room.setName(roomDTO.getName());
            room.setQuantity(roomDTO.getQuantity());
            room.setType(roomDTO.getType());
            room.setPnum(roomDTO.getPnum());
            room.setDes(roomDTO.getDes());
            room.setPrice(roomDTO.getPrice());
            room.setOffer(roomDTO.getOffer());
            room.setHotel(hotel);
            if(roomDTO.getFile() != null){
                byte[] photoBytes = roomDTO.getFile().getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                room.setPhoto(photoBlob);
            }
            rooms.add(room);
            hotel.setRooms(rooms);
            return roomRepo.save(room);
        }
        else{
            throw new SQLException("Hotel not found with id: " + hotelId);
        }
    }
    @Override
    public Room getRoomById(Long roomId){
        return roomRepo.findById(roomId).orElse(null);
    }
    @Override
    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
        Room room = roomRepo.findById(roomId).orElse(null);
        if(room != null){
            Blob photoBlob = room.getPhoto();
            if(photoBlob != null){
                return photoBlob.getBytes(1, (int) photoBlob.length());
            }
        }
        return null;
    }
    @Override
    public Room updateRoomById(Long roomId, RoomDTO roomDTO) throws SQLException, IOException{
        Room room = roomRepo.findById(roomId).orElse(null);
        if(room != null){
            room.setName(roomDTO.getName());
            room.setQuantity(roomDTO.getQuantity());
            room.setType(roomDTO.getType());
            room.setPnum(roomDTO.getPnum());
            room.setDes(roomDTO.getDes());
            room.setPrice(roomDTO.getPrice());
            room.setOffer(roomDTO.getOffer());
            if(roomDTO.getFile() != null){
                byte[] photoBytes = roomDTO.getFile().getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                room.setPhoto(photoBlob);
            }
            return roomRepo.save(room);
        }else{
            return null;
        }
    }
    @Override
    public void deleteRoom(Long roomId){
        roomRepo.deleteById(roomId);
    }
}