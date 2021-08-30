package ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.entities.Client;
import ru.gb.pugacheva.security.ru.gb.pugacheva.security_lesson.services.ClientService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    public String homePage(){
        return "homePage";
    }

    @GetMapping("/unsecured")
    public String unsecuredPage (){
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage(){
        return "authenticated";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/user_info")
    public String daoTestPage (Principal principal){
        Client client = clientService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("Unable to find client by username: " + principal.getName()));
        return "Authenticated user info: " + client.getUsername() + " : " + client.getEmail();
    }
}
