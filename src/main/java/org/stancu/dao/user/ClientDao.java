package org.stancu.dao.user;

import org.stancu.dao.DAO;
import org.stancu.model.userModel.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDao implements DAO<Client> {

    private ArrayList<Client> clients = new ArrayList<>();

    @Override
    public Client findById(Integer id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public List<Client> selectAll() {
        return clients;
    }

    @Override
    public Client delete(Integer id) {
        Client client = findById(id);
        if (client != null) {
            clients.removeIf(client1 -> client1.equals(client));
            return client;
        } else return null;
    }

    @Override
    public Client update(Client model, Integer id) {
        Client Client = findById(id);
        if (Client != null) {
            int cnt = 0;
            for (Client myClient : clients) {
                cnt++;
                if (myClient.equals(Client)) {
                    clients.remove(myClient);
                    clients.add(cnt, model);
                    return model;
                }
            }
        }
        return null;
    }

    @Override
    public Client insert(Client model) {
        if (clients.size() == 0) {
            model.setId(1);
        } else {
            model.setId(getLastId() + 1);
        }
        clients.add(model);
        return model;
    }

    @Override
    public Integer getLastId() {
        return clients.get(clients.size() - 1).getId();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
