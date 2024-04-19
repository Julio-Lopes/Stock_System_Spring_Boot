package br.com.springboot.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import br.com.springboot.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ClientDAO implements CRUD<Client, Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Client client) {
        entityManager.remove(client);
    }

    @Override
    public Client findById(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public void insert(Client client) {
        entityManager.persist(client);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> list() {
        Query query = entityManager.createQuery("SELECT c FROM Client c");
        return (List<Client>) query.getResultList();
    }

    @Override
    public void update(Client client) {
        entityManager.merge(client);
    }
}