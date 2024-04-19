package br.com.springboot.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import br.com.springboot.model.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SupplierDAO implements CRUD<Supplier, Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Supplier Supplier) {
        entityManager.remove(Supplier);
    }

    @Override
    public Supplier findById(Long id) {
        return entityManager.find(Supplier.class, id);
    }

    @Override
    public void insert(Supplier Supplier) {
        entityManager.persist(Supplier);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Supplier> list() {
        Query query = entityManager.createQuery("SELECT s FROM Supplier s");
        return (List<Supplier>) query.getResultList();
    }

    @Override
    public void update(Supplier Supplier) {
        entityManager.merge(Supplier);
    }
}