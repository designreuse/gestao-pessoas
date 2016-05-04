package ufc.quixada.npi.gp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ufc.quixada.npi.gp.model.enums.CaraterTreinamento;
import ufc.quixada.npi.gp.model.enums.Comprometimento;
import ufc.quixada.npi.gp.model.enums.CuidadoMateriais;
import ufc.quixada.npi.gp.model.enums.CumprimentoPrazos;
import ufc.quixada.npi.gp.model.enums.Disciplina;
import ufc.quixada.npi.gp.model.enums.Frequencia;
import ufc.quixada.npi.gp.model.enums.Iniciativa;
import ufc.quixada.npi.gp.model.enums.Permanencia;
import ufc.quixada.npi.gp.model.enums.QualidadeDeTrabalho;
import ufc.quixada.npi.gp.model.enums.QuantidadeDeTrabalho;
import ufc.quixada.npi.gp.model.enums.Relacionamento;
import ufc.quixada.npi.gp.model.enums.TrabalhoEmEquipe;

@Entity
public class AvaliacaoRendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double nota;
	
	private double notaSeminario;

	@Lob
	private String atividadeCurricular;
	
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Date dataTermino;
	
	@Temporal(TemporalType.DATE)
	private Date dataAvaliacao;
	
	@Lob
	private String objetivoEstagio;
	
	@Lob
	private String fatorAssiduidadeDisciplina;

	@Enumerated(EnumType.STRING)
	private Frequencia frequencia;
	
	@Enumerated(EnumType.STRING)
	private Iniciativa iniciativa;
	
	@Enumerated(EnumType.STRING)
	private Disciplina disciplina;
	
	@Enumerated(EnumType.STRING)
	private Comprometimento comprometimento;
	
	@Enumerated(EnumType.STRING)
	private CuidadoMateriais cuidadoMateriais;
	
	@Enumerated(EnumType.STRING)
	private Permanencia permanencia; 
	
	@Enumerated(EnumType.STRING)
	private QualidadeDeTrabalho qualidadeTrabalho;
	
	@Enumerated(EnumType.STRING)
	private QuantidadeDeTrabalho quantidadeTrabalho;
	
	@Enumerated(EnumType.STRING)
	private Relacionamento relacionamento;
	
	@Enumerated(EnumType.STRING)
	private TrabalhoEmEquipe trabalhoEquipe;
	
	@Enumerated(EnumType.STRING)
	private CumprimentoPrazos cumprimentoPrazos; 
	
	@Enumerated(EnumType.STRING)
	private CaraterTreinamento caraterTreinamento;
	
	@Lob
	private String comentarioAssuidadeDisciplina;
	
	@Lob
	private String comentarioIniciativaProdutividade;

	@Lob
	private String comentarioResponsabilidade;

	@Lob
	private String comentarioRelacionamento;
	
	@Lob
	private String comentarioSeminario;

	@ManyToOne
	@JoinColumn(name = "documento_id")
	private Documento documento;

	@Lob
	private String comentarioFinal;

	@ManyToOne
	@JoinColumn(name = "turma_id")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "supervisor_id")
	private Pessoa supervisor;

	@ManyToOne
	@JoinColumn(name = "estagiario_id")
	private Estagiario estagiario;
	
	private Boolean confirmado; 
	
	private Boolean assuidade;
	
	private Boolean fatorDisciplina;
	
	private Boolean capacidadeIniciativa;
	
	private Boolean produtividade;
	
	private Boolean responsabilidade;
	
	private Boolean outrosMotivos;
	
	@Lob
	private String especificacaoMotivo;
	
	@Lob
	private String comentarioOrientador;
	
	private Boolean necessidadeTreinamento;
	
	@Lob
	private String especificacaoTreinamento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getComentarioAssiduidadeDisciplina() {
		return comentarioAssuidadeDisciplina;
	}

	public void setComentarioAssiduidadeDisciplina(String comentarioAssiduidadeDisciplina) {
		this.comentarioAssuidadeDisciplina = comentarioAssiduidadeDisciplina;
	}

	public String getComentarioIniciativaProdutividade() {
		return comentarioIniciativaProdutividade;
	}

	public void setComentarioIniciativaProdutividade(String comentarioIniciativaProdutividade) {
		this.comentarioIniciativaProdutividade = comentarioIniciativaProdutividade;
	}

	public String getComentarioResponsabilidade() {
		return comentarioResponsabilidade;
	}

	public void setComentarioResponsabilidade(String comentarioResponsabilidade) {
		this.comentarioResponsabilidade = comentarioResponsabilidade;
	}

	public String getComentarioRelacionamento() {
		return comentarioRelacionamento;
	}

	public void setComentarioRelacionamento(String fatorRelacionamento) {
		this.comentarioRelacionamento = fatorRelacionamento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getComentarioFinal() {
		return comentarioFinal;
	}

	public void setComentario(String comentario) {
		this.comentarioFinal = comentario;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Pessoa getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Pessoa supervisor) {
		this.supervisor = supervisor;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}
	
	public double getNotaSeminario() {
		return notaSeminario;
	}

	public void setNotaSeminario(double notaSeminario) {
		this.notaSeminario = notaSeminario;
	}
	
	public String getComentarioSeminario() {
		return comentarioSeminario;
	}

	public void setComentarioSeminario(String comentarioSeminario) {
		this.comentarioSeminario = comentarioSeminario;
	}

	public String getComentarioAssuidadeDisciplina() {
		return comentarioAssuidadeDisciplina;
	}

	public void setComentarioAssuidadeDisciplina(String comentarioAssuidadeDisciplina) {
		this.comentarioAssuidadeDisciplina = comentarioAssuidadeDisciplina;
	}
	
	public Frequencia getFrequencia() {
		return frequencia;
	}

	public Iniciativa getIniciativa() {
		return iniciativa;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public Comprometimento getComprometimento() {
		return comprometimento;
	}

	public CuidadoMateriais getCuidadoMateriais() {
		return cuidadoMateriais;
	}

	public Permanencia getPermanencia() {
		return permanencia;
	}

	public QualidadeDeTrabalho getQualidadeTrabalho() {
		return qualidadeTrabalho;
	}

	public QuantidadeDeTrabalho getQuantidadeTrabalho() {
		return quantidadeTrabalho;
	}

	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	public TrabalhoEmEquipe getTrabalhoEquipe() {
		return trabalhoEquipe;
	}

	public CumprimentoPrazos getCumprimentoPrazos() {
		return cumprimentoPrazos;
	}

	public CaraterTreinamento getCaraterTreinamento() {
		return caraterTreinamento;
	}
	
	public String getAtividadeCurricular() {
		return atividadeCurricular;
	}

	public void setAtividadeCurricular(String atividadeCurricular) {
		this.atividadeCurricular = atividadeCurricular;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public String getObjetivoEstagio() {
		return objetivoEstagio;
	}

	public void setObjetivoEstagio(String objetivoEstagio) {
		this.objetivoEstagio = objetivoEstagio;
	}

	public String getFatorAssiduidadeDisciplina() {
		return fatorAssiduidadeDisciplina;
	}

	public void setFatorAssiduidadeDisciplina(String fatorAssiduidadeDisciplina) {
		this.fatorAssiduidadeDisciplina = fatorAssiduidadeDisciplina;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Boolean getAssuidade() {
		return assuidade;
	}

	public void setAssuidade(Boolean assuidade) {
		this.assuidade = assuidade;
	}

	public Boolean getFatorDisciplina() {
		return fatorDisciplina;
	}

	public void setFatorDisciplina(Boolean fatorDisciplina) {
		this.fatorDisciplina = fatorDisciplina;
	}

	public Boolean getCapacidadeIniciativa() {
		return capacidadeIniciativa;
	}

	public void setCapacidadeIniciativa(Boolean capacidadeIniciativa) {
		this.capacidadeIniciativa = capacidadeIniciativa;
	}

	public Boolean getProdutividade() {
		return produtividade;
	}

	public void setProdutividade(Boolean produtividade) {
		this.produtividade = produtividade;
	}

	public Boolean getResponsabilidade() {
		return responsabilidade;
	}

	public void setResponsabilidade(Boolean responsabilidade) {
		this.responsabilidade = responsabilidade;
	}

	public Boolean getOutrosMotivos() {
		return outrosMotivos;
	}

	public void setOutrosMotivos(Boolean outrosMotivos) {
		this.outrosMotivos = outrosMotivos;
	}

	public String getEspecificacaoMotivo() {
		return especificacaoMotivo;
	}

	public void setEspecificacaoMotivo(String especificacaoMotivo) {
		this.especificacaoMotivo = especificacaoMotivo;
	}

	public String getComentarioOrientador() {
		return comentarioOrientador;
	}

	public void setComentarioOrientador(String comentarioOrientador) {
		this.comentarioOrientador = comentarioOrientador;
	}

	public Boolean getNecessidadeTreinamento() {
		return necessidadeTreinamento;
	}

	public void setNecessidadeTreinamento(Boolean necessidadeTreinamento) {
		this.necessidadeTreinamento = necessidadeTreinamento;
	}

	public String getEspecificacaoTreinamento() {
		return especificacaoTreinamento;
	}

	public void setEspecificacaoTreinamento(String especificacaoTreinamento) {
		this.especificacaoTreinamento = especificacaoTreinamento;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public void setIniciativa(Iniciativa iniciativa) {
		this.iniciativa = iniciativa;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void setComprometimento(Comprometimento comprometimento) {
		this.comprometimento = comprometimento;
	}

	public void setCuidadoMateriais(CuidadoMateriais cuidadoMateriais) {
		this.cuidadoMateriais = cuidadoMateriais;
	}

	public void setPermanencia(Permanencia permanencia) {
		this.permanencia = permanencia;
	}

	public void setQualidadeTrabalho(QualidadeDeTrabalho qualidadeTrabalho) {
		this.qualidadeTrabalho = qualidadeTrabalho;
	}

	public void setQuantidadeTrabalho(QuantidadeDeTrabalho quantidadeTrabalho) {
		this.quantidadeTrabalho = quantidadeTrabalho;
	}

	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}

	public void setTrabalhoEquipe(TrabalhoEmEquipe trabalhoEquipe) {
		this.trabalhoEquipe = trabalhoEquipe;
	}

	public void setCumprimentoPrazos(CumprimentoPrazos cumprimentoPrazos) {
		this.cumprimentoPrazos = cumprimentoPrazos;
	}

	public void setCaraterTreinamento(CaraterTreinamento caraterTreinamento) {
		this.caraterTreinamento = caraterTreinamento;
	}

	public void setComentarioFinal(String comentarioFinal) {
		this.comentarioFinal = comentarioFinal;
	}

	/*
	public boolean isConfirmadoEstagio() {
		return confirmadoEstagio;
	}

	public void setConfirmadoEstagio(boolean confirmadoEstagio) {
		this.confirmadoEstagio = confirmadoEstagio;
	}*/
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AvaliacaoRendimento) {
			AvaliacaoRendimento other = (AvaliacaoRendimento) obj;
			if (other != null && other.getId() != null && this.id != null && other.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

	
}
