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
	
	Estagiario getEstagiarioByPessoaCpf(String cpf);
			
	Estagiario getEstagiarioByPessoa(Long idPessoa);
	
	Servidor getServidorByPessoa(Long idPessoa);
	
	Pessoa getPessoaLogada(String cpf);
	
}
