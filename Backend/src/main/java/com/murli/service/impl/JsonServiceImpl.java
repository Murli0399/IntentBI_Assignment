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
        if (listingsNode != null && listingsNode.isArray()) {
            for (int i = 0; i < listingsNode.size(); i++) {
                JsonNode node = listingsNode.get(i);
                Car car = new Car();
                car.setVin(node.has("vin") ? node.get("vin").asText() : null);
                car.setYear(node.has("build") && node.get("build").has("year") ? node.get("build").get("year").asInt() : null);
                car.setVehicleType(node.has("build") && node.get("build").has("vehicle_type") ? node.get("build").get("vehicle_type").asText() : null);
                car.setDrivetrain(node.has("build") && node.get("build").has("drivetrain") ? node.get("build").get("drivetrain").asText() : null);
                car.setCylinders(node.has("build") && node.get("build").has("cylinders") ? node.get("build").get("cylinders").asInt() : null);
                car.setBodySubtype(node.has("build") && node.get("build").has("body_type") ? node.get("build").get("body_type").asText() : null);
                car.setDoors(node.has("build") && node.get("build").has("doors") ? node.get("build").get("doors").asInt() : null);
                car.setMadeIn(node.has("build") && node.get("build").has("made_in") ? node.get("build").get("made_in").asText() : null);
                car.setTrim(node.has("build") && node.get("build").has("trim") ? node.get("build").get("trim").asText() : null);
                car.setEngine(node.has("build") && node.get("build").has("engine") ? node.get("build").get("engine").asText() : null);
                car.setModel(node.has("build") && node.get("build").has("model") ? node.get("build").get("model").asText() : null);
                car.setEngineSize(node.has("build") && node.get("build").has("engine_size") ? node.get("build").get("engine_size").asDouble() : null);
                car.setFuelType(node.has("build") && node.get("build").has("fuel_type") ? node.get("build").get("fuel_type").asText() : null);
                car.setTrimR(node.has("build") && node.get("build").has("trim_r") ? node.get("build").get("trim_r").asText() : null);
                car.setMake(node.has("build") && node.get("build").has("make") ? node.get("build").get("make").asText() : null);
                car.setPrice(node.has("price") ? node.get("price").asInt() : null);
                car.setSellerType(node.has("seller_type") ? node.get("seller_type").asText() : null);
                car.setSource(node.has("source") ? node.get("source").asText() : null);
                car.setInteriorColor(node.has("interior_color") ? node.get("interior_color").asText() : null);
                car.setExteriorColor(node.has("exterior_color") ? node.get("exterior_color").asText() : null);

                // Add the car to the list
                cars.add(car);
            }
        } else {
            throw new CarException("No listings found in the JSON file.");
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
            System.out.println(1);
            throw new CarException("VIN is required for each car.");
        }
        if (car.getYear() == null) {
            System.out.println(2);
            throw new CarException("Year is required for each car.");
        }
        if (car.getVehicleType() == null || car.getVehicleType().isEmpty()) {
            System.out.println(3);
            throw new CarException("Vehicle type is required for each car.");
        }
        if (car.getDrivetrain() == null || car.getDrivetrain().isEmpty()) {
            System.out.println(4);
            throw new CarException("Drivetrain is required for each car.");
        }
        if (car.getCylinders() == null || car.getCylinders() <= 0) {
            System.out.println(5);
            throw new CarException("Number of cylinders must be a positive value.");
        }
        if (car.getBodySubtype() == null || car.getBodySubtype().isEmpty()) {
            System.out.println(6);
            throw new CarException("Body subtype is required for each car.");
        }
        if (car.getDoors() == null || car.getDoors() <= 0) {
            System.out.println(7);
            throw new CarException("Number of doors must be a positive value.");
        }
        if (car.getMadeIn() == null || car.getMadeIn().isEmpty()) {
            System.out.println(8);
            throw new CarException("Country of manufacture is required for each car.");
        }
        if (car.getTrim() == null || car.getTrim().isEmpty()) {
            System.out.println(9);
            throw new CarException("Trim is required for each car.");
        }
        if (car.getEngine() == null || car.getEngine().isEmpty()) {
            System.out.println(10);
            throw new CarException("Engine details are required for each car.");
        }
        if (car.getModel() == null || car.getModel().isEmpty()) {
            System.out.println(11);
            throw new CarException("Model is required for each car.");
        }
        if (car.getEngineSize() == null || car.getEngineSize() <= 0) {
            System.out.println(12);
            throw new CarException("Engine size must be a positive value.");
        }
        if (car.getFuelType() == null || car.getFuelType().isEmpty()) {
            System.out.println(13);
            throw new CarException("Fuel type is required for each car.");
        }
        if (car.getTrimR() == null || car.getTrimR().isEmpty()) {
            System.out.println(14);
            throw new CarException("Trim R is required for each car.");
        }
        if (car.getMake() == null || car.getMake().isEmpty()) {
            System.out.println(15);
            throw new CarException("Make is required for each car.");
        }
        if (car.getPrice() == null || car.getPrice() <= 0) {
            System.out.println(16);
            throw new CarException("Price must be a positive value.");
        }
        if (car.getSellerType() == null || car.getSellerType().isEmpty()) {
            System.out.println(17);
            throw new CarException("Seller type is required for each car.");
        }
        if (car.getSource() == null || car.getSource().isEmpty()) {
            System.out.println(18);
            throw new CarException("Source is required for each car.");
        }
        if (car.getInteriorColor() == null || car.getInteriorColor().isEmpty()) {
            System.out.println(19);
            throw new CarException("Interior color is required for each car.");
        }
        if (car.getExteriorColor() == null || car.getExteriorColor().isEmpty()) {
            System.out.println(20);
            throw new CarException("Exterior color is required for each car.");
        }
    }


    @Override
    public List<Car> addCars(List<Car> cars) {
        return carRepository.saveAll(cars);
    }
}
