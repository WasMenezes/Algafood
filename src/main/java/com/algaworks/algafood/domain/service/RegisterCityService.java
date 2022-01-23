package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterCityService {
    @Autowired
    private CityRepository cityRepository;
    
    
    public City save(City city) {
        return this.cityRepository.save(city);
    }

    public void delete(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de cidade com código %d", cityId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cityId)
            );
        }
    }
}
