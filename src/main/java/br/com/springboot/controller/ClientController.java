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
import br.com.springboot.model.Client;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    private ClientBO bo;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView create(ModelMap model){
        model.addAttribute("client", new Client());
        return new ModelAndView("/client/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Client client, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()) return "/client/form";
        
        if(client.getId() == null){
            bo.insert(client);
            attr.addFlashAttribute("feedback", "Cliente foi cadastrado com sucesso");
        }else{
            bo.update(client);
            attr.addFlashAttribute("feedback", "Cliente foi atualizado com sucesso");
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(ModelMap model){
        model.addAttribute("clients", bo.list());
        return new ModelAndView("/client/list", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("client", bo.findById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/client/form", model);
	}

    @RequestMapping(value = "/disabled/{id}", method = RequestMethod.GET)
    public String disable(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr){
        Client client = bo.findById(id);
        bo.disable(client);
        attr.addFlashAttribute("feedback", "Cliente foi desativado com sucesso");
        return "redirect:/clients";
    }

    @RequestMapping(value = "/enabled/{id}", method = RequestMethod.GET)
    public String enable(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr){
        Client client = bo.findById(id);
        bo.enable(client);
        attr.addFlashAttribute("feedback", "Cliente foi ativado com sucesso");
        return "redirect:/clients";
    }
}
