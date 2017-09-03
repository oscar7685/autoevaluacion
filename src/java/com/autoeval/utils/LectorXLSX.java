package com.autoeval.utils;

import com.autoeval.ejb.AdministrativoFacade;
import com.autoeval.ejb.DirectorprogramaFacade;
import com.autoeval.ejb.DocenteFacade;
import com.autoeval.ejb.EgresadoFacade;
import com.autoeval.ejb.EmpleadorFacade;
import com.autoeval.ejb.EstudianteFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.PersonaFacade;
import com.autoeval.entity.Administrativo;
import com.autoeval.entity.Directorprograma;
import com.autoeval.entity.Docente;
import com.autoeval.entity.Egresado;
import com.autoeval.entity.Empleador;
import com.autoeval.entity.Estudiante;
import com.autoeval.entity.Persona;
import com.autoeval.entity.Proceso;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import javax.transaction.UserTransaction;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.log4j.Logger;

public class LectorXLSX {

    private final Logger LOGGER = Logger.getLogger(LectorXLSX.class);
    EmpleadorFacade empleadorFacade = lookupEmpleadorFacadeBean();
    DirectorprogramaFacade directorprogramaFacade = lookupDirectorprogramaFacadeBean();
    AdministrativoFacade administrativoFacade = lookupAdministrativoFacadeBean();
    EgresadoFacade egresadoFacade = lookupEgresadoFacadeBean();
    DocenteFacade docenteFacade = lookupDocenteFacadeBean();
    EstudianteFacade estudianteFacade = lookupEstudianteFacadeBean();
    FuenteFacade fuenteFacade = lookupFuenteFacadeBean();
    PersonaFacade personaFacade = lookupPersonaFacadeBean();
    PrintWriter out;
    public boolean errorGlobal = false;
    public boolean personaExistente = false;

