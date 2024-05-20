package com.dominio.conexion;
/**
 * @project petroleospain
 * @autor AdQuinti on 18/05/2024
 * 
 * MVC Modelo
 * BBDD petroleo2024spain
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ModeloPetrol {
	private DataSource origenDatos;

	public ModeloPetrol (DataSource origenDatos) { 
		// quita error en Controlador.java en modeloPetrol = new ModeloPetrol(miPool);
		this.origenDatos = origenDatos;
	}

	/** @param metodo para devolver una lista
	 * @return petroleo (listado de datos gastos combustible de BBDD
	 **/
	public List<VistaPetrol> getPetrol() throws Exception {
		List<VistaPetrol>petroleo = new ArrayList<>();
		Connection conexion = null;
		Statement statement = null;
		ResultSet ResultSet = null;
		try {
			//establecer la conexion
			conexion = origenDatos.getConnection();
			//crear sentencia sql
			String instruccionSql = "SELECT * FROM petroleo2024provincia";
			statement = conexion.createStatement();
			//ejecutar sql
			ResultSet = statement.executeQuery(instruccionSql);
			//recorrer el miResultset obtenido
			while (ResultSet.next()) {
				int idLista = ResultSet.getInt("petrol_id");
				String fechaLista = ResultSet.getString("fecha");
				String comunidadLista = ResultSet.getString("comunidad");
				String provinciaLista = ResultSet.getString("provincia");
				String productoLista = ResultSet.getString("producto");
				int consumoLista = ResultSet.getInt("consumo");
				//añadimos estos datos a la lista en cada vuelta de bucle - constructo Vista.java
				VistaPetrol tempPetrol = new VistaPetrol (idLista, fechaLista, comunidadLista,provinciaLista, productoLista, consumoLista);
				//añadimos el objeto a la lista
				petroleo.add(tempPetrol);
			} // END While Array - SQL
			} catch (SQLException e) {
				System.out.println("ERROR carga lista SQL en array: " + e.getMessage());
			} finally {
				conexion.close();
				statement.close();
			}
		return petroleo;
	} // END getPetrol
	
	/** 
	 * @param metodo CREATE en BBDD
	 **/
	public void insertarNuevoPetrol(VistaPetrol petroleo) throws Exception {
		// forma parte formularioInsertar.jsp
		//establecer la conexion
		Connection conexion = null;
		PreparedStatement statement = null;
			try {
				conexion= origenDatos.getConnection();
				//creamos instruccion sql
				String sql="INSERT INTO petroleo2024provincia (petrol_id, fecha, comunidad, provincia, producto, consumo) VALUES (?,?,?,?,?,?)";
				
				statement = conexion.prepareStatement(sql);
				
				//establecer los parametros para insertar el actor insert into
				statement.setInt(1, petroleo.getPetrol_id());
				statement.setString (2, petroleo.getFecha());
				statement.setString (3, petroleo.getComunidad());
				statement.setString (4, petroleo.getProvincia());
				statement.setString (5, petroleo.getProducto());
				statement.setInt (6, petroleo.getConsumo());
				//ejecutamos el sql
				statement.execute();	
			}catch(Exception e) {
				System.out.println("ERROR: "+ e.getMessage());
			}finally {
		        if (conexion!= null) {
		            try {
		                conexion.close();
		            } catch (SQLException ex) {
		                System.out.println("insertarNuevoDino - Error al cerrar la conexión: " + ex.getMessage());
		            }
		        }
		        if (statement!= null) {
		            try {
		                statement.close();
		            } catch (SQLException ex) {
		                System.out.println("insertarNuevoDino - Error al cerrar el statement: " + ex.getMessage());
		            }
		        }
			}
	} // END insertarNuevoPetrol

	/** 
	 * @param load date from  BBDD
	 * @return petrolBuscar Petrol object data to load from BBDD
	 **/
	 public VistaPetrol getPetrolCargar(int idPetrol) throws Exception{
			// forma parte de formularioActualizar.jsp
			//creamos un objeto de la clase VistaPetrol para guardar los datos del registro
			VistaPetrol petrolBuscar = null;
			Connection conexion = null;
			PreparedStatement statement = null;
			ResultSet resultset = null;
			//establecemos la conexion
			try {
				conexion = origenDatos.getConnection();
				//crear la sentencia sql
				String sql="SELECT*FROM petroleo2024provincia WHERE petrol_id=?";
				statement = conexion.prepareStatement(sql);
				statement.setInt(1,idPetrol);
				resultset = statement.executeQuery();
			
			if (resultset.next()) {
				int id_PetrolCargar = resultset.getInt("petrol_id");
				String fechaCargar = resultset.getString("fecha");
				String comunidadCargar = resultset.getString("comunidad");
				String provinciaCargar = resultset.getString("provincia");
				String productoCargar = resultset.getString("producto");
				int consumoCargar = resultset.getInt("consumo");
				petrolBuscar = new VistaPetrol(id_PetrolCargar,fechaCargar,comunidadCargar,provinciaCargar,productoCargar,consumoCargar);
			}else {
				throw new Exception("NO se ha encontrado el actor con el id: "+ idPetrol);
				
			} // END IF resultset.next()
				
			} catch (SQLException e) {
				System.out.println("ERROR: "+ e.getMessage());
			}
			return petrolBuscar;
		} // END getPetrolCargar

	/** 
	 * @param update date Petrol in  BBDD
	 **/
	public void actualizarPetrolBBDD(VistaPetrol nuevoPetrol)  throws Exception {
		// forma parte de formularioActualiza.jsp
		Connection conexion = null;
		PreparedStatement statement = null;
		//establecemos conexion
		try {
			conexion = origenDatos.getConnection();
			String sql = "UPDATE petroleo2024provincia SET fecha=?, comunidad=?, provincia=?, producto=?, consumo=? WHERE petrol_id=? ";
		//crear consulta preparada
			statement = conexion.prepareStatement(sql);
			statement.setString (1, nuevoPetrol.getFecha());
			statement.setString (2, nuevoPetrol.getComunidad());
			statement.setString (3, nuevoPetrol.getProvincia());
			statement.setString (4, nuevoPetrol.getProducto());
			statement.setInt (5, nuevoPetrol.getConsumo());
			statement.setInt (6, nuevoPetrol.getPetrol_id());
			// EJECUTA ACCION SQL
			statement.execute();
		} catch (SQLException e) {
			System.out.println("ERROR: "+ e.getMessage());
		}finally {
	        if (conexion!= null) {
	            try {
	                conexion.close();
	            } catch (SQLException ex) {
	                System.out.println("actualizaDinoBBDD - Error cerrar conexión: " + ex.getMessage());
	            }
	        }
	        if (statement!= null) {
	            try {
	                statement.close();
	            } catch (SQLException ex) {
	                System.out.println("actualizaDinoBBDD - Error cerrar statement: " + ex.getMessage());
	            }
	        }
	    }
		
	} // END actualizarPetrolBBDD

	/** 
	 * @param delete date Petrol in BBDD
	 **/
	public void eliminarBBDD(int idPetrol)  throws Exception {
		// ELIMINACION
		Connection conexion = null;
		PreparedStatement statement = null;
		try {
			conexion= origenDatos.getConnection();
			String sql = "DELETE FROM petroleo2024provincia WHERE petrol_id=?";
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, idPetrol);
			statement.execute();
		
		}catch (SQLException e) {
			System.out.println("ERROR: "+ e.getMessage());
		}
		finally {
	        if (conexion!= null) {
	            try {
	                conexion.close();
	            } catch (SQLException ex) {
	                System.out.println("eliminarBBDD - Error cerrar conexión: " + ex.getMessage());
	            }
	        }
	        if (statement!= null) {
	            try {
	                statement.close();
	            } catch (SQLException ex) {
	                System.out.println("eliminarBBDD - Error cerrar statement: " + ex.getMessage());
	            }
	        }
	    }
	} // END eliminarBBDD

} // END ModeloPetrol
