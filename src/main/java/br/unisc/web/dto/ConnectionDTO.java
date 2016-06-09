package br.unisc.web.dto;

import br.unisc.util.DatabaseAware;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class ConnectionDTO implements DatabaseAware {

    private EntityManager em;

    private String nrIp;
    private Integer nrPort;
    private String nmSchema;
    private String nmDatabase;
    private String nmUser;
    private String cdPass;
    private Integer dbType;

    public String getNrIp() {
        return nrIp;
    }

    public void setNrIp(String nrIp) {
        this.nrIp = nrIp;
    }

    public Integer getNrPort() {
        return nrPort;
    }

    public void setNrPort(Integer nrPort) {
        this.nrPort = nrPort;
    }

    public String getNmSchema() {
        return nmSchema;
    }

    public void setNmSchema(String nmSchema) {
        this.nmSchema = nmSchema;
    }

    public String getNmDatabase() {
        return nmDatabase;
    }

    public void setNmDatabase(String nmDatabase) {
        this.nmDatabase = nmDatabase;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getCdPass() {
        return cdPass;
    }

    public void setCdPass(String cdPass) {
        this.cdPass = cdPass;
    }

    public String validateParams() {
        if (nmUser == null || nmUser.isEmpty()) {
            return "User cannot be blank";
        }
        if (cdPass == null || cdPass.isEmpty()) {
            return "Password cannot be blank";
        }
        if (nrIp == null || nrIp.isEmpty()) {
            return "Host cannot be blank";
        }
        if (nrPort == null || nrPort == 0) {
            return "Port cannot be blank";
        }
        if (nmDatabase == null || nmDatabase.isEmpty()) {
            return "Port cannot be blank";
        }
        return "";
    }

    public String open() {
        String validate = validateParams();
        if (!validate.isEmpty()) {
            return validate;
        }
        Map<String, String> properties = getMapByDbType();
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceOthersUnit", properties);
            em = emf.createEntityManager();
            em.getTransaction().begin();
        } catch (Exception ex) {
            ex.printStackTrace();
            close();
            return ex.getMessage();
        }
        return "";
    }

    public void close() {
        if (em != null && em.isOpen()) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    public void commit() {
        if (em != null && em.isOpen()) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().commit();
            }
            em.close();
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Integer getDbType() {
        return dbType;
    }

    public void setDbType(Integer dbType) {
        this.dbType = dbType;
    }

    private Map<String, String> getMapByDbType() {
        Map<String, String> properties = new HashMap<String, String>();

        properties.put("eclipselink.cache.shared.default", "false");
        properties.put("javax.persistence.jdbc.user", nmUser);
        properties.put("javax.persistence.jdbc.password", cdPass);

        if (POSTGRESQL == dbType) {
            properties.put("eclipselink.target-database", "PostgreSQL");
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + nrIp + ":" + nrPort.toString() + "/" + nmDatabase);
        } else if (MYSQL == dbType) {
            properties.put("eclipselink.target-database", "MySQL");
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("javax.persistence.jdbc.url", "jdbc:mysql://" + nrIp + ":" + nrPort.toString() + "/" + nmDatabase);
        }

        return properties;
    }

}
