package service.computer.DTO;

import java.util.Date;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Data
@Getter
public class ComputerRequestNoRent {
    private long id;
    private String name;
    private String description;
    private int cost;
    private Date expirationDate;
}
