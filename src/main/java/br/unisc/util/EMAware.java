/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.util;

import javax.persistence.EntityManager;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public interface EMAware {

    public void setEm(EntityManager em);

}
