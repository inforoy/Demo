package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.OrganoJudicialDAO;
import gob.pe.proc.capadatos.Organojudicial;
import gob.pe.proc.capaservicio.OrganoJudicialService;
@Service
public class OrganoJudicialServiceImpl implements OrganoJudicialService {
	@Autowired
	private OrganoJudicialDAO organojudicialDAO;
	@Override
	public List<Organojudicial> obtenerListaOrganoJudicial(Organojudicial organoJudicial) {
		return organojudicialDAO.obtenerListaOrganoJudicial(organoJudicial);
	}

	@Override
	public Organojudicial obtenerOrganoJudicialporId(Integer idOrganoJudicial) {
		return organojudicialDAO.obtenerOrganoJudicialporID(idOrganoJudicial);
	}

	@Override
	public void guardarOrganoJudicial(Organojudicial organoJudicial) {
		organojudicialDAO.guardarOrganoJudicial(organoJudicial);

	}

	@Override
	public void borrarOrganoJudicial(Integer idOrganoJudicial) {
		organojudicialDAO.borrarOrganoJudicial(idOrganoJudicial);

	}

}
