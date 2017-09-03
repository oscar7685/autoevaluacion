/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.controller;

import com.autoeval.entity.Proceso;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author acreditacion
 */
public class Subir extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Subir</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Subir at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
       
        HttpSession sesion = request.getSession();

        Proceso r = (Proceso) sesion.getAttribute("Proceso");
        try {
            String str = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/com/autoeval/files/anexos");
            // nos quedamos solo con el nombre y descartamos el path
            File fichero = new File(str);
            String[] ficheros = fichero.list();
            String aux = "";
            if (ficheros == null) {
            } else {
                for (int i = 0; i < ficheros.length; i++) {
                    String elementos[] = ficheros[i].split("\\.");
                    String elemento2[] = ficheros[i].split("-");
                    if (!aux.equals("")) {
                        if (elementos[1].equals("pdf")) {
                            if (elemento2[0].equals("" + r.getId())) {
                                aux += ",{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + "pdf2.png" + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                            }
                        } else {
                            if (elemento2[0].equals("" + r.getId())) {
                                aux += ",{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + ficheros[i] + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                            }

                        }

                    } else {
                        if (elementos[1].equals("pdf")) {
                            if (elemento2[0].equals("" + r.getId())) {
                                aux += "{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + "pdf2.png" + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                            }

                        } else {
                            if (elemento2[0].equals("" + r.getId())) {
                                aux += "{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + ficheros[i] + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                            }

                        }

                    }

                }
                System.out.println("existe 3: "+aux);
                writer.write("{\"files\":[" + aux + "]}");
            }

        } catch (Exception e) {
        } finally {
        }
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String file = (String) request.getParameter("file");
        String str = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/com/autoeval/files/anexos");
        String str2 = request.getSession().getServletContext().getRealPath("/thumbnails/");
        File fichero1 = new File(str + "\\" + file);
        File fichero2 = new File(str2 + "\\" + file);
        if (fichero1.delete()) {
            //    System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            //  System.out.println("El fichero no puede ser borrado");
        }
        if (fichero2.delete()) {
            //System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            //System.out.println("El fichero no puede ser borrado");
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        PrintWriter out = response.getWriter();
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();

        response.setContentType("text/plain");
        HttpSession sesion = request.getSession();

        Proceso r = (Proceso) sesion.getAttribute("Proceso");
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {

                    FileItem actual = null;
                    actual = item;
                    String fileName = actual.getName();
                    String str = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/com/autoeval/files/anexos");
                    String str2 = request.getSession().getServletContext().getRealPath("/thumbnails/");

                    fileName = "" + r.getId() + "-" + fileName;
                    // nos quedamos solo con el nombre y descartamos el path
                    File fichero = new File(str + "\\" + fileName);

                    // escribimos el fichero colgando del nuevo path
                    actual.write(fichero);

                    String extension[] = fileName.split("\\.");

                    if (!extension[1].equals("pdf")) {
                        BufferedImage img = new BufferedImage(80, 60, BufferedImage.TYPE_INT_RGB);
                        img.createGraphics().drawImage(ImageIO.read(fichero).getScaledInstance(80, 60, Image.SCALE_SMOOTH), 0, 0, null);
                        ImageIO.write(img, "jpg", new File(str2 + "\\" + fileName));
                    }

                    // nos quedamos solo con el nombre y descartamos el path
                    File fichero1 = new File(str);
                    String[] ficheros = fichero1.list();
                    String aux = "";
                    if (ficheros == null) {
                    } else {
                        for (int i = 0; i < ficheros.length; i++) {
                            String elementos[] = ficheros[i].split("\\.");
                            if (!aux.equals("")) {
                                if (elementos[1].equals("pdf")) {
                                    aux += ",{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + "pdf2.png" + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                                } else {
                                    aux += ",{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + ficheros[i] + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                                }

                            } else {
                                if (elementos[1].equals("pdf")) {
                                    aux += "{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + "pdf2.png" + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                                } else {
                                    aux += "{\"name\":\"" + ficheros[i] + "\",\"size\":\"" + 2000 + "\",\"url\":\"/autoevaluacion/DescargarAnexo?f=" + ficheros[i] + "\",\"thumbnailUrl\":\"/autoevaluacion/thumbnails/" + ficheros[i] + "\",\"deleteUrl\":\"/autoevaluacion/Subir?file=" + ficheros[i] + "\",\"deleteType\":\"DELETE\",\"type\":\"" + elementos[1] + "\"}";
                                }

                            }

                        }
                        writer.write("{\"files\":[" + aux + "]}");
                    }
                    break; // assume we only get one file at a time
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }


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
