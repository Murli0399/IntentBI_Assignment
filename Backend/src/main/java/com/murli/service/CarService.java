package com.murli.service;

import com.murli.entity.Car;
import com.murli.exceptions.CarException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {
    public Car addCar(Car car) throws CarException;

    public List<Car> getAllCars() throws CarException;

    public Car getCarById(Long id) throws CarException;

    public Car updateCar(Car car) throws CarException;

    public void deleteCar(Long id) throws CarException;

    public List<Car> getAllCarsSorted(String sortBy, String sortOrder) throws CarException;

    public Page<Car> getAllCarsPaged(int page, int size, String sortBy, String sortOrder) throws CarException;

}
