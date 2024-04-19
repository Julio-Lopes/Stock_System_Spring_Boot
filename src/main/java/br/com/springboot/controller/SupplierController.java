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

import br.com.springboot.bo.SupplierBO;
import br.com.springboot.model.Supplier;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierBO bo;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView create(ModelMap model){
        model.addAttribute("supplier", new Supplier());
        return new ModelAndView("/supplier/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Supplier Supplier, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()) return "/supplier/form";
        
        if(Supplier.getId() == null){
            bo.insert(Supplier);
            attr.addFlashAttribute("feedback", "Fornecedor foi cadastrado com sucesso");
        }else{
            bo.update(Supplier);
            attr.addFlashAttribute("feedback", "Fornecedor foi atualizado com sucesso");
        }
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(ModelMap model){
        model.addAttribute("suppliers", bo.list());
        return new ModelAndView("/supplier/list", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("supplier", bo.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/supplier/form", model);
	}

    @RequestMapping(value = "/disabled/{id}", method = RequestMethod.GET)
    public String disable(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr){
        Supplier supplier = bo.findById(id);
        bo.disable(supplier);
        attr.addFlashAttribute("feedback", "Fornecedor foi desativado com sucesso");
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/enabled/{id}", method = RequestMethod.GET)
    public String enable(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr){
        Supplier supplier = bo.findById(id);
        bo.enable(supplier);
        attr.addFlashAttribute("feedback", "Fornecedor foi ativado com sucesso");
        return "redirect:/suppliers";
    }
}
