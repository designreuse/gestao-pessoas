package ufc.quixada.npi.gp.service;

import java.util.List;
import java.util.Map;

import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Pessoa;
import ufc.quixada.npi.gp.repository.QueryType;

public interface GenericService<T> {
	
	  void save(T entity);

	  void update(T entity);

	  T find(Class<T> entityClass, Object id);

	  List<T> find(Class<T> entityClass);

	  void delete(T entity);
	
	  List<T> find(QueryType type, String query,
			Map<String, Object> namedParams);
}
