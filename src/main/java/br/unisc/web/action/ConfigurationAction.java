package br.unisc.web.action;

import br.unisc.core.controller.ImportacaoController;
import br.unisc.core.model.Desafio;
import br.unisc.web.controller.GamificationController;
import br.unisc.web.dto.ConnectionDTO;
import br.unisc.util.EMAware;
import br.unisc.web.controller.ConfiguracaoController;
import br.unisc.core.controller.SysAutomacaoController;
import br.unisc.core.controller.SysRegraExtracaoController;
import br.unisc.core.controller.SysRegraTabelaController;
import br.unisc.web.model.SysAtributo;
import br.unisc.web.model.SysAutomacao;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysOperacao;
import br.unisc.web.model.SysRegra;
import br.unisc.web.model.SysRegraExtracao;
import br.unisc.web.model.SysRegraTabela;
import br.unisc.web.model.SysTabela;
import br.unisc.web.model.SysTipoAtributo;
import br.unisc.web.model.SysTipoAtributoOperacao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class ConfigurationAction extends ActionSupport implements EMAware {

    private EntityManager em;
    private String id;
    private String index;
    private ConnectionDTO connection;

    private String dsMessage, dsInfo, regraHtml;
    private Map<String, Object> sessionMap;

    private SysConfiguracao configuracao;
    private SysTabela tabela;
    private SysRegra regra;
    private SysTipoAtributo tipoAtributo;
    private SysRegraTabela regraTabela;
    private List<SysTipoAtributoOperacao> tipoAtributoOperacaoList;
    private List<SysTabela> tabelaList;
    private List<SysAutomacao> automacaoList;
    private List<SysAtributo> atributoList;
    private List<SysRegraTabela> regraTabelaList;
    private List<SysConfiguracao> configuracaoList;
    private List<SysOperacao> operacaoList;
    private List<SysRegraExtracao> regraExtracaoList;
    private List<Desafio> desafioList;
    private LinkedHashMap<Integer, String> tipoBdList;
    private LinkedHashMap<Integer, String> tipoImportacaoList;

    public String main() throws Exception {
        try {
            configuracao = new SysConfiguracao();
            tabelaList = em.createNamedQuery("SysTabela.findAll", SysTabela.class).getResultList();

            if (StringUtils.isNotBlank(id)
                    || (configuracao != null && configuracao.getId() != null)) {
                configuracao = em.find(SysConfiguracao.class, Integer.parseInt(id));
                automacaoList = em.createNamedQuery("SysAutomacao.findByConfiguracao", SysAutomacao.class)
                        .setParameter("idConfiguracao", Integer.parseInt(id)).getResultList();
                regraExtracaoList = em.createNamedQuery("SysRegraExtracao.findByConfiguracao", SysRegraExtracao.class)
                        .setParameter("idConfiguracao", Integer.parseInt(id)).getResultList();
                regraTabelaList = em.createNamedQuery("SysRegraTabela.findByConfiguracao", SysRegraTabela.class)
                        .setParameter("idConfiguracao", Integer.parseInt(id)).getResultList();
                for (SysRegraTabela srt : regraTabelaList) {
                    if (tabelaList.contains(srt.getTabela())) {
                        tabelaList.remove(srt.getTabela());
                    }
                }
            } else {
                regraTabelaList = new ArrayList<SysRegraTabela>();
                regraExtracaoList = new ArrayList<SysRegraExtracao>();
                automacaoList = new ArrayList<SysAutomacao>();
                for (int i = 1; i <= 7; i++) {
                    automacaoList.add(new SysAutomacao(i, false, null));
                }
            }

            for (SysTabela t : tabelaList) {
                regraTabelaList.add(new SysRegraTabela(t));
            }
            configuracaoList = em.createNamedQuery("SysConfiguracao.findAll", SysConfiguracao.class).getResultList();
            SysConfiguracao emptyOption = new SysConfiguracao();
            emptyOption.setNmConfiguracao("Nova Configuração");
            configuracaoList.add(0, emptyOption);
        } catch (Exception ex) {
            ex.printStackTrace();
            tabelaList = new ArrayList<SysTabela>();
            regraTabelaList = new ArrayList<SysRegraTabela>();
        }
        return SUCCESS;
    }

    public String salvar() throws Exception {
        try {
            em.getTransaction().begin();
            configuracao = new ConfiguracaoController(em).save(configuracao);
            regraTabelaList = new SysRegraTabelaController(em).save(regraTabelaList, configuracao);
            automacaoList = new SysAutomacaoController(em).save(automacaoList, configuracao);
            regraExtracaoList = new SysRegraExtracaoController(em).save(regraExtracaoList, configuracao);
            em.getTransaction().commit();
            id = configuracao.getId().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return ERROR;
        }
        return SUCCESS;
    }

    public String salvaRegraTabelaList() throws Exception {
        try {
            em.getTransaction().begin();
            regraTabelaList = new SysRegraTabelaController(em).save(regraTabelaList, configuracao);
            em.getTransaction().commit();
            id = configuracao.getId().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return ERROR;
        }
        return SUCCESS;
    }

    public String salvaRegraExtracaoList() throws Exception {
        try {
            em.getTransaction().begin();
            regraExtracaoList = new SysRegraExtracaoController(em).save(regraExtracaoList, configuracao);
            em.getTransaction().commit();
            id = configuracao.getId().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return ERROR;
        }
        return SUCCESS;
    }

    public String carregaOperacaoByTipoAtributo() throws Exception {
        try {
            tipoAtributoOperacaoList = new ConfiguracaoController(em)
                    .findTipoAtributoOperacaoByTipoAtributo(tipoAtributo.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            tipoAtributoOperacaoList = new ArrayList<SysTipoAtributoOperacao>();
        }
        return SUCCESS;
    }

    public String carregaNovaRegra() throws Exception {
        try {
            atributoList = em.createNamedQuery("SysAtributo.findByIdTabela", SysAtributo.class)
                    .setParameter("idTabela", tabela.getId()).getResultList();
            regraTabela = em.createNamedQuery("SysRegraTabela.findByConfiguracaoAndTabela", SysRegraTabela.class)
                    .setParameter("idConfiguracao", configuracao.getId())
                    .setParameter("idTabela", tabela.getId()).getResultList().get(0);

            tabela = em.find(SysTabela.class, tabela.getId());
            if ("desafio".equals(tabela.getNmTabela())) {
                desafioList = em.createNamedQuery("Desafio.findAll", Desafio.class).getResultList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            atributoList = new ArrayList<SysAtributo>();
            desafioList = new ArrayList<Desafio>();
            tipoAtributoOperacaoList = new ArrayList<SysTipoAtributoOperacao>();
        }
        return SUCCESS;
    }

    public String salvaRegra() throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(regra);
            em.getTransaction().commit();
            SysAtributo sa = em.find(SysAtributo.class, regra.getAtributo().getId());
            SysOperacao so = em.find(SysOperacao.class, regra.getOperacao().getId());
            regraHtml = "<div style='padding-bottom: 10px; padding-top: 10px;' class='div-regra' regra-id='" + regra.getId() + "'>"
                    + "<div class=\"col-md-9\">\n"
                    + "<i class=\"glyphicon glyphicon-asterisk\"></i> <strong>Quando</strong> "
                    + sa.getNmAtributo()
                    + " <strong>for</strong> "
                    + so.getNmOperacao()
                    + " <strong>a/que/de</strong> " + regra.getVlRegra()
                    + "</div>"
                    + "<div class=\"col-md-3\"> "
                    + "<a class=\"btn btn-xs btn-default\" href=\"javascript:excluiRegra("
                    + regra.getId() + ");\"><i class=\"glyphicon glyphicon-trash\"></i> Remover</a> "
                    + "</div>"
                    + "</div>";
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return SUCCESS;
    }

    public String excluiRegra() throws Exception {
        try {
            em.getTransaction().begin();
            regra = em.find(SysRegra.class, regra.getId());
            em.remove(regra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return SUCCESS;
    }

    public String metodoPadrao() throws Exception {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = ex.getMessage();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return ERROR;
        }
        return SUCCESS;
    }

    public String processarImportacao() {
        try {
            configuracao = em.find(SysConfiguracao.class, Integer.parseInt(id));
            if (SysConfiguracao.TIPO_IMPORTACAO_BANCO_DE_DADOS == configuracao.getSgTipoImportacao()) {
                testConnection();
                if (StringUtils.isNotBlank(dsMessage)) {
                    return SUCCESS;
                }
                configuracao.getConn().open();
            }

            em.getTransaction().begin();
            ImportacaoController ic = new ImportacaoController(em, configuracao);
            ic.processarImportacao();

//            GamificationController gc = new GamificationController(em, connection);
//            dsInfo = "";
//            dsInfo += gc.processVwIndividuoGrupo();
//            dsInfo += "\n\n" + gc.processVwIndividuoAtividade();
//            dsInfo += "\n\n" + gc.processVwGrupoAtividade();
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = "Something wrong occoured";
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            configuracao.getConn().close();
        }
        return SUCCESS;
    }

    private String testConnection() {
        try {
            dsMessage = configuracao.getConn().open();
            configuracao.getConn().close();
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = "Something wrong occoured";
        }
        return SUCCESS;
    }

    public ConnectionDTO getConnection() {
        return connection;
    }

    public void setConnection(ConnectionDTO connection) {
        this.connection = connection;
    }

    public String getDsMessage() {
        if (dsMessage != null && !dsMessage.isEmpty()) {
            dsMessage = "<div class=\"alert alert-danger\" role=\"alert\">"
                    + dsMessage + "</div>";
        }
        return dsMessage;
    }

    public void setDsMessage(String dsMessage) {
        this.dsMessage = dsMessage;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getDsInfo() {
        if (dsInfo != null && !dsInfo.isEmpty()) {
            dsInfo = "<div class=\"alert alert-success\" role=\"alert\">"
                    + dsInfo + "</div>";
        }
        return dsInfo;
    }

    public void setDsInfo(String dsInfo) {
        this.dsInfo = dsInfo;
    }

    public SysConfiguracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(SysConfiguracao configuracao) {
        this.configuracao = configuracao;
    }

    public List<SysTabela> getTabelaList() {
        return tabelaList;
    }

    public void setTabelaList(List<SysTabela> tabelaList) {
        this.tabelaList = tabelaList;
    }

    public List<SysRegraTabela> getRegraTabelaList() {
        return regraTabelaList;
    }

    public void setRegraTabelaList(List<SysRegraTabela> regraTabelaList) {
        this.regraTabelaList = regraTabelaList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SysConfiguracao> getConfiguracaoList() {
        return configuracaoList;
    }

    public void setConfiguracaoList(List<SysConfiguracao> configuracaoList) {
        this.configuracaoList = configuracaoList;
    }

    public LinkedHashMap<Integer, String> getTipoBdList() {
        tipoBdList = new LinkedHashMap<Integer, String>();
        tipoBdList.put(SysConfiguracao.TIPO_BD_POSTGRES, "PostgresSql");
        tipoBdList.put(SysConfiguracao.TIPO_BD_MYSQL, "MySql");
        return tipoBdList;
    }

    public void setTipoBdList(LinkedHashMap<Integer, String> tipoBdList) {
        this.tipoBdList = tipoBdList;
    }

    public LinkedHashMap<Integer, String> getTipoImportacaoList() {
        tipoImportacaoList = new LinkedHashMap<Integer, String>();
        tipoImportacaoList.put(SysConfiguracao.TIPO_IMPORTACAO_BANCO_DE_DADOS, "Banco de Dados");
        tipoImportacaoList.put(SysConfiguracao.TIPO_IMPORTACAO_CSV, "CSV");
        return tipoImportacaoList;
    }

    public void setTipoImportacaoList(LinkedHashMap<Integer, String> tipoImportacaoList) {
        this.tipoImportacaoList = tipoImportacaoList;
    }

    public SysTabela getTabela() {
        return tabela;
    }

    public void setTabela(SysTabela tabela) {
        this.tabela = tabela;
    }

    public List<SysAtributo> getAtributoList() {
        return atributoList;
    }

    public void setAtributoList(List<SysAtributo> atributoList) {
        this.atributoList = atributoList;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public SysRegra getRegra() {
        return regra;
    }

    public void setRegra(SysRegra regra) {
        this.regra = regra;
    }

    public List<SysOperacao> getOperacaoList() {
        return operacaoList;
    }

    public void setOperacaoList(List<SysOperacao> operacaoList) {
        this.operacaoList = operacaoList;
    }

    public SysTipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(SysTipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public List<SysTipoAtributoOperacao> getTipoAtributoOperacaoList() {
        return tipoAtributoOperacaoList;
    }

    public void setTipoAtributoOperacaoList(List<SysTipoAtributoOperacao> tipoAtributoOperacaoList) {
        this.tipoAtributoOperacaoList = tipoAtributoOperacaoList;
    }

    public SysRegraTabela getRegraTabela() {
        return regraTabela;
    }

    public void setRegraTabela(SysRegraTabela regraTabela) {
        this.regraTabela = regraTabela;
    }

    public String getRegraHtml() {
        return regraHtml;
    }

    public void setRegraHtml(String regraHtml) {
        this.regraHtml = regraHtml;
    }

    public List<SysAutomacao> getAutomacaoList() {
        return automacaoList;
    }

    public void setAutomacaoList(List<SysAutomacao> automacaoList) {
        this.automacaoList = automacaoList;
    }

    public List<SysRegraExtracao> getRegraExtracaoList() {
        return regraExtracaoList;
    }

    public void setRegraExtracaoList(List<SysRegraExtracao> regraExtracaoList) {
        this.regraExtracaoList = regraExtracaoList;
    }

    public List<Desafio> getDesafioList() {
        return desafioList;
    }

    public void setDesafioList(List<Desafio> desafioList) {
        this.desafioList = desafioList;
    }

}
