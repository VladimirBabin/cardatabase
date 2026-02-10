package com.github.vladimirbabin.cardatabase.web;


import com.github.vladimirbabin.cardatabase.domain.Car;
import com.github.vladimirbabin.cardatabase.domain.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }
}
