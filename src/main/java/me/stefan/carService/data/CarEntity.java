package me.stefan.carService.data;

import lombok.Data;
import jakarta.persistence.*;
@Data
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String brand;
    private String name;
    private int horsePower;
}
