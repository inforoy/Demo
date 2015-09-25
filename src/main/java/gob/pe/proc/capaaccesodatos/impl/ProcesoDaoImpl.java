package gob.pe.proc.capaaccesodatos.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.ProcesoDAO;
import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Ubigeo;
@Repository
public class ProcesoDaoImpl implements ProcesoDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public ProcesoDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}
	@SuppressWarnings("unchecked")
	public Set<Proceso> obtenerListaProceso(Proceso proceso) {
//		from Proceso p
//		left join fetch p.demandante
//		left join fetch p.demandado
//		where p.nroExpediente like '%123%'
//		el truco es que hagas Left Join fetch de la coleccion
		
		StringBuffer query = new StringBuffer(" from Proceso p left join fetch p.demandante left join fetch p.demandado where p.estado.idEstado=1 ");
		
		if(proceso != null && proceso.getNroExpediente() != null && proceso.getNroExpediente().length() > 0){			
			query.append(" and upper(p.nroExpediente) like '").append(proceso.getNroExpediente().toUpperCase()).append("%' ");
		}
		
		List<Proceso> list = (List<Proceso>) hibernateTemplate.find(query.toString());
		Set<Proceso> lista=new HashSet<Proceso>();
		lista.addAll(list);
		return lista;
	}

	public Proceso obtenerProcesoporID(String nroExpediente) {
		Proceso proceso = (Proceso) hibernateTemplate.get(Proceso.class, nroExpediente);
		return proceso;
	}
	public void guardarProceso(Proceso proceso) {
		hibernateTemplate.saveOrUpdate(proceso);
	}
	public void borrarProceso(String nroExpediente) {
		Proceso proceso = obtenerProcesoporID(nroExpediente);
		hibernateTemplate.delete(proceso);
	}
	@SuppressWarnings("unchecked")
	public List<Materia> listarMateria(Integer id) {
		StringBuffer query = new StringBuffer(" from Materia m where m.naturaleza.idNaturaleza=");
		query.append(id);
		List<Materia> list = (List<Materia>)hibernateTemplate.find(query.toString());
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Estado> listarEstado() {
		StringBuffer query = new StringBuffer(" from Estado ");
		List<Estado> list = (List<Estado>)hibernateTemplate.find(query.toString());
		return list;
	}
	public int contarFolderPorAnio(Integer anio) {
		StringBuffer query = new StringBuffer(" SELECT COUNT(nroFolder) FROM Proceso WHERE anio=");
		query.append(anio);
		//List list=hibernateTemplate.find(query.toString());
		Integer contador=DataAccessUtils.intResult(hibernateTemplate.find(query.toString()));
		return contador;
	}

	@SuppressWarnings("unchecked")
	public Set<Proceso> obtenerListaProcesoporFolder(Proceso proceso) {
		StringBuffer query = new StringBuffer(" from Proceso p left join fetch p.demandante left join fetch p.demandado where p.estado.idEstado=1 ");
		
		if(proceso != null && proceso.getNroFolder() != null && proceso.getNroFolder().length() > 0){
			
			query.append(" and p.nroFolder like '%").append(proceso.getNroFolder()).append("%' ");
		}
		List<Proceso> list = (List<Proceso>) hibernateTemplate.find(query.toString());
		Set<Proceso> lista=new HashSet<Proceso>();
		lista.addAll(list);
		return lista;
	}
	@SuppressWarnings("unchecked")
	public Set<Proceso> obtenerUltimaTupla() {
		StringBuffer query = new StringBuffer(" FROM Proceso p WHERE p.estadoProceso=true and p.nroExpediente=(SELECT MAX(nroExpediente) FROM Proceso) ");
		List<Proceso> list = (List<Proceso>) hibernateTemplate.find(query.toString());
		Set<Proceso> lista=new HashSet<Proceso>();
		lista.addAll(list);
		return lista;
		
	}

	@SuppressWarnings("unchecked")
	public List<Naturaleza> listarNaturaleza() {
		StringBuffer query = new StringBuffer(" from Naturaleza ");
		List<Naturaleza> list = (List<Naturaleza>)hibernateTemplate.find(query.toString());
		return list;
	}
	@SuppressWarnings("unchecked")
	public Set<Proceso> reporteEstadoProceso(Proceso proceso) {
		StringBuffer query=new StringBuffer("from Proceso p where p.estado.idEstado=");
		query.append(proceso.getEstado().getIdEstado()).append("and p.fechaInicio between '").append(proceso.getFechaInicio()).append("' and '").append(proceso.getFechaFin()).append("'");
		List<Proceso> list=(List<Proceso>)hibernateTemplate.find(query.toString());
		Set<Proceso> lista=new HashSet<Proceso>();
		lista.addAll(list);
		return lista;
	}
	@SuppressWarnings("unchecked")
	public Set<Proceso> reporteMateriaProceso(Proceso proceso) {
		StringBuffer query=new StringBuffer("from Proceso p where p.materia.idMateria=");
		query.append(proceso.getMateria().getIdMateria())
		.append("and p.estado.idEstado=")
		.append(proceso.getEstado().getIdEstado()).
		append("and p.fechaInicio between '").append(proceso.getFechaInicio()).append("' and '").append(proceso.getFechaFin()).append("'");
		List<Proceso> list=(List<Proceso>)hibernateTemplate.find(query.toString());
		Set<Proceso> lista=new HashSet<Proceso>();
		lista.addAll(list);
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<Ubigeo> listarUbigeo() {
		StringBuffer query = new StringBuffer(" from Ubigeo ");
		List<Ubigeo> list = (List<Ubigeo>)hibernateTemplate.find(query.toString());
		return list;
	}
	
	public void desactivarProceso(String numExpediente) {
		Proceso proceso=obtenerProcesoporID(numExpediente);
		proceso.getEstado().setIdEstado(2);
		guardarProceso(proceso);
	}
}
