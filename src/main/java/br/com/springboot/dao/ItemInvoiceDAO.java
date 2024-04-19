package br.com.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.springboot.model.ItemInvoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ItemInvoiceDAO implements CRUD<ItemInvoice, Long>{

    @Autowired
    private EntityManager em;

    @Override
    public ItemInvoice findById(Long id) {
        return em.find(ItemInvoice.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ItemInvoice> list() {
        Query query = em.createQuery("SELECT i FROM ItemInvoice i");
        return query.getResultList();
    }

    @Override
    public void insert(ItemInvoice itemInvoice) {
        em.persist(itemInvoice);
    }

    @Override
    public void update(ItemInvoice itemInvoice) {
        em.merge(itemInvoice);
    }

    @Override
    public void delete(ItemInvoice itemInvoice) {
        em.remove(itemInvoice);
    }
    
    @SuppressWarnings("unchecked")
    public List<ItemInvoice> listItemInvoice(Long invoiceId){
        Query query = em.createQuery("FROM ItemInvoice i WHERE i.invoice.id = :invoiceId")
                        .setParameter("invoiceId", invoiceId);
        return query.getResultList();
    }
}