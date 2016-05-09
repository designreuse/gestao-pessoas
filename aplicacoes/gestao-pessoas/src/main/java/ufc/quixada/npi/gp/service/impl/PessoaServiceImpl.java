package ufc.quixada.npi.gp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Papel;
import ufc.quixada.npi.gp.model.Pessoa;
import ufc.quixada.npi.gp.model.Servidor;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.repository.FrequenciaRepository;
import ufc.quixada.npi.gp.service.FolgaService;
import ufc.quixada.npi.gp.service.PessoaService;

@Named
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa> implements PessoaService {

	@Inject
	private GenericRepository<Pessoa> pessoaRepository;
	
	@Inject
	private GenericRepository<Papel> papelRepository;
	
	@Inject
	private GenericRepository<Servidor> servidorRepository;
	
	@Inject
	private GenericRepository<Estagiario> estagiarioRepository;
	
	@Inject
	private FrequenciaRepository frequenciaRepository;
	
	@Inject
	private FolgaService folgaService;

	@Override
	public Pessoa getPessoaByCpf(String cpf) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);
		return (Pessoa) pessoaRepository.findFirst(QueryType.JPQL, "from Pessoa where cpf = :cpf", params);
	}

	@Override
	public Pessoa getPessoaById(Long id) {
		return pessoaRepository.find(Pessoa.class, id);
	}

	@Override
	public List<Papel> getPapeis(String cpf) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);
		return papelRepository.find(QueryType.JPQL, "select p.papeis FROM Pessoa p WHERE p.cpf = :cpf", params);
	}

	@Override
	public boolean isPessoa(String cpf) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);
		
		Pessoa pessoa = (Pessoa) pessoaRepository.findFirst(QueryType.JPQL, "from Pessoa where cpf = :cpf", params);

		if(pessoa !=null){
			return true;
		}
	
		return false;
	}	
	
	@Override
	public boolean isServidor(String cpf) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);
		
		Servidor servidor = (Servidor) servidorRepository.findFirst(QueryType.JPQL, "select s FROM Servidor s WHERE s.pessoa.cpf = :cpf", params);

		if(!servidor.equals(null)){
			return true;
		}

		return false;
	}

	@Override
	public boolean isEstagiario(String cpf) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);
		
		Estagiario estagiario = (Estagiario) estagiarioRepository.findFirst(QueryType.JPQL, "select e FROM Estagiario e WHERE e.pessoa.cpf = :cpf", params);

		if( estagiario != null ){
			return true;
		}

		return false;
	}
	
	@Override
	public Estagiario getEstagiarioByPessoaId(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		Estagiario estagiario = (Estagiario) estagiarioRepository.findFirst(QueryType.JPQL, "select e from Estagiario e where e.pessoa.id = :id", params);
		
		return estagiario;
	}
	@Override
	public Pessoa getPessoaByEstagiarioId(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		Pessoa estagiario = (Pessoa) estagiarioRepository.findFirst(QueryType.JPQL, "select p from Pessoa p where p.id = :id", params);
		
		return estagiario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estagiario> getEstagiarioByTurmaId(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		List<Estagiario> estagiarios = estagiarioRepository.find(QueryType.JPQL, "select e from Estagiario e join e.turmas t where t.id = :id", params);

		return estagiarios;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estagiario> getEstagiarioByNotTurmaIdOrSemTurma(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		List<Estagiario> estagiarios = estagiarioRepository.find(QueryType.JPQL, "select e from Estagiario e where :id not member of e.turmas or e.turmas IS EMPTY", params);

		return estagiarios;
	}
	
	@Override
	public Estagiario getEstagiarioByPessoaCpf(String cpf) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);

		Estagiario estagiario = (Estagiario) estagiarioRepository.findFirst(QueryType.JPQL, "select e from Estagiario e where e.pessoa.cpf = :cpf", params);
		
		return estagiario;
	}

	@Override
	public boolean possuiTurmaAtiva(String cpf) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);

		Turma turma = (Turma) estagiarioRepository.findFirst(QueryType.JPQL, "select t from Estagiario e join e.turmas t where e.pessoa.cpf = :cpf and t IS NOT NULL", params);

		if (turma != null) {
			return true;
		}

		return false;
	}
	@Override
	public List<Estagiario> getAniversariantesMesByTurmaId(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		List<Estagiario> estagiarios = estagiarioRepository.find(QueryType.JPQL, "select e from Estagiario e join e.turmas t where t.id = :id and month(e.dataNascimento) = month(current_date())", params);

		return estagiarios;
	}

	public Papel getPapel(String papel) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("papel", papel);

		Papel papelPessoa = (Papel) papelRepository.findFirst(QueryType.JPQL, "from Papel where nome = :papel", params);

		return papelPessoa;
	}

	@Override
	public Estagiario getEstagiarioByPessoa(long idPessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Servidor getServidorByPessoa(long idPessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa getPessoaLogada(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

}
