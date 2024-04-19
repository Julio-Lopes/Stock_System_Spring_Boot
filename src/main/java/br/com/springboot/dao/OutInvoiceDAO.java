package br.com.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.springboot.model.OutInvoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OutInvoiceDAO implements CRUD<OutInvoice, Long>{
    @Autowired
    private EntityManager em;

    @Override
    public OutInvoice findById(Long id) {
        return em.find(OutInvoice.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OutInvoice> list() {
        Query query = em.createQuery("SELECT oi FROM OutInvoice oi");
        return query.getResultList();
    }

    @Override
    public void insert(OutInvoice oi) {
        em.persist(oi);
    }

    @Override
    public void update(OutInvoice oi) {
        em.merge(oi);
    }

    @Override
    public void delete(OutInvoice oi) {
        em.remove(oi);
    }
}
