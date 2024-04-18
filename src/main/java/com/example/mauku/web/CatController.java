package com.example.mauku.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/add")
    public String addCat(Model model) {
        model.addAttribute("cat", new Cat());
        model.addAttribute("colours", colourRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        return "addcat";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Cat cat) {
        catRepository.save(cat);
        return "redirect:/cats";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCat(@PathVariable("id") Long id, Model model) {
        catRepository.deleteById(id);
        return "redirect:/cats";
    }

    @RequestMapping(value = "/location/{id}")
    public String catLocation(@PathVariable("id") Long locationid, Model model) {
        model.addAttribute("cat", catRepository.findById(locationid));
        model.addAttribute("locations", locationRepository.findAll());
        return "location";
    }

    @RequestMapping(value = "/editlocation/{id}", method = RequestMethod.GET)
    public String editLocation(@PathVariable("id") Long id, Model model) {
        Cat cat = catRepository.findById(id).orElse(null);
        model.addAttribute("cat", cat);
        model.addAttribute("locations", locationRepository.findAll());
        return "editlocation";
    }

    @RequestMapping(value = "/savelocation", method = RequestMethod.POST)
    public String saveLocation(@RequestParam("id") Long id, @RequestParam("location") Long locationid) {
        Cat cat = catRepository.findById(id).orElse(null);
        Location location = locationRepository.findById(locationid).orElse(null);
        cat.setLocation(location);
        catRepository.save(cat);
        return "redirect:/location/" + id;
    }
}
