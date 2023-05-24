package me.stefan.carService;

import me.stefan.carService.data.CarDto;
import me.stefan.carService.data.CarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CarRESTController {
    @Autowired
    private CarDataService carDataService;

    @GetMapping("/cars")
    public List<CarResource> getAllCars() {
        List<CarResource> carResources = carDataService.getCarResources();
        return carResources;
    }

    @RequestMapping(value ="/cars", method = RequestMethod.POST)
    public CarResource addCar(@RequestBody CarDto carDto) {
        return carDataService.addCar(carDto);
    }

    @RequestMapping(value ="/cars/{carId}", method = RequestMethod.PUT)
    public CarResource editCar(@PathVariable int carId, @RequestBody CarDto
            carDto) {
        return carDataService.editCar(carId, carDto);
    }

    @RequestMapping(value ="/cars/{carId}", method = RequestMethod.DELETE)
    public CarResource deleteCar(@PathVariable int carId) {
        return carDataService.deleteCar(carId);
    }


}
