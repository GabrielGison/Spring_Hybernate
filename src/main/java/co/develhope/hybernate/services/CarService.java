package co.develhope.hybernate.services;

import co.develhope.hybernate.DTO.CarDTO;
import co.develhope.hybernate.entities.Car;
import co.develhope.hybernate.repositories.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService {

    @Autowired
    private Cars cars;

    public List<CarDTO> carsList() {
        return cars.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private CarDTO convertEntityToDTO(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModel(car.getModel());
        carDTO.setCarType(car.getCarType());
        return carDTO;
    }

    public void saveCar(Car car) { cars.save(car); }

    public Car getCar(Integer id) {return cars.findById(id).get();}

    public void deleteCar(Integer id) {
        cars.deleteById(id);
    }
}
