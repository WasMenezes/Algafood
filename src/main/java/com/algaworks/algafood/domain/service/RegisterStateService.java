package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterStateService {
    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return this.stateRepository.save(state);
    }

    public void remove(Long stateId) {
        try {
            this.stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de estado com código %d", stateId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", stateId)
            );
        }
    }


}
