package com.murli.service.impl;

import com.murli.entity.Car;
import com.murli.exceptions.CarException;
import com.murli.repository.CarRepository;
import com.murli.service.CarService;
import com.murli.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private JsonService jsonService;

    @Override
    public Car addCar(Car car) throws CarException {
        try {
            jsonService.validateCar(car);
            return carRepository.save(car);
        } catch (CarException ex) {
            throw new CarException("Unable to add car in database");
        }
    }

    @Override
    public List<Car> getAllCars() throws CarException {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) throws CarException {
        return carRepository.findById(id).orElseThrow(() -> new CarException("Car is not present with " + id));
    }

    @Override
    public Car updateCar(Car car) throws CarException {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) throws CarException {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAllCarsSorted(String sortBy, String sortOrder) throws CarException {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return carRepository.findAll(Sort.by(direction, sortBy));
    }

    @Override
    public Page<Car> getAllCarsPaged(int page, int size, String sortBy, String sortOrder) throws CarException {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return carRepository.findAll(pageable);
    }
}
