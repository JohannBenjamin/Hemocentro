package com.perrito.hemolink.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.model.entity.Hemocentro;
import com.perrito.hemolink.service.HemocentroService;

@RestController
@RequestMapping("/api/hemocentro")
public class HemocentroController {
	@Autowired
	private HemocentroService hemocentroService;
	
	@GetMapping
	public List<Hemocentro> getAllHemocentro() {
		return hemocentroService.getAllHemocentro();
	}
		
	@GetMapping("/{codigo}")
	public ResponseEntity<Hemocentro> getHemocentroByCodigo(@PathVariable int codigo) {
		Hemocentro hemocentro = hemocentroService.getHemocentroById(codigo);
		if (hemocentro != null) {
			return new ResponseEntity<>(hemocentro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{data}")
	public List<Hemocentro> getHemocentroByData(@PathVariable Date data) {
        return hemocentroService.getHemocentroByData(data);
    }
		
	@GetMapping("/{tipo}")
	public ResponseEntity<Hemocentro> getHemocentroByTipoDesc(@PathVariable String tipo) {
		Hemocentro hemocentro = hemocentroService.getHemocentroByTipoDesc(tipo);
		if (hemocentro != null) {
			return new ResponseEntity<>(hemocentro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
}
