package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.BookingDTO;
import webgr7.hotelbk.model.BookedRoom;
import webgr7.hotelbk.model.Room;
import webgr7.hotelbk.model.Slip;
import webgr7.hotelbk.repository.ClientRepo;
import webgr7.hotelbk.repository.RoomRepo;
import webgr7.hotelbk.repository.SlipRepo;
import webgr7.hotelbk.repository.VoucherRepo;
import webgr7.hotelbk.service.SlipService;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SlipServiceImp implements SlipService {
    @Autowired
    private SlipRepo slipRepo;
    @Autowired
    private VoucherRepo voucherRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public Slip addBookingSlip(BookingDTO bookingDTO) {
        Slip slip = new Slip();
        slip.setBills(new ArrayList<>());
        slip.setTime(bookingDTO.getTime());
        slip.setVouchers(voucherRepo.findAllById(bookingDTO.getVoucherIds()));

        List<Room> roomList = roomRepo.findAllById(bookingDTO.getRoomIds());
        List<BookedRoom> brList = new ArrayList<>();
        for(int i = 0; i < bookingDTO.getQuantity().size(); i++){
            BookedRoom br = new BookedRoom();
            br.setRoom(roomList.get(i));
            br.setCheckIn(bookingDTO.getCheckIn());
            br.setCheckOut(bookingDTO.getCheckOut());
            br.setQuantity(bookingDTO.getQuantity().get(i));

            Instant ist = bookingDTO.getCheckIn().toInstant();
            Instant ied = bookingDTO.getCheckOut().toInstant();

            Duration duration = Duration.between(ist,ied);

            Float price = duration.toDays() * br.getRoom().getPrice();

            br.setPrice(price);
            br.setSlip(slip);

            brList.add(br);
        }

        slip.setBookedRooms(brList);
        slip.setNote("");
        slip.setClient(clientRepo.findById(bookingDTO.getClientId()).get());

        return slipRepo.save(slip);
    }

    @Override
    public List<Slip> getSlipByClient(Long clientId) {
        return slipRepo.findByClientId(clientId);
    }

    @Override
    public boolean deleteSlip(Long slipId) {
        try{
            slipRepo.deleteById(slipId);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
