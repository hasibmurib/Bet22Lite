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

class CreateEventDAB {

	//sut:system under test
	static DataAccess sut=new DataAccess();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();

	//private boolean ev;



	@Test
	@DisplayName("Evento no existe en la BD")
	public void test1() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;

		boolean spor;

		try {

			try {
				oneDate = sdf.parse("11/12/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			spor = testDA.addSport(elDeporte);
			System.out.println("se ha creado el deporte: "+spor);
			testDA.close();			

			//invoke System Under Test (sut)  
			boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			System.out.println("Se ha insertado el evento: "+q);
			System.out.println(eventText+" "+oneDate+" "+elDeporte);

			//verify the results
			assertTrue(q);


			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
			System.out.println("Existe el evento: "+exist);
			assertTrue(exist);
			testDA.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
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
	@DisplayName("Descripción null y evento no existe en la BD")
	public void test2() throws ParseException {

		//define paramaters
		String eventText=null;
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
			//			testDA.open();
			//			boolean spor = testDA.addSport(elDeporte);
			//			System.out.println("se ha creado el deporte: "+spor);
			//			testDA.close();			


			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			//System.out.println("Se ha creado el evento: "+p);
			assertTrue(!p);

			//assertThrows(NullPointerException.class, ()-> sut.gertaerakSortu(eventText, oneDate, elDeporte));

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			fail();
			//System.out.println(e.toString());
		} finally {

		}
	}


	@Test
	@DisplayName("Fecha null y evento no existe en la BD")
	public void test3() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";

		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		final Date oneDate=null;;

		try {


			/*
			 * try { oneDate = sdf.parse("11/12/2022"); } catch (ParseException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

			//configure the state of the system (create object in the dabatase)
			//			testDA.open();
			//			boolean spor = testDA.addSport(elDeporte);
			//			System.out.println("se ha creado el deporte: "+spor);
			//			testDA.close();			


			boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(!p);
			//System.out.println("Se ha creado el evento: "+p);

			//assertTrue(!p);


			//			//q datubasean dago
			//			testDA.open();
			//			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
			//			System.out.println("Existe el evento: "+exist);
			//			assertTrue(!exist);
			//			testDA.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			//assertTrue(true);
			fail();
			System.out.println(e.toString());
		} finally {

		}
	}
	
		
		@Test
		@DisplayName("Sport null y evento no existe en la BD")
		public void test4() throws ParseException {
			
			//define paramaters
			String eventText="Real_Madrid-Barcelona";
			//Date fecha = new Date();
			String elDeporte = null;
	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			
			try {
				  try { 
					  oneDate = sdf.parse("11/12/2022"); 
					  } catch (ParseException e) {  e.printStackTrace(); }
				 
	
				//configure the state of the system (create object in the dabatase)
	//			testDA.open();
	//			boolean spor = testDA.addSport(elDeporte);
	//			System.out.println("se ha creado el deporte: "+spor);
	//			testDA.close();			
	
				
				boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
				//fail();
			
				
				assertTrue(!p);
				
	
	//			//q datubasean dago
	//			testDA.open();
	//			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
	//			System.out.println("Existe el evento: "+exist);
	//			assertTrue(!exist);
	//			testDA.close();
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// if the program goes to this point fail  
				//assertTrue(true);
				fail();
				//System.out.println(e.toString());
			} finally {
				       
			}
		}

		@Test
		@DisplayName("Evento con esa descripción existe en la BD")
		public void test5() throws ParseException {
			
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
		@DisplayName("Añadir evento para una fecha pasada")
		public void test6() throws ParseException {
			
			//define paramaters
			String eventText="Real_Madrid-Barcelona";
			//Date fecha = new Date();
			String elDeporte = "Futbol";
	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			
			try {
				  try { 
					  oneDate = sdf.parse("11/09/2022"); 
					  
					  } catch (ParseException e) {  e.printStackTrace(); }
				 
	
				//configure the state of the system (create object in the dabatase)
	//			testDA.open();
	//			boolean spor = testDA.addSport(elDeporte);
	//			System.out.println("se ha creado el deporte: "+spor);
	//			testDA.close();			
	
				
				boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
				//fail();
				assertTrue(!p);
			
				
				//assertTrue(p);
				
	
	//			//q datubasean dago
	//			testDA.open();
	//			boolean exist = testDA.existEvent(eventText, oneDate, elDeporte);
	//			System.out.println("Existe el evento: "+exist);
	//			assertTrue(!exist);
	//			testDA.close();
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// if the program goes to this point fail  
				//assertTrue(true);
				System.out.println(e.toString());
				
				fail();
			} finally {
				testDA.open();
				boolean b=testDA.removeEvent(eventText, oneDate, elDeporte);
				System.out.println("evento borrado");
				testDA.close();
				System.out.println("Finally "+b);     
			}
		}

}

