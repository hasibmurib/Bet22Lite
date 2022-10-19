package pruebaIntegración;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccessGertaerakSortu;
import test.dataAccess.TestDataAccess;

public class CreateEventDAB2 {

	//sut:system under test
	static DataAccessGertaerakSortu sut=new DataAccessGertaerakSortu();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();
	
	boolean spor = false;


//	@Test
//	@DisplayName("Evento no existe en la BD")
//	public void test1() throws ParseException {
//		
//		
//		//define paramaters
//		String eventText="Real_Madrid-Barcelona";
//		//Date fecha = new Date();
//		String elDeporte = "Futbol";
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date oneDate=null;
//		
//		System.out.println("Test: Evento no existe en la BD");
//		
//		boolean spor =false;
//
//		try {
//
//			try {
//				oneDate = sdf.parse("11/12/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//
//			//configure the state of the system (create object in the dabatase)
//			testDA.open();
//			spor = testDA.addSport(elDeporte);
//			System.out.println("se ha creado el deporte: "+spor);
//			testDA.close();			
//
//			//invoke System Under Test (sut)  
//			boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
//			System.out.println("Se ha insertado el evento: "+q);
//			System.out.println(eventText+" "+oneDate+" "+elDeporte);
//
//			//verify the results
//			assertTrue(q);
//
//
//			//comprobar si existe el deporte
//			testDA.open();
//			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
//			System.out.println("Existe el evento: "+exist);
//			assertTrue(exist);
//			testDA.close();
//
//		} catch (Exception e) {
//			// if the program goes to this point fail  
//			fail();
//		} finally {
//			testDA.open();
//			boolean b=testDA.removeEvent(eventText, oneDate, elDeporte);
//			testDA.close();
//			System.out.println("evento borrado "+b);     
//			if (spor) 
//				testDA.eliminarSport(elDeporte);
//		}
//	}

	@Test
	//@DisplayName("Descripción null y evento no existe en la BD")
	public void test2() throws ParseException {
		
		
		//define paramaters
		String eventText=null;
		//Date fecha = new Date();
		String elDeporte = "Futbol";
		
		System.out.println("Test: Descripción null y evento no existe en la BD");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		spor = false;
		Date oneDate=null;
		try {
			try {			
				oneDate = sdf.parse("11/12/2022");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			boolean spor = testDA.addSport(elDeporte);
			System.out.println("se ha creado el deporte: "+spor);
			testDA.close();			


			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			
			assertTrue(!p);

		}
		catch (Exception e) {
			
			fail();
			
		} finally {
			if (spor) 
				testDA.eliminarSport(elDeporte);
		}
	}

	
	@Test
	//@DisplayName("Fecha null y evento no existe en la BD")
	public void test3() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";
		
		System.out.println("Test: Fecha null y evento no existe en la BD");
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		final Date oneDate=null;;

		try {

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			boolean spor = testDA.addSport(elDeporte);
			System.out.println("se ha creado el deporte: "+spor);
			testDA.close();			


			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(!p);
			

		} catch (Exception e) {
			fail();
			System.out.println(e.toString());
		} finally {
			if (spor) 
				testDA.eliminarSport(elDeporte);
		}
	}


	@Test
	//@DisplayName("Sport null y evento no existe en la BD")
	public void test4() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		String elDeporte = null;
		
		System.out.println("Test: sport null y evento no existe en la BD");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;

		try {
			try { 
				oneDate = sdf.parse("11/12/2022"); 
			} catch (ParseException e) {  e.printStackTrace(); }


			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);

			assertTrue(!p);

		} catch (Exception e) {

			fail();

		} finally {

		}
	}

	@Test
	//@DisplayName("Evento con esa descripción existe en la BD")
	public void test5() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";
		
		spor=false;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Test: Evento con esa descripción existe en la BD");
		Date oneDate=null;

		try {


			try {
				oneDate = sdf.parse("11/12/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			boolean spor = testDA.addSport(elDeporte);
			System.out.println("se ha creado el deporte: "+spor);
			testDA.close();			

			boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			System.out.println("Se ha creado el evento: "+q);
			assertTrue(q);

			//intento añadirlo para segunda vez

			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			System.out.println("Se ha creado el evento: "+p);

			assertTrue(!p);


			
			testDA.open();
			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
			System.out.println("Existe el evento: "+exist);
			testDA.close();
			assertTrue(exist);

		} catch (Exception e) {
			
			// if the program goes to this point fail  
			

		} finally {
			testDA.open();
			boolean b=testDA.removeEvent(eventText, oneDate, elDeporte);
			testDA.close();
			System.out.println("evento borrado "+b);   
			if (spor)
				testDA.eliminarSport(elDeporte);
		}
	}

	@Test
	//@DisplayName("Añadir evento para una fecha pasada")
	public void test6() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";
		spor=false;
		
		System.out.println("Test: Añadir evento para una fecha pasada");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;

		try {
			try { 
				oneDate = sdf.parse("11/09/2022"); 

			} catch (ParseException e) {  e.printStackTrace(); }

			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(!p);



		} catch (Exception e) {

			System.out.println(e.toString());

			fail();
		} finally {
			testDA.open();
			boolean b=testDA.removeEvent(eventText, oneDate, elDeporte);
			testDA.close();
			System.out.println("evento borrado "+b);     
		}
	}

}

