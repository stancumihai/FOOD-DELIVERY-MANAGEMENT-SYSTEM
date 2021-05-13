package org.stancu.service.user;

import org.stancu.dao.DAO;
import org.stancu.dao.user.ClientDao;
import org.stancu.model.userModel.Client;

import java.util.List;

/***
 * The business logic for the Client Class
 */
public class ClientService {

    public static ClientService clientService = null;
    public static DAO<Client> clientDataAccessService = new ClientDao();

    private ClientService(DAO<Client> clientDataAccessService) {
        ClientService.clientDataAccessService = clientDataAccessService;
    }

    /***
     *
     * @return it returns singleton instance of ClientService
     */
    public static ClientService getInstance() {
        if (clientService == null) {
            clientService = new ClientService(clientDataAccessService);
        }
        return clientService;
    }

    public Client findById(Integer id) {
        return clientDataAccessService.findById(id);
    }

    public Client update(Integer id, Client model) {
        return clientDataAccessService.update(model, id);
    }

    public void insert(Client model) {
        clientDataAccessService.insert(model);
    }

    public Client delete(Integer id) {
        return clientDataAccessService.delete(id);
    }

    public List<Client> selectAll() {
        return clientDataAccessService.selectAll();
    }

    public Integer getLastId() {
        return clientDataAccessService.getLastId();
    }
}
