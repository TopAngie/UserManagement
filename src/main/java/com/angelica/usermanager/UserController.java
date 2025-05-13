package com.angelica.usermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new Users());
        return "HomeManager";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Users user) {
        if (user.getAddresses() != null) {
            user.getAddresses().setUser(user);
        }
        userRepository.save(user);
        return "redirect:/thanks";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        Users user = new Users();
        user.setAddresses(new Addresses());
        model.addAttribute("user", user);
        return "UserRegister";
    }




    @GetMapping("/thanks")
    public String showThanksPage() {
        return "Thanks";
    }


    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "DisplayUsers";
    }







    @GetMapping("/details/{id}")
    public String showUserDetails(@PathVariable Long id, Model model) {
        Users user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return "redirect:/users";
        }


        model.addAttribute("users", List.of(user));
        return "DisplayAll";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Users user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "EditUser";
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") Users user) {
        userRepository.save(user); // saves changes
        return "redirect:/users";
    }


}
