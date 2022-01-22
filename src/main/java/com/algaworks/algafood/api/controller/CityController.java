package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.service.RegisterCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegisterCityService registerCityService;

    @GetMapping
    public ResponseEntity<List<City>> listCities() {
        return ResponseEntity.ok(cityRepository.list());
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> listCities(@PathVariable Long cityId) {
        City City = cityRepository.byId(cityId);
        if (City != null) {
            return ResponseEntity.ok(cityRepository.byId(cityId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(registerCityService.save(city));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
        try {
            City cityActual = this.cityRepository.byId(cityId);
            if (cityActual == null) return ResponseEntity.notFound().build();
            BeanUtils.copyProperties(city, cityActual, "id");
            return ResponseEntity.ok().body(this.registerCityService.save(cityActual));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> delete(@PathVariable Long cityId) {
        try {
            this.registerCityService.delete(cityId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
