package com.perrito.hemolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.model.entity.Hospital;
import com.perrito.hemolink.service.HospitalService;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	
	@GetMapping
	public List<Hospital> getAllHospital() {
		return hospitalService.getAllHospital();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Hospital> getHospitalById(@PathVariable int codigo) {
        Hospital hospital = hospitalService.getHospitalById(codigo);
		if (hospital != null) {
			return new ResponseEntity<>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
}
