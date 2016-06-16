package br.unisc.core.controller;

import br.unisc.core.dto.VwIndividuoGrupoDTO;
import br.unisc.core.model.Desafio;
import br.unisc.core.model.Grupo;
import br.unisc.core.model.GrupoIndividuo;
import br.unisc.core.model.Individuo;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegra;
import br.unisc.web.model.SysRegraTabela;
import java.util.ArrayList;
import java.util.Date;
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
    protected SysConfiguracao configuracao;

    public VwIndividuoGrupoDTOController(EntityManager em, SysConfiguracao configuracao) {
        this.em = em;
        this.configuracao = configuracao;
    }

    public List<VwIndividuoGrupoDTO> findVwIndividuoGrupoByRegra(
            SysRegraTabela regraIndividuo, SysRegraTabela regraGrupo, SysRegraTabela regraGrupoIndividuo) {
        if (regraIndividuo.getFgImportar() || regraGrupo.getFgImportar() || regraGrupoIndividuo.getFgImportar()) {
            SysRegraTabelaController srtc = new SysRegraTabelaController(em);
            String where = " WHERE 1=1 " + srtc.criaWhereByRegraTabela(regraIndividuo, true);
            where += srtc.criaWhereByRegraTabela(regraGrupo, true);
            where += srtc.criaWhereByRegraTabela(regraGrupoIndividuo, true);

            Query q = configuracao.getConn().getEm().createNativeQuery("SELECT DISTINCT * FROM vw_individuo_grupo "
                    + where
                    + "ORDER BY id_individuo ", VwIndividuoGrupoDTO.class);
            return q.getResultList();
        }
        return new ArrayList<VwIndividuoGrupoDTO>();
    }

    public Individuo salvaIndividuo(Individuo obj, SysRegraTabela regra, SysConfiguracao configuracao) {
        if (regra.getFgImportar()) {
            obj.setConfiguracao(configuracao);
            Individuo iBD = findIndividuoByIdExterno(obj.getIdExterno());

            //quando cai em regra de transformacao de nome, entra aqui
            if ((iBD == null || iBD.getId() == null) && StringUtils.isNotBlank(obj.getNmIndividuo())) {
                iBD = findIndividuoByNmIndividuo(obj.getNmIndividuo());
            }

            if (iBD != null && iBD.getId() != null) {
                obj.setId(iBD.getId());

                //remocao lógica
                if (SysRegraTabela.SG_TIPO_REMOCAO_REGRA_REMOCAO == regra.getSgTipoRemocao()) {
                    removerIndividuoByRegra(obj, regra);
                }
            }

            //aplica regra de filtros configurados na tela da ferramenta
            if (individuoFiltrado(obj, regra, SysRegra.SG_TIPO_REGRA_FILTRO)) {
                return null;
            }

            Grupo gAux = obj.getGrupo();
            if (SysRegraTabela.SG_TIPO_INSERCAO_INSERIR_E_ALTERAR == regra.getSgTipoInsercao()) {
                obj = em.merge(obj);
            } else if (SysRegraTabela.SG_TIPO_INSERCAO_APENAS_INSERIR == regra.getSgTipoInsercao()) {

                if (iBD == null) {
                    em.persist(obj);
                }
            }
            em.flush();
            obj.setGrupo(gAux);
        }

        return obj;
    }

    public Grupo salvaGrupo(Grupo obj, SysRegraTabela regra, SysConfiguracao configuracao) {
        if (regra.getFgImportar() && obj != null && StringUtils.isNotBlank(obj.getNmGrupo())) {
            obj.setConfiguracao(configuracao);
            Grupo gBD = findGrupoByIdExterno(obj.getIdExterno());

            //quando cai em regra de transformacao de nome, entra aqui
            if ((gBD == null || gBD.getId() == null) && StringUtils.isNotBlank(obj.getNmGrupo())) {
                gBD = findGrupoByNmGrupo(obj.getNmGrupo());
            }

            if (gBD != null && gBD.getId() != null) {
                obj.setId(gBD.getId());

                //remocao lógica
                if (SysRegraTabela.SG_TIPO_REMOCAO_REGRA_REMOCAO == regra.getSgTipoRemocao()) {
                    removerGrupoByRegra(obj, regra);
                }
            }

            //aplica regra de filtros configurados na tela da ferramenta
            if (grupoFiltrado(obj, regra, SysRegra.SG_TIPO_REGRA_FILTRO)) {
                return null;
            }

            if (SysRegraTabela.SG_TIPO_INSERCAO_INSERIR_E_ALTERAR == regra.getSgTipoInsercao()) {
                obj.setDtUltimaSincronizacao(new Date());
                obj = em.merge(obj);
            } else if (SysRegraTabela.SG_TIPO_INSERCAO_APENAS_INSERIR == regra.getSgTipoInsercao()) {
                if (gBD == null) {
                    obj.setDtUltimaSincronizacao(new Date());
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

    public Individuo findIndividuoByIdExterno(int idExterno) {
        Query q = em.createNativeQuery("SELECT * FROM individuo "
                + "WHERE id_externo = ?1 AND id_configuracao = ?2", Individuo.class);
        q.setParameter(1, idExterno);
        q.setParameter(2, configuracao.getId());
        List<Individuo> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Grupo findGrupoByIdExterno(Integer idExterno) {
        Query q = em.createNativeQuery("SELECT * FROM grupo "
                + "WHERE id_externo = ?1 AND id_configuracao = ?2", Grupo.class);
        q.setParameter(1, idExterno);
        q.setParameter(2, configuracao.getId());
        List<Grupo> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Desafio findDesafioByIdExterno(int idExterno) {
        Query q = em.createNativeQuery("SELECT * FROM desafio "
                + "WHERE id_externo = ?1 AND id_configuracao = ?2", Desafio.class);
        q.setParameter(1, idExterno);
        q.setParameter(2, configuracao.getId());
        List<Desafio> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    private Grupo findGrupoByNmGrupo(String nmGrupo) {
        Query q = em.createNativeQuery("SELECT * FROM grupo "
                + "WHERE lower(nm_grupo) LIKE lower(?1) AND id_configuracao = ?2", Grupo.class);
        q.setParameter(1, nmGrupo);
        q.setParameter(2, configuracao.getId());
        List<Grupo> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    private Individuo findIndividuoByNmIndividuo(String nmIndividuo) {
        Query q = em.createNativeQuery("SELECT * FROM individuo "
                + "WHERE lower(nm_individuo) LIKE lower(?1) AND id_configuracao = ?2", Individuo.class);
        q.setParameter(1, nmIndividuo);
        q.setParameter(2, configuracao.getId());
        List<Individuo> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    private boolean individuoFiltrado(Individuo obj, SysRegraTabela regra, int tipoRegra) {
        boolean filtrado = false;
        SysRegraController src = new SysRegraController(em);
        for (SysRegra sr : regra.getSysRegraList()) {
            if (tipoRegra == sr.getSgTipoRegra()) {
                filtrado = true;
                //se valor do atributo for igual ao definido no filtro, entao nao filtrado
                String valorAtributo = obj.getVal(sr.getAtributo().getNmAtributo());
                if (src.compareByRegra(valorAtributo, sr)) {
                    filtrado = false;
                    break;
                }
            }
        }
        return filtrado;
    }

    private Individuo removerIndividuoByRegra(Individuo obj, SysRegraTabela regra) {
        if (individuoFiltrado(obj, regra, SysRegra.SG_TIPO_REGRA_REMOCAO)) {
            obj.setFgAtivo(false);
        }
        return obj;
    }

    private boolean grupoFiltrado(Grupo obj, SysRegraTabela regra, int tipoRegra) {
        boolean filtrado = false;
        SysRegraController src = new SysRegraController(em);
        for (SysRegra sr : regra.getSysRegraList()) {
            if (tipoRegra == sr.getSgTipoRegra()) {
                filtrado = true;
                //se valor do atributo for igual ao definido no filtro, entao nao filtrado
                String valorAtributo = obj.getVal(sr.getAtributo().getNmAtributo());
                if (src.compareByRegra(valorAtributo, sr)) {
                    filtrado = false;
                    break;
                }
            }
        }
        return filtrado;
    }

    private Grupo removerGrupoByRegra(Grupo obj, SysRegraTabela regra) {
        if (grupoFiltrado(obj, regra, SysRegra.SG_TIPO_REGRA_REMOCAO)) {
            obj.setFgAtivo(false);
        }
        return obj;
    }

    public Individuo aplicaTransformacaoByRegra(Individuo obj, SysRegraTabela regraTabela) {
        SysRegraController src = new SysRegraController(em);
        for (SysRegra sr : regraTabela.getSysRegraList()) {
            if (SysRegra.SG_TIPO_REGRA_TRANSFORMACAO == sr.getSgTipoRegra()) {
                //se valor do atributo for igual ao definido na transformacao, entao seta valor
                String valorAtributo = obj.getVal(sr.getAtributo().getNmAtributo());
                if (src.compareByRegra(valorAtributo, sr)) {
                    obj.setVal(sr.getAtributo().getNmAtributo(), sr.getVlRegraNovo());
                }
            }
        }
        return obj;
    }

    public Grupo aplicaTransformacaoGrupoByRegra(Grupo obj, SysRegraTabela regraTabela) {
        SysRegraController src = new SysRegraController(em);
        for (SysRegra sr : regraTabela.getSysRegraList()) {
            if (SysRegra.SG_TIPO_REGRA_TRANSFORMACAO == sr.getSgTipoRegra()) {
                //se valor do atributo for igual ao definido na transformacao, entao seta valor
                String valorAtributo = obj.getVal(sr.getAtributo().getNmAtributo());
                if (src.compareByRegra(valorAtributo, sr)) {
                    obj.setVal(sr.getAtributo().getNmAtributo(), sr.getVlRegraNovo());
                }
            }
        }
        return obj;
    }

}