    public LectorXLSX(File fileName, Proceso p, HttpServletResponse response, String fuente) throws ParseException, IOException {
        out = response.getWriter();
        if (fuente.equals("Estudiante")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
                List cellDataList = new ArrayList();
                XSSFSheet hssfSheet = workBook.getSheetAt(0);
                Iterator rowIterator = hssfSheet.rowIterator();
                while (rowIterator.hasNext()) {
                    XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                    List cellTempList = new ArrayList();//lista Temporal para agregar cada uno de los 6 datos de una fila
                    for (int j = 0; j < 4; j++) {
                        XSSFCell hssfCell = (XSSFCell) hssfRow.getCell(j);
                        cellTempList.add(hssfCell);
                    }
                    if (!((cellTempList.get(0) == null)
                            && (cellTempList.get(1) == null)
                            && (cellTempList.get(2) == null)
                            && (cellTempList.get(3) == null))) {
                        cellDataList.add(cellTempList);
                    }
                }
                Leer(cellDataList, 0, p, fuente);


            } catch (Exception e) {
                LOGGER.error("ha ocurrido un error", e);
                e.printStackTrace();
            }
        } else if (fuente.equals("Docente") || fuente.equals("Egresado") || fuente.equals("Administrativo") || fuente.equals("Empleador")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
                List cellDataList = new ArrayList();
                XSSFSheet hssfSheet = workBook.getSheetAt(0);
                Iterator rowIterator = hssfSheet.rowIterator();
                while (rowIterator.hasNext()) {
                    XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                    List cellTempList = new ArrayList();//lista Temporal para agregar cada uno de los 3 datos de una fila
                    for (int j = 0; j < 3; j++) {
                        XSSFCell hssfCell = (XSSFCell) hssfRow.getCell(j);
                        cellTempList.add(hssfCell);
                    }
                    if (!((cellTempList.get(0) == null)
                            && (cellTempList.get(1) == null)
                            && (cellTempList.get(2) == null))) {
                        cellDataList.add(cellTempList);
                    }
                }
                Leer(cellDataList, 0, p, fuente);


            } catch (Exception e) {
                LOGGER.error("ha ocurrido un error", e);
                e.printStackTrace();
            }
        } else if (fuente.equals("Directivo")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
                List cellDataList = new ArrayList();
                XSSFSheet hssfSheet = workBook.getSheetAt(0);
                Iterator rowIterator = hssfSheet.rowIterator();
                while (rowIterator.hasNext()) {
                    XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                    List cellTempList = new ArrayList();//lista Temporal para agregar cada uno de los 3 datos de una fila
                    for (int j = 0; j < 2; j++) {
                        XSSFCell hssfCell = (XSSFCell) hssfRow.getCell(j);
                        cellTempList.add(hssfCell);
                    }
                    if (!((cellTempList.get(0) == null)
                            && (cellTempList.get(1) == null))) {
                        cellDataList.add(cellTempList);
                    }
                }
                Leer(cellDataList, 0, p, fuente);


            } catch (Exception e) {
                LOGGER.error("ha ocurrido un error", e);
                e.printStackTrace();
            }
        }


    }

    private void Leer(List cellDataList, int i0, Proceso pr, String fuente) {
        boolean flag = false; //avisa si ha ocurrido un error de validación
        String errores = "";
        for (int i = 1; i < cellDataList.size(); i++) { //recorre las filas
            personaExistente = false;
            Persona p = new Persona();
            Estudiante e = new Estudiante();
            Docente doc = new Docente();
            Egresado egr = new Egresado();
            Administrativo adm = new Administrativo();
            Directorprograma dpro = new Directorprograma();
            Empleador emp = new Empleador();
            List cellTempList = (List) cellDataList.get(i);
            for (int j = 0; j < cellTempList.size(); j++) {//recorre las celdas
                XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                if (i0 == 0) {
                    if (j == 0) {// identificacion
                        try {
                             String aux = "" ;
                            if (!fuente.equals("Empleador")) {
                               aux+= new BigDecimal(Double.valueOf(hssfCell.getNumericCellValue())).toPlainString();
                            } else {
                                 aux += "" + hssfCell.getStringCellValue();
                            }

                            if (!aux.equals("0")) {
                                p = personaFacade.find(aux);
                                if (p == null) {
                                    p = new Persona();
                                    p.setId(aux);
                                    p.setPassword(aux);
                                } else {
                                    personaExistente = true;
                                }


                            } else {
                                errores += "<br/>ha ocurrido un error de validación con la identificación en el registro #" + (i + 1);
                                flag = true;
                            }

                        } catch (Exception exc0) {
                            LOGGER.error("ha ocurrido un error de validación con la identificación en el registro #" + (i + 1), exc0);
                            errores += "<br/>ha ocurrido un error de validación con la identificación en el registro #" + (i + 1);
                            flag = true;
                        }


                    } else if (j == 1 && !personaExistente) {//nombre
                        try {
                            if (!hssfCell.toString().equals("")) {
                                p.setNombre(hssfCell.toString());
                                p.setApellido(".");
                                p.setMail("mail@gmail.com");
                            } else {
                                errores += "<br/>ha ocurrido un error de validación con el nombre en el registro #" + (i + 1);
                                flag = true;
                            }
                        } catch (Exception exc2) {
                            LOGGER.error("ha ocurrido un error de validación con la identificación en el registro #" + (i + 1), exc2);
                            errores += "<br/>ha ocurrido un error de validación con el nombre en el registro #" + (i + 1);
                            flag = true;
                        }
                    } else if (j == 2) {
                        try {
                            if (!hssfCell.toString().equals("")) {
                                if (fuente.equals("Estudiante")) {
                                    e.setCurso(hssfCell.toString());
                                } else if (fuente.equals("Docente")) {
                                    doc.setTipo(hssfCell.toString());
                                } else if (fuente.equals("Egresado")) {
                                    egr.setTipo(hssfCell.toString());
                                } else if (fuente.equals("Administrativo")) {
                                    adm.setTipo(hssfCell.toString());
                                } else if (fuente.equals("Empleador")) {
                                    emp.setEmpresa(hssfCell.toString());
                                }
                            } else {
                                errores += "<br/>ha ocurrido un error de validación con el curso en el registro #" + (i + 1);
                                flag = true;
                            }
                        } catch (Exception ex3) {
                            LOGGER.error("ha ocurrido un error de validación con la identificación en el registro #" + (i + 1), ex3);
                            errores += "<br/>ha ocurrido un error de validación con el apellido en el registro #" + (i + 1);
                            flag = true;
                        }
                    } else if (j == 3) {//tipo
                        try {
                            e.setTipo(hssfCell.toString());
                        } catch (Exception exc) {
                            LOGGER.error("ha ocurrido un error de validación con la identificación en el registro #" + (i + 1), exc);
                            errores += "<br/>ha ocurrido un error de validación con el semestre en el registro #" + (i + 1);
                            flag = true;
                        }
                    }
                }
            }

            if (i0 == 0) {
                if (p.getId() != null) {
                    if (!personaExistente) {
                        personaFacade.create(p);
                        p = personaFacade.find(p.getId());
                    }
                    if (fuente.equals("Estudiante")) {
                        Estudiante aux = estudianteFacade.findBySingle2("procesoId", pr, "personaId", p);
                        if (!personaExistente || aux == null) {
                            e.setPersonaId(p);
                            e.setId("" + pr.getId() + "-" + p.getId());
                            e.setAnio("2016");
                            e.setPeriodo("01");
                            e.setSemestre("03");
                            e.setProcesoId(pr);
                            e.setProgramaId(pr.getProgramaId());
                            String tipo = e.getTipo();
                            if (tipo.equals("OFICIALES") || tipo.equals("CADETES")) {
                                e.setFuenteId(fuenteFacade.find(1));
                            } else if (tipo.equals("MAESTRIA")) {
                                e.setFuenteId(fuenteFacade.find(8));
                            } else if (tipo.equals("ESPECIALIZACION")) {
                                e.setFuenteId(fuenteFacade.find(7));
                            }
                            estudianteFacade.create(e);
                        }
                    } else if (fuente.equals("Docente")) {
                        Docente aux = docenteFacade.findBySingle2("procesoId", pr, "personaId", p);
                        if (!personaExistente || aux == null) {
                            doc.setPersonaId(p);
                            doc.setProcesoId(pr);
                            doc.setProgramaId(pr.getProgramaId());
                            String tipo = doc.getTipo();
                            if (tipo.equals("MILITAR") || tipo.equals("NOMINA") || tipo.equals("OCASIONALES")) {
                                doc.setFuenteId(fuenteFacade.find(2));
                            } else if (tipo.equals("CATEDRA")) {
                                doc.setFuenteId(fuenteFacade.find(11));
                            }
                            docenteFacade.create(doc);
                        }
                    } else if (fuente.equals("Egresado")) {
                        Egresado aux = egresadoFacade.findBySingle2("procesoId", pr, "personaId", p);
                        if (!personaExistente || aux == null) {
                            egr.setPersonaId(p);
                            egr.setProcesoId(pr);
                            egr.setProgramaId(pr.getProgramaId());
                            String tipo = egr.getTipo();
                            if (tipo.equals("PREGRADO")) {
                                egr.setFuenteId(fuenteFacade.find(4));
                            } else if (tipo.equals("ESPECIALIZACION")) {
                                egr.setFuenteId(fuenteFacade.find(9));
                            } else if (tipo.equals("MAESTRIA")) {
                                egr.setFuenteId(fuenteFacade.find(10));
                            }
                            egresadoFacade.create(egr);
                        }
                    } else if (fuente.equals("Administrativo")) {
                        Administrativo aux = administrativoFacade.findBySingle2("procesoId", pr, "personaId", p);
                        if (!personaExistente || aux == null) {
                            adm.setPersonaId(p);
                            adm.setProcesoId(pr);
                            adm.setProgramaId(pr.getProgramaId());
                            adm.setFuenteId(fuenteFacade.find(3));
                            adm.setCargo("cargo");
                            administrativoFacade.create(adm);
                        }
                    } else if (fuente.equals("Empleador")) {
                        Empleador aux = empleadorFacade.findBySingle2("procesoId", pr, "personaId", p);
                        if (!personaExistente || aux == null) {
                            emp.setPersonaId(p);
                            emp.setProcesoId(pr);
                            emp.setProgramaId(pr.getProgramaId());
                            emp.setFuenteId(fuenteFacade.find(6));
                            emp.setCargo("cargo");
                            empleadorFacade.create(emp);
                        }
                    } else if (fuente.equals("Directivo")) {
                        Directorprograma aux = directorprogramaFacade.findBySingle2("procesoId", pr, "personaId", p);
                        if (!personaExistente || aux == null) {
                            dpro.setPersonaId(p);
                            dpro.setProcesoId(pr);
                            dpro.setProgramaId(pr.getProgramaId());
                            dpro.setFuenteId(fuenteFacade.find(5));
                            directorprogramaFacade.create(dpro);
                        }
                    }

                }
            }
        } //termina la hoja actual

        if (flag) {
            errorGlobal = flag;
            out.print("{\"errores\":\"" + errores + "\"}");
        }
    }

    private PersonaFacade lookupPersonaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PersonaFacade) c.lookup("java:global/autoevaluacion/PersonaFacade!com.autoeval.ejb.PersonaFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private FuenteFacade lookupFuenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FuenteFacade) c.lookup("java:global/autoevaluacion/FuenteFacade!com.autoeval.ejb.FuenteFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EstudianteFacade lookupEstudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EstudianteFacade) c.lookup("java:global/autoevaluacion/EstudianteFacade!com.autoeval.ejb.EstudianteFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DocenteFacade lookupDocenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DocenteFacade) c.lookup("java:global/autoevaluacion/DocenteFacade!com.autoeval.ejb.DocenteFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EgresadoFacade lookupEgresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EgresadoFacade) c.lookup("java:global/autoevaluacion/EgresadoFacade!com.autoeval.ejb.EgresadoFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private AdministrativoFacade lookupAdministrativoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (AdministrativoFacade) c.lookup("java:global/autoevaluacion/AdministrativoFacade!com.autoeval.ejb.AdministrativoFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DirectorprogramaFacade lookupDirectorprogramaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DirectorprogramaFacade) c.lookup("java:global/autoevaluacion/DirectorprogramaFacade!com.autoeval.ejb.DirectorprogramaFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EmpleadorFacade lookupEmpleadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EmpleadorFacade) c.lookup("java:global/autoevaluacion/EmpleadorFacade!com.autoeval.ejb.EmpleadorFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}