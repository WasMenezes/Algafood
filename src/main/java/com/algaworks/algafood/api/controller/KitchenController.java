package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.KitchensXmlWrapper;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping()
    public List<Kitchen> list() {
        return kitchenRepository.list();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long id) {
        Kitchen kitchen =  kitchenRepository.byId(id);
        if (kitchen != null) {
            return ResponseEntity.ok(kitchen);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listXml() {
        return new KitchensXmlWrapper(kitchenRepository.list());
    }
}
