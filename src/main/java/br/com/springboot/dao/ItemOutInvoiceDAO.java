package br.com.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.springboot.model.ItemOutInvoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ItemOutInvoiceDAO implements CRUD<ItemOutInvoice, Long>{

    @Autowired
    private EntityManager em;

    @Override
    public ItemOutInvoice findById(Long id) {
        return em.find(ItemOutInvoice.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ItemOutInvoice> list() {
        Query query = em.createQuery("SELECT io FROM ItemOutInvoice io");
        return query.getResultList();
    }

    @Override
    public void insert(ItemOutInvoice itemOutInvoice) {
        em.persist(itemOutInvoice);
    }

    @Override
    public void update(ItemOutInvoice itemOutInvoice) {
        em.merge(itemOutInvoice);
    }

    @Override
    public void delete(ItemOutInvoice itemOutInvoice) {
        em.remove(itemOutInvoice);
    }
    
    @SuppressWarnings("unchecked")
    public List<ItemOutInvoice> listItemOutInvoice(Long invoiceOutId){
        Query query = em.createQuery("FROM ItemOutInvoice io WHERE io.outInvoice.id = :outInvoiceId")
                        .setParameter("outInvoiceId", invoiceOutId);
        return query.getResultList();
    }
    
}