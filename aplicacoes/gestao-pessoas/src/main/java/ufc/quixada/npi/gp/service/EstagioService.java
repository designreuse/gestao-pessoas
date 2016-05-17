package ufc.quixada.npi.gp.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ufc.quixada.npi.gp.model.AvaliacaoRendimento;
import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Estagio;
import ufc.quixada.npi.gp.model.Frequencia;
import ufc.quixada.npi.gp.model.Submissao;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.model.enums.TipoSubmissao;

public interface EstagioService {
	
	Submissao getSubmissaoByEstagioIdAndTipo(Long idEstagio, TipoSubmissao tipoSubmissao);
	
	void avaliarSubmissao(Submissao submissao);
	void realizarAvaliacaoRendimento(AvaliacaoRendimento avaliacaoRendimento);
	
	@Transactional
	void atualizarStatus();

	boolean liberarPreseca(Turma turma);
	
	boolean permitirPresenca(Estagio estagio);
	
	Frequencia getFrequenciaDeHojeByEstagiarioId(Long id);

	List<Frequencia> getFrequenciasByEstagiarioId(Long idEstagiario, Long idTurma);

	Frequencia getFrequenciaByDataByTurmaByEstagiario(Date data, Long turma, Long estagiario);

	List<Frequencia> getFrequenciasByTurmaIdAndData(Date data, Long idTurma);

	DadoConsolidado calcularDadosConsolidados(List<Frequencia> frequencia);

	List<Frequencia> gerarFrequencia(Turma turma, Estagiario estagiario);
	
	List<Estagiario> getEstagiariosSemFrequencia(Date data, Long idTurma);
	
	List<Frequencia> frequenciaPendente(Turma turma, Estagiario estagiario);
	
	void adicionarFrequencia(Frequencia frequencia);
	
	List<Estagiario> getAniversariantesMesByTurmaId(Long id);
	
	Estagio getEstagioByIdAndEstagiarioId(Long idEstagio, Long idEstagiario);
	
	Estagio getEstagioByIdAndEstagiarioCpf(Long idEstagio, String cpf);

	

}
