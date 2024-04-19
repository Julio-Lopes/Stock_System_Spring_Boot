package br.com.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.bo.ProductBO;
import br.com.springboot.bo.StockProductBO;
import br.com.springboot.model.Product;
import br.com.springboot.model.StockProduct;

@RestController
public class StockProductRestController {
    
    @Autowired
    private StockProductBO bo;

    @Autowired
    private ProductBO pBO;

    @RequestMapping(value = "/api/stock" , method = RequestMethod.GET)
    public List<StockProduct> listAll(){
        return bo.list();
    }

    @RequestMapping(value = "/api/stock/{id}", method = RequestMethod.GET)
    public StockProduct findById(@PathVariable Long id){
        return bo.findById(id);
    }

    @RequestMapping(value = "/api/stock", method = RequestMethod.POST)
    public StockProduct insert(@RequestBody StockProduct stockProduct){
        Product product = pBO.findById(stockProduct.getProduct().getId());
		stockProduct.setProduct(product);
		bo.insert(stockProduct);
		return stockProduct;
    }

    @RequestMapping(value = "/api/stock/{id}", method = RequestMethod.PUT)
    public StockProduct update(@PathVariable Long id, @RequestBody StockProduct stockProduct){
        stockProduct.setId(id);
        stockProduct.setProduct(pBO.findById(stockProduct.getProduct().getId()));
        bo.update(stockProduct);
        return stockProduct;
    }

    @RequestMapping(value = "/api/stock/{id}", method = RequestMethod.DELETE)
    public StockProduct remove(@PathVariable Long id){
        StockProduct stockProduct = bo.findById(id);
        bo.delete(stockProduct);
        return stockProduct;
    }
}