package com.miApp.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miApp.models.entities.LocationData;

@Repository
public interface ILocationDataRepository extends JpaRepository<LocationData, Long> {

	LocationData findLocationDataByNameAndProvince(String name, String province);
}

