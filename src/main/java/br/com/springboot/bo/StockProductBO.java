package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.StockProductDAO;
import br.com.springboot.model.StockProduct;

@Service
public class StockProductBO implements CRUD<StockProduct, Long>{

    @Autowired
    private StockProductDAO dao;

    @Override
    public StockProduct findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<StockProduct> list() {
        return dao.list();
    }

    @Override
    public void insert(StockProduct sp) {
        dao.insert(sp);
    }

    @Override
    public void update(StockProduct sp) {
        dao.update(sp);
    }

    @Override
    public void delete(StockProduct sp) {
        dao.delete(sp);
    }
    
}
