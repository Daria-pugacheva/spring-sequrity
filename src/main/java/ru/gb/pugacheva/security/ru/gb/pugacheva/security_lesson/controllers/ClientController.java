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
        return "Приветствие магазина";
    }

//    @GetMapping("/unsecured")
//    public String unsecuredPage (){
//        return "unsecured";
//    }
//
//    @GetMapping("/auth_page")
//    public String authenticatedPage(){
//        return "authenticated";
//    }

    @GetMapping("/admin")
    public String adminPage(){
        return "Страница, доступная только администратору";
    }

    @GetMapping("/user_info")
    public String daoTestPage (Principal principal){
        Client client = clientService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("Unable to find client by username: " + principal.getName()));
        return "Authenticated user info: " + client.getUsername() + " : " + client.getEmail() + client.getAuthorities();
    }

    @GetMapping("/edit_product")
    public String showEditPage (){
        return "Форма для редактирования товара";
    }

    @GetMapping("/delete_product")
    public String showDeleteButton(){
        return "Кнопка с надписью и функционалом УДАЛИТЬ";
    }

    @GetMapping("/add_product")
    public String showAddPage(){
        return "Форма добавления товара";
    }

    @GetMapping("/products")
    public String showProductsList(){
        return "Таблица со списком всех товаров";
    }

    @GetMapping("/order")
    public String showOrder(){
        return "Функционал (кнопка, страница с корзиной) для формирования заказа";
    }





}
