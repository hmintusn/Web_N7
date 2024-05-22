package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webgr7.hotelbk.dto.BookingDTO;
import webgr7.hotelbk.service.SlipService;

@RestController
@RequestMapping("/slip")
public class SlipController {
    @Autowired
    private SlipService slipService;

    @PostMapping("/booking")
    public ResponseEntity<?> newBooking(@RequestBody BookingDTO bookingDTO){
        return ResponseEntity.ok(slipService.addBookingSlip(bookingDTO));
    }

    @GetMapping("/client/{client_id}")
    public ResponseEntity<?> getClientSlip(@PathVariable Long client_id){
        return ResponseEntity.ok(slipService.getSlipByClient(client_id));
    }


    @DeleteMapping("/delete/{slip_id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long slip_id){
        return ResponseEntity.ok(slipService.deleteSlip(slip_id));
    }
}
