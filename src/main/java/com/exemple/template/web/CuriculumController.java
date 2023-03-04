package com.exemple.template.web;

import com.exemple.template.entities.Curiculum;
import com.exemple.template.repository.CuriculumRepository;
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
public class CuriculumController {
    CuriculumRepository curiculumRepository;
    @GetMapping("/")
    public String home() {
        //redirection vers l'index
        return "home";
    }

    @GetMapping(path = "/index")
    public String patients(Model model,
                           //pour la pagination
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        //aves page au lieu d'utiliser List c'est page
        Page<Curiculum> pageCuriculums = curiculumRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listeCuriculum", pageCuriculums.getContent()); //getcontent pour n'affichez que la liste
        //pour stocker le nombre de page
        model.addAttribute("pages", new int[pageCuriculums.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "curiculums";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        curiculumRepository.deleteById(id);
        //redirection vers l'index
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }



    // utilisation sans thymleaf
    @GetMapping("/curiculums")
    @ResponseBody
    public List<Curiculum> listCuriculums() {
        return curiculumRepository.findAll();
    }

    @GetMapping("/formCuriculums")
    public String formCuriculums(Model model) {
        model.addAttribute("curiculum", new Curiculum());
        return "formCuriculums";
    }

    @GetMapping("/Offre")
    public String offre(Model model) {
        model.addAttribute("curiculum", new Curiculum());
        //redirection vers l'index
        return "offre";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid Curiculum curiculum, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formCuriculums";
        curiculumRepository.save(curiculum);
        return "redirect:/index?page=" + page + "&keyword" + keyword;
    }

    @GetMapping("/editCuriculums")
    public String editCuriculums(Model model, Long id, String keyword, int page) {
        Curiculum curiculum = curiculumRepository.findById(id).orElse(null);
        if (curiculum == null) throw new RuntimeException("Curiculum introuvable");
        model.addAttribute("curiculum", curiculum);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editCuriculums";
    }
}
