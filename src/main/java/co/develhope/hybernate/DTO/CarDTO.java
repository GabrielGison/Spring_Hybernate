package co.develhope.hybernate.DTO;

import co.develhope.hybernate.enumerations.CarType;
import lombok.Data;


@Data
public class CarDTO {
    private Integer id;
    private String model = "";
    private CarType carType = CarType.CITYCAR;
}