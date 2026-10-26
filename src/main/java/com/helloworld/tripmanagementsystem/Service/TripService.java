package com.helloworld.tripmanagementsystem.Service;
import com.helloworld.tripmanagementsystem.DTO.TripDTO;
import com.helloworld.tripmanagementsystem.Entity.DriverEntity;
import com.helloworld.tripmanagementsystem.Entity.TripEntity;
import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import com.helloworld.tripmanagementsystem.Exception.ResourceNotFoundException;
import com.helloworld.tripmanagementsystem.Mapper.TripMapper;
import com.helloworld.tripmanagementsystem.Repository.DriverRepository;
import com.helloworld.tripmanagementsystem.Repository.TripRepository;
import com.helloworld.tripmanagementsystem.Repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    VehicleRepository vRepo;
    DriverRepository dRepo;
    TripRepository tRepo;
    TripMapper tMap;

    public TripService(TripRepository tRepo, TripMapper tMap, VehicleRepository vRepo,DriverRepository dRepo){
        this.tRepo=tRepo;
        this.tMap=tMap;
        this.vRepo=vRepo;
        this.dRepo=dRepo;


    } // why this constructor

    //Get Individual Trip Details
    public ResponseEntity<TripDTO> getIndividualTripDetails(int id){
        Optional<TripEntity> trip=tRepo.findById(id);
        if(trip.isPresent()){
            TripDTO dto=tMap.toTripDTO(trip.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    //Get All Vehicle Details
    public List<TripDTO> getAllTripDetails(){
        return tRepo.findAll()
                .stream()
                .map(tMap::toTripDTO)
                .toList();

    }

    //Create Trip Details
    public TripDTO  createTripDetails(TripDTO  dto){
        TripEntity entity = tMap.toTripEntity(dto);

        System.out.println("DTO Driver: " + dto.getDriverName());
        System.out.println("DTO Vehicle: " + dto.getVehicleType());
        DriverEntity driver = dRepo.findByDriverName(dto.getDriverName());
        VehicleEntity vehicle = vRepo.findByVehicleType(dto.getVehicleType());

        System.out.println("DB Driver: " + driver);
        System.out.println("DB Vehicle: " + vehicle);
        entity.setDriver(driver);
        entity.setVehicle(vehicle);

        TripEntity saved = tRepo.save(entity);

        return tMap.toTripDTO(saved);
    }

    //Update Trip Details
    public TripDTO updateTripDetails(int id, TripDTO dto){
        TripEntity entity = tRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id is not Available"));
        entity.setBookedBy(dto.getBookedBy());
        entity.setBookedDate(dto.getBookedDate());
        entity.setBookedOn(dto.getBookedOn());
        entity.setDuration(dto.getDuration());
        entity.setTotalTripDistance(dto.getTotalTripDistance());
        entity.setUpdatedDate(LocalDate.now());

        TripEntity saved=tRepo.save(entity);
        return tMap.toTripDTO(saved);
    }

    //Soft Delete By Id For Trip
    public String softDeleteTripDetails(int id){
        TripEntity entity=tRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User is not Find"));
        entity.setSoftDelete(false);
        tRepo.save(entity);
        return ("Successfully Soft Deleted");


    }

    //Delete the Trip Details
    public String deleteTripDetails(int id){

        TripEntity vehicle = tRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));

        tRepo.delete(vehicle);

        return "Vehicle Deleted Successfully";
    }








}
