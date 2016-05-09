package ufc.quixada.npi.gp.service;

import java.util.List;

import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Papel;
import ufc.quixada.npi.gp.model.Pessoa;
import ufc.quixada.npi.gp.model.Servidor;

public interface PessoaService {

	Pessoa getPessoaByCpf(String cpf);
	
	Pessoa getPessoaById(Long id);

	List<Papel> getPapeis(String cpf);

	boolean isServidor(String cpf);
	
	boolean isEstagiario(String cpf);

	boolean isPessoa(String cpf);
	
	Papel getPapel(String papel);
	
	Estagiario getEstagiarioByPessoaId(Long id);
	
	Pessoa getPessoaByEstagiarioId(Long id);

	Estagiario getEstagiarioByPessoaCpf(String cpf);
	
	List<Estagiario> getEstagiarioByTurmaId(Long id);
	
	List<Estagiario> getEstagiarioByNotTurmaIdOrSemTurma(Long id);
	
	List<Estagiario> getAniversariantesMesByTurmaId(Long id);
	
	boolean possuiTurmaAtiva(String cpf);
	
	Estagiario getEstagiarioByPessoa(long idPessoa);
	Servidor getServidorByPessoa(long idPessoa);
	Pessoa getPessoaLogada(String cpf);
	
}
