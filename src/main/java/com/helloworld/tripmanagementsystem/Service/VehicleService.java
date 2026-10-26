package com.helloworld.tripmanagementsystem.Service;

import com.helloworld.tripmanagementsystem.DTO.TripDTO;
import com.helloworld.tripmanagementsystem.DTO.VehicleDTO;
import com.helloworld.tripmanagementsystem.DTO.VehicleDropDownDTO;
import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import com.helloworld.tripmanagementsystem.Exception.ResourceNotFoundException;
import com.helloworld.tripmanagementsystem.Mapper.VehicleMapper;
import com.helloworld.tripmanagementsystem.Repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleService {

    VehicleRepository vRepo;
    VehicleMapper vMap;

    public VehicleService(VehicleRepository vRepo,VehicleMapper vMap){
        this.vRepo=vRepo;
        this.vMap=vMap;
    }
    //Get All Vehicle Details
    public List<VehicleDTO> getAllVehicleDetails(){
        return vRepo.findAll()
                .stream()
                .map(vMap::toVehicleDTO)
                .toList();

    }

    //Get Individual Vehicle Details
    public ResponseEntity<VehicleDTO> getIndividualVehicleDetails(int id){
        Optional<VehicleEntity> trip=vRepo.findById(id);
        if(trip.isPresent()){
            VehicleDTO dto=vMap.toVehicleDTO(trip.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    //Get All Vehicle ID Alone
    public List<VehicleDropDownDTO> getAllVehicleId(){
        List<VehicleEntity> vehicleDetails=vRepo.findAll();
        return getVehicleDropDown(vehicleDetails);
    }

    public List<VehicleDropDownDTO> getVehicleDropDown(List<VehicleEntity> vehicle){

        List<VehicleDropDownDTO> vehicleDropDownList=new ArrayList<>();
        for(VehicleEntity vehicleDetails:vehicle){
            VehicleDropDownDTO vehicleDropDownDTO=new VehicleDropDownDTO();
            vehicleDropDownDTO.setVehicleId(vehicleDetails.getVehicleId());
            vehicleDropDownDTO.setVehicleRegistration(vehicleDetails.getRegistrationNumber());
            vehicleDropDownList.add(vehicleDropDownDTO);
        }

        return vehicleDropDownList;
    }

    //Get All Vehicle Type Alone
    public List<String> getAllVehicleType(){
        List<String> VehicleType=vRepo.findAllVehicleType();
        return VehicleType;
    }

    //Create Vehicle Details
    public VehicleDTO  createVehicleDetails(VehicleDTO  dto){
        System.out.println(dto);
        VehicleEntity entity=vMap.toVehicleEntity(dto);
        VehicleEntity saved=vRepo.save(entity);
        return vMap.toVehicleDTO(saved);
    }
    //Update Vehicle Details
    public VehicleDTO updateVehicleDetails(int id, VehicleDTO dto) {
        VehicleEntity entity = vRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id is not Available"));
        entity.setVehicleType(dto.getVehicleType());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setInsured(dto.getInsured());
        VehicleEntity saved=vRepo.save(entity);
        return vMap.toVehicleDTO(saved);

    }

    //Delete the Vehicle Details
    public String deleteVehicleDetails(int id){

        VehicleEntity vehicle = vRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));

        vRepo.delete(vehicle);

        return "Vehicle Deleted Successfully";
    }
}
