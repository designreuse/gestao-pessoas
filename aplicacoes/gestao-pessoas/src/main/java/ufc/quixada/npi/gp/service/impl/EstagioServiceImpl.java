package ufc.quixada.npi.gp.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.repository.GenericRepository;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;
import ufc.quixada.npi.gp.model.AvaliacaoRendimento;
import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Estagio;
import ufc.quixada.npi.gp.model.Folga;
import ufc.quixada.npi.gp.model.Frequencia;
import ufc.quixada.npi.gp.model.Submissao;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.model.enums.StatusEntrega;
import ufc.quixada.npi.gp.model.enums.StatusFrequencia;
import ufc.quixada.npi.gp.model.enums.TipoFrequencia;
import ufc.quixada.npi.gp.repository.FrequenciaRepository;
import ufc.quixada.npi.gp.service.DadoConsolidado;
import ufc.quixada.npi.gp.service.EstagioService;
import ufc.quixada.npi.gp.service.FolgaService;
import ufc.quixada.npi.gp.utils.UtilGestao;
@Named
public class EstagioServiceImpl implements EstagioService {
	
	@Inject
	private GenericRepository<Submissao> submissaoRepository;
	
	@Inject
	private FrequenciaRepository frequenciaRepository;
		
	@Inject
	private FolgaService folgaService;
	
	@Inject
	private GenericRepository<Estagio> estagioRepository;
	
	@Inject 
	private GenericRepository<AvaliacaoRendimento> avaliacaoRendimentoRepository;
	
	
	
	// INICIO FREQUENCIA

	@Transactional
	public List<Frequencia> getFrequenciasByTurmaIdAndData(Date data, Long idTurma) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", data);
		params.put("turma", idTurma);
		
