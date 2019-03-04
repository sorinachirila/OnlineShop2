package ro.sda.shop.service;

import ro.sda.shop.model.Client;
import ro.sda.shop.service.exceptions.NotFoundException;
import ro.sda.shop.storage.ClientDAO;

import java.util.List;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public Client getClient(Long id) {
        return clientDAO.findById(id);
    }

    public Client save(Client client) {
        Client updatedClient = null;
        if (client.getId() == null) {
            updatedClient = clientDAO.add(client);
        } else {
            clientDAO.update(client);
            updatedClient = client;
        }
        return updatedClient;
    }

    public void deactivateClientAccount(Long id) {
        Client client = getClient(id);
        if (client == null) {
            throw new NotFoundException("Client with id: " + id + " is not found");
        }
        client.setActive(false);
        save(client);
    }
}
