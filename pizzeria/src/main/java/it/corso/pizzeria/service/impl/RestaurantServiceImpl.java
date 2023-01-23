package it.corso.pizzeria.service.impl;

import it.corso.pizzeria.dao.RestourantRepository;
import it.corso.pizzeria.model.Restourant;
import it.corso.pizzeria.service.RestourantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestourantService {

    private final RestourantRepository repository;

    @Override
    public Restourant save(Restourant entity) {
        return repository.save(entity);
    }

    @Override
    public List<Restourant> save(List<Restourant> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Restourant> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Restourant> findAll() {
        return repository.findAll();
    }

    @Override
    public Restourant update(Restourant entity, Long id) {
        Optional<Restourant> _restaurant = repository.findById(id);
        if (_restaurant.isPresent()) {
            return save(entity);
        } else {
            return null;
        }
    }

}
