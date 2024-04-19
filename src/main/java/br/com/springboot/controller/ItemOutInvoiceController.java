package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.ItemOutInvoiceBO;
import br.com.springboot.bo.OutInvoiceBO;
import br.com.springboot.bo.ProductBO;
import br.com.springboot.model.ItemOutInvoice;
import br.com.springboot.model.OutInvoice;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/itemOutInvoice")
public class ItemOutInvoiceController {
    
    @Autowired
    private ProductBO pBO;

    @Autowired
    private OutInvoiceBO iBO;

    @Autowired
    private ItemOutInvoiceBO iiBO;
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute ItemOutInvoice itemInvoice, 
            BindingResult result, 
            RedirectAttributes attr, 
            ModelMap model){
                Long productId = itemInvoice.getProduct().getId();
                
                if(productId == null) result.rejectValue("product", "field.required");

                if(iiBO.itemAlreadyAdded(itemInvoice)){
                    result.rejectValue("product", "duplicate");
                }

                if(result.hasErrors()){
                    model.addAttribute("products", pBO.list());
                    return "/itemOutInvoice/form";
                }

                OutInvoice invoice = iBO.findById(itemInvoice.getOutInvoice().getId());
                itemInvoice.setOutInvoice(invoice);

                if(itemInvoice.getId() == null){
                    iiBO.insert(itemInvoice);
                    attr.addFlashAttribute("feedback", "Produto adicionado com sucesso");
                }else{
                    iiBO.update(itemInvoice);
                    attr.addFlashAttribute("feedback", "Produto atualizado com sucesso");
                }

                Long invoiceId = itemInvoice.getOutInvoice().getId();
                return "redirect:/outInvoice/edit/" + invoiceId;
            }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("itemOutInvoice", iiBO.findById(id));
        model.addAttribute("products", pBO.list());
        
        return new ModelAndView("/itemOutInvoice/form", model);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id, RedirectAttributes attr){
        Long noteId = 0L;
        ItemOutInvoice itemInvoice = iiBO.findById(id);
        noteId = itemInvoice.getOutInvoice().getId();

        iiBO.delete(itemInvoice);
        attr.addFlashAttribute("feedback", "Produto removido com sucesso");
        
        return "redirect:/outInvoice/edit/" + noteId;
    }
}