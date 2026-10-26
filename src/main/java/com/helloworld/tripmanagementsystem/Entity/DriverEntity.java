package com.helloworld.tripmanagementsystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;


    @NotNull

    private String driverName;

    private int experience;

    @Column(name="ContactNumber", unique = true, nullable = false)
    private Long contactNumber;




}
