package br.unisc.core.controller;

import br.unisc.web.dto.ConnectionDTO;
import br.unisc.core.dto.VwIndividuoGrupoDTO;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoIndividuo;
import br.unisc.core.model.Individuo;
import br.unisc.util.DBUtil;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegraTabela;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class VwIndividuoGrupoDTOController {

    protected EntityManager em;
    protected ConnectionDTO conn;
    protected SysConfiguracao configuracao;

    public VwIndividuoGrupoDTOController(EntityManager em, ConnectionDTO c) {
        this.em = em;
        this.conn = c;
    }

    public VwIndividuoGrupoDTOController(EntityManager em, SysConfiguracao configuracao) {
        this.em = em;
        this.configuracao = configuracao;
    }

    public List<VwIndividuoGrupoDTO> findVwIndividuoGrupoByRegra(
            SysRegraTabela regraIndividuo, SysRegraTabela regraGrupo, SysRegraTabela regraGrupoIndividuo) {
        SysRegraTabelaController srtc = new SysRegraTabelaController(em);
        String where = " WHERE 1=1 " + srtc.criaWhereByRegraTabela(regraIndividuo, true);
        where += srtc.criaWhereByRegraTabela(regraGrupo, true);
        where += srtc.criaWhereByRegraTabela(regraGrupoIndividuo, true);

        Query q = configuracao.getConn().getEm().createNativeQuery("SELECT DISTINCT * FROM vw_individuo_grupo "
                + where
                + "ORDER BY id_individuo ", VwIndividuoGrupoDTO.class);
        return q.getResultList();
    }

    /**
     * @deprecated @return
     */
    public List<VwIndividuoGrupoDTO> findIndividuoGrupo() {
        Query q = conn.getEm().createNativeQuery("SELECT DISTINCT * FROM vw_individuo_grupo "
                + "ORDER BY id_individuo ", VwIndividuoGrupoDTO.class);
        return q.getResultList();
    }

    public Individuo salvaIndividuo(Individuo obj, SysRegraTabela regra, SysConfiguracao configuracao) {
        if (regra.getFgImportar()) {
            obj.setConfiguracao(configuracao);
            Individuo iBD = findIndividuoByIdExterno(obj.getIdExterno());
            if (iBD != null) {
                obj.setId(iBD.getId());
            }

            if (SysRegraTabela.SG_TIPO_INSERCAO_INSERIR_E_ALTERAR == regra.getSgTipoInsercao()) {
                obj = em.merge(obj);
            } else if (SysRegraTabela.SG_TIPO_INSERCAO_APENAS_INSERIR == regra.getSgTipoInsercao()) {

                if (iBD == null) {
                    em.persist(obj);
                }
            }
            em.flush();
        }

        return obj;
    }

    public Grupo salvaGrupo(Grupo obj, SysRegraTabela regra, SysConfiguracao configuracao) {
        if (regra.getFgImportar() && obj != null && StringUtils.isNotBlank(obj.getNmGrupo())) {
            obj.setConfiguracao(configuracao);
            Grupo gBD = findGrupoByIdExterno(obj.getIdExterno());
            if (gBD != null) {
                obj.setId(gBD.getId());
            }

            if (SysRegraTabela.SG_TIPO_INSERCAO_INSERIR_E_ALTERAR == regra.getSgTipoInsercao()) {
                obj = em.merge(obj);
            } else if (SysRegraTabela.SG_TIPO_INSERCAO_APENAS_INSERIR == regra.getSgTipoInsercao()) {
                if (gBD == null) {
                    em.persist(obj);
                }
            }
            em.flush();
        }
        return obj;
    }

    public GrupoIndividuo salvaGrupoIndividuo(Individuo individuo, Grupo grupo, SysRegraTabela regra, SysConfiguracao configuracao) {
        GrupoIndividuo gi = null;
        if (individuo == null || individuo.getId() == null
                || grupo == null || grupo.getId() == null) {
            return gi;
        }

        if (regra.getFgImportar()) {
            gi = new GrupoIndividuo(grupo, individuo, true);
            gi.setConfiguracao(configuracao);
            GrupoIndividuo giBD = findIndividuoGrupoByIdsExternos(grupo, individuo);
            if (giBD != null) {
                gi.setId(giBD.getId());
            }

            if (SysRegraTabela.SG_TIPO_INSERCAO_INSERIR_E_ALTERAR == regra.getSgTipoInsercao()) {
                gi = em.merge(gi);
            } else if (SysRegraTabela.SG_TIPO_INSERCAO_APENAS_INSERIR == regra.getSgTipoInsercao()) {
                if (giBD == null) {
                    em.persist(gi);
                }
            }
            em.flush();
        }
        return gi;
    }

    public GrupoIndividuo findIndividuoGrupoByIdsExternos(Grupo g, Individuo i) {
        Query q = em.createNativeQuery("SELECT gi.* FROM grupo_individuo gi "
                + "JOIN grupo g ON gi.id_grupo = g.id "
                + "JOIN individuo i ON gi.id_individuo = i.id "
                + "	AND g.id_externo = ?1 "
                + "	AND i.id_externo = ?2 "
                + "ORDER BY gi.id_individuo ASC ", GrupoIndividuo.class);
        q.setParameter(1, g.getId());
        q.setParameter(2, i.getId());
        List<GrupoIndividuo> result = q.getResultList();

        //Do not exists in DB, return a new one
        if (result.isEmpty()) {
            GrupoIndividuo gi = new GrupoIndividuo(g, i);
            return gi;
        }
        return result.get(0);
    }

    private Individuo findIndividuoByIdExterno(int idExterno) {
        Query q = em.createNativeQuery("SELECT * FROM individuo "
                + "WHERE id_externo = ?1", Individuo.class);
        q.setParameter(1, idExterno);
        List<Individuo> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    private Grupo findGrupoByIdExterno(Integer idExterno) {
        Query q = em.createNativeQuery("SELECT * FROM grupo "
                + "WHERE id_externo = ?1", Grupo.class);
        q.setParameter(1, idExterno);
        List<Grupo> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
