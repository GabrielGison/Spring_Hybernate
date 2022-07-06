package co.develhope.hybernate.repositories;

import co.develhope.hybernate.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cars extends JpaRepository<Car, Integer> {
    List<Car> findByModel(String model);
}
