package com.example.ntsoumoucarel.controller;

import com.example.ntsoumoucarel.model.Address;
import com.example.ntsoumoucarel.model.AddressForm;
import com.example.ntsoumoucarel.repository.AddressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/adresses")
    public String showAddresses(Model model) {
        model.addAttribute("allAddresses", addressRepository.findAll());
        model.addAttribute("addressForm", new AddressForm(null));
        return "addresses";
    }

    @GetMapping("/adresse")
    public String adresseForm(Model model) {
        model.addAttribute("address", new Address()); // Si vous utilisez un objet Address pour stocker les adresses saisies
        return "adresse"; // Nom du fichier HTML pour la page d'adresse
    }


}
