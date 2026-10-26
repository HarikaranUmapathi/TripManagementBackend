package com.helloworld.tripmanagementsystem.Controller;

import com.helloworld.tripmanagementsystem.DTO.DriverDTO;
import com.helloworld.tripmanagementsystem.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@CrossOrigin(origins = {"http://localhost:5173" ,"http://localhost:5174"})
public class DriverController {

    @Autowired
    DriverService dService;
    //Get individual Driver Details
    @GetMapping("/getdriver/{id}")
    public ResponseEntity<DriverDTO> getIndividualDriverDetail(@PathVariable int id){
        return dService.getIndividualDriverDetails(id);
    }

    //Get OverAll Driver Details
    @GetMapping("/getalldriver")
    public List<DriverDTO> getAllDriverDetail(){
        return dService.getAllDriverDetails();
    }

    //Get All the Driver Name
    @GetMapping("/getalldrivernames")
    public List<String> getAllDriverName(){
        return dService.getAllDriverNames();
    }

    //Create Driver Details
    @PostMapping("/createdriver")
    public DriverDTO createTripDetail(@RequestBody DriverDTO dto){
        return dService.createDriverDetails(dto);
    }
    //Update The Driver Details
    @PutMapping("/updatedriver/{id}")
    public DriverDTO updateDriverDetail(@PathVariable int id,@RequestBody DriverDTO body){
        return dService.updateDriverDetails(id,body);
    }

    //Delete Driver
    @DeleteMapping("/deletedriver/{id}")
    public String deleteDriverDetail(@PathVariable int id){
        return dService.deleteDriverDetails(id);
    }


}
