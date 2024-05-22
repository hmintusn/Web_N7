package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webgr7.hotelbk.dto.BillDTO;
import webgr7.hotelbk.service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @PostMapping("/paying")
    public ResponseEntity<?> paying(@RequestBody BillDTO billDTO){
        return ResponseEntity.ok(billService.addBill(billDTO));
    }


    @GetMapping("/history/{client_id}")
    public ResponseEntity<?> getClientBill(@PathVariable Long client_id){
        return ResponseEntity.ok(billService.getBillByClient(client_id));
    }

}
