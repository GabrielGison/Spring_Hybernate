package co.develhope.hybernate.controllers;

import co.develhope.hybernate.DTO.CarDTO;
import co.develhope.hybernate.entities.Car;
import co.develhope.hybernate.enumerations.CarType;
import co.develhope.hybernate.repositories.Cars;
import co.develhope.hybernate.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CarController {

    @Autowired
    private Cars cars;

    @Autowired
    private CarService carService;


    //GET ALL

    @GetMapping("/cars")
    public List<CarDTO> list() { return carService.carsList(); }

    //GET WITH ID

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Integer id) {
        try {
            Car car = carService.getCar(id);
            carService.saveCar(car);
            return new ResponseEntity<Car>(car, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);}
    }

    //GET WITH NAME

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCarByModel(@RequestParam String model) {
        return new ResponseEntity<List<Car>>(cars.findByModel(model), HttpStatus.OK);
    }

    //POST NEW WITH PARAMS
    
    @PostMapping("/cars")
    public List<CarDTO> addCar(@RequestParam(name = "model",required = true) String model,
                         @RequestParam(name = "type",required = true) CarType carType){
        Car car = new Car();
        car.setModel(model);
        car.setCarType(carType);
        carService.saveCar(car);
        return carService.carsList();
    }

    //UPDATE BY ID

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> update(@RequestBody Car Car, @PathVariable Integer id) {
        try {
            Car existCar = carService.getCar(id);
            Car.setId(id);
            carService.saveCar(Car);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE BY ID

    @DeleteMapping("/cars/{id}")
    public void delete(@PathVariable Integer id) { carService.deleteCar(id); }

}
