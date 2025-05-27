package com.perrito.hemolink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrito.hemolink.service.HemocentroService;

@RestController
@RequestMapping("/api/hemocentro")
public class HemocentroController {
	@Autowired
	private HemocentroService hemocentroService;
	
	//public List
}
