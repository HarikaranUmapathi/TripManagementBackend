package com.helloworld.tripmanagementsystem.Service;

import com.helloworld.tripmanagementsystem.DTO.DriverDTO;
import com.helloworld.tripmanagementsystem.Entity.DriverEntity;
import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import com.helloworld.tripmanagementsystem.Exception.ResourceNotFoundException;
import com.helloworld.tripmanagementsystem.Mapper.DriverMapper;
import com.helloworld.tripmanagementsystem.Repository.DriverRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    DriverRepository dRepo;
    DriverMapper dMap;

    public DriverService(DriverRepository dRepo, DriverMapper dMap){
        this.dRepo=dRepo;
        this.dMap=dMap;
    }

    //Get Individual Driver Details
    public ResponseEntity<DriverDTO> getIndividualDriverDetails(int id){
        Optional<DriverEntity> trip=dRepo.findById(id);
        if(trip.isPresent()){
            DriverDTO dto=dMap.toDriverDTO(trip.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    //Get All Driver Details
    public List<DriverDTO> getAllDriverDetails(){
        return dRepo.findAll()
                .stream()
                .map(dMap::toDriverDTO)
                .toList();

    }

    //Get Driver Names alone
    public List<String> getAllDriverNames(){
        List<String> DriverNames=dRepo.findByDriverNames();
        return DriverNames;
    }

    //Create Driver Details
    public DriverDTO  createDriverDetails(DriverDTO  dto){
        DriverEntity entity=dMap.toDriverEntity(dto);
        DriverEntity saved=dRepo.save(entity);
        return dMap.toDriverDTO(saved);
    }

    //Update Driver Details
    public DriverDTO updateDriverDetails(int id, DriverDTO dto){
        DriverEntity entity = dRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id is not Available"));
        entity.setDriverName(dto.getDriverName());
        entity.setExperience(dto.getExperience());
        entity.setContactNumber(dto.getContactNumber());

        DriverEntity saved=dRepo.save(entity);
        return dMap.toDriverDTO(saved);
    }

    //Delete the Driver Details
    public String deleteDriverDetails(int id){

        DriverEntity vehicle = dRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));

        dRepo.delete(vehicle);

        return "Vehicle Deleted Successfully";
    }
}
