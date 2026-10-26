package com.helloworld.tripmanagementsystem.Repository;

import com.helloworld.tripmanagementsystem.Entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<DriverEntity,Integer> {

    @Query("SELECT d.driverName FROM DriverEntity d")
    List<String> findByDriverNames();
    DriverEntity findByDriverName(String driverName);



}


