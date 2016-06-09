package br.unisc.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class DBUtil {

    public static boolean objectExists(EntityManager em, String table, String field, Integer value) {
        Query q = em.createNativeQuery("SELECT * FROM " + table
                + " WHERE " + field + " = " + value);
        return !q.getResultList().isEmpty();
    }

}
