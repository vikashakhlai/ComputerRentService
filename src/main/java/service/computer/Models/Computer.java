package service.computer.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "computer")
@Data
@Getter
@Setter
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date expirationDate;

    @Column
    private int cost;

    public Computer(){}

    public Computer(String name, String description, int cost, Date date)
    {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.expirationDate = date;
    }

}
