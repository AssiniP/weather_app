package com.miApp.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationDataRepository extends JpaRepository<LocationData, Long> {

	LocationData findLocationDataByNameAndProvince(String name, String province);
}

