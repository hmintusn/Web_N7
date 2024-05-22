package webgr7.hotelbk.service;

import webgr7.hotelbk.dto.ClientDTO;
import webgr7.hotelbk.dto.UserDTO;
import webgr7.hotelbk.model.Client;
import webgr7.hotelbk.model.User;

public interface AuthService {
    public boolean signIn(UserDTO userDTO);

    public boolean clientSignUp(ClientDTO userDTO);
}
