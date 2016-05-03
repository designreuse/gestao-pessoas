package ufc.quixada.npi.gp.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ufc.quixada.npi.gp.model.AvaliacaoRendimento;
import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Frequencia;
import ufc.quixada.npi.gp.model.Turma;

public interface EstagioService {
	
	List<AvaliacaoRendimento> getAvaliacaoBySupervisorId(Long idSupervisor);

	List<AvaliacaoRendimento> getAvaliacaoByEstagiarioId(Long idEstagiario);

	AvaliacaoRendimento getAvaliacaoEstagioById(Long idAvaliacao);

	List<AvaliacaoRendimento> getAvaliacoesEstagioByEstagiarioIdAndTurmaById(Long idEstagiario, Long idTurma);
	
	@Transactional
	void atualizarStatus();

	boolean liberarPreseca(Turma turma);
	
	Frequencia getFrequenciaDeHojeByEstagiarioId(Long id);

	List<Frequencia> getFrequenciasByEstagiarioId(Long idEstagiario, Long idTurma);

	Frequencia getFrequenciaByDataByTurmaByEstagiario(Date data, Long turma, Long estagiario);

	List<Frequencia> getFrequenciasByTurmaIdAndData(Date data, Long idTurma);

	DadoConsolidado calcularDadosConsolidados(List<Frequencia> frequencia);

	List<Frequencia> gerarFrequencia(Turma turma, Estagiario estagiario);
	
	List<Estagiario> getEstagiariosSemFrequencia(Date data, Long idTurma);
	
	List<Frequencia> frequenciaPendente(Turma turma, Estagiario estagiario);
	

}
