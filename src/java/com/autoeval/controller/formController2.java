/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.controller;

import com.autoeval.ejb.AsignacionencuestaFacade;
import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.EncuestaFacade;
import com.autoeval.ejb.FactorFacade;
import com.autoeval.ejb.FacultadFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.IndicadorFacade;
import com.autoeval.ejb.InstrumentoFacade;
import com.autoeval.ejb.ModeloFacade;
import com.autoeval.ejb.MuestraFacade;
import com.autoeval.ejb.PonderacioncaracteristicaFacade;
import com.autoeval.ejb.PonderacionfactorFacade;
import com.autoeval.ejb.PreguntaFacade;
import com.autoeval.ejb.ProcesoFacade;
import com.autoeval.ejb.ProgramaFacade;
import com.autoeval.ejb.RepresentanteFacade;
import com.autoeval.entity.Asignacionencuesta;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Factor;
import com.autoeval.entity.Facultad;
import com.autoeval.entity.Fuente;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import com.autoeval.entity.Representante;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Ususario
 */
public class formController2 extends HttpServlet {

    @EJB
    private FacultadFacade facultadFacade;
    @EJB
    private ProcesoFacade procesoFacade;
    @EJB
    private ProgramaFacade programaFacade;
    @EJB
    private RepresentanteFacade representanteFacade;
    @EJB
    private FuenteFacade fuenteFacade;
    @EJB
    private AsignacionencuestaFacade asignacionencuestaFacade;
    @EJB
    private InstrumentoFacade instrumentoFacade;
    @EJB
    private EncuestaFacade encuestaFacade;
    @EJB
    private PreguntaFacade preguntaFacade;
    @EJB
    private IndicadorFacade indicadorFacade;
    @EJB
    private CaracteristicaFacade caracteristicaFacade;
    @EJB
    private FactorFacade factorFacade;
    @EJB
    private ModeloFacade modeloFacade;
    @EJB
    private PonderacionfactorFacade ponderacionfactorFacade;
    @EJB
    private PonderacioncaracteristicaFacade PonderacioncaracteristicaFacade;
    @EJB
    private MuestraFacade muestraFacade;
    private final Logger LOGGER = Logger.getLogger(formController2.class);

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
        HttpSession sesion = request.getSession();
        String action = (String) request.getParameter("action");

