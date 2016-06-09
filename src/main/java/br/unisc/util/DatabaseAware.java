/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.util;

/**
 *
 * @author m68663 - Guilherme Rohr
 */
public interface DatabaseAware {

    int POSTGRESQL = 1;
    int MYSQL = 2;

    String POSTGRES_TABLES_QUERY = "select table_name "
            + "from information_schema.tables "
            + "where TABLE_SCHEMA = ?1 "
            + "order by table_name ";
    String POSTGRES_TABLE_FIELD_QUERY = "SELECT column_name  "
            + "from information_schema.columns  "
            + "WHERE table_name = ?1  "
            + "ORDER BY column_name";
    String MYSQL_TABLES_QUERY = "select table_name "
            + "from information_schema.tables "
            + "where TABLE_SCHEMA = ?1 "
            + "order by table_name ";
    String MYSQL_TABLE_FIELD_QUERY = "SELECT COLUMN_NAME from information_schema.COLUMNS "
            + "WHERE TABLE_NAME = ?1 "
            + "ORDER BY COLUMN_KEY = 'PRI' DESC, COLUMN_NAME";

}
