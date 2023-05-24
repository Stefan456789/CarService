package me.stefan.carService;

import me.stefan.carService.data.Car;
import me.stefan.carService.data.CarDto;
import me.stefan.carService.data.CarEntity;
import me.stefan.carService.data.CarResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CarDataService {
    @Autowired
    private CarRepository carRepository;

    List<CarResource> getCarResources() {
        List<CarResource> result = new ArrayList<>();
        for (CarEntity cCar : carRepository.findAll()) {
            CarResource cResource =
                    convertCarToCarResource(convertCarEntityToCar(cCar));
            result.add(cResource);
        }
        return result;
    }
    private CarResource convertCarToCarResource(Car cCar) {
        CarResource cResource = new CarResource();
        cResource.setId(cCar.getId());
        cResource.setBrandAndName(String.format("%s %s", cCar.getBrand(),
                cCar.getName()));
        cResource.setHorsePower(String.format("%d PS", cCar.getHorsePower()));
        return cResource;
    }
    public CarResource addCar(CarDto carDto) {
        Car newCar = new Car();
        newCar.setId(-1);
        newCar.setHorsePower(psToInt(carDto.getHorsePower()));
        newCar.setBrand(carDto.getBrand());
        newCar.setName(carDto.getName());
        CarEntity storedEntity = this.carRepository.save(convertCarToCarEntity(newCar));
        return convertCarToCarResource(convertCarEntityToCar(storedEntity));
    }
    private int psToInt(String psString) {
        String psRaw = psString.replace(" ", "").replace("PS", "");
        return Integer.parseInt(psRaw);

    }
    public CarResource editCar(int carId, CarDto carDto) {
        Car carToChange = convertCarEntityToCar(carRepository.findById(carId).get());
        carToChange.setBrand(carDto.getBrand());
        carToChange.setName(carDto.getName());
        carToChange.setHorsePower(psToInt(carDto.getHorsePower()));
        CarEntity changedEntity =
                carRepository.save(convertCarToCarEntity(carToChange));
        return convertCarToCarResource(convertCarEntityToCar(changedEntity));
    }
    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }
    public CarResource deleteCar(int carId) {
        Optional<CarEntity> carToRemove = carRepository.findById(carId);
        carRepository.delete(carToRemove.get());
        return convertCarToCarResource(convertCarEntityToCar(carToRemove.get()));
    }
    public CarResource getCar(int carId) {
        Optional<CarEntity> foundCar = carRepository.findById(carId);
        return convertCarToCarResource(convertCarEntityToCar(foundCar.get()));
    }
    public CarEntity convertCarToCarEntity(Car c) {
        CarEntity result = new CarEntity();
        if(c.getId() != -1) {
            result.setId(c.getId());
        }
        result.setName(c.getName());
        result.setBrand(c.getBrand());
        result.setHorsePower(c.getHorsePower());
        return result;
    }
    public Car convertCarEntityToCar(CarEntity c) {
        Car result = new Car();
        result.setId(c.getId());
        result.setName(c.getName());
        result.setBrand(c.getBrand());
        result.setHorsePower(c.getHorsePower());
        return result;
    }
}