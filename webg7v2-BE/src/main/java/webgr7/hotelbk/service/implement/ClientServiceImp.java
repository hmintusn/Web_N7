package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.model.Client;
import webgr7.hotelbk.model.User;
import webgr7.hotelbk.repository.ClientRepo;
import webgr7.hotelbk.repository.UserRepo;
import webgr7.hotelbk.service.ClientService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Override
    public Client retrieveClientById(Long userId) {
        Optional<User>  user = userRepo.findById(userId);
        if(user.isPresent() && user.get() instanceof Client){
            return (Client) user.get();
        }else {
            return null;
        }
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public boolean deleteClient(Long userId) {
        try{
            clientRepo.delete(retrieveClientById(userId));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
