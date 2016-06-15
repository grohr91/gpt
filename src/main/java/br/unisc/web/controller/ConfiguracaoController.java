package br.unisc.web.controller;

import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysTipoAtributoOperacao;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class ConfiguracaoController {

    protected EntityManager em;

    public ConfiguracaoController(EntityManager em) {
        this.em = em;
    }

    public SysConfiguracao save(SysConfiguracao obj) {
        if (obj.getId() == null) {
            em.persist(obj);
            em.flush();
            return obj;
        }

        if (StringUtils.isBlank(obj.getCdPass())) {
            SysConfiguracao objBD = em.find(SysConfiguracao.class, obj.getId());
            obj.setCdPass(objBD.getCdPass());
        }

        return em.merge(obj);
    }

    public List<SysTipoAtributoOperacao> findTipoAtributoOperacaoByTipoAtributo(Integer idAtributo) {
        return em.createNativeQuery("SELECT tao.* FROM sys_tipo_atributo_operacao tao "
                + "JOIN sys_atributo a ON tao.id_tipo_atributo = a.id_tipo_atributo "
                + "WHERE a.id = ?1 "
                + "ORDER BY tao.id_operacao", SysTipoAtributoOperacao.class)
                .setParameter(1, idAtributo).getResultList();
    }

}
