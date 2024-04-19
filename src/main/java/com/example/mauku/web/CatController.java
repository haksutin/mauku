package com.example.mauku.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mauku.domain.Cat;
import com.example.mauku.domain.CatRepository;
import com.example.mauku.domain.ColourRepository;
import com.example.mauku.domain.Location;
import com.example.mauku.domain.LocationRepository;

@Controller
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private ColourRepository colourRepository;

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping(value = { "/", "/cats" })
    public String catList(Model model) {
        model.addAttribute("cats", catRepository.findAll());
        return "catlist";
    }

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

    @RequestMapping(value = "/add")
    public String addCat(Model model) {
        model.addAttribute("cat", new Cat());
        model.addAttribute("colours", colourRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        return "addcat";
    }

    @PostMapping("/save")
    public String save(Cat cat) {
        catRepository.save(cat);
        return "redirect:/cats";
    }

    @RequestMapping(value = "/location/{id}")
    public String catLocation(@PathVariable("id") Long locationid, Model model) {
        model.addAttribute("cat", catRepository.findById(locationid));
        model.addAttribute("locations", locationRepository.findAll());
        return "location";
    }

    @GetMapping("/editlocation/{id}")
    public String showEditLocation(@PathVariable("id") Long id, Model model) {
        Cat cat = catRepository.findById(id).orElse(null);
        model.addAttribute("cat", cat);
        model.addAttribute("locations", locationRepository.findAll());
        return "editlocation";
    }

    @PostMapping("/editlocation/{id}")
    public String editLocation(@PathVariable("id") Long id, @RequestParam("location") Location name) {
        Cat cat = catRepository.findById(id).orElse(null);
        cat.setLocation(name);
        catRepository.save(cat);
        return "redirect:/location/" + id;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCat(@PathVariable("id") Long id, Model model) {
        catRepository.deleteById(id);
        return "redirect:/cats";
    }

}
