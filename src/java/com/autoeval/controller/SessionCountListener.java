/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.controller;

import com.autoeval.entity.Representante;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class SessionCountListener implements HttpSessionListener, ServletContextListener {

    private final Logger LOGGER = Logger.getLogger(SessionCountListener.class);
    private static List<String> personasLogueadas;

    public static List<String> getPersonasLogueadas() {
        return personasLogueadas;
    }

    public static void setPersonasLogueadas(List<String> personasLogueadas) {
        SessionCountListener.personasLogueadas = personasLogueadas;
    }

    public static void addPersonaLogueada(String personaLogueada) {
        personasLogueadas.add(personaLogueada);
    }

    public void sessionCreated(final HttpSessionEvent event) {
        System.out.println("personasLogueadas: "+personasLogueadas.size());
    }

    public void sessionDestroyed(final HttpSessionEvent event) {
    
        try {
            for (int i = 0; i < personasLogueadas.size(); i++) {
                if (personasLogueadas.get(i).equals(event.getSession().getAttribute("personaLogueada"))) {
                    personasLogueadas.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error: ", e);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        personasLogueadas = new ArrayList<String>();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
