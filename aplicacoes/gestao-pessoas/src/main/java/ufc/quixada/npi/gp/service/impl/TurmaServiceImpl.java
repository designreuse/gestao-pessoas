package ufc.quixada.npi.gp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.repository.GenericRepository;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import ufc.quixada.npi.gp.model.Evento;
import ufc.quixada.npi.gp.model.Horario;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.service.TurmaService;

@Named
public class TurmaServiceImpl implements TurmaService{
	

	@Inject
	private GenericRepository<Evento> eventoRepository;
	
	@Inject
	private GenericRepository<Horario> horarioRepository;
	
	@Inject
	private GenericRepository<Turma> turmaRepository;
	

	
	//ajeitar consulta quando tiver orientador no modelo
	@Override
	public List<Turma> getTurmasBySupervisorOrOrientador(Long idSupervisor, Long idOrientador) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idSupervisor", idSupervisor);
		@SuppressWarnings("unchecked")
		List<Turma> turmas = turmaRepository.find(QueryType.JPQL,"select t from Turma t where t.supervisor.id = :idSupervisor", params);

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
	public Turma getTurma(Long idTurma) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idTurma", idTurma);
		
		Turma turma =  (Turma) turmaRepository.findFirst(QueryType.JPQL, "select t from Turma t where t.id = :idTurma", params);

		return turma;
	}

	@Override
	public List<Turma> getAllTurmas() {
		List <Turma> turmas = turmaRepository.find(Turma.class);

		return turmas;
	}

	@Override
	public void adicionarTurma(Turma turma) {
		turmaRepository.save(turma);
	}

	@Override
	public void editarTurma(Turma turma) {
		turmaRepository.update(turma);
	}

	@Override
	public void removerTurma(Long idTurma) {
		turmaRepository.delete(turmaRepository.find(Turma.class, idTurma));
	}

	@Override
	public void adicionarEvento(Evento evento) {
		eventoRepository.save(evento);
	}

	@Override
	public void editarEvento(Evento evento) {
		eventoRepository.update(evento);
	}

	@Override
	public void removerEvento(Long idEvento) {
		eventoRepository.delete(eventoRepository.find(Evento.class, idEvento));
	}

	@Override
	public Evento getEvento(Long idEvento) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idEvento", idEvento);
		
		Evento evento =  (Evento) eventoRepository.findFirst(QueryType.JPQL, "select e from Evento e where e.id = :idEvento", params);

		return evento;
	}

	@Override
	public List<Evento> getAllEventosByTurma(Long idTurma) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idTurma", idTurma);
		
		List<Evento> eventos =  eventoRepository.find(QueryType.JPQL, "select e from Evento e where e.turma.id = :idTurma", params);

		return eventos;
	}

	@Override
	public void adicionarHorario(Horario horario) {
		horarioRepository.save(horario);
	}

	@Override
	public void removerHorario(Long idHorario) {
		horarioRepository.delete(horarioRepository.find(Horario.class, idHorario));
	}

	
		
	
}
