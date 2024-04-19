package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Invoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class InvoiceDAO implements CRUD<Invoice, Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Invoice findById(Long id) {
        return entityManager.find(Invoice.class,id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Invoice> list() {
        Query query = entityManager.createQuery("SELECT in FROM Invoice in");
        return query.getResultList();
    }

    @Override
    public void insert(Invoice invoice) {
        entityManager.persist(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        entityManager.merge(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        entityManager.remove(invoice);
    }
}