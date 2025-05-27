package com.perrito.hemolink.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrito.hemolink.model.entity.Hemocentro;
import com.perrito.hemolink.model.repository.HemocentroRepository;

@Service
public class HemocentroService {
	@Autowired
	private HemocentroRepository hemocentroRepository;
	
	//Listar todos os hemocentros
	public List<Hemocentro> getAllHemocentro() {
		return hemocentroRepository.findAll();
	}
	
	//Buscar por Id
	public Hemocentro getHemocentroById(int codigo) {
        Optional<Hemocentro> optionalHemocentro = hemocentroRepository.findById(codigo);
        return optionalHemocentro.orElse(null);
    }	
	
	//Buscar pela data (pegar os registros por um dia especifico)
	public List<Hemocentro> getHemocentroByData(Date data) {
        return hemocentroRepository.findByData(data);
    }
	
	//Busca o ultimo registro pelo tipo sanguineo (pra mostrar o nivel mais atual de sangue)
	public Hemocentro getHemocentroByTipoDesc(String tipo) {
		Optional<Hemocentro> optionalHemocentro = hemocentroRepository.findFirstByTipoOrderByDataDesc(tipo);
		return optionalHemocentro.orElse(null);
	}
	
}
