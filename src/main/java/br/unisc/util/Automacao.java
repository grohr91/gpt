/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.util;

import br.unisc.core.controller.ImportacaoController;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    public static final Integer DIA = 86400000; // 24 horas

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("GPT no ar!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("GPT: até mais");
    }

    public void rodaTarefaDeImportacao() {
        Timer timer = new Timer();
        Calendar startingTime = new GregorianCalendar();
        startingTime.set(Calendar.HOUR_OF_DAY, 2);
        startingTime.set(Calendar.MINUTE, 00);
        startingTime.set(Calendar.SECOND, 00);

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                importarConfiguracoes();
            }
        }, startingTime.getTime(), DIA);

    }

    public void importarConfiguracoes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceTccUnit");
        EntityManager em = emf.createEntityManager();
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
        em.close();
    }

}
