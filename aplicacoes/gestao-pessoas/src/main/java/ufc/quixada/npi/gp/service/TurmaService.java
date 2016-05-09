package ufc.quixada.npi.gp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.ufc.quixada.npi.service.GenericService;
import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Evento;
import ufc.quixada.npi.gp.model.Horario;
import ufc.quixada.npi.gp.model.Submissao;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.model.enums.StatusTurma;
import ufc.quixada.npi.gp.model.enums.Tipo;


public interface TurmaService extends GenericService<Turma> {

	List<Turma>  getTurmasBySupervisorOrOrientador(Long idSupervisor, Long idOrientador);

	Horario getHorarioTurmaById(Long idHorario, Long idTurma);
	
	List<Evento> getEventosByTurma(Long idTurma);
	
	//crud turma
	void adicionarTurma(Turma turma);
	void editarTurma(Turma turma);
	void removerTurma(long idTurma);
	Turma getTurma(long idTurma);
	List<Turma> getAllTurmas();
	
	//crud evento
	void adicionarEvento(Evento evento);
	void editarEvento(Evento evento);
	void removerEvento(long idEvento);
	Evento getEvento(long idEvento);
	List<Evento> getAllEventosByTurma(long idTurma);
	
	//save delete horario
	void adicionarHorario(Horario horario);
	void removerHorario(long idHorario);
	


}
