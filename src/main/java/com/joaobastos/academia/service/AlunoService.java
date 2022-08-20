package com.joaobastos.academia.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaobastos.academia.entity.Aluno;
import com.joaobastos.academia.repository.AlunoRepository;
import com.joaobastos.academia.service.exceptions.DatabaseException;
import com.joaobastos.academia.service.exceptions.ResourceNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Aluno findById(Long id) {
		Optional<Aluno> obj = alunoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Aluno insert(Aluno obj) {
		return alunoRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			alunoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Aluno update(Long id, Aluno obj) {
		try {
			Aluno entity = alunoRepository.getOne(id);
			updateData(entity, obj);
			return alunoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Aluno entity, Aluno obj) {
		entity.setNome(obj.getNome());
	}

}
