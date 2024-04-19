package br.com.springboot.controller;

import java.util.Arrays;

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

import br.com.springboot.bo.ProductBO;
import br.com.springboot.model.Category;
import br.com.springboot.model.Product;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductBO bo;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView create(ModelMap model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", Arrays.asList(Category.values()));
        return new ModelAndView("/product/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Product product, BindingResult result, RedirectAttributes attr, ModelMap model){
        if(result.hasErrors()) {
            model.addAttribute("categories", Arrays.asList(Category.values()));
            return "/product/form";
        }
        if(product.getId() == null){
            bo.insert(product);
            attr.addFlashAttribute("feedback", "O produto foi cadastrado com sucesso");
        }else{
            bo.update(product);
            attr.addFlashAttribute("feedback", "O produto foi atualizado com sucesso");
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(ModelMap model){
        model.addAttribute("products", bo.list());
        return new ModelAndView("/product/list", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("product", bo.findById(id));
            model.addAttribute("categories", Arrays.asList(Category.values()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/product/form", model);
	}

    @RequestMapping(value = "/disabled/{id}", method = RequestMethod.GET)
    public String disable(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr){
        Product product = bo.findById(id);
        bo.disable(product);
        attr.addFlashAttribute("feedback", "O produto foi desativado com sucesso");
        return "redirect:/products";
    }

    @RequestMapping(value = "/enabled/{id}", method = RequestMethod.GET)
    public String enable(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr){
        Product product = bo.findById(id);
        bo.enable(product);
        attr.addFlashAttribute("feedback", "O produto foi ativado com sucesso");
        return "redirect:/products";
    }
}