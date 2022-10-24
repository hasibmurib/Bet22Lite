package createEventTest;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;


import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import exceptions.EventFinished;
import test.businessLogic.TestFacadeImplementation;


public class CreateEventInt {
//	static BLFacadeImplementation sut;
//	 static TestFacadeImplementation testBL;
//
//	
//	@BeforeClass
//	public static void setUpClass() {
//		//sut= new BLFacadeImplementation();
//		
//		// you can parametrize the DataAccess used by BLFacadeImplementation
//		//DataAccess da= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
//		DataAccess da= new DataAccess();
//
//		sut=new BLFacadeImplementation(da);
//		
//		testBL= new TestFacadeImplementation();
//	}
//
//	@Test
//	//@DisplayName("Sport en BD buscado null")
//	public void test1() {
//		try {
//			//define paramaters
//			String eventText="New_Zealand-South_Africa";
//			//Date fecha = new Date();
//			String elDeporte = "Cricket";
//
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			Date oneDate=null;
//			try {			
//				oneDate = sdf.parse("11/12/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
//			assertTrue(!q); 
//			
//			boolean exist = testBL.existEvent(eventText, oneDate, elDeporte);
//			System.out.println("Existe el evento: "+exist);
//			assertTrue(!exist);
//			
//		}
//		catch(Exception e) {
//			fail("NO HAY QUE ENTRAR AQUI");
//		} finally {
//
//		}
//
//	}
//	
//	@Test
//	//@DisplayName("No existe evento en esa fecha")
//	public void test2() throws ParseException {
//
//		//define paramaters
//		String eventText="Real_Madrid-Barcelona";
//		//Date fecha = new Date();
//		String elDeporte = "Futbol";
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date oneDate=null;
//		try {
//			try {			
//				oneDate = sdf.parse("13/11/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//
//			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
//			assertTrue(q); 
//			
//			//q datubasean dago
//			
//			boolean exist = testBL.existEvent(eventText, oneDate, elDeporte);
//			System.out.println("Existe el evento: "+exist);
//			assertTrue(exist);
//			
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			// if the program goes to this point fail 
//			fail();
//			//System.out.println(e.toString());
//		} finally {
//			
//			boolean b=testBL.removeEvent(eventText, oneDate, elDeporte);
//			System.out.println("Se ha borrado el evento: "+b);
//			assertTrue(b);
//			
//		}
//	}
//	
//	@Test
//	//@DisplayName("Evento con esa descripción existe en la BD en esa fecha")
//	public void test3() throws ParseException {
//		
//		//define paramaters
//		String eventText="Real_Madrid-Barcelona";
//		//Date fecha = new Date();
//		String elDeporte = "Futbol";
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date oneDate=null;
//		
//		try {
//
//			
//			try {
//				oneDate = sdf.parse("11/12/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//
//			
//			
//			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
//			assertTrue(q); 
//			
//			boolean exist = testBL.existEvent(eventText, oneDate, elDeporte);
//			System.out.println("Existe el evento: "+exist);
//			assertTrue(exist);
//			
//			//Añado el evento para la segunda vez y esa vez devuelve el mockito false.
//			
//			
//			Boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
//			assertTrue(!p); 
//			
//			
//			
//			
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// if the program goes to this point fail  
//			
//		} finally {
//
//			boolean b=testBL.removeEvent(eventText, oneDate, elDeporte);
//			System.out.println("Se ha borrado el evento: "+b);
//			assertTrue(b);
//		}
//	}
//	
//	@Test
//	//@DisplayName("Evento con esa descripción no existe en la BD pero existe eventos en esa fecha")
//	public void test4() throws ParseException {
//		
//		//define paramaters
//		String eventText="Real_Madrid-Barcelona";
//		//Date fecha = new Date();
//		String elDeporte = "Futbol";
//
//		String eventText2="Athletic_Club-Real_Sociedad";
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date oneDate=null;
//		
//		try {
//
//			
//			try {
//				oneDate = sdf.parse("11/12/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//
//			
//			
//			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
//			assertTrue(q); 
//			
//			boolean exist = testBL.existEvent(eventText, oneDate, elDeporte);
//			System.out.println("Existe el evento: "+exist);
//			assertTrue(exist);
//			
//			Boolean p=sut.gertaerakSortu(eventText2, oneDate, elDeporte);
//			assertTrue(p); 
//			
//			boolean exist2 = testBL.existEvent(eventText2, oneDate, elDeporte);
//			System.out.println("Existe el evento: "+exist);
//			assertTrue(exist2);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// if the program goes to this point fail  
//			
//		} finally {
//			boolean b=testBL.removeEvent(eventText, oneDate, elDeporte);
//			System.out.println("Se ha borrado el evento: "+b);
//			assertTrue(b);
//			
//			boolean a=testBL.removeEvent(eventText2, oneDate, elDeporte);
//			System.out.println("Se ha borrado el evento: "+a);
//			assertTrue(a);
//			
//		}
//	}
//	
//	@Test
//	//@DisplayName("Evento con esa descripción no existe en la BD pero existe eventos en esa fecha")
//	public void test5() throws ParseException {
//		
//		//define paramaters
//		String eventText="Real_Madrid-Barcelona";
//		//Date fecha = new Date();
//		String elDeporte = "Futbol";
//
//		String eventText2="Athletic_Club-Real_Sociedad";
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date oneDate=null;
//		
//		try {
//
//			
//			try {
//				oneDate = sdf.parse("11/09/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//
//			
//			
//			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
//			assertTrue(q); 
//			
//			
//			
//		} catch (EventFinished e) {
//			// TODO Auto-generated catch block
//			// if the program goes to this point fail  
//			assertTrue(true);
//			
//		} finally {
//			
//			
//		}
//	}
}
