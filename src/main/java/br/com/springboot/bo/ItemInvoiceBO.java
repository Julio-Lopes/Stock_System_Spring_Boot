package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ItemInvoiceDAO;
import br.com.springboot.model.ItemInvoice;

@Service
public class ItemInvoiceBO implements CRUD<ItemInvoice, Long>{

    @Autowired
    private ItemInvoiceDAO dao;

    @Override
    public ItemInvoice findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<ItemInvoice> list() {
        return dao.list();
    }

    @Override
    public void insert(ItemInvoice itemInvoice) {
        dao.insert(itemInvoice);
    }

    @Override
    public void update(ItemInvoice itemInvoice) {
        dao.update(itemInvoice);
    }

    @Override
    public void delete(ItemInvoice itemInvoice) {
        dao.delete(itemInvoice);
    }
    
    public boolean itemAlreadyAdded(ItemInvoice itemInvoice){
        Long invoiceId = itemInvoice.getInvoice().getId();
        List<ItemInvoice> items = dao.listItemInvoice(invoiceId);

        Long productId = itemInvoice.getProduct().getId();

        if(itemInvoice.getId() == null) {
            for (ItemInvoice item : items) {
                if (item.getProduct().getId() == productId){
                    return true;
                }
            }
            return false;
        }else{
            Long itemInvoiceId = itemInvoice.getId();
            for (ItemInvoice item : items) {
                if (item.getProduct().getId() == productId && itemInvoiceId != item.getId()){
                    return true;
                }
            }
            return false;
        }
    }
}