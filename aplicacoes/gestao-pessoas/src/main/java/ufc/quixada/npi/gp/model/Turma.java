package ufc.quixada.npi.gp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import ufc.quixada.npi.gp.model.enums.StatusTurma;
import ufc.quixada.npi.gp.model.enums.TipoTurma;

@Entity
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Servidor orientador;

	@OneToMany
	private List<Servidor> supervisores;

	@OneToMany
	@JoinColumn(name = "turma_id")
	List<Horario> horarios;

	@OneToMany(mappedBy = "turma")
	private List<Evento> eventos;

	@OneToMany(mappedBy = "turma")
	private List<Estagio> estagios;

	private String nome;

	@Column(nullable = false)
	@NotEmpty(message = "Informe o semestre.")
	private String semestre;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Informe a data incial.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date inicio;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Informe a data final.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date termino;

	@Enumerated(EnumType.STRING)
	private StatusTurma status;

	@Enumerated(EnumType.STRING)
	private TipoTurma tipoTurma;

	public List<Servidor> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(List<Servidor> supervisores) {
		this.supervisores = supervisores;
	}

	public Servidor getOrientador() {
		return orientador;
	}

	public void setOrientador(Servidor orientador) {
		this.orientador = orientador;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagio(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public StatusTurma getStatus() {
		return status;
	}

	public void setStatus(StatusTurma statusTurma) {
		this.status = statusTurma;
	}

	public TipoTurma getTipoTurma() {
		return tipoTurma;
	}

	public void setTipoTurma(TipoTurma tipoTurma) {
		this.tipoTurma = tipoTurma;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}
}
