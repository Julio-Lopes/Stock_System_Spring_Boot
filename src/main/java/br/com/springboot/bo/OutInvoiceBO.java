package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.OutInvoiceDAO;
import br.com.springboot.model.OutInvoice;

@Service
public class OutInvoiceBO implements CRUD<OutInvoice, Long>{
    
    @Autowired
    private OutInvoiceDAO dao;

    @Override
    public OutInvoice findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<OutInvoice> list() {
        return dao.list();
    }

    @Override
    public void insert(OutInvoice oi) {
        dao.insert(oi);
    }

    @Override
    public void update(OutInvoice oi) {
        dao.update(oi);
    }

    @Override
    public void delete(OutInvoice oi) {
        dao.delete(oi);
    }
}