package me.stefan.carService;

import me.stefan.carService.data.Car;
import me.stefan.carService.data.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarEntity, Integer> {
}
