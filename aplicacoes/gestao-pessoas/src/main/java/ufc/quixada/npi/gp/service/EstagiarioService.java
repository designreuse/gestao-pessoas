package ufc.quixada.npi.gp.service;

import java.util.List;

import ufc.quixada.npi.gp.model.Estagiario;
import br.ufc.quixada.npi.service.GenericService;

public interface EstagiarioService extends GenericService<Estagiario>{

	Estagiario getEstagiarioByPessoaId(Long id);

	Estagiario getEstagiarioByCpf(String cpf);
	
	Estagiario getEstagiarioPesssoa(String cpf, String senha);
	
	List<Estagiario> getEstagiarioTurma(Long id);
	
	boolean possuiTurma(String cpf);
	
}
