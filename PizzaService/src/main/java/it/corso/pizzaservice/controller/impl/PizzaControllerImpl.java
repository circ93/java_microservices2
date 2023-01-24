package it.corso.pizzaservice.controller.impl;

import it.corso.pizzaservice.controller.PizzaController;
import it.corso.pizzaservice.dto.PizzaDTO;
import it.corso.pizzaservice.mapper.PizzaMapper;
import it.corso.pizzaservice.model.Pizza;
import it.corso.pizzaservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaControllerImpl implements PizzaController {

    private final PizzaService pizzaService;
    private final PizzaMapper pizzaMapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaDTO save(@RequestBody PizzaDTO pizzaDTO) {
        Pizza _pizza = pizzaMapper.asEntity(pizzaDTO);
        return pizzaMapper.asDTO(pizzaService.save(_pizza));
    }

    @Override
    @GetMapping("/{id}")
    public PizzaDTO findById(@PathVariable Long id) {
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
        return pizzaMapper.asDTOList(pizzaService.findAll());
    }

    @Override
    @PutMapping("/{id}")
    public PizzaDTO update(@RequestBody PizzaDTO pizzaDTO,@PathVariable Long id) {
        Pizza _pizza = pizzaMapper.asEntity(pizzaDTO);
        return pizzaMapper.asDTO(pizzaService.update(_pizza, id));
    }

}
