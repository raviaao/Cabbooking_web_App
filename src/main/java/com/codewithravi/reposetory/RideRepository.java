package com.codewithravi.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithravi.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Integer> {

}
