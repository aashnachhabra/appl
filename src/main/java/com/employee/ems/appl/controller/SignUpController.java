package com.employee.ems.appl.controller;

import com.employee.ems.appl.entity.User;
import com.employee.ems.appl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("userSignUp",new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            redirectAttributes.addFlashAttribute("error", "Username already exists");
            return "redirect:/signup";
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "Sign up successful! You can now login.");
        return "redirect:/login";
    }
}

