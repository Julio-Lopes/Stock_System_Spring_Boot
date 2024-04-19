package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.InvoiceDAO;
import br.com.springboot.model.Invoice;

@Service
public class InvoiceBO implements CRUD<Invoice, Long>{

    @Autowired
    private InvoiceDAO dao;

    @Override
    public Invoice findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Invoice> list() {
        return dao.list();
    }

    @Override
    public void insert(Invoice invoice) {
        dao.insert(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        dao.update(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        dao.delete(invoice);
    }
    
}