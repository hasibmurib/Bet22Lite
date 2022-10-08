package createEventTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dataAccess.DataAccess;
import test.dataAccess.TestDataAccess;

class CreateEventDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	

	@Test
	@DisplayName("No existe evento en esa fecha")
	public void test2() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			try {			
				oneDate = sdf.parse("07/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	


			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(p);

			
			
			testDA.open();
			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
			System.out.println("Existe el evento: " + exist);
			assertTrue(exist);
			testDA.close();
			
		}
		catch (Exception e) {
			
			fail();
			
		} finally {
			testDA.open();
			boolean b=testDA.removeEvent(eventText, oneDate, elDeporte);
			System.out.println("evento borrado");
			testDA.close();
			System.out.println("Finally "+b);
		}
	}
	
	@Test
	@DisplayName("Sport null buscado en base de datos")
	public void test1() throws ParseException {

		// define paramaters
		String eventText = "Australia-West_Indies";
		// Date fecha = new Date();
		String elDeporte = "Cricket";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = null;

		try {

			try {
				oneDate = sdf.parse("11/12/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			// invoke System Under Test (sut)
			boolean q = sut.gertaerakSortu(eventText, oneDate, elDeporte);
			System.out.println("Se ha insertado el evento: " + q);
			System.out.println(eventText + " " + oneDate + " " + elDeporte);

			// verify the results
			assertTrue(!q);

			// q datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
			System.out.println("Existe el evento: " + exist);
			assertTrue(!exist);
			testDA.close();

		} catch (Exception e) {
			
			// if the program goes to this point fail
			fail();
		} finally {
			
		}
	}

	

	
	@Test
	@DisplayName("Evento con esa descripción existe en la BD en esa fecha")
	public void test3() throws ParseException {
		
		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			
			//intento añadirlo para segunda vez
			
			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			System.out.println("Se ha creado el evento: "+p);
			
			assertTrue(!p);
			

			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
			System.out.println("Existe el evento: "+exist);
			assertTrue(exist);
			testDA.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			
		} finally {
			testDA.open();
			boolean b=testDA.removeEvent(eventText, oneDate, elDeporte);
			System.out.println("evento borrado");
			testDA.close();
			System.out.println("Finally "+b);          
		}
	}
	
	@Test
	@DisplayName("Evento con esa descripción no existe en la BD pero existe eventos en esa fecha")
	public void test4() throws ParseException {
		
		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";

		String eventText2="Athletic_Club-Real_Sociedad";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		
		try {

			
			try {
				oneDate = sdf.parse("11/12/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	


			boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			System.out.println("Se ha creado el evento: "+q);
			
			
			//intento añadirlo para segunda vez
			
			boolean p=sut.gertaerakSortu(eventText2, oneDate, elDeporte);
			System.out.println("Se ha creado el evento: "+p);
			
			assertTrue(p);
			

			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
			System.out.println("Existe el evento: "+exist);
			assertTrue(exist);
			testDA.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			
		} finally {
			testDA.open();
			boolean primerEvento=testDA.removeEvent(eventText, oneDate, elDeporte);
			boolean segundoEvento=testDA.removeEvent(eventText2, oneDate, elDeporte);
			System.out.println("evento borrado");
			testDA.close();
			System.out.println("Finally "+primerEvento);
			System.out.println("Finally "+segundoEvento);
		}
	}

	 

}
