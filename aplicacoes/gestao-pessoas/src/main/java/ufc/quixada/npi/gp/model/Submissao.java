package ufc.quixada.npi.gp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ufc.quixada.npi.gp.model.enums.StatusEntrega;
import ufc.quixada.npi.gp.model.enums.TipoSubmissao;

@Entity
public class Submissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "documento_id")
	private Documento documento;
	
	@ManyToOne
	private Estagio estagio;
	
	private double nota;
	
	private String comentario;

	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Temporal(TemporalType.TIME)
	private Date hora;

	@Enumerated(EnumType.STRING)
	private TipoSubmissao tipoSubmissao;
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega statusEntrega;
	
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
	
	public Date getData() {
		return data;
	}
	
	public Date getHora() {
		return hora;
	}

	public void setHora(Date tempo) {
		this.hora = tempo;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public StatusEntrega getStatusEntrega() {
		return statusEntrega;
	}

	public void setStatusEntrega(StatusEntrega statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

	public TipoSubmissao getTipoSubmissao() {
		return tipoSubmissao;
	}

	public void setTipoSubmissao(TipoSubmissao tipo) {
		this.tipoSubmissao = tipo;
	}
	
	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Submissao) {
			Submissao other = (Submissao) obj;
			if (other != null && other.getId() != null && this.id != null && other.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}
	
}
