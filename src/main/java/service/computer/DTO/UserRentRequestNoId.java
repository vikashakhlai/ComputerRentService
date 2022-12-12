package service.computer.DTO;

import service.computer.Models.User;
import service.computer.Models.Computer;
import service.computer.Models.Computer;
import service.computer.Models.User;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
public class UserRentRequestNoId {
    @NotNull
    private User user;
    @NotNull
    @Size(min = 4 , message = "name from 4 ")
    private String name;
    @NotNull
    @Size(min = 4 , message = "surname from 4 ")
    private String surname;
    @NotNull
    private Computer computer;
}
