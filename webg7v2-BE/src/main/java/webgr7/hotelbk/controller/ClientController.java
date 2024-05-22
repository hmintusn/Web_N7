package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webgr7.hotelbk.model.Client;
import webgr7.hotelbk.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getClientById(@PathVariable Long user_id){
        return ResponseEntity.ok(clientService.retrieveClientById(user_id));
    }

    @PostMapping("/update/")
    public ResponseEntity<?> editClient(@RequestBody Client client){
        return ResponseEntity.ok(clientService.updateClient(client));
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<?> removeClient(@PathVariable Long user_id){
        if(clientService.deleteClient(user_id)){
            return ResponseEntity.ok("Delete success");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Unable to delete this account.");
        }
    }
}
