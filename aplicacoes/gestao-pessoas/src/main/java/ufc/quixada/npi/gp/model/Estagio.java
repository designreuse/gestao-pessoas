package ufc.quixada.npi.gp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ufc.quixada.npi.gp.model.enums.Resultado;
import ufc.quixada.npi.gp.model.enums.Situacao;
import ufc.quixada.npi.gp.model.enums.TipoEstagio;

@Entity
@IdClass(TurmaEstagiarioId.class)
public class Estagio {
	
	private String local;
	
	@Enumerated(EnumType.STRING)
	private TipoEstagio tipoEstagio;
	
	@Id
	@ManyToOne
	@JoinColumn(name="turma_id")
	private Turma turma; 
	
	@Id
	@ManyToOne
	@JoinColumn(name="estagiario_id")
	private Estagiario estagiario;

	
	@OneToMany
	private List<Submissao> submissoes;
	
	@OneToMany
	private List<Frequencia> frequencias;
	
	@OneToOne
	private AvaliacaoRendimento avaliacaoRendimento;
	
	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	private double nota;
	
	@Lob
	private String comentarioSituacao;
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public TipoEstagio getTipoEstagio() {
		return tipoEstagio;
	}

	public void setEstagio(TipoEstagio tipoEstagio) {
		this.tipoEstagio = tipoEstagio;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public List<Submissao> getSubmissoes() {
		return submissoes;
	}
	
	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public AvaliacaoRendimento getAvaliacaoRendimento() {
		return avaliacaoRendimento;
	}

	public void setAvaliacaoRendimento(AvaliacaoRendimento avaliacaoRendimento) {
		this.avaliacaoRendimento = avaliacaoRendimento;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public void setTipoEstagio(TipoEstagio tipoEstagio) {
		this.tipoEstagio = tipoEstagio;
	}

	public String getComentarioSituacao() {
		return comentarioSituacao;
	}

	public void setComentarioSituacao(String comentarioSituacao) {
		this.comentarioSituacao = comentarioSituacao;
	}
	
	
}
