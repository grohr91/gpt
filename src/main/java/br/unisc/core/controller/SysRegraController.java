package br.unisc.core.controller;

import br.unisc.web.model.SysRegra;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import org.apache.commons.lang.xwork.StringUtils;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class SysRegraController {

    protected EntityManager em;

    public SysRegraController(EntityManager em) {
        this.em = em;
    }

    public boolean compareByRegra(String valorAtributo, SysRegra sr) {
        if (StringUtils.isBlank(valorAtributo) || StringUtils.isBlank(sr.getVlRegra())) {
            if (StringUtils.isBlank(valorAtributo) && StringUtils.isBlank(sr.getVlRegra())) {
                return true;
            }
            return false;
        }
        if ("null".equals(valorAtributo) || "null".equals(sr.getVlRegra())) {
            if ("null".equals(valorAtributo) && "null".equals(sr.getVlRegra())) {
                return true;
            }
            return false;
        }

        if (sr.getAtributo().getTipoAtributo().getNmTipoAtributo().equals("Numérico")) {
            return comparaNumerico(valorAtributo, sr.getVlRegra(), sr.getOperacao().getNmOperacao());
        } else if (sr.getAtributo().getTipoAtributo().getNmTipoAtributo().equals("Alfanumérico")) {
            return comparaAlfanumerico(valorAtributo, sr.getVlRegra(), sr.getOperacao().getNmOperacao());
        } else if (sr.getAtributo().getTipoAtributo().getNmTipoAtributo().equals("Data")) {
            return comparaData(valorAtributo, sr.getVlRegra(), sr.getOperacao().getNmOperacao());
        }

        return false;
    }

    private boolean comparaNumerico(String valorAtributo, String vlRegra, String nmOperacao) {
        try {
            Float vlAttr = Float.parseFloat(valorAtributo);
            Float vlReg = Float.parseFloat(vlRegra);

            if ("Igual".equals(nmOperacao)) {
                return vlAttr.equals(vlReg);
            } else if ("Diferente".equals(nmOperacao)) {
                return !vlAttr.equals(vlReg);
            } else if ("Maior".equals(nmOperacao)) {
                return vlAttr.compareTo(vlReg) > 0;
            } else if ("Menor".equals(nmOperacao)) {
                return vlAttr.compareTo(vlReg) < 0;
            }

        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    private boolean comparaAlfanumerico(String valorAtributo, String vlRegra, String nmOperacao) {
        try {
            if ("Igual".equals(nmOperacao)) {
                return valorAtributo.toLowerCase().equals(vlRegra.toLowerCase());
            } else if ("Diferente".equals(nmOperacao)) {
                return !valorAtributo.toLowerCase().equals(vlRegra.toLowerCase());
            } else if ("Contém".equals(nmOperacao)) {
                return valorAtributo.toLowerCase().contains(vlRegra.toLowerCase());
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    private boolean comparaData(String valorAtributo, String vlRegra, String nmOperacao) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date vlAttr = sdf.parse(valorAtributo);
            Date vlReg = sdf.parse(vlRegra);

            if ("Igual".equals(nmOperacao)) {
                return vlAttr.equals(vlReg);
            } else if ("Diferente".equals(nmOperacao)) {
                return !vlAttr.equals(vlReg);
            } else if ("Maior".equals(nmOperacao)) {
                return vlAttr.compareTo(vlReg) > 0;
            } else if ("Menor".equals(nmOperacao)) {
                return vlAttr.compareTo(vlReg) < 0;
            }

        } catch (Exception ex) {
            return false;
        }
        return false;
    }

}
