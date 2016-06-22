/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.util;

import br.unisc.core.controller.ImportacaoController;
import br.unisc.web.model.SysConfiguracao;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Guilherme
 */
public class Automacao implements ServletContextListener {

    public static final Integer SEMANA = 604800000; // 24 horas x 7 dias
    EntityManager em;

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("GPT no ar!");

//        try {
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceTccUnit");
//            em = emf.createEntityManager();
//            List<SysConfiguracao> configuracaoList = em.createNamedQuery("", SysConfiguracao.class).getResultList();
//            
//            
//
//            rodaTarefaDeImportacao(Calendar.SUNDAY, 0, 0);
//        } catch (Exception ex) {
//
//        } finally {
//            em.close();
//        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("GPT: até mais");
    }

    public void rodaTarefaDeImportacao(int dia, int hora, int minuto) {
        Calendar dataInicio = new GregorianCalendar();
        dataInicio.set(Calendar.DAY_OF_WEEK, dia);
        dataInicio.set(Calendar.HOUR_OF_DAY, hora);
        dataInicio.set(Calendar.MINUTE, minuto);
        dataInicio.set(Calendar.SECOND, 00);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                importarConfiguracoes();
            }
        }, dataInicio.getTime(), SEMANA);
    }

    public void importarConfiguracoes() {
        try {
            em.getTransaction().begin();
            ImportacaoController importacaoController = new ImportacaoController(em);
            importacaoController.processarTodasImportacoes();
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Oops! Algo deu errado na importação");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

}
