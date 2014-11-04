package ufc.quixada.npi.gp.utils;

import java.util.ArrayList;
import java.util.List;

import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Pessoa;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class EstagiarioDataSource extends JRAbstractBeanDataSourceProvider {
	
	public EstagiarioDataSource() {
		super(Estagiario.class);
	}

	private List<Estagiario> listEstagiario;

	@Override
	public JRDataSource create(JasperReport jrReport) throws JRException {
		listEstagiario = new ArrayList<Estagiario>();
		
		listEstagiario = new ArrayList<Estagiario>();
		Pessoa pessoa = new Pessoa("123456", "Jefferson", "Barbosa",
				"jefferson@hotmail.com", "6507687");
		listEstagiario.add(new Estagiario((long) 1, "Jefferson1", null,
				"Algum", "teste", "63900056", "Quixadá", "CE", "36720000",
				"Engenharia", "8", 338888, "redmine", "github", "hagout",
				pessoa));
		
		return new JRBeanCollectionDataSource(listEstagiario);
	}

	@Override
	public void dispose(JRDataSource jrds) throws JRException {
		listEstagiario.clear();
		listEstagiario = null;
	}
}