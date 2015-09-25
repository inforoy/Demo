package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Organojudicial;
import java.util.List;

public interface OrganoJudicialDAO {
	public List<Organojudicial> obtenerListaOrganoJudicial(Organojudicial organoJudicial);
	public Organojudicial obtenerOrganoJudicialporID(Integer idOrganoJudicial);
	public void guardarOrganoJudicial(Organojudicial organoJudicial);
	public void borrarOrganoJudicial(Integer idOrganoJudicial);
}
