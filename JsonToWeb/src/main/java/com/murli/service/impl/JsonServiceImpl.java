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
        }
        if (car.getVehicleType() == null || car.getVehicleType().isEmpty()) {
            throw new CarException("Vehicle type is required for each car.");
        }
        if (car.getDrivetrain() == null || car.getDrivetrain().isEmpty()) {
            throw new CarException("Drivetrain is required for each car.");
        }
        if (car.getCylinders() == null || car.getCylinders() <= 0) {
            throw new CarException("Number of cylinders must be a positive value.");
        }
        if (car.getBodySubtype() == null || car.getBodySubtype().isEmpty()) {
            throw new CarException("Body subtype is required for each car.");
        }
        if (car.getDoors() == null || car.getDoors() <= 0) {
            throw new CarException("Number of doors must be a positive value.");
        }
        if (car.getMadeIn() == null || car.getMadeIn().isEmpty()) {
            throw new CarException("Country of manufacture is required for each car.");
        }
        if (car.getTrim() == null || car.getTrim().isEmpty()) {
            throw new CarException("Trim is required for each car.");
        }
        if (car.getEngine() == null || car.getEngine().isEmpty()) {
            throw new CarException("Engine details are required for each car.");
        }
        if (car.getModel() == null || car.getModel().isEmpty()) {
            throw new CarException("Model is required for each car.");
        }
        if (car.getEngineSize() == null || car.getEngineSize() <= 0) {
            throw new CarException("Engine size must be a positive value.");
        }
        if (car.getFuelType() == null || car.getFuelType().isEmpty()) {
            throw new CarException("Fuel type is required for each car.");
        }
        if (car.getTrimR() == null || car.getTrimR().isEmpty()) {
            throw new CarException("Trim R is required for each car.");
        }
        if (car.getMake() == null || car.getMake().isEmpty()) {
            throw new CarException("Make is required for each car.");
        }
        if (car.getPrice() == null || car.getPrice() <= 0) {
            throw new CarException("Price must be a positive value.");
        }
        if (car.getSellerType() == null || car.getSellerType().isEmpty()) {
            throw new CarException("Seller type is required for each car.");
        }
        if (car.getSource() == null || car.getSource().isEmpty()) {
            throw new CarException("Source is required for each car.");
        }
        if (car.getInteriorColor() == null || car.getInteriorColor().isEmpty()) {
            throw new CarException("Interior color is required for each car.");
        }
        if (car.getExteriorColor() == null || car.getExteriorColor().isEmpty()) {
            throw new CarException("Exterior color is required for each car.");
        }
    }


    @Override
    public List<Car> addCars(List<Car> cars) {
        return carRepository.saveAll(cars);
    }
}
