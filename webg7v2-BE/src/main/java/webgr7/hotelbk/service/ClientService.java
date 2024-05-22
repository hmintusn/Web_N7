package webgr7.hotelbk.service;

import webgr7.hotelbk.model.Client;

public interface ClientService {
    public Client retrieveClientById(Long userId);

    public Client updateClient(Client client);

    public boolean deleteClient(Long userId);
}