		@SuppressWarnings("unchecked")
		List<Frequencia> frequencias = frequenciaRepository.find(QueryType.JPQL, "select f.id, f.estagiario.nomeCompleto, f.observacao, f.statusFrequencia, f.tipoFrequencia, f.horario from Frequencia f join f.turma t where t.id = :turma and f.data = :data", params);
		return frequencias;
	}

	@Transactional
	public List<Frequencia> getFrequenciaTurma(Turma turma) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("turma", turma.getId());
		
		@SuppressWarnings("unchecked")
		List<Frequencia> frequencias = frequenciaRepository.find(QueryType.JPQL, "select distinct f from Frequencia f join f.turma t where t.id = :turma", params);

		return frequencias;
	}

	
	@Transactional
	public void atualizarStatus() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusAtualizado", StatusFrequencia.FALTA);
		
		frequenciaRepository.updateStatus("update Frequencia f set statusFrequencia ='FALTA' where f.data = CURRENT_DATE and f.statusFrequencia = 'AGUARDO' ", params);
	}

	@Override
	public Frequencia getFrequenciaDeHojeByEstagiarioId(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		Frequencia frequencia = (Frequencia) frequenciaRepository.findFirst(QueryType.JPQL, "select f from Frequencia f where f.data = CURRENT_DATE and f.estagiario.id = :id", params);

		return frequencia;
	}

	@Override
	public List<Frequencia> getFrequenciasByEstagiarioId(Long idEstagiario, Long idTurma) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idEstagiario", idEstagiario);
		params.put("idTurma", idTurma);
		
		@SuppressWarnings("unchecked")
		List<Frequencia> frequencias = frequenciaRepository.find(QueryType.JPQL, "select f from Frequencia f where f.estagiario.id = :idEstagiario and f.turma.id = :idTurma ORDER BY data ASC", params);

		return frequencias;
	}

	public DadoConsolidado calcularDadosConsolidados(List<Frequencia> frequencia) {
		int faltas = frequenciaFalta(frequencia);
		int diasTrabalhados = frequenciaDiasTrabalhados(frequencia);
		double porcentagemFrequencia = frequenciaPorcentagem(diasTrabalhados, faltas);
		DadoConsolidado dadosConsolidados = new DadoConsolidado(faltas, diasTrabalhados, porcentagemFrequencia);
		return dadosConsolidados;
	}

	private int frequenciaFalta(List<Frequencia> frequencia) {
		int faltas = 0;

		for (Frequencia frequenciaFaltas : frequencia) {
			if (frequenciaFaltas.getStatusFrequencia() != null && frequenciaFaltas.getStatusFrequencia().equals(StatusFrequencia.FALTA)) {
				faltas++;
			}
		}
		return faltas;
	}

	private int frequenciaDiasTrabalhados(List<Frequencia> frequencia) {
		int diasTrabalhados = 0;

		for (Frequencia frequenciaFaltas : frequencia) {
			if (frequenciaFaltas.getStatusFrequencia() != null &&  frequenciaFaltas.getStatusFrequencia().equals(StatusFrequencia.PRESENTE)) {
				diasTrabalhados++;
			}
		}

		return diasTrabalhados;
	}

	private int frequenciaPorcentagem(int diasTrabalhados, int faltas) {
		if(diasTrabalhados != 0) {
			return (diasTrabalhados * 100) / (diasTrabalhados + faltas);		
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean liberarPreseca(Turma turma ) {
		List<Folga> folgas = folgaService.find(Folga.class);
		
		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();

		for (Folga folga : folgas) {
			dataDosFeriados.add(new LocalDate(folga.getData()));
		}

		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(dataDosFeriados);

		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("NPI", calendarioDeFeriados);
		DateCalculator<LocalDate> calendarioDeFeriadosNPI = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("NPI", HolidayHandlerType.FORWARD);

		LocalDate dia = new LocalDate();

		if (!calendarioDeFeriadosNPI.isNonWorkingDay(dia)) {
			if(UtilGestao.hojeEDiaDeTrabahoDaTurma(turma.getHorarios()) && UtilGestao.isHoraPermitida(turma.getHorarios())){
				return true;
			}
		}

		return false;
	}
	@Override
	public List<Frequencia> gerarFrequencia(Turma turma, Estagiario estagiario) {

		LocalDate inicioPeriodoTemporario = new LocalDate(turma.getInicio());
		LocalDate fimPeriodo = new LocalDate(new Date());

		List<Folga> folgas = folgaService.getFolgasByAno(Calendar.getInstance().get(Calendar.YEAR));
		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();

		if (folgas != null) {
			for (Folga folga : folgas) {
				dataDosFeriados.add(new LocalDate(folga.getData()));
			}
		}

		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(dataDosFeriados);
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("NPI", calendarioDeFeriados);

		List<Frequencia> frequencias = new ArrayList<Frequencia>();
		
		while (!inicioPeriodoTemporario.isAfter(fimPeriodo)) {

			if (UtilGestao.isDiaDeTrabahoDaTurma(turma.getHorarios(), inicioPeriodoTemporario)) {
				Frequencia frequencia = getFrequenciaByDataByTurmaByEstagiario(inicioPeriodoTemporario.toDate(), turma.getId(), estagiario.getId());

				if(frequencia == null){
					frequencia = new Frequencia();
					frequencia.setTipoFrequencia(TipoFrequencia.NORMAL);
					frequencia.setData(inicioPeriodoTemporario.toDate());
				}

				frequencias.add(frequencia);
			}
			inicioPeriodoTemporario = inicioPeriodoTemporario.plusDays(1);
		}

		return frequencias;
	}
		
	public List<Estagiario> getEstagiariosSemFrequencia(Date data, Long idTurma){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", data);
		params.put("idTurma", idTurma);
		
		@SuppressWarnings("unchecked")
		List<Estagiario> frequencias = frequenciaRepository.find(QueryType.JPQL, "select e from Estagiario as e "
				+ "where  e.id in (select e.id from Estagiario as e, Frequencia as f  "
				+ "where f.turma.id = :idTurma and e.id = f.estagiario.id group by e.id) "
				+ "and e.id not in (select f.estagiario.id from Frequencia as f "
				+ "where f.turma.id = :idTurma and f.data = :data)", params);

		return frequencias;
	}
	
	@Override
	public Frequencia getFrequenciaByDataByTurmaByEstagiario(Date data, Long turma, Long estagiario) {
		return frequenciaRepository.findFrequenciaByDataByTurmaByEstagiario(data, turma, estagiario);
	}
		
	public List<Frequencia> frequenciaPendente(Turma turma, Estagiario estagiario){
		List<Frequencia> frequenciaTotal = gerarFrequencia(turma, estagiario);
		
		List<Frequencia> frequenciaPendentes = new ArrayList<Frequencia>() ;
		for (Frequencia frequencia : frequenciaTotal) {
			if(frequencia.getStatusFrequencia() == null){
				frequenciaPendentes.add(frequencia);
			}
		}
		return frequenciaPendentes;
	}
	
	// NOVOS METODOS
	public void submeterPlano(Submissao submissao){
		submissaoRepository.save(submissao);
	}
	public void submeterRelatorio(Submissao submissao){
		submissaoRepository.save(submissao);
	}
	public void editarPlano(Submissao submissao) throws Exception{
		if(StatusEntrega.SUBMETIDO.equals(submissao.getStatusEntrega()) || StatusEntrega.CORRECAO.equals(submissao.getStatusEntrega())){
			submissaoRepository.update(submissao);
		}else{
			throw new Exception();
		}
	}
	public void editarRelatorio(Submissao submissao) throws Exception{
		if(StatusEntrega.SUBMETIDO.equals(submissao.getStatusEntrega()) || StatusEntrega.CORRECAO.equals(submissao.getStatusEntrega())){
			submissaoRepository.update(submissao);
		}else{
			throw new Exception();
		}
	}
	public void realizarPresenca(Estagio estagio){
		
	}

	//Verificar consulta por tipo
	@Override
	public Submissao getSubmissaoByEstagio(Estagio estagio) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("estagio", estagio);
		
		Submissao submissao = (Submissao) submissaoRepository.find(QueryType.JPQL, "select s from Submissao join Estagio e where e.turma.id = estagio.turma.id and e.estagiario.id = estagio.estagiario", params);

		return submissao;

	}

	@Override
	public void avaliarSubmissao(Submissao submissao) {
		submissaoRepository.update(submissao);		
	}
	
	@Override
	public void realizarAvaliacaoRendimento(AvaliacaoRendimento avaliacaoRendimento) {
		avaliacaoRendimentoRepository.save(avaliacaoRendimento);	
	}

	@Override
	public List<Estagiario> getAniversariantesMesByTurmaId(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		List<Estagiario> estagiarios = estagioRepository.find(QueryType.JPQL, "select e from Estagiario e join e.turmas t where t.id = :id and month(e.dataNascimento) = month(current_date())", params);

		return estagiarios;
	}
	
	// FIM NOVOS METODOS 

}
