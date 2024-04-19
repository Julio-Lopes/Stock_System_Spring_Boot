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

import br.com.springboot.bo.ClientBO;
import br.com.springboot.bo.OutInvoiceBO;
import br.com.springboot.bo.ProductBO;
import br.com.springboot.model.ItemOutInvoice;
import br.com.springboot.model.OutInvoice;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/outInvoice")
public class OutInvoiceController {
    
    @Autowired
    private OutInvoiceBO bo;

    @Autowired
    private ClientBO clientBO;

    @Autowired
    private ProductBO productBO;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView create(ModelMap model){
        model.addAttribute("outInvoice", new OutInvoice());
        model.addAttribute("clients", clientBO.list());

        return new ModelAndView("/outInvoice/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute OutInvoice invoice, BindingResult result, RedirectAttributes attr){
        
        if(invoice.getClient().getId() == null) {
            result.rejectValue("outinvoice", "field.required");
        }

        if(result.hasErrors()) return "/outInvoice/form";

        if(invoice.getId() == null){
            bo.insert(invoice);
            attr.addFlashAttribute("feedback", "A nota de saida foi cadastrado com sucesso");
        }else{
            bo.update(invoice);
            attr.addFlashAttribute("feedback", "Os dados da nota de saida foram atualizados com sucesso");
        }

        return "redirect:/outInvoice";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(ModelMap model){
        model.addAttribute("notes", bo.list());
        return new ModelAndView("/outInvoice/list", model);
    }

    @RequestMapping(value = "/{id}/item", method = RequestMethod.GET)
    public ModelAndView product(@PathVariable("id") Long id, ModelMap model){
        ItemOutInvoice item = new ItemOutInvoice();
        OutInvoice invoice = bo.findById(id);
        item.setOutInvoice(invoice);
        model.addAttribute("itemOutInvoice", item);
        model.addAttribute("products", productBO.list());

        return new ModelAndView("/itemOutInvoice/form", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("itemOutInvoice", new ItemOutInvoice());
        model.addAttribute("clients", clientBO.list());
        model.addAttribute("outInvoice", bo.findById(id));

        return new ModelAndView("/outInvoice/form", model);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id, RedirectAttributes attr){
        OutInvoice invoice = bo.findById(id);
        bo.delete(invoice);
        attr.addFlashAttribute("feedback", "Nota de saida removida com sucesso");

        return "redirect:/outInvoice";
    }
}