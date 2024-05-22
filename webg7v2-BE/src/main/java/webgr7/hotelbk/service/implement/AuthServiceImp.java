package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.ClientDTO;
import webgr7.hotelbk.dto.UserDTO;
import webgr7.hotelbk.model.Client;
import webgr7.hotelbk.model.User;
import webgr7.hotelbk.repository.ClientRepo;
import webgr7.hotelbk.repository.UserRepo;
import webgr7.hotelbk.security.JWTUtils;
import webgr7.hotelbk.service.AuthService;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public boolean signIn(UserDTO userDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(),
                    userDTO.getPassword()));
            var user = userRepo.findByUsername(userDTO.getUsername()).orElseThrow();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            userDTO.setToken(jwt);
            userDTO.setRefreshToken(refreshToken);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean clientSignUp(ClientDTO clientDTO) {
        try{
            Client c = new Client();
            c.setDob(clientDTO.getDob());
            c.setUsername(clientDTO.getUsername());
            c.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
            c.setEmail(clientDTO.getEmail());
            c.setTel(clientDTO.getTel());
            c.setRole(clientDTO.getRole());
            c.setIdCard(clientDTO.getIdCard());
            c.setGender(clientDTO.getGender());
            c.setName(clientDTO.getName());

            c.setBills(new ArrayList<>());
            c.setSlips(new ArrayList<>());
            c.setVouchers(new ArrayList<>());
            clientRepo.save(c);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
