package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ItemOutInvoiceDAO;
import br.com.springboot.model.ItemOutInvoice;

@Service
public class ItemOutInvoiceBO implements CRUD<ItemOutInvoice, Long>{

    @Autowired
    private ItemOutInvoiceDAO dao;

    @Override
    public ItemOutInvoice findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<ItemOutInvoice> list() {
        return dao.list();
    }

    @Override
    public void insert(ItemOutInvoice itemOutInvoice) {
        dao.insert(itemOutInvoice);
    }

    @Override
    public void update(ItemOutInvoice itemOutInvoice) {
        dao.update(itemOutInvoice);
    }

    @Override
    public void delete(ItemOutInvoice itemOutInvoice) {
        dao.delete(itemOutInvoice);
    }
    
    public boolean itemAlreadyAdded(ItemOutInvoice itemOutInvoice){
        Long invoiceId = itemOutInvoice.getOutInvoice().getId();
        List<ItemOutInvoice> items = dao.listItemOutInvoice(invoiceId);

        Long productId = itemOutInvoice.getProduct().getId();

        if(itemOutInvoice.getId() == null) {
            for (ItemOutInvoice item : items) {
                if (item.getProduct().getId() == productId){
                    return true;
                }
            }
            return false;
        }else{
            Long itemOutInvoiceId = itemOutInvoice.getId();
            for (ItemOutInvoice item : items) {
                if (item.getProduct().getId() == productId && itemOutInvoiceId != item.getId()){
                    return true;
                }
            }
            return false;
        }
    }
    
}