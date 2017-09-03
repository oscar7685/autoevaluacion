/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author acreditacion
 */
public class DescargarFormato extends HttpServlet {
private final Logger LOGGER = Logger.getLogger(DescargarFormato.class);
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fuente = (String) request.getParameter("fuente");
        try {
            String ruta = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/com/autoeval/files/");

            if (fuente.equals("Estudiante")) {
                ruta += "\\formato_estudiantes.xlsx";
            } else if (fuente.equals("Docente")) {
                ruta += "\\formato_docentes.xlsx";
            } else if (fuente.equals("Administrativo")) {
                ruta += "\\formato_administrativos.xlsx";
            } else if (fuente.equals("Directivo")) {
                ruta += "\\formato_directivos.xlsx";
            } else if (fuente.equals("Egresado")) {
                ruta += "\\formato_egresados.xlsx";
            } else if (fuente.equals("Empleador")) {
                ruta += "\\formato_empleadores.xlsx";
            }



            File f2 = new File(ruta);
            FileInputStream inStream = new FileInputStream(f2);
            OutputStream outStream = response.getOutputStream();

            response.setContentType("application/force-download");
            response.setContentLength((int) f2.length());
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + f2.getName());//fileName

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inStream.close();
            outStream.close();
        } catch (IOException e) {
           LOGGER.error("Se ha presentado un error", e);
        } catch (Exception e) {
            LOGGER.error("Se ha presentado un error", e);
        } finally {
            //out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
