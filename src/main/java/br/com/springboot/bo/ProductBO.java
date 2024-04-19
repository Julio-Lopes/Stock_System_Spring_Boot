package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ProductDAO;
import br.com.springboot.model.Product;

@Service
public class ProductBO implements CRUD<Product, Long> {

    @Autowired
    private ProductDAO dao;

    @Override
    public Product findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Product> list() {
        return dao.list();
    }

    @Override
    public void insert(Product product) {
        dao.insert(product);
    }

    @Override
    public void update(Product product) {
        dao.update(product);
    }

    @Override
    public void delete(Product product) {
        dao.delete(product);
    }

    public void enable(Product product){
        product.setEnabled(true);
        dao.update(product);
    }

    public void disable(Product product){
        product.setEnabled(false);
        dao.update(product);
    }
}