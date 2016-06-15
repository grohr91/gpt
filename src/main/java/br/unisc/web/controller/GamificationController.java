package br.unisc.web.controller;

import br.unisc.core.controller.VwIndividuoGrupoDTOController;
import br.unisc.core.controller.VwIndividuoAtividadeDTOController;
import br.unisc.core.controller.VwGrupoAtividadeDTOController;
import br.unisc.web.dto.ConnectionDTO;
import br.unisc.core.dto.VwGrupoAtividadeDTO;
import br.unisc.core.dto.VwIndividuoAtividadeDTO;
import br.unisc.core.dto.VwIndividuoGrupoDTO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public class GamificationController {

    protected EntityManager em;
    protected ConnectionDTO conn;

    public GamificationController(EntityManager em, ConnectionDTO c) {
        this.em = em;
        this.conn = c;
    }

    /**
     * Insert or update all players and their respected groups
     *
     * @return
     */
    public String processVwIndividuoGrupo() {
        VwIndividuoGrupoDTOController igc = new VwIndividuoGrupoDTOController(em, conn);
        List<VwIndividuoGrupoDTO> individuoGrupoList = igc.findIndividuoGrupo();
        int countRight = 0;
        int countWrong = 0;
        for (VwIndividuoGrupoDTO ig : individuoGrupoList) {
            try {
//                igc.insertOrUpdate(ig);
                countRight++;
            } catch (Exception ex) {
                countWrong++;
            }
        }
        System.out.println("Indivíduos inseridos ou alterados: " + countRight + " de " + individuoGrupoList.size());
        System.out.println("Indivíduos não tratados: " + countWrong + " de " + individuoGrupoList.size());
        return ("Indivíduos inseridos ou alterados: " + countRight + " de " + individuoGrupoList.size());
    }

    /**
     * Insert new challanges to players
     *
     * @return
     */
    public String processVwIndividuoAtividade() {
        VwIndividuoAtividadeDTOController idc = new VwIndividuoAtividadeDTOController(em, conn);
        List< VwIndividuoAtividadeDTO> individuoDesafioList = idc.findIndividuoAtividade();
        int countRight = 0;
        int countWrong = 0;
        for (VwIndividuoAtividadeDTO id : individuoDesafioList) {
            try {
                idc.insertOrUpdate(id);
                countRight++;
            } catch (Exception ex) {
                countWrong++;
            }
        }
        System.out.println("Desafios de Indivíduos inseridos ou alterados: " + countRight + " de " + individuoDesafioList.size());
        System.out.println("\"Desafios de Indivíduos não tratados: " + countWrong + " de " + individuoDesafioList.size());
        return ("Desafios de Indivíduos inseridos ou alterados: " + countRight + " de " + individuoDesafioList.size());
    }

    /**
     * Insert new challanges to player groups
     *
     * @return
     */
    public String processVwGrupoAtividade() {
        VwGrupoAtividadeDTOController igc = new VwGrupoAtividadeDTOController(em, conn);
        List< VwGrupoAtividadeDTO> grupoDesafioList = igc.findGrupoAtividade();
        int countRight = 0;
        int countWrong = 0;
        for (VwGrupoAtividadeDTO gd : grupoDesafioList) {
            try {
                igc.insertOrUpdate(gd);
                countRight++;
            } catch (Exception ex) {
                countWrong++;
            }
        }
        System.out.println("Desafios de Grupos inseridos ou alterados: " + countRight + " de " + grupoDesafioList.size());
        System.out.println("Desafios de Grupos não tratados: " + countWrong + " de " + grupoDesafioList.size());
        return ("Desafios de Grupos inseridos ou alterados: " + countRight + " de " + grupoDesafioList.size());
    }
}
