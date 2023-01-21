package com.exemple.template.web;

import com.exemple.template.entities.Professeur;
import com.exemple.template.repository.ProfesseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProfesseurController {
    ProfesseurRepository professeurRepository;

    @GetMapping(path = "/index")
    public String patients(Model model,
                           //pour la pagination
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        //aves page au lieu d'utiliser List c'est page
        Page<Professeur> pageProfesseurs = professeurRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listeProfesseur", pageProfesseurs.getContent()); //getcontent pour n'affichez que la liste
        //pour stocker le nombre de page
        model.addAttribute("pages", new int[pageProfesseurs.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "professeurs";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        professeurRepository.deleteById(id);
        //redirection vers l'index
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        //redirection vers l'index
        return "home";
    }

    // utilisation sans thymleaf
    @GetMapping("/professeurs")
    @ResponseBody
    public List<Professeur> listProfesseurs() {
        return professeurRepository.findAll();
    }

    @GetMapping("/formProfesseurs")
    public String formProfesseurs(Model model) {
        model.addAttribute("professeur", new Professeur());
        return "formProfesseurs";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid Professeur professeur, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formProfesseurs";
        professeurRepository.save(professeur);
        return "redirect:/index?page=" + page + "&keyword" + keyword;
    }

    @GetMapping("/editProfesseurs")
    public String editProfesseurs(Model model, Long id, String keyword, int page) {
        Professeur professeur = professeurRepository.findById(id).orElse(null);
        if (professeur == null) throw new RuntimeException("Professeur introuvable");
        model.addAttribute("professeur", professeur);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editProfesseurs";
    }
}
