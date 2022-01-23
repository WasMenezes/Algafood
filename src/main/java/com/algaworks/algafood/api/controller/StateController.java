package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.RegisterStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired RegisterStateService registerStateService;

    @GetMapping
    public ResponseEntity<List<State>> list() {
        return ResponseEntity.ok(this.stateRepository.findAll());
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> getById(@PathVariable Long stateId) {
        Optional<State> state = this.stateRepository.findById(stateId);
        if (state.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(state.get());
    }

    @PostMapping
    public ResponseEntity<State> create(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.registerStateService.save(state));
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<?> update(@PathVariable Long stateId, @RequestBody State state) {
        try {
            Optional<State> stateActual = this.stateRepository.findById(stateId);
            if (stateActual == null) return ResponseEntity.notFound().build();
            BeanUtils.copyProperties(state, stateActual, "id");
            return ResponseEntity.ok().body(this.registerStateService.save(state));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> delete(@PathVariable Long stateId) {
        try {
            this.registerStateService.remove(stateId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
