package com.murli.service;

import com.murli.entity.Car;
import com.murli.exceptions.CarException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface JsonService {
    public List<Car> extractCarsFromJson(MultipartFile file) throws CarException, IOException;
    public void validateCars(List<Car> cars) throws CarException;
    public void validateCar(Car car) throws CarException;

    public List<Car> addCars(List<Car> cars);
}
