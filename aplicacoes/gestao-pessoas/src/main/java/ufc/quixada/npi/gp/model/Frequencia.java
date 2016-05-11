package ufc.quixada.npi.gp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ufc.quixada.npi.gp.model.enums.StatusFrequencia;
import ufc.quixada.npi.gp.model.enums.TipoFrequencia;

@Entity
public class Frequencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Turma turma;

	@ManyToOne
	private Estagio estagio;

	@Enumerated(EnumType.STRING)
	private StatusFrequencia status;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Temporal(TemporalType.TIME)
	private Date horario;

	@Enumerated(EnumType.STRING)
	private TipoFrequencia tipo;

	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusFrequencia getStatusFrequencia() {
		return status;
	}

	public void setStatusFrequencia(StatusFrequencia statusFrequencia) {
		this.status = statusFrequencia;
	}

	public Date getData() {
		return data;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date tempo) {
		this.horario = tempo;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoFrequencia getTipoFrequencia() {
		return tipo;
	}

	public void setTipoFrequencia(TipoFrequencia tipoFrequencia) {
		this.tipo = tipoFrequencia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public Estagio getEstagio() {
		return estagio;
	}

}
