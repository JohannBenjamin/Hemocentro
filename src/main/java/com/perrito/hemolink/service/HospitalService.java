package com.perrito.hemolink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrito.hemolink.model.entity.Hospital;
import com.perrito.hemolink.model.repository.HospitalRepository;

@Service
public class HospitalService {
	@Autowired
	private HospitalRepository hospitalRepository;
	
	public List<Hospital> getAllHospital() {
		return hospitalRepository.findAll();
	}
	
	public Hospital getHospitalById(int codigo) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(codigo);
        return optionalHospital.orElse(null);
    }
	
}