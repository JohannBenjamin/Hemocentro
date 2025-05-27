package com.perrito.hemolink.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perrito.hemolink.model.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer>{

}
