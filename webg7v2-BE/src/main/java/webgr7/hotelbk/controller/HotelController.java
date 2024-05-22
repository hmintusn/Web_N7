package webgr7.hotelbk.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import webgr7.hotelbk.dto.HotelDTO;
import webgr7.hotelbk.model.Hotel;
import webgr7.hotelbk.model.Picture;
import webgr7.hotelbk.model.Room;
import webgr7.hotelbk.response.HotelResponse;
import webgr7.hotelbk.response.PictureResponse;
import webgr7.hotelbk.response.RoomResponse;
import webgr7.hotelbk.service.HotelService;
import webgr7.hotelbk.service.RoomService;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

//    @GetMapping("/search")
//    private ResponseEntity<?> searchHotel(
//            @RequestParam("lcation") String location,
//            @RequestParam("checkIn") @DateTimeFormat(pattern = "dd-MM-yyyy") Date checkIn,
//            @RequestParam("checkOut") @DateTimeFormat(pattern = "dd-MM-yyyy") Date checkOut,
//            @RequestParam("pnum") int pnum){
//        return ResponseEntity.ok(hotelService.findHotelBy(location, checkIn, checkOut, pnum));
//    }
//
//    @GetMapping("/detail/{hotel_id}")
//    private ResponseEntity<?> getHotel(@PathVariable Long hotel_id){
//        return ResponseEntity.ok(hotelService.retriveHotelById(hotel_id));
//    }
//}
    @Autowired
    HotelService hotelService;

    @Autowired
    RoomService roomService;

    @GetMapping("/all-hotels")
    public ResponseEntity<?> getAllHotels(){
        try{
            List<Hotel> hotels = hotelService.getAllHotels();
            List<HotelResponse> hotelResponses = new ArrayList<>();
            for(Hotel hotel : hotels){
                HotelResponse hotelResponse = getHotelResponse(hotel);
                hotelResponses.add(hotelResponse);
            }
            return ResponseEntity.ok(hotelResponses);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error fetching hotels " + e.getMessage());
        }
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<?> getAvailableRooms(
        @RequestParam("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkIn,
        @RequestParam("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOut,
        @RequestParam("pnum") int pnum) throws SQLException {
        List<Room> availableRooms = roomService.getAvailableRooms(checkIn, checkOut, pnum);
        Map<Hotel, List<Room>> hotelRoomsMap = new HashMap<>();
        for (Room room : availableRooms) {
            hotelRoomsMap
                    .computeIfAbsent(room.getHotel(), k -> new ArrayList<>())
                    .add(room);
        }

        List<HotelResponse> hotelResponses = new ArrayList<>();
        for (Map.Entry<Hotel, List<Room>> entry : hotelRoomsMap.entrySet()) {
            Hotel hotel = entry.getKey();
            HotelResponse hotelResponse = getHotelResponse(hotel);
            hotelResponses.add(hotelResponse);
        }
        if(hotelResponses.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(hotelResponses);
        }
    }

    //convert Hotel to HotelResponse
    public HotelResponse getHotelResponse(Hotel hotel) throws SQLException {
        HotelResponse hotelResponse = new HotelResponse(hotel.getId(), hotel.getName(), hotel.getStar(), hotel.getLocation(), hotel.getType(), hotel.getStatus(), hotel.getTel(), hotel.getContact(), hotel.getDes());
        for(Room room : hotel.getRooms()){
            RoomResponse roomResponse = getRoomResponse(room);
            hotelResponse.getRooms().add(roomResponse);
        }
        List<PictureResponse> pictureResponses = new ArrayList<>();
        for(Picture picture : hotel.getPictures()){
            byte[] photoBytes = picture.getData().getBytes(1, (int) picture.getData().length());
            if(photoBytes != null && photoBytes.length > 0){
                String base64Photo = Base64.encodeBase64String(photoBytes);
                pictureResponses.add(new PictureResponse(picture.getId(), photoBytes));
            }
        }
        hotelResponse.setPictures(pictureResponses);
        return hotelResponse;
    }

    //convert Room to RoomResponse
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

    @PostMapping("/addPicture/{hotelId}")
    public ResponseEntity<?> updatePictureHotel(@PathVariable Long hotelId, @RequestParam("file") MultipartFile file){
        try{
            if(hotelService.getHotelById(hotelId) == null) {
                return ResponseEntity.badRequest().body("Hotel not found");
            }
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            hotelService.updatePictureHotel(hotelId, photoBlob);
            return ResponseEntity.ok("Hotel photo updated successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating hotel photo "+ e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHotel(@RequestBody HotelDTO hotelDTO){
        try{
            return ResponseEntity.ok(hotelService.createHotel(hotelDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error adding hotel " + e.getMessage());
        }
    }

    @PutMapping("/update/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO){
        try {
            return ResponseEntity.ok(hotelService.updateHotelById(hotelId, hotelDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating hotel "+ e.getMessage());
        }
    }

    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId){
        try{
            if(hotelService.getHotelById(hotelId) == null) {
                return ResponseEntity.badRequest().body("Hotel not found");
            }
            hotelService.deleteHotel(hotelId);
            return ResponseEntity.ok("Hotel deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error deleting hotel " + e.getMessage());
        }
    }

}