        try {
            if (action.equals("indexCC")) {
                String url = "/WEB-INF/vista/comiteCentral/index.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            } else if (action.equals("finalizarProCC")) {

                String idM = request.getParameter("id");

                Proceso p = procesoFacade.find(Integer.parseInt(idM));


                java.util.Date date = new java.util.Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(date);
                p.setFechacierre(fecha);
                procesoFacade.edit(p);


            } else if (action.equals("ejecutarProCC")) {

                System.out.println("ejecutando");

                String idM = request.getParameter("id");

                Proceso p = procesoFacade.find(Integer.parseInt(idM));

                if (!ponderacionfactorFacade.findByList("procesoId", p).isEmpty() && !PonderacioncaracteristicaFacade.findByList("procesoId", p).isEmpty() && !muestraFacade.findByList("procesoId", p).isEmpty()) {
                    java.util.Date date = new java.util.Date();
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String fecha = sdf.format(date);
                    p.setFechainicio(fecha);
                    p.setFechacierre("--");
                    procesoFacade.edit(p);
                    out.println(1);
                } else {
                    out.println(0);
                }
            } else if (action.equals("controlPanelCC")) {

                sesion.setAttribute("listProcesos", procesoFacade.findAll());

                String url = "/WEB-INF/vista/comiteCentral/control/controlPanel.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            } else {
                if (action.toLowerCase().contains("modelo")) {
                    if (action.equals("crearModelo")) {
                        String nombre = (String) request.getParameter("nombre");
                        String descripcion = (String) request.getParameter("descripcion");
                        java.util.Date date = new java.util.Date();
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                        String fecha = sdf.format(date);
                        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha2 = null;
                        try {

                            fecha2 = formatoDelTexto.parse(fecha);

                        } catch (ParseException ex) {

                            ex.printStackTrace();

                        }
                        Modelo m = new Modelo();
                        m.setFechacreacion(fecha2);
                        m.setDescripcion(descripcion);
                        m.setNombre(nombre);
                        modeloFacade.create(m);
                    } else {
                        if (action.equals("crearModeloCC")) {
                            String url = "/WEB-INF/vista/comiteCentral/modelo/crear.jsp";
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            if (action.equals("listarModeloCC")) {
                                String url = "/WEB-INF/vista/comiteCentral/modelo/listar.jsp";
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                sesion.setAttribute("listaM", modeloFacade.findAll());
                                rd.forward(request, response);

                            } else {
                                if (action.equals("entrarModeloCC")) {
                                    String idM = request.getParameter("id");
                                    Modelo m = modeloFacade.find(Integer.parseInt(idM));
                                    sesion.setAttribute("modelo", m);
                                    sesion.setAttribute("listaF", factorFacade.findByModelo(m));
                                    sesion.setAttribute("listaC", caracteristicaFacade.findByModelo(m));
                                    sesion.setAttribute("listaI", indicadorFacade.findByModelo(m));
                                    sesion.setAttribute("listaP", preguntaFacade.findByModelo(m));
                                    sesion.setAttribute("listaE", encuestaFacade.findByModelo(m));
                                    sesion.setAttribute("listaFu", fuenteFacade.findAll());
                                    sesion.setAttribute("instrumentos", instrumentoFacade.findAll());
                                    String url = "/WEB-INF/vista/comiteCentral/inicio.jsp";
                                    RequestDispatcher rd = request.getRequestDispatcher(url);
                                    rd.forward(request, response);

                                } else {
                                    if (action.equals("editarModeloCC")) {
                                        String id = request.getParameter("id");
                                        Modelo m = modeloFacade.find(Integer.parseInt(id));
                                        sesion.setAttribute("modelo", m);
                                        String url = "/WEB-INF/vista/comiteCentral/modelo/editar.jsp";
                                        RequestDispatcher rd = request.getRequestDispatcher(url);
                                        rd.forward(request, response);

                                    } else {
                                        if (action.equals("editarModelo")) {
                                            Modelo m = (Modelo) sesion.getAttribute("modelo");
                                            String nombre = (String) request.getParameter("nombre");
                                            String descripcion = (String) request.getParameter("descripcion");
                                            m.setDescripcion(descripcion);
                                            m.setNombre(nombre);
                                            modeloFacade.edit(m);
                                        }
                                    }

                                }
                            }
                        }
                    }
                } else if (action.equals("listarProgramasCC")) {
                    String url = "/WEB-INF/vista/comiteCentral/programa/listar.jsp";
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    sesion.setAttribute("listaPro", programaFacade.findAll());
                    rd.forward(request, response);
                } else if (action.equals("crearProgramaCC")) {
                    String url = "/WEB-INF/vista/comiteCentral/programa/crear.jsp";
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    sesion.setAttribute("listaFac", facultadFacade.findAll());
                    rd.forward(request, response);
                } else if (action.equals("crearPrograma")) {
                    String nombre = request.getParameter("nombre");
                    String descripcion = request.getParameter("descripcion");
                    String modalidad = request.getParameter("modalidad");
                    String tipoformacion = request.getParameter("tipoformacion");
                    String facultad = request.getParameter("facultad");
                    Facultad faux = facultadFacade.find(Integer.parseInt(facultad));
                    Programa p = new Programa();
                    p.setNombre(nombre);
                    p.setDescripcion(descripcion);
                    p.setModalidad(modalidad);
                    p.setTipoformacion(tipoformacion);
                    p.setFacultadId(faux);
                    programaFacade.create(p);
                } else if (action.equals("editarProgramaCC")) {
                    String id = request.getParameter("id");
                    Programa p2 = programaFacade.find(Integer.parseInt(id));
                    sesion.setAttribute("programa", p2);
                    sesion.setAttribute("listaFac", facultadFacade.findAll());
                    String url = "/WEB-INF/vista/comiteCentral/programa/editar.jsp";
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);

                } else if (action.equals("editarPrograma")) {
                    String nombre = request.getParameter("nombre");
                    String descripcion = request.getParameter("descripcion");
                    String modalidad = request.getParameter("modalidad");
                    String tipoformacion = request.getParameter("tipoformacion");
                    String facultad = request.getParameter("facultad");
                    Facultad faux = facultadFacade.find(Integer.parseInt(facultad));
                    Programa p = (Programa) sesion.getAttribute("programa");
                    p.setNombre(nombre);
                    p.setDescripcion(descripcion);
                    p.setModalidad(modalidad);
                    p.setTipoformacion(tipoformacion);
                    p.setFacultadId(faux);
                    programaFacade.edit(p);
                } else {
                    if (action.toLowerCase().contains("factor")) {
                        if (action.equals("crearFactor")) {
                            String codigo = (String) request.getParameter("codigo");
                            String nombre = (String) request.getParameter("nombre");
                            Modelo m2 = (Modelo) sesion.getAttribute("modelo");
                            Factor f = new Factor();
                            f.setCodigo(codigo);
                            f.setNombre(nombre);
                            f.setModeloId(m2);
                            List<Caracteristica> aux = new ArrayList<Caracteristica>();
                            List<Caracteristica> listadeCaracteristicas = (List<Caracteristica>) sesion.getAttribute("listaC");
                            if (listadeCaracteristicas != null) {
                                for (int i = 0; i < listadeCaracteristicas.size(); i++) {
                                    if (request.getParameter("C" + listadeCaracteristicas.get(i).getId()).equals("1")) {
                                        aux.add(listadeCaracteristicas.get(i));

                                    }
                                }
                            }
                            f.setCaracteristicaList(aux);
                            factorFacade.create(f);
                            sesion.setAttribute("listaF", factorFacade.findByModelo(m2));
                        } else {
                            if (action.equals("listarFactoresCC")) {
                                String url = "/WEB-INF/vista/comiteCentral/factor/listar.jsp";
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            } else {
                                if (action.equals("crearFactorCC")) {
                                    String url = "/WEB-INF/vista/comiteCentral/factor/crear.jsp";
                                    RequestDispatcher rd = request.getRequestDispatcher(url);
                                    rd.forward(request, response);
                                } else {
                                    if (action.equals("editarFactorCC")) {
                                        String id = request.getParameter("id");
                                        Factor f = factorFacade.find(Integer.parseInt(id));
                                        f.setCaracteristicaList(caracteristicaFacade.findByFactor(f));
                                        sesion.setAttribute("factor", f);
                                        String url = "/WEB-INF/vista/comiteCentral/factor/editar.jsp";
                                        RequestDispatcher rd = request.getRequestDispatcher(url);
                                        rd.forward(request, response);

                                    } else {
                                        if (action.equals("editarFactor")) {
                                            Factor f = (Factor) sesion.getAttribute("factor");
                                            String nombre = (String) request.getParameter("nombre");
                                            String codigo = (String) request.getParameter("codigo");
                                            f.setCodigo(codigo);
                                            f.setNombre(nombre);

                                            List<Caracteristica> actuales = caracteristicaFacade.findByFactor(f);
                                            if (actuales != null) {
                                                for (int i = 0; i < actuales.size(); i++) {
                                                    actuales.get(i).setFactorId(null);
                                                    caracteristicaFacade.edit(actuales.get(i));

                                                }
                                            }


                                            List<Caracteristica> aux = new ArrayList<Caracteristica>();
                                            List<Caracteristica> listadeCaracteristicas = (List<Caracteristica>) sesion.getAttribute("listaC");
                                            if (listadeCaracteristicas != null) {
                                                for (int i = 0; i < listadeCaracteristicas.size(); i++) {
                                                    if (request.getParameter("C" + listadeCaracteristicas.get(i).getId()).equals("1")) {
                                                        aux.add(listadeCaracteristicas.get(i));
                                                        listadeCaracteristicas.get(i).setFactorId(f);
                                                        caracteristicaFacade.edit(listadeCaracteristicas.get(i));

                                                    }
                                                }
                                            }
                                            f.setCaracteristicaList(aux);
                                            factorFacade.edit(f);
                                            Modelo m = (Modelo) sesion.getAttribute("modelo");
                                            sesion.setAttribute("listaF", factorFacade.findByModelo(m));
                                            sesion.setAttribute("listaC", caracteristicaFacade.findByModelo(m));
                                        }

                                    }
                                }
                            }

                        }
                    } else {
                        if (action.toLowerCase().contains("caracteristica")) {
                            if (action.equals("crearCaracteristica")) {
                                String codigo = (String) request.getParameter("codigo");
                                String nombre = (String) request.getParameter("nombre");
                                String factor = (String) request.getParameter("factor");
                                Factor f = factorFacade.find(Integer.parseInt(factor));
                                Modelo m2 = (Modelo) sesion.getAttribute("modelo");
                                Caracteristica c = new Caracteristica();
                                c.setCodigo(codigo);
                                c.setNombre(nombre);
                                c.setModeloId(m2);
                                c.setFactorId(f);
                                List<Indicador> aux = new ArrayList<Indicador>();
                                List<Indicador> listadeIndicadores = (List<Indicador>) request.getAttribute("listaI");
                                if (listadeIndicadores != null) {
                                    for (int i = 0; i < listadeIndicadores.size(); i++) {
                                        if (request.getParameter("I" + listadeIndicadores.get(i).getId()).equals("1")) {
                                            aux.add(listadeIndicadores.get(i));

                                        }
                                    }
                                }
                                c.setIndicadorList(aux);
                                caracteristicaFacade.create(c);
                                sesion.setAttribute("listaC", caracteristicaFacade.findByModelo(m2));
                            } else {
                                if (action.equals("crearCaracteristicaCC")) {
                                    String url = "/WEB-INF/vista/comiteCentral/caracteristica/crear.jsp";
                                    Modelo m = (Modelo) sesion.getAttribute("modelo");
                                    sesion.setAttribute("listaF", factorFacade.findByModelo(m));
                                    RequestDispatcher rd = request.getRequestDispatcher(url);
                                    rd.forward(request, response);
                                } else {
                                    if (action.equals("listarCaracteristicasCC")) {
                                        String url = "/WEB-INF/vista/comiteCentral/caracteristica/listar.jsp";
                                        RequestDispatcher rd = request.getRequestDispatcher(url);
                                        rd.forward(request, response);
                                    } else {
                                        if (action.equals("editarCaracteristicaCC")) {
                                            String id = request.getParameter("id");
                                            Caracteristica c = caracteristicaFacade.find(Integer.parseInt(id));
                                            sesion.setAttribute("caracteristica", c);
                                            String url = "/WEB-INF/vista/comiteCentral/caracteristica/editar.jsp";
                                            RequestDispatcher rd = request.getRequestDispatcher(url);
                                            rd.forward(request, response);

                                        } else {
                                            if (action.equals("editarCaracteristica")) {
                                                Caracteristica c = (Caracteristica) sesion.getAttribute("caracteristica");
                                                String nombre = (String) request.getParameter("nombre");
                                                String codigo = (String) request.getParameter("codigo");
                                                String factor = (String) request.getParameter("factor");
                                                c.setCodigo(codigo);
                                                c.setNombre(nombre);
                                                c.setFactorId(factorFacade.find(Integer.parseInt(factor)));
                                                List<Indicador> actuales = indicadorFacade.findByCaracteristica(c);
                                                if (actuales != null) {
                                                    for (int i = 0; i < actuales.size(); i++) {
                                                        actuales.get(i).setCaracteristicaId(null);
                                                        indicadorFacade.edit(actuales.get(i));

                                                    }
                                                }


                                                List<Indicador> aux = new ArrayList<Indicador>();
                                                List<Indicador> lista = (List<Indicador>) sesion.getAttribute("listaI");
                                                if (lista != null) {
                                                    for (int i = 0; i < lista.size(); i++) {
                                                        if (request.getParameter("I" + lista.get(i).getId()).equals("1")) {
                                                            aux.add(lista.get(i));
                                                            lista.get(i).setCaracteristicaId(c);
                                                            indicadorFacade.edit(lista.get(i));

                                                        }
                                                    }
                                                }
                                                c.setIndicadorList(aux);

                                                caracteristicaFacade.edit(c);
                                                Modelo m = (Modelo) sesion.getAttribute("modelo");
                                                sesion.setAttribute("listaC", caracteristicaFacade.findByModelo(m));
                                                sesion.setAttribute("listaI", indicadorFacade.findByModelo(m));
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (action.toLowerCase().contains("indicador")) {
                                if (action.equals("crearIndicador")) {
                                    String codigo = (String) request.getParameter("codigo");
                                    String nombre = (String) request.getParameter("nombre");
                                    String caracteristica = (String) request.getParameter("caracteristica");
                                    Caracteristica c = caracteristicaFacade.find(Integer.parseInt(caracteristica));
                                    Modelo m2 = (Modelo) sesion.getAttribute("modelo");
                                    List<Pregunta> listadePreguntas = (List<Pregunta>) sesion.getAttribute("listaP");
                                    List<Pregunta> aux = new ArrayList<Pregunta>();
                                    for (int i = 0; i < listadePreguntas.size(); i++) {
                                        if (request.getParameter("P" + listadePreguntas.get(i).getId()).equals("1")) {
                                            aux.add(listadePreguntas.get(i));

                                        }
                                    }
                                    Indicador i = new Indicador();
                                    i.setCodigo(codigo);
                                    i.setNombre(nombre);
                                    i.setModeloId(m2);
                                    i.setInstrumentoList(null);
                                    i.setCaracteristicaId(c);
                                    i.setPreguntaList(aux);
                                    indicadorFacade.create(i);
                                    Indicador i2 = indicadorFacade.findByUltimo();
                                    String instrumentos[] = request.getParameterValues("instrumento");
                                    List<Instrumento> instrumentoList = new ArrayList<Instrumento>();
                                    if (instrumentos != null) {
                                        for (int k = 0; k < instrumentos.length; k++) {
                                            Instrumento instr = instrumentoFacade.find(Integer.parseInt(instrumentos[k]));
                                            instr.getIndicadorList().add(i2);
                                            instr.setIndicadorList(instr.getIndicadorList());
                                            instrumentoFacade.edit(instr);
                                            instrumentoList.add(instr);
                                        }
                                    }
                                    i.setInstrumentoList(instrumentoList);
                                    indicadorFacade.edit(i2);
                                    sesion.setAttribute("listaI", indicadorFacade.findByModelo(m2));


                                } else {
                                    if (action.equals("crearIndicadorCC")) {
                                        String url = "/WEB-INF/vista/comiteCentral/indicador/crear.jsp";

                                        RequestDispatcher rd = request.getRequestDispatcher(url);
                                        rd.forward(request, response);
                                    } else {
                                        if (action.equals("listarIndicadoresCC")) {
                                            String url = "/WEB-INF/vista/comiteCentral/indicador/listar.jsp";
                                            RequestDispatcher rd = request.getRequestDispatcher(url);
                                            rd.forward(request, response);
                                        } else {
                                            if (action.equals("editarIndicadorCC")) {
                                                String id = request.getParameter("id");
                                                Indicador i = indicadorFacade.find(Integer.parseInt(id));
                                                sesion.setAttribute("instrumentos", instrumentoFacade.findAll());
                                                sesion.setAttribute("indicador", i);
                                                String url = "/WEB-INF/vista/comiteCentral/indicador/editar.jsp";
                                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                                rd.forward(request, response);

                                            } else {
                                                if (action.equals("editarIndicador")) {
                                                    Indicador i = (Indicador) sesion.getAttribute("indicador");
                                                    String nombre = (String) request.getParameter("nombre");
                                                    String codigo = (String) request.getParameter("codigo");
                                                    String caracteristica = (String) request.getParameter("caracteristica");
                                                    Caracteristica c = caracteristicaFacade.find(Integer.parseInt(caracteristica));
                                                    Modelo m2 = (Modelo) sesion.getAttribute("modelo");
                                                    String instrumentos[] = request.getParameterValues("instrumento");
                                                    List<Instrumento> instrumentoList = new ArrayList<Instrumento>();

                                                    List<Instrumento> instrumentosIniciales = i.getInstrumentoList();
                                                    for (Instrumento instrumento : instrumentosIniciales) {
                                                        instrumento.getIndicadorList().remove(i);
                                                        instrumentoFacade.edit(instrumento);
                                                    }

                                                    i.setInstrumentoList(null);
                                                    indicadorFacade.edit(i);

                                                    if (instrumentos != null) {
                                                        for (int k = 0; k < instrumentos.length; k++) {
                                                            Instrumento instr = instrumentoFacade.find(Integer.parseInt(instrumentos[k]));
                                                            instr.getIndicadorList().add(i);
                                                            instr.setIndicadorList(instr.getIndicadorList());
                                                            instrumentoFacade.edit(instr);
                                                            instrumentoList.add(instr);
                                                        }
                                                    }
                                                    List<Pregunta> listadePreguntas = (List<Pregunta>) sesion.getAttribute("listaP");
                                                    List<Pregunta> aux = new ArrayList<Pregunta>();
                                                    for (int k = 0; k < listadePreguntas.size(); k++) {
                                                        if (request.getParameter("P" + listadePreguntas.get(k).getId()).equals("1")) {
                                                            aux.add(listadePreguntas.get(k));
                                                            listadePreguntas.get(k).setIndicadorId(i);
                                                            preguntaFacade.edit(listadePreguntas.get(k));
                                                        }
                                                    }



                                                    i.setCodigo(codigo);
                                                    i.setNombre(nombre);
                                                    i.setModeloId(m2);
                                                    i.setPreguntaList(aux);
                                                    i.setCaracteristicaId(c);
                                                    i.setInstrumentoList(instrumentoList);
                                                    indicadorFacade.edit(i);
                                                    Modelo m = (Modelo) sesion.getAttribute("modelo");
                                                    sesion.setAttribute("listaI", indicadorFacade.findByModelo(m));
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (action.toLowerCase().contains("pregunta")) {
                                    if (action.equals("crearPregunta")) {
                                        String codigo = (String) request.getParameter("codigo");
                                        String nombre = (String) request.getParameter("nombre");
                                        String tipo = (String) request.getParameter("tipo");
                                        String indicador = (String) request.getParameter("indicador");
                                        Indicador i = indicadorFacade.find(Integer.parseInt(indicador));
                                        Modelo m2 = (Modelo) sesion.getAttribute("modelo");

                                        Pregunta p = new Pregunta();
                                        p.setCodigo(codigo);
                                        p.setPregunta(nombre);
                                        p.setModeloId(m2);
                                        p.setTipo(tipo);
                                        p.setIndicadorId(i);
                                        preguntaFacade.create(p);
                                        sesion.setAttribute("listaP", preguntaFacade.findByModelo(m2));


                                    } else {
                                        if (action.equals("crearPreguntaCC")) {
                                            String url = "/WEB-INF/vista/comiteCentral/pregunta/crear.jsp";

                                            RequestDispatcher rd = request.getRequestDispatcher(url);
                                            rd.forward(request, response);
                                        } else {
                                            if (action.equals("listarPreguntasCC")) {
                                                String url = "/WEB-INF/vista/comiteCentral/pregunta/listar.jsp";
                                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                                rd.forward(request, response);
                                            } else {
                                                if (action.equals("editarPreguntaCC")) {
                                                    String id = request.getParameter("id");
                                                    Pregunta p = preguntaFacade.find(Integer.parseInt(id));
                                                    p.setIndicadorId(p.getIndicadorId());
                                                    sesion.setAttribute("pregunta", p);
                                                    String url = "/WEB-INF/vista/comiteCentral/pregunta/editar.jsp";
                                                    RequestDispatcher rd = request.getRequestDispatcher(url);
                                                    rd.forward(request, response);
                                                } else {
                                                    if (action.equals("editarPregunta")) {
                                                        Pregunta p = (Pregunta) sesion.getAttribute("pregunta");
                                                        String pregunta = (String) request.getParameter("nombre");
                                                        String codigo = (String) request.getParameter("codigo");
                                                        String tipo = (String) request.getParameter("tipo");
                                                        String indicador = (String) request.getParameter("indicador");
                                                        p.setIndicadorId(indicadorFacade.find(Integer.parseInt(indicador)));


                                                        p.setCodigo(codigo);
                                                        p.setPregunta(pregunta);
                                                        p.setTipo(tipo);
                                                        preguntaFacade.edit(p);
                                                        Modelo m = (Modelo) sesion.getAttribute("modelo");
                                                        sesion.setAttribute("listaP", preguntaFacade.findByModelo(m));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (action.toLowerCase().contains("encuesta")) {
                                        if (action.equals("crearEncuesta")) {
                                            String nombre = (String) request.getParameter("nombre");
                                            String objetivo = (String) request.getParameter("objetivo");
                                            String instrucciones = (String) request.getParameter("instrucciones");
                                            String codigo = (String) request.getParameter("codigo");
                                            String version = (String) request.getParameter("version");
                                            String fecha = (String) request.getParameter("fecha");
                                            Modelo m = (Modelo) sesion.getAttribute("modelo");
                                            Encuesta e = new Encuesta();
                                            e.setCodigo(codigo);
                                            e.setNombre(nombre);
                                            e.setObjetivo(objetivo);
                                            e.setInstrucciones(instrucciones);
                                            e.setVersion(version);
                                            e.setFecha(fecha);
                                            e.setModeloId(m);
                                            encuestaFacade.create(e);
                                            sesion.setAttribute("listaE", encuestaFacade.findByModelo(m));

                                        } else {
                                            if (action.equals("crearEncuestaCC")) {
                                                String url = "/WEB-INF/vista/comiteCentral/encuesta/crear.jsp";

                                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                                rd.forward(request, response);
                                            } else {
                                                if (action.equals("listarEncuestasCC")) {
                                                    String url = "/WEB-INF/vista/comiteCentral/encuesta/listar.jsp";
                                                    RequestDispatcher rd = request.getRequestDispatcher(url);
                                                    rd.forward(request, response);
                                                } else {
                                                    if (action.equals("editarEncuestaCC")) {
                                                        String idE = request.getParameter("id");
                                                        Encuesta e = encuestaFacade.find(Integer.parseInt(idE));
                                                        List<Asignacionencuesta> as = asignacionencuestaFacade.findByEncuesta(e);

                                                        Fuente f = new Fuente();
                                                        if (as != null) {
                                                            for (int i = 0; i < as.size(); i++) {
                                                                f = as.get(i).getFuenteId();

                                                            }
                                                        }
                                                        sesion.setAttribute("fuenteSeleccionada", f);
                                                        sesion.setAttribute("encuesta", e);
                                                        String url = "/WEB-INF/vista/comiteCentral/encuesta/editar.jsp";
                                                        RequestDispatcher rd = request.getRequestDispatcher(url);
                                                        rd.forward(request, response);

                                                    } else {
                                                        if (action.equals("editarEncuesta")) {
                                                            Encuesta e = (Encuesta) sesion.getAttribute("encuesta");
                                                            String nombre = (String) request.getParameter("nombre");
                                                            String objetivo = (String) request.getParameter("objetivo");
                                                            String instrucciones = (String) request.getParameter("instrucciones");
                                                            String codigo = (String) request.getParameter("codigo");
                                                            String version = (String) request.getParameter("version");
                                                            String fecha = (String) request.getParameter("fecha");
                                                            String fuente = (String) request.getParameter("fuente");
                                                            Modelo m = (Modelo) sesion.getAttribute("modelo");
                                                            if (fuente != null) {
                                                                Fuente f = fuenteFacade.find(Integer.parseInt(fuente));
                                                                List<Asignacionencuesta> la = asignacionencuestaFacade.findByEncuesta(e);
                                                                if (la.size() > 0) {
                                                                    for (int i = 0; i < la.size(); i++) {
                                                                        Asignacionencuesta individual = la.get(i);
                                                                        individual.setFuenteId(f);
                                                                        asignacionencuestaFacade.edit(individual);
                                                                    }
                                                                } else {
                                                                    Asignacionencuesta nueva = new Asignacionencuesta();
                                                                    nueva.setEncuestaId(e);
                                                                    nueva.setFuenteId(f);
                                                                    nueva.setModeloId(m);
                                                                    asignacionencuestaFacade.create(nueva);

                                                                }

                                                            }

                                                            e.setCodigo(codigo);
                                                            e.setNombre(nombre);
                                                            e.setObjetivo(objetivo);
                                                            e.setInstrucciones(instrucciones);
                                                            e.setVersion(version);
                                                            e.setFecha(fecha);
                                                            e.setModeloId(m);

                                                            List<Pregunta> listadePreguntas = (List<Pregunta>) sesion.getAttribute("listaP");
                                                            List<Pregunta> aux = new ArrayList<Pregunta>();
                                                            for (int i = 0; i < listadePreguntas.size(); i++) {
                                                                Pregunta pregunta = listadePreguntas.get(i);
                                                                if (request.getParameter("P" + listadePreguntas.get(i).getId()).equals("1")) {
                                                                    aux.add(pregunta);
                                                                }
                                                            }
                                                            e.setPreguntaList(aux);
                                                            encuestaFacade.edit(e);
                                                            sesion.setAttribute("listaE", encuestaFacade.findByModelo(m));
                                                            sesion.setAttribute("listaP", preguntaFacade.findByModelo(m));


                                                        } else {
                                                            if (action.equals("vistaPreviaEncuestaCC")) {
                                                                String idE = request.getParameter("id");
                                                                Encuesta e = encuestaFacade.find(Integer.parseInt(idE));
                                                                sesion.setAttribute("encuesta", e);
                                                                String url = "/WEB-INF/vista/comiteCentral/encuesta/vistaPrevia.jsp";
                                                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                                                rd.forward(request, response);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (action.toLowerCase().contains("coordinador")) {
                                            if (action.equals("crearCoordinador")) {
                                                List<Programa> progra = new ArrayList<Programa>();
                                                String id2 = request.getParameter("codigo");
                                                String nombre = request.getParameter("nombre");
                                                String apellidos = request.getParameter("apellidos");
                                                String clave = request.getParameter("clave");
                                                String correo = request.getParameter("correo");
                                                String prog = request.getParameter("programas");
                                                String[] programas = prog.split(",");
                                                for (String idprograma : programas) {
                                                    Programa p = programaFacade.find(Integer.parseInt(idprograma));
                                                    progra.add(p);
                                                }

                                                Representante r = new Representante();
                                                r.setId(Integer.parseInt(id2));
                                                r.setNombre(nombre);
                                                r.setApellido(apellidos);
                                                r.setPassword(clave);
                                                r.setMail(correo);
                                                r.setProgramaList(progra);
                                                r.setPrivilegioList(null);
                                                r.setRol("Comite programa");
                                                representanteFacade.create(r);
                                                for (Programa p : progra) {
                                                    List<Representante> rep = p.getRepresentanteList();
                                                    rep.add(r);
                                                    p.setRepresentanteList(rep);
                                                    programaFacade.edit(p);
                                                }


                                            } else {
                                                if (action.equals("crearCoordinadorCC")) {
                                                    String url = "/WEB-INF/vista/comiteCentral/coordinador/crear.jsp";
                                                    sesion.setAttribute("programas", programaFacade.findAll());
                                                    RequestDispatcher rd = request.getRequestDispatcher(url);
                                                    rd.forward(request, response);
                                                } else {
                                                    if (action.equals("listarCoordinadoresCC")) {
                                                        String url = "/WEB-INF/vista/comiteCentral/coordinador/listar.jsp";
                                                        RequestDispatcher rd = request.getRequestDispatcher(url);
                                                        sesion.setAttribute("listaCoo", representanteFacade.findByRol("Comite programa"));
                                                        rd.forward(request, response);

                                                    } else {
                                                        if (action.equals("editarCoordinadorCC")) {
                                                            String id = request.getParameter("id");
                                                            Representante r2 = representanteFacade.find(Integer.parseInt(id));
                                                            sesion.setAttribute("representante", r2);
                                                            sesion.setAttribute("programas", programaFacade.findAll());
                                                            String url = "/WEB-INF/vista/comiteCentral/coordinador/editar.jsp";
                                                            RequestDispatcher rd = request.getRequestDispatcher(url);
                                                            rd.forward(request, response);

                                                        } else {
                                                            if (action.equals("editarCoordinador")) {
                                                                List<Programa> progra = new ArrayList<Programa>();
                                                                Representante r = (Representante) sesion.getAttribute("representante");
                                                                List<Programa> antiguosP = r.getProgramaList();
                                                                String id2 = request.getParameter("codigo");
                                                                String nombre = request.getParameter("nombre");
                                                                String apellidos = request.getParameter("apellidos");
                                                                String clave = request.getParameter("clave");
                                                                String correo = request.getParameter("correo");
                                                                String prog = request.getParameter("programas");
                                                                String[] programas = prog.split(",");
                                                                for (String idprograma : programas) {
                                                                    Programa p = programaFacade.find(Integer.parseInt(idprograma));
                                                                    progra.add(p);
                                                                }

                                                                //quitamos los "antiguos" programas
                                                                for (Programa program : antiguosP) {
                                                                    List<Representante> re = program.getRepresentanteList();
                                                                    re.remove(r);
                                                                    program.setRepresentanteList(re);
                                                                    programaFacade.edit(program);
                                                                }

                                                                //metemos a los "nuevos" programas
                                                                for (Programa program : progra) {
                                                                    List<Representante> re = program.getRepresentanteList();
                                                                    re.add(r);
                                                                    program.setRepresentanteList(re);
                                                                    programaFacade.edit(program);
                                                                }

                                                                r.setId(Integer.parseInt(id2));
                                                                r.setNombre(nombre);
                                                                r.setApellido(apellidos);
                                                                r.setPassword(clave);
                                                                r.setMail(correo);
                                                                r.setProgramaList(progra);
                                                                representanteFacade.edit(r);



                                                            }
                                                        }


                                                    }
                                                }
                                            }
                                        } else if (action.equals("verProcesosCC")) {
                                            String idProg = request.getParameter("id");
                                            Programa pro = programaFacade.find(Integer.parseInt(idProg));
                                            sesion.setAttribute("Programa", pro);
                                            List procesos = (List) procesoFacade.findByPrograma(pro);
                                            if (!procesos.isEmpty()) {
                                                Iterator iter = procesos.iterator();
                                                while (iter.hasNext()) {
                                                    Proceso p = (Proceso) iter.next();
                                                    if (p.getFechainicio().equals("En Configuración")) {
                                                        sesion.setAttribute("EstadoProceso", 1);
                                                        sesion.setAttribute("Proceso", p);
                                                        sesion.setAttribute("Modelo", p.getModeloId());
                                                    } else if (p.getFechacierre().equals("--")) {
                                                        sesion.setAttribute("EstadoProceso", 2);
                                                        sesion.setAttribute("Proceso", p);
                                                        sesion.setAttribute("Modelo", p.getModeloId());

                                                        /////Comienza para saber si el modelo en cuestion tiene preguntas abiertas
                                                        boolean tienePreguntasAbiertas = false;
                                                        List<Pregunta> preguntasModelo = p.getModeloId().getPreguntaList();
                                                        for (Pregunta pregunta : preguntasModelo) {
                                                            if (pregunta.getTipo().equals("2")) {
                                                                tienePreguntasAbiertas = true;
                                                                break;
                                                            }
                                                        }
                                                        if (tienePreguntasAbiertas) {
                                                            sesion.setAttribute("abiertas", "true");
                                                        } else {
                                                            sesion.setAttribute("abiertas", "false");
                                                        }
                                                        /////////Termina 


                                                    } else {
                                                        sesion.setAttribute("EstadoProceso", 0);
                                                        //  session.setAttribute("Proceso", p);
                                                        //session.setAttribute("Modelo", p.getModeloId());
                                                    }
                                                }
                                            } else {
                                                sesion.setAttribute("EstadoProceso", 0);
                                            }
                                            String url = "/WEB-INF/vista/comiteCentral/control/comitePrograma/index.jsp";
                                            RequestDispatcher rd = request.getRequestDispatcher(url);
                                            rd.forward(request, response);
                                        } else {

                                            if (action.equals("inicioCC")) {
                                                String url = "/WEB-INF/vista/comiteCentral/inicio.jsp";
                                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                                rd.forward(request, response);
                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }
        } catch (ServletException e) {
            LOGGER.error("Ha ocurrido un error de tipo ServletException: ", e);
        } catch (IOException e) {
            LOGGER.error("Ha ocurrido un error de tipo IOException: ", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Ha ocurrido un error de tipo NumberFormatException: ", e);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error: ", e);
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
