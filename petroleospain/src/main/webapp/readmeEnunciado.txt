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