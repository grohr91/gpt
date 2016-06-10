package br.unisc.web.action;

import br.unisc.web.controller.GamificationController;
import br.unisc.web.dto.ConnectionDTO;
import br.unisc.util.EMAware;
import br.unisc.web.controller.ConfiguracaoController;
import br.unisc.web.controller.SysRegraTabelaController;
import br.unisc.web.model.SysConfiguracao;
import br.unisc.web.model.SysRegraTabela;
import br.unisc.web.model.SysTabela;
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
    private ConnectionDTO connection;

    private String dsMessage, dsInfo;
    private Map<String, Object> sessionMap;

    private SysConfiguracao configuracao;
    private List<SysTabela> tabelaList;
    private List<SysRegraTabela> regraTabelaList;
    private List<SysConfiguracao> configuracaoList;
    private LinkedHashMap<Integer, String> tipoBdList;
    private LinkedHashMap<Integer, String> tipoImportacaoList;

    public String main() throws Exception {
        try {
            configuracao = new SysConfiguracao();
            tabelaList = em.createNamedQuery("SysTabela.findAll", SysTabela.class).getResultList();

            if (StringUtils.isNotBlank(id)
                    || (configuracao != null && configuracao.getId() != null)) {
                configuracao = em.find(SysConfiguracao.class, Integer.parseInt(id));
                regraTabelaList = em.createNamedQuery("SysRegraTabela.findByConfiguracao", SysRegraTabela.class)
                        .setParameter("idConfiguracao", Integer.parseInt(id)).getResultList();
                for(SysRegraTabela srt: regraTabelaList){
                    if(tabelaList.contains(srt.getTabela())){
                        tabelaList.remove(srt.getTabela());
                    }
                }
                
            } else {
                regraTabelaList = new ArrayList<SysRegraTabela>();
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

    public String processGamification() {
        try {
            if (validConnection()) {
                em.getTransaction().begin();
                connection.open();
                GamificationController gc = new GamificationController(em, connection);
                dsInfo = "";
                dsInfo += gc.processVwIndividuoGrupo();
                dsInfo += "\n\n" + gc.processVwIndividuoAtividade();
                dsInfo += "\n\n" + gc.processVwGrupoAtividade();
                em.getTransaction().commit();
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = "Something wrong occoured";
        } finally {
            connection.close();
        }
        return SUCCESS;
    }

    private String testConnection() {
        try {
            dsMessage = connection.open();
            connection.close();
            sessionMap.put("CURRENT_CONNECTION", connection);
        } catch (Exception ex) {
            ex.printStackTrace();
            dsMessage = "Something wrong occoured";
        }
        return SUCCESS;
    }

    private boolean validConnection() {
        sessionMap = ActionContext.getContext().getSession();
        if (connection == null) {
            connection = (ConnectionDTO) sessionMap.get("CURRENT_CONNECTION");
        } else {
            testConnection();
            if (dsMessage != null && !dsMessage.isEmpty()) {
                return false;
            }
        }

        if (connection == null) {
            dsMessage = "No connection was defined/save";
            return false;
        }

        return true;
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

}
