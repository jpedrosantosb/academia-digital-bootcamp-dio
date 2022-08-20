package com.joaobastos.academia.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaobastos.academia.entity.Aluno;
import com.joaobastos.academia.entity.AvaliacaoFisica;
import com.joaobastos.academia.entity.Matricula;
import com.joaobastos.academia.repository.AlunoRepository;
import com.joaobastos.academia.repository.AvaliacaoFisicaRepository;
import com.joaobastos.academia.repository.MatriculaRepository;

@Configuration
public class cfg implements CommandLineRunner {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AvaliacaoFisicaRepository avaliacaoRepository;

	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
	
		Aluno aluno1 = new Aluno(null, "João Pedro", "000111222-33", LocalDate.now());
		Aluno aluno2 = new Aluno(null, "Alan Santana", "999888777-66", LocalDate.now());
		
		//alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));
		
		AvaliacaoFisica af1 = new AvaliacaoFisica(null, aluno1, 90.00, 1.85);
		AvaliacaoFisica af2 = new AvaliacaoFisica(null, aluno2, 95.00, 1.60);
		AvaliacaoFisica af3 = new AvaliacaoFisica(null, aluno2, 90.00, 1.60);
		
		//avaliacaoRepository.saveAll(Arrays.asList(af1, af2, af3));

		
		Matricula matricula1 = new Matricula(null, aluno1, LocalDateTime.now());
		Matricula matricula2 = new Matricula(null, aluno2, LocalDateTime.now());
		
		matriculaRepository.saveAll(Arrays.asList(matricula1, matricula2));
		//alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));
		avaliacaoRepository.saveAll(Arrays.asList(af1, af2, af3));

		
	}
	
}
