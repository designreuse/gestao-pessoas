package ufc.quixada.npi.gp.model.enums;

public enum StatusEntrega {
	AVALIADO("Avaliado"), CORRECAO("Correção"), REJEITADO("Rejeitado"), SUBMETIDO("Submetido");
	
	private String label;

	private StatusEntrega(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
