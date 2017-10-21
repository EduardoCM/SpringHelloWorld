package org.codigorupestre.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codigorupestre.AdministradorPrimerEjercicio;
import org.codigorupestre.bean.Admin;
import org.codigorupestre.dao.AdminDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class MainApp {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

		/*
		 * AdministradorPrimerEjercicio administrador =
		 * (AdministradorPrimerEjercicio) applicationContext.getBean("admin");
		 * System.out.println(administrador); administrador.imprimirDireccion();
		 */

		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");

		/*
		Timestamp ts = new Timestamp(new Date().getTime());
		Admin admin = new Admin();
		admin.setCargo("CEO");
		admin.setNombre("Verenice");
		admin.setFechaCreacion(ts);
		adminDao.save(admin);
*/
		
			/*
			 * List<Admin> admis = adminDao.findAll(); for (Admin admin2 :
			 * admis) { System.out.println(admin2); }
			 */

			/*
			System.out.println(":::: 1 " + adminDao.findById(5));
			System.out.println(":::: 2 " + adminDao.findById(3));
			System.out.println(":::: 3 " + adminDao.findByNombre("E").toString());
			*/
			
			
			//Update
			/*
			Admin admin2 = adminDao.findById(5);
			
			System.out.println("Admin Id 2: " + admin2);
			
			admin2.setCargo("Inversionista");
			admin2.setNombre("Martin");
			
			if(adminDao.update(admin2)){
				System.out.println("Actualizado correctamente: " + admin2);
			}
			
			if(adminDao.delete(admin2.getIdAd())){
				System.out.println("Admin: " + admin2.getNombre() + " Eliminado correctamente");
			}
			*/
		try {
			Timestamp ts = new Timestamp(new Date().getTime());
			List<Admin> admins = new ArrayList<Admin>();
			admins.add(new Admin(60,"Eduardo", "Lider de proyecto", ts));
			admins.add(new Admin(61,"Verenice", "Lider de Ventas", ts));
			admins.add(new Admin(62,"Zoe", "Comercio exterior", ts));
			
			int[] valores = adminDao.saveAll(admins);
			for (int i : valores) {
				System.out.println("Filas afectadas para esta operacion: " + i);
			}

		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}

		((ClassPathXmlApplicationContext) applicationContext).close();

	}

}
