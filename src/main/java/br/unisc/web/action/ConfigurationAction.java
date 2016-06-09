package br.unisc.web.action;

import br.unisc.web.controller.GamificationController;
import br.unisc.web.dto.ConnectionDTO;
import br.unisc.util.EMAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class ConfigurationAction extends ActionSupport implements EMAware {

    private EntityManager em;
    private ConnectionDTO connection;
    private String dsMessage, dsInfo;
    private Map<String, Object> sessionMap;

    public String main() throws Exception {
        try {
            connection = new ConnectionDTO();
            connection.setNrIp("localhost");
            connection.setNrPort(5433);
            connection.setNmDatabase("ideatennis");
            connection.setNmUser("postgres");
            connection.setCdPass("postgres");
            connection.setNmSchema("public");

        } catch (Exception ex) {
            ex.printStackTrace();
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

}
