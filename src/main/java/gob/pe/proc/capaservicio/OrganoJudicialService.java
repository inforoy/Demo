package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Organojudicial;

import java.util.List;

public interface OrganoJudicialService {
	public List<Organojudicial> obtenerListaOrganoJudicial(Organojudicial organoJudicial);
	public Organojudicial obtenerOrganoJudicialporId(Integer idOrganoJudicial);
	public void guardarOrganoJudicial(Organojudicial organoJudicial);
	public void borrarOrganoJudicial(Integer idOrganoJudicial);
}
