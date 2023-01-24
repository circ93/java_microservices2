package it.corso.restaurantservice.controller.impl;

import it.corso.restaurantservice.controller.DriverController;
import it.corso.restaurantservice.dto.DriverDTO;
import it.corso.restaurantservice.mapper.DriverMapper;
import it.corso.restaurantservice.model.Driver;
import it.corso.restaurantservice.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverControllerImpl implements DriverController {

    private final DriverService driverService;
    private final DriverMapper driverMapper;


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO save(@RequestBody DriverDTO driverDTO) {
        Driver _driver = driverMapper.asEntity(driverDTO);
        return driverMapper.asDTO(driverService.save(_driver));
    }

    @Override
    @GetMapping("/{id}")
    public DriverDTO findById(@PathVariable Long id) {
        Driver _driver = driverService.findById(id).orElse(null);
        return driverMapper.asDTO(_driver);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        driverService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<DriverDTO> findAll() {
        return driverMapper.asDTOList(driverService.findAll());
    }

    @Override
    @PutMapping("{id}")
    public DriverDTO update(@RequestBody DriverDTO driverDTO,@PathVariable Long id) {
        Driver _driver = driverMapper.asEntity(driverDTO);
        return driverMapper.asDTO(driverService.update(_driver, id));
    }
}
