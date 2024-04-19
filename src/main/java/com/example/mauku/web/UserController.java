package com.example.mauku.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.mauku.domain.AppUserRepository;
import com.example.mauku.domain.SignupForm;
import com.example.mauku.domain.AppUser;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private AppUserRepository repository;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        String username = signupForm.getUsername();
        if (repository.findByUsername(username) != null) {
            bindingResult.rejectValue("username", "err.username", "Username already exists");
            return "signup";
        }

        String password = signupForm.getPassword();
        String passwordCheck = signupForm.getPasswordCheck();
        if (!password.equals(passwordCheck)) {
            bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
            return "signup";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        AppUser newUser = new AppUser();
        newUser.setPasswordHash(hashedPassword);
        newUser.setUsername(username);
        newUser.setRole("USER");
        repository.save(newUser);

        return "redirect:/login";
    }

}