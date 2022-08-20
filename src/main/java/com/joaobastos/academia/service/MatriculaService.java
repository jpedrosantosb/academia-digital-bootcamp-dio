package com.joaobastos.academia.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaobastos.academia.entity.Matricula;
import com.joaobastos.academia.repository.MatriculaRepository;
import com.joaobastos.academia.service.exceptions.DatabaseException;
import com.joaobastos.academia.service.exceptions.ResourceNotFoundException;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository matriculaRepository;

	public List<Matricula> findAll() {
		return matriculaRepository.findAll();
	}

	public Matricula findById(Long id) {
		Optional<Matricula> obj = matriculaRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Matricula insert(Matricula obj) {
		return matriculaRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			matriculaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Matricula update(Long id, Matricula obj) {
		try {
			Matricula entity = matriculaRepository.getOne(id);
			updateData(entity, obj);
			return matriculaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Matricula entity, Matricula obj) {
		entity.setAluno(obj.getAluno());
	}
}
