package ufc.quixada.npi.gp.model.enums;

public enum TipoSubmissao {
	PLANO_ESTAGIO("Plano de Estágio"),
	RELATORIO_FINAL_ESTAGIO("Relatório Final de Estágio");

	private String labelTipo;

	private TipoSubmissao(String labelTipo) {
		this.labelTipo = labelTipo;
	}

	public String getLabelTipo() {
		return labelTipo;
	}

	public void setLabelTipo (String labelTipo) {
		this.labelTipo = labelTipo;
	}

}
