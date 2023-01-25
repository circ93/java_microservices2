package it.corso.pizzeria.service.impl;

import it.corso.pizzeria.dao.ToppingRepository;
import it.corso.pizzeria.model.Topping;
import it.corso.pizzeria.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ToppingServiceImpl implements ToppingService {

    //usato al posto dell'autowired, spiegato nel pizzaServiceImpl
    private final ToppingRepository repository;

    @Override
    public Topping save(Topping entity) {
        return repository.save(entity);
    }

    @Override
    public List<Topping> save(List<Topping> entities) {
        //qui tocca fare il cast perchè la save all ritorna oggetti generici non sa di che tipo sono
        return (List<Topping>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Topping> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Topping> findAll() {
        return repository.findAll();
    }

    @Override
    public Topping update(Topping entity, Long id) {
        Optional<Topping> _topping = findById(id);
        if (_topping.isPresent()) {
            //non uso l'id passato perchè nell'entity passato c'è già l'id quindi hibernate capisce di dover aggiornare
            //il controllo che faccio nell'if è per controllare che ci sia un oggetto con quell'id altrimenti hibernate con la save creerebbe una nuova tupla
            return save(entity);
        } else {
            return null;
        }
    }
}
