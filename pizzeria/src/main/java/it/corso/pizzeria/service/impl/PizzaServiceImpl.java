package it.corso.pizzeria.service.impl;

import it.corso.pizzeria.dao.PizzaRepository;
import it.corso.pizzeria.model.Pizza;
import it.corso.pizzeria.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
//crea un costruttore con solo i parametri stanziati come final
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    //iniection dell'autowire avviene a run time, quindi se richiamo una una variabile non ancora instanziata me ne accorgo solo a run time
    //@Autowired
    //PizzaRepository pizzaRepository;

    //quindi con l'annotazione @RequiredArgConstructor mi crea un costruttore con solo gli argomenti final,
    // in questo caso se la Repo a tempo di compilazione non è presente si blocca
    //PS: lo chiamo solo repository così da poter copiare tutte le implementazioni anche nell'altra classe ToppinServiceImpl
    private final PizzaRepository repository;

    @Override
    public Pizza save(Pizza entity) {
        return repository.save(entity);
    }

    @Override
    public List<Pizza> save(List<Pizza> entities) {
        //qui tocca fare il cast perchè la save all ritorna oggetti generici non sa di che tipo sono
        return (List<Pizza>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pizza> findAll() {
        return repository.findAll();
    }

    @Override
    public Pizza update(Pizza entity, Long id) {
        Optional<Pizza> _pizza = repository.findById(id);
        if (_pizza.isPresent()) {
            return save(entity);
        } else {
            return null;
        }
    }
}
