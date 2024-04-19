package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.StockProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StockProductDAO implements CRUD<StockProduct, Long>{
    
    @PersistenceContext
    private EntityManager en;

    @Override
    public StockProduct findById(Long id) {
        return en.find(StockProduct.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<StockProduct> list() {
        Query query = en.createQuery("SELECT sp FROM StockProduct sp");
        return query.getResultList();
    }

    @Override
    public void insert(StockProduct sp) {
        en.persist(sp);
    }

    @Override
    public void update(StockProduct sp) {
        en.merge(sp);
    }

    @Override
    public void delete(StockProduct sp) {
        en.remove(sp);
    }
}
