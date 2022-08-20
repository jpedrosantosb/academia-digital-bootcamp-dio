package com.joaobastos.academia.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaobastos.academia.entity.AvaliacaoFisica;
import com.joaobastos.academia.repository.AvaliacaoFisicaRepository;
import com.joaobastos.academia.service.exceptions.DatabaseException;
import com.joaobastos.academia.service.exceptions.ResourceNotFoundException;

@Service
public class AvaliacaoFisicaService {

	@Autowired
	private AvaliacaoFisicaRepository avaliacaoRepository;

	public List<AvaliacaoFisica> findAll() {
		return avaliacaoRepository.findAll();
	}

	public AvaliacaoFisica findById(Long id) {
		Optional<AvaliacaoFisica> obj = avaliacaoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public AvaliacaoFisica insert(AvaliacaoFisica obj) {
		return avaliacaoRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			avaliacaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public AvaliacaoFisica update(Long id, AvaliacaoFisica obj) {
		try {
			AvaliacaoFisica entity = avaliacaoRepository.getOne(id);
			updateData(entity, obj);
			return avaliacaoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(AvaliacaoFisica entity, AvaliacaoFisica obj) {
		entity.setAluno(obj.getAluno());
	}
}
