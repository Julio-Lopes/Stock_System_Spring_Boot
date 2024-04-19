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

import br.com.springboot.bo.InvoiceBO;
import br.com.springboot.bo.ProductBO;
import br.com.springboot.bo.SupplierBO;
import br.com.springboot.model.Invoice;
import br.com.springboot.model.ItemInvoice;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    
    @Autowired
    private InvoiceBO bo;

    @Autowired
    private SupplierBO supplierBO;

    @Autowired
    private ProductBO productBO;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView create(ModelMap model){
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("suppliers", supplierBO.list());

        return new ModelAndView("/invoice/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Invoice invoice, BindingResult result, RedirectAttributes attr){
        
        if(invoice.getSupplier().getId() == null) {
            result.rejectValue("supplier", "field.required");
        }

        if(result.hasErrors()) return "/invoice/form";

        if(invoice.getId() == null){
            bo.insert(invoice);
            attr.addFlashAttribute("feedback", "A nota de entrada foi cadastrado com sucesso");
        }else{
            bo.update(invoice);
            attr.addFlashAttribute("feedback", "Os dados da nota de entrada foram atualizados com sucesso");
        }

        return "redirect:/invoice";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(ModelMap model){
        model.addAttribute("notes", bo.list());
        return new ModelAndView("/invoice/list", model);
    }

    @RequestMapping(value = "/{id}/item", method = RequestMethod.GET)
    public ModelAndView product(@PathVariable("id") Long id, ModelMap model){
        ItemInvoice item = new ItemInvoice();
        Invoice invoice = bo.findById(id);
        item.setInvoice(invoice);
        model.addAttribute("itemInvoice", item);
        model.addAttribute("products", productBO.list());

        return new ModelAndView("/itemInvoice/form", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("itemInvoice", new ItemInvoice());
        model.addAttribute("suppliers", supplierBO.list());
        model.addAttribute("invoice", bo.findById(id));

        return new ModelAndView("/invoice/form", model);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id, RedirectAttributes attr){
        Invoice invoice = bo.findById(id);
        bo.delete(invoice);
        attr.addFlashAttribute("feedback", "Nota de entrada removida com sucesso");

        return "redirect:/invoice";
    }
}