package ufc.quixada.npi.gp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ufc.quixada.npi.gp.model.enums.Resultado;
import ufc.quixada.npi.gp.model.enums.Situacao;
import ufc.quixada.npi.gp.model.enums.TipoEstagio;

@Entity
public class Estagio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private AvaliacaoRendimento avaliacaoRendimento;

	@OneToMany (mappedBy = "estagio")
	private List<Submissao> submissoes;
	
	@OneToMany (mappedBy = "estagio")
	private List<Frequencia> frequencias;
	
	@ManyToOne
	private Turma turma; 
	
	@ManyToOne
	private Estagiario estagiario;

	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@Enumerated(EnumType.STRING)
	private TipoEstagio estagio;
	
	private String comentarioSituacao;
	
	private double nota;
		
	public TipoEstagio getEstagio() {
		return estagio;
	}

	public void setEstagio(TipoEstagio tipoEstagio) {
		this.estagio = tipoEstagio;
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

	public String getComentarioSituacao() {
		return comentarioSituacao;
	}

	public void setComentarioSituacao(String comentarioSituacao) {
		this.comentarioSituacao = comentarioSituacao;
	}
	
	
}
