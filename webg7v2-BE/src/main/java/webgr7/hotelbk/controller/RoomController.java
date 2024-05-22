package webgr7.hotelbk.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webgr7.hotelbk.dto.RoomDTO;
import webgr7.hotelbk.model.Room;
import webgr7.hotelbk.response.RoomResponse;
import webgr7.hotelbk.service.HotelService;
import webgr7.hotelbk.service.RoomService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelService hotelService;

    @Autowired
    RoomService roomService;

    //Retrieve all room by hotel id
    @GetMapping("/all-rooms/{hotelId}")
    public ResponseEntity<?> getAllRoomsByHotelId(@PathVariable Long hotelId){
        try{
            List<Room> rooms = roomService.getAllRoomsByHotelId(hotelId);
            List<RoomResponse> roomResponses = new ArrayList<>();
            for(Room room : rooms){
                roomResponses.add(getRoomResponse(room));
            }
            return ResponseEntity.ok(roomResponses);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error fetching rooms " + e.getMessage());
        }
    }

    @PostMapping("/add-room/{hotelId}")
    public ResponseEntity<?> addRoom(@PathVariable Long hotelId, @ModelAttribute RoomDTO roomDTO){
        try{
            if(hotelService.getHotelById(hotelId) == null) {
                return ResponseEntity.badRequest().body("Hotel not found");
            }
            Room room = roomService.createRoom(hotelId, roomDTO);
            RoomResponse roomResponse = getRoomResponse(room);
            return ResponseEntity.ok(roomResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error adding hotel " + e.getMessage());
        }
    }


    @PutMapping("/update-room/{roomId}")
    public ResponseEntity<?> updateRoom(@PathVariable Long roomId, @ModelAttribute RoomDTO roomDTO) throws Exception{
        try {
            Room room = roomService.updateRoomById(roomId, roomDTO);
            RoomResponse roomResponse = getRoomResponse(room);
            return ResponseEntity.ok(roomResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating room "+ e.getMessage());
        }
    }

    //convert room to roomResponse
    public RoomResponse getRoomResponse(Room room) throws SQLException {
        RoomResponse roomResponse = new RoomResponse();
        byte[] photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
        if(photoBytes != null && photoBytes.length > 0){
            String base64Photo = Base64.encodeBase64String(photoBytes);
            roomResponse = new RoomResponse(room.getId(), photoBytes, room.getName(), room.getQuantity(), room.getType(), room.getPnum(), room.getDes(), room.getPrice(), room.getOffer());
            roomResponse.setPhoto(base64Photo);
        }
        return roomResponse;
    }

    @DeleteMapping("/delete-room/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId){
        try{
            if(roomService.getRoomById(roomId) == null) {
                return ResponseEntity.badRequest().body("Room not found");
            }
            roomService.deleteRoom(roomId);
            return ResponseEntity.ok("Room deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error deleting room " + e.getMessage());
        }
    }
}
