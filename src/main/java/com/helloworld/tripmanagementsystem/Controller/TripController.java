package com.helloworld.tripmanagementsystem.Controller;
import com.helloworld.tripmanagementsystem.DTO.TripDTO;
import com.helloworld.tripmanagementsystem.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
@CrossOrigin(origins ={ "http://localhost:5174","http://localhost:5173"})
public class TripController {
    @Autowired
    TripService tService;

    //Get individual Trip Details
    @GetMapping("/gettrip/{id}")
    public ResponseEntity<TripDTO> getIndividualTripDetail(@PathVariable int id){
        return tService.getIndividualTripDetails(id);
    }

    //Get OverAll Trip Details
    @GetMapping("/getalltrip")
    public List<TripDTO> getAllTripDetail(){

        return tService.getAllTripDetails();
    }

    //Create Trip Details
    @PostMapping("/createtrip")
    public TripDTO createTripDetail(@RequestBody TripDTO dto){

        return tService.createTripDetails(dto);
    }

    //Update The Trip Details
    @PutMapping("/updatetrip/{id}")
    public TripDTO updateTripDetail(@PathVariable int id,@RequestBody TripDTO body){
        return tService.updateTripDetails(id,body);
    }

    //Soft Delete Trip Individual Details
    @DeleteMapping("deletetripss/{id}")
    public String softDeleteTripDetail(@PathVariable int id){
        return tService.softDeleteTripDetails(id);
    }

    //Delete Vehicle
    @DeleteMapping("/deletetrip/{id}")
    public String deleteTripDetail(@PathVariable int id){
        return tService.deleteTripDetails(id);
    }


}
