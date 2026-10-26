package com.helloworld.tripmanagementsystem.Controller;
import com.helloworld.tripmanagementsystem.DTO.VehicleDTO;
import com.helloworld.tripmanagementsystem.DTO.VehicleDropDownDTO;
import com.helloworld.tripmanagementsystem.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = {"http://localhost:5174","http://localhost:5173"})
public class VehicleController {
    @Autowired
    VehicleService vService;
    //Get individual Vehicle Details
    @GetMapping("/getvehicle/{id}")
    public ResponseEntity<VehicleDTO> getIndividualVehicleDetail(@PathVariable int id){
        return vService.getIndividualVehicleDetails(id);
    }

    //Get OverAll Vehicle Details
    @GetMapping("/getallvehicle")
    public List<VehicleDTO> getAllVehicleDetail(){
        return vService.getAllVehicleDetails();
    }

    //Get All Vehicle ID Alone
    @GetMapping("/getallvehicleId")
    public List<VehicleDropDownDTO> getAllVehicleId(){
        return vService.getAllVehicleId();
    }
    //Get All Vehicle Type Alone
    @GetMapping("/getallvehicleType")
    public List<String> getAllVehicleType(){
        return vService.getAllVehicleType();
    }

    //Create Vehicle Details
    @PostMapping("/createvehicle")
    public VehicleDTO createVehicleDetail(@RequestBody VehicleDTO dto){
        return vService.createVehicleDetails(dto);
    }

    //Update The Vehicle Details
    @PutMapping("/updatevehicle/{id}")
    public VehicleDTO updateVehicleDetail(@PathVariable int id,@RequestBody VehicleDTO body){
        return vService.updateVehicleDetails(id,body);
    }
    //Delete Vehicle
    @DeleteMapping("/deletevehicle/{id}")
    public String deleteVehicleDetail(@PathVariable int id){
        return vService.deleteVehicleDetails(id);
    }
}
