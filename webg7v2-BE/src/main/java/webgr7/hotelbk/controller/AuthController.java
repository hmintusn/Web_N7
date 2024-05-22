package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webgr7.hotelbk.dto.ClientDTO;
import webgr7.hotelbk.dto.UserDTO;
import webgr7.hotelbk.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserDTO userDTO){
        if(authService.signIn(userDTO)){
            return ResponseEntity.ok(userDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong username or password");
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody ClientDTO clientDTO){
        if(authService.clientSignUp(clientDTO)){
            return ResponseEntity.ok("Registration successful!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot register!");
        }

    }
}
