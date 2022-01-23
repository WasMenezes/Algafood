package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

//    @GetMapping("kitchens/by-name")
//    public List<Kitchen> kitchensByName(@RequestParam String name) {
//        //return kitchenRepository.findBy(name);
//    }
}
