package ufc.quixada.npi.gp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import ufc.quixada.npi.gp.model.Evento;
import ufc.quixada.npi.gp.model.Horario;
import ufc.quixada.npi.gp.model.Submissao;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.service.TurmaService;

@Named
public class TurmaServiceImpl extends GenericServiceImpl<Turma> implements TurmaService {
	
	@Autowired
	private GenericRepository<Submissao> submissaoRepository;
	
	@Inject
	private GenericRepository<Evento> eventoRepository;
	
	@Inject
	private GenericRepository<Horario> horarioRepository;

	
	//ajeitar consulta
	@Override
	public List<Turma> getTurmasBySupervisorOrOrientador(Long idSupervisor, Long idOrientador) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idSupervisor", idSupervisor);
		@SuppressWarnings("unchecked")
		List<Turma> turmas = find(QueryType.JPQL,"select t from Turma t where t.supervisor.id = :idSupervisor", params);

		return turmas;
	}
		
	@Override
	public List<Evento> getEventosByTurma(Long idTurma) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idTurma", idTurma);
		
		@SuppressWarnings("unchecked")
		List<Evento> eventos = eventoRepository.find(QueryType.JPQL, "select e from Evento e where e.turma.id = :idTurma ORDER BY e.id DESC", params);

		return eventos;
	}
	
	@Override
	public Horario getHorarioTurmaById(Long idHorario, Long idTurma) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idHorario", idHorario);
		params.put("idTurma", idTurma);
		
		Horario horario =  (Horario) horarioRepository.findFirst(QueryType.JPQL, "select h from Horario h where h.turma.id = :idTurma and h.id = :idHorario", params);

		return horario;
	}

	@Override
	public Turma getTurma(long idTurma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turma> getAllTurmas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void adicionarTurma(Turma turma) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editarTurma(Turma turma) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerTurma(long idTurma) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionarEvento(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editarEvento(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerEvento(long idEvento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Evento getEvento(long idEvento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> getAllEventosByTurma(long idTurma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void adicionarHorario(Horario horario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerHorario(long idHorario) {
		// TODO Auto-generated method stub
		
	}

	
		
	
}
