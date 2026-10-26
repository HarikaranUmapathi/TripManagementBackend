package com.helloworld.tripmanagementsystem.Repository;

import com.helloworld.tripmanagementsystem.Entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<TripEntity,Integer> {
}
