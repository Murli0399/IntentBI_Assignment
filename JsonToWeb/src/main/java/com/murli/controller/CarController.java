package com.murli.controller;

import com.murli.entity.Car;
import com.murli.exceptions.CarException;
import com.murli.service.CarService;
import com.murli.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "http://localhost:5500")
public class CarController {


    @Autowired
    private JsonService jsonService;

    @Autowired
    private CarService carService;

    @GetMapping
    public String hello() {
        return "Hello world";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCars(@RequestParam("file") MultipartFile file) {
        try {
            List<Car> cars = jsonService.extractCarsFromJson(file);
            jsonService.validateCars(cars);
            jsonService.addCars(cars);
            return ResponseEntity.ok("Cars uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error uploading cars: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        try {
            Car savedCar = carService.addCar(car);
            return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
        } catch (CarException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> cars = carService.getAllCars();
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (CarException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        try {
            Car car = carService.getCarById(id);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (CarException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        try {
            Car updatedCar = carService.updateCar(car);
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } catch (CarException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        try {
            carService.deleteCar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CarException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Car>> getAllCarsSorted(@RequestParam(defaultValue = "id") String sortBy,
                                                      @RequestParam(defaultValue = "asc") String sortOrder) {
        try {
            List<Car> cars = carService.getAllCarsSorted(sortBy, sortOrder);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (CarException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Car>> getAllCarsPaged(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortOrder) {
        try {
            Page<Car> carsPage = carService.getAllCarsPaged(page, size, sortBy, sortOrder);
            return new ResponseEntity<>(carsPage, HttpStatus.OK);
        } catch (CarException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
