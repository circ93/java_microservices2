package it.corso.pizzeria.controller.Impl;

import it.corso.pizzeria.controller.PizzaController;
import it.corso.pizzeria.dto.PizzaDTO;
import it.corso.pizzeria.mapper.PizzaMapper;
import it.corso.pizzeria.model.Pizza;
import it.corso.pizzeria.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//il rest controller fa la serializzazione e deserializzazione delle info in json
//nel controller normale invece la response va definita in che modo la si vuole
@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaControllerImpl implements PizzaController {

    //pizzaService lavora sugli oggetti di tipo Pizza
    private final PizzaService pizzaService;

    //pizzaMapper lavora sugli oggetti di tipo PizzaDTO
    private final PizzaMapper pizzaMapper;
    //mi occorrono entrambe perche la comunicazione avviene tramite oggetti DTO, i quali vengono convertiti in oggetti del model, quindi da pizzaDTO a Pizza,
    //con l'oggetto Pizza eseguo tutte le operazioni di crud nel db e poi riconverto l'oggetto da Pizza a PizzaDTO per poterlo inviare come response

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaDTO save(@RequestBody PizzaDTO pizzaDTO) {
        //richiamo il pizza mapper perche mi converte il pizzaDTO in un oggetto Pizza utilizzando le classi autogenerate dal mapper
        Pizza _pizza = pizzaMapper.asEntity(pizzaDTO);
        //siccome devo restituire un DTO riconverto l'oggetto pizza appena creato in un oggetto DTO
        return pizzaMapper.asDTO(pizzaService.save(_pizza));
    }

    @Override
    @GetMapping("/{id}")
    public PizzaDTO findById(@PathVariable Long id) {
        //siccome il findbyid mi ritorna un optional devo gestire il tipo di dato tornato,
        //con orElse gli dico che se mi ritorna un oggetti Pizza allora ok altrimenti mi ritorna null
        Pizza _pizza = pizzaService.findById(id).orElse(null);
        return pizzaMapper.asDTO(_pizza);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        pizzaService.deleteById(id);

    }

    @Override
    @GetMapping
    public List<PizzaDTO> findAll() {
        //con il service recupero tutte le Pizze e poi le converto in una lista di PizzaDTO
        return pizzaMapper.asDTOList(pizzaService.findAll());
    }

    @Override
    @PutMapping("/{id}")
    public PizzaDTO update(@RequestBody PizzaDTO pizzaDTO,@PathVariable Long id) {
        Pizza _pizza = pizzaMapper.asEntity(pizzaDTO);
        return pizzaMapper.asDTO(pizzaService.update(_pizza, id));
    }

    @Override
    @GetMapping("/restaurant/{id}")
    public List<PizzaDTO> findByRestaurantId(@PathVariable Long id) {
        List<Pizza> _pizzas = pizzaService.findByRestaurantId(id);
        return pizzaMapper.asDTOList(_pizzas);
    }
}
