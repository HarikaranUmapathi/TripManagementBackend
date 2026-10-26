package com.helloworld.tripmanagementsystem.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



// In the DTO we do not use JPA Annotions like @ID,@Column etc but we can use Validation like @NotBlank, @Not Null
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

    private Long id;

    private String driverName;

    private int experience;

    private Long contactNumber;
}
