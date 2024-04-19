package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ClientDAO;
import br.com.springboot.model.Client;

@Service
public class ClientBO implements CRUD<Client, Long>{
    
    @Autowired
    private ClientDAO dao;

    @Override
    public Client findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Client> list() {
        return dao.list();
    }

    @Override
    public void insert(Client client) {
        dao.insert(client);
    }

    @Override
    public void update(Client client) {
        dao.update(client);
    }

    @Override
    public void delete(Client client) {
        dao.delete(client);
    }

    public void disable(Client client){
        client.setEnabled(false);
        dao.update(client);
    }

    public void enable(Client client){
        client.setEnabled(true);
        dao.update(client);
    }
}