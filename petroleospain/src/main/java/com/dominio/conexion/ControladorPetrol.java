package com.dominio.conexion;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorPetrol
 * 
 * Sistema de Gestión de Actores Descripción General
 * Desarrollar una aplicación web utilizando el modelo MVC
 * (Modelo-Vista-Controlador) para gestionar información sobre 
 *	gasto combusitble realizado por provincias en españa 2024
 *	de una base de datos descargado de web oficial.
 * 
 * @project petroleospain
 * @autor AdQuinti on 18/05/2024
 * 
 * MVC Controlador
 * BBDD petroleo2024spain
 */
@WebServlet("/ControladorPetrol")
public class ControladorPetrol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//creamos una variable del tipo MODELO
	private ModeloPetrol modeloPetrol;
	
	@Resource(name="jdbc/petrol") // linea de context.xml
	private DataSource miPool;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @param incializa el programa
	 **/

	@Override
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    try {
	    	modeloPetrol = new ModeloPetrol(miPool);
	    } catch (Exception e) {
	        //throw new ServletException(e);
	        System.out.println("ERROR en init: " + e.getMessage());
	    }
//System.out.println("paso por init");
	}
	
	/**
	 * @param Gestiona las peticiones - Manage requests
	 **/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obtenemos la lista de actores desde el modelo
		String comando = request.getParameter("instruccion");
		if (comando == null) comando = "listado";
		switch (comando) {
			case "listado":
				obtenerPetrol(request, response); // muestra listado.jsp
				break;
				
			case "insertarBBDD":
				insertarPetrol(request,response);
				break;
				
			case "cargar":
				try {
					cargarPetrol(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ERROR: "+ e.getMessage());
				}
				break;
				
			case "actualizarBBDD":
				try {
					actualizarNuevoPetrol(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				break;
		
			case "eliminar":
				try {
					eliminarPetrol(request,response);
					}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
					}
				break;
				default: obtenerPetrol(request,response); // carga metodo listarPetrol.jsp
		} // END SWITCH-CASE
	} // END doGet
	
	/**
	 * @param METODOS CONEXTION Y ACTION TO PAG.WEB
	 **/
	
	/**
	 * @param Method Delete do CRUD
	 **/
	private void eliminarPetrol(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    int idPetrol = Integer.parseInt(request.getParameter("id_Petrol"));
	   // llamamos al metodo en ModeloPetrol
	    modeloPetrol.eliminarBBDD(idPetrol);
	    obtenerPetrol(request, response);
	} // END eliminarPetrol
	
	
	/**
	 * @param Method Update do the CRUD
	 **/
	private void actualizarNuevoPetrol(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// forma parte de formularioActualizar.jsp
		int idPetrolActualizar = Integer.parseInt(request.getParameter("idPetrol"));
		String fechaActualizar =request.getParameter("fechaA");
		String comunidadActualizar = request.getParameter("comunidadA");
		String provinciaActualizar = request.getParameter("provinciaA");
		String productoActualizar = request.getParameter("productoA");
		int consumoActualizar = Integer.parseInt(request.getParameter("consumoA"));
		// guardamos datos actualizados en variable que será mandada a ModeloPetrol
		VistaPetrol nuevoPetrol = new VistaPetrol (idPetrolActualizar,fechaActualizar, comunidadActualizar,provinciaActualizar,productoActualizar,consumoActualizar);
		modeloPetrol.actualizarPetrolBBDD(nuevoPetrol);
		obtenerPetrol(request,response);
	} // END actualizarNuevoPetrol

	
	/**
	 * @param Method load date from BBDD
	 **/
	private void cargarPetrol(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//forma parte de formularioActualizar.jsp
		//leer datos del id del actor
		int idPetrolCargar = Integer.parseInt(request.getParameter("id_Petrol"));
		//comunicar con el modelo para enviarle el id y que este nos devuelva el actor
		VistaPetrol petrolCargar = modeloPetrol.getPetrolCargar(idPetrolCargar);
		//establecer el atributo
		request.setAttribute("id_petrolCargar", petrolCargar);
		//enviar el id al modelo
		RequestDispatcher dispatcher = request.getRequestDispatcher("/formularioActualizar.jsp");
		dispatcher.forward(request, response);
	} // END cargarPetrol

	/**
	 * @param Method create of the CRUD
	 **/
	private void insertarPetrol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// metodo que inserta datos con formularioInsertar.jsp
		int idPetrolInsertar = Integer.parseInt(request.getParameter("idPetrol"));
		String fechaInsertar = request.getParameter("fechaA");
		String comunidadInsertar= request.getParameter("comunidadA");
		String provinciaInsertar= request.getParameter("provinciaA");
		String productoInsertar= request.getParameter("productoA");
		int consumoInsertar= Integer.parseInt(request.getParameter("consumoA"));
		
		VistaPetrol petroleo= new VistaPetrol (idPetrolInsertar,fechaInsertar,comunidadInsertar,provinciaInsertar,productoInsertar,consumoInsertar);
		try {
			modeloPetrol.insertarNuevoPetrol(petroleo);
		} catch (Exception e) {
			System.out.println("ERROR mostrar lista.jsp: " + e.getMessage());
		}
		obtenerPetrol(request,response);
	} // END insertarPetrol

	
	/**
	 * @param Method READ of the CRUD
	 **/
	private void obtenerPetrol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Muestra listaPetro.jsp
		List<VistaPetrol>petroleo;
		try {
			petroleo = modeloPetrol.getPetrol(); // metodo en Modelo.java
			//añadimos la lista de actores al request
			request.setAttribute("listaPetrol", petroleo); // etiqueta items en listaPetrol.jsp
			//enviar el request a la pagina lista.jsp
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/listaPetrol.jsp");
			miDispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("ERROR mostrar lista.jsp: " + e.getMessage());
			} 
	} // END obtenerPetrol
	
	/**
	 * Enunciado del Proyecto: Sistema de Gestión de Gasto Combustible  Descripción General
	 * Desarrollar una aplicación web utilizando el modelo MVC
	 * (Modelo-Vista-Controlador) para gestionar información sobre actores de una
	 * base de datos. El sistema debe permitir realizar operaciones CRUD (Crear,
	 * Leer, Actualizar y Eliminar) sobre los registros de Gasto Combustible. La base de datos
	 * utilizada será actorholywood, que contiene información básica sobre los
	 * gastos de combustible.
	 * 
	 * Requisitos Funcionales Listar Gasto Combustible.
	 * 
	 * Mostrar una lista de todos los actores en la base de datos. Cada registro
	 * debe mostrar el ID del Gasto Combustible, nombre, apellido y fecha de actualización. Debe
	 * haber enlaces para actualizar y eliminar cada actor. Insertar Nuevo Gasto Combustible
	 * 
	 * Proporcionar un formulario para ingresar los datos de un nuevo actor. Los
	 * campos del formulario deben incluir ID del actor, nombre, apellido y fecha de
	 * actualización. Al enviar el formulario, el actor debe ser añadido a la base
	 * de datos. Actualizar Gasto Combustible Existente
	 * 
	 * Proporcionar un formulario para actualizar los datos de un actor existente.
	 * El formulario debe estar prellenado con los datos actuales del actor. Al
	 * enviar el formulario, los datos del Gasto Combustible deben ser actualizados en la base
	 * de datos. Eliminar Gasto Combustible
	 * 
	 * Permitir la eliminación de un Gasto Combustible de la base de datos. Solicitar
	 * confirmación antes de proceder con la eliminación. Estructura del Proyecto 1.
	 * Modelo Clase Actor: Representa la entidad Actor con atributos actor_id,
	 * first_name, last_name, y last_update. Clase ModeloActor: Contiene los métodos
	 * para interactuar con la base de datos, como obtener la lista de actores,
	 * insertar un nuevo actor, actualizar un actor existente y eliminar un actor.
	 * 
	 * 2. Vista JSPs listaActores.jsp: Muestra la lista de Gasto Combustible con enlaces para
	 * actualizar y eliminar. formularioInsertar.jsp: Formulario para ingresar un
	 * nuevo Gasto Combustible. formularioActualizar.jsp: Formulario para actualizar un actor
	 * existente. 3. Controlador Servlet ControladorPetrol: Controla las solicitudes
	 * del usuario y determina las acciones a tomar según las instrucciones
	 * recibidas (listar, insertarBBDD, cargar, actualizarBBDD, eliminar). Detalles
	 * de Implementación Base de Datos Nombre: petroleo2024spain Tabla: 	petroleo2024provincia
	 * Campos: petrol_id (int), fecha (varchar), comunidad (varchar), provincia (varchar),
	 * produto (varchar), consumo (int)
	 * 
	 * @project petroleospain
	 * @autor AdQuinti on 18/05/2024
	 * 
	 * MVC Controlador
	 * BBDD petroleo2024spain
	 **/
} // END ControladorPetrol
