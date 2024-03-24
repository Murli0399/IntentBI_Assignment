package com.murli.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murli.entity.Car;
import com.murli.exceptions.CarException;
import com.murli.repository.CarRepository;
import com.murli.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JsonServiceImpl implements JsonService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> extractCarsFromJson(MultipartFile file) throws CarException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file.getInputStream());
        JsonNode listingsNode = rootNode.get("listings");
        System.out.println(listingsNode.isArray());
        List<Car> cars = new ArrayList<>();

        Iterator<JsonNode> iterator = listingsNode.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();

            Car car = new Car();
            car.setVin(node.get("vin").asText());
            car.setYear(node.get("build").get("year").asInt());
            car.setVehicleType(node.get("build").get("vehicle_type").asText());
            car.setDrivetrain(node.get("build").get("drivetrain").asText());
            car.setCylinders(node.get("build").get("cylinders").asInt());
            car.setBodySubtype(node.get("build").get("body_subtype").asText());
            car.setDoors(node.get("build").get("doors").asInt());
            car.setMadeIn(node.get("build").get("made_in").asText());
            car.setTrim(node.get("build").get("trim").asText());
            car.setEngine(node.get("build").get("engine").asText());
            car.setModel(node.get("build").get("model").asText());
            car.setEngineSize(node.get("build").get("engine_size").asDouble());
            car.setFuelType(node.get("build").get("fuel_type").asText());
            car.setTrimR(node.get("build").get("trim_r").asText());
            car.setMake(node.get("build").get("make").asText());
            car.setPrice(node.get("price").asInt());
            car.setSellerType(node.get("seller_type").asText());
            car.setSource(node.get("source").asText());
            car.setInteriorColor(node.get("interior_color").asText());
            car.setExteriorColor(node.get("exterior_color").asText());

            // Add the car to the list
            System.out.println(car);
            cars.add(car);
        }

        return cars;
    }

    @Override
    public void validateCars(List<Car> cars) throws CarException {
        for (Car car : cars) {
            validateCar(car);
        }
    }

    @Override
    public void validateCar(Car car) throws CarException {
        if (car.getVin() == null || car.getVin().isEmpty()) {
            throw new CarException("VIN is required for each car.");
        }
        if (car.getYear() == null) {
            throw new CarException("Year is required for each car.");
            // Add similar checks for other required fields and data types
        }
    }

    @Override
    public List<Car> addCars(List<Car> cars) {
        return carRepository.saveAll(cars);
    }
}
