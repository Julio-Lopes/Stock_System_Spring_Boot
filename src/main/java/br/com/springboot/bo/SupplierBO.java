package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.SupplierDAO;
import br.com.springboot.model.Supplier;

@Service
public class SupplierBO implements CRUD<Supplier, Long>{
    
    @Autowired
    private SupplierDAO dao;

    @Override
    public Supplier findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Supplier> list() {
        return dao.list();
    }

    @Override
    public void insert(Supplier Supplier) {
        dao.insert(Supplier);
    }

    @Override
    public void update(Supplier Supplier) {
        dao.update(Supplier);
    }

    @Override
    public void delete(Supplier Supplier) {
        dao.delete(Supplier);
    }

    public void disable(Supplier Supplier){
        Supplier.setEnabled(false);
        dao.update(Supplier);
    }

    public void enable(Supplier Supplier){
        Supplier.setEnabled(true);
        dao.update(Supplier);
    }
}