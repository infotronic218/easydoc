package com.novatech.bf.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.novatech.bf.dao.RepositoryVille;
import com.novatech.bf.entities.Ville;

@Controller
@RequestMapping(value="/admin")
public class AdminVilleControlleur {
	@Autowired
	private RepositoryVille repVille;

	@RequestMapping(value="/villes")
  public String ville(Model model, 
		  @RequestParam(required=false)String message ,
		  @RequestParam(required=false)String error) {
		if(message!=null)
			model.addAttribute("message", message);
		if(error !=null)
		    model.addAttribute("error", error);
	  return "admin/ville";
  }
	
	@RequestMapping(value="/villes/json")
	@ResponseBody
	public List<Ville> jsonVilles(){
		return repVille.findAll();
	}
	@RequestMapping(value="/ville/save", method=RequestMethod.POST)
	public String save(@RequestParam(name="name")String name, 
			@RequestParam(name="id", required=false)Long id,
			Model model,RedirectAttributes ra) {
		 if(id!=null && repVille.existsById(id) && name!=null ) {
			 Ville v = repVille.getOne(id);
			 v.setName(name);
			 repVille.save(v);
			 ra.addAttribute("message","La ville a été modifié avec succès !");
			 return "redirect:/admin/villes";
		 }else {
			 if(name!=null && repVille.findByName(name)==null ){
				 Ville v = new Ville(name);
				 repVille.save(v);
				 ra.addAttribute("message","La ville a été enrégistrer avec succès !");
				 return "redirect:/admin/villes";
			 }
		}
		ra.addAttribute("error","La ville n'a été enrégistrer . Veuillez vérifier les données et essayer à nouveau !");
		return "redirect:/admin/villes";

	}
	
	@RequestMapping(value="/ville/{id}/activate", method=RequestMethod.POST)
	@ResponseBody
	public boolean setAsGrandeInstance(@PathVariable(name="id")Long id, Model model) {
	if(repVille.existsById(id)){
		Ville v = repVille.getOne(id);
		v.setGrandeInstance(!v.isGrandeInstance());
		repVille.save(v);
		return true;
	}
    return false;
	}
	
	@RequestMapping(value="/ville/{id}/delete")
	public String deleteCity(@PathVariable(name="id")Long id, Model model, RedirectAttributes ra) {
	if(repVille.existsById(id)){
		repVille.deleteById(id);
		ra.addAttribute("message","La ville a été supprimer avec succès dans la base donnée!");
		return "redirect:/admin/villes";
	}
	ra.addAttribute("error","Erreur de suppression de la ville dans la base de donnée !");
	return "redirect:/admin/villes";
	}
}
