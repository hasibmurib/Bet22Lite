package createEventTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import exceptions.EventFinished;

@RunWith(MockitoJUnitRunner.class)
public class CreateEventMockInt {
	DataAccess daoMock=Mockito.mock(DataAccess.class);
	// Event mockedEvent=Mockito.mock(Event.class);
	//EntityManager mockedDB = Mockito.mock(EntityManager.class);

	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(daoMock);


	@Test
	//@DisplayName("Sport en BD buscado null")
	public void test1() {
		try {
			//define paramaters
			String eventText="New_Zealand-South_Africa";
			//Date fecha = new Date();
			String elDeporte = "Cricket";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {			
				oneDate = sdf.parse("11/12/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//configure Mock
			Mockito.when(daoMock.gertaerakSortu(eventText, oneDate, elDeporte)).thenReturn(false);
			
			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(!q); 
			
			ArgumentCaptor<String> eventTextCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> oneDateCaptor = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> elDeporteCaptor = ArgumentCaptor.forClass(String.class);
			
			Mockito.verify(daoMock,Mockito.times(1)).gertaerakSortu(eventTextCaptor.capture(),oneDateCaptor.capture(), elDeporteCaptor.capture());
		
			
			
			assertEquals(eventText, eventTextCaptor.getValue());
			assertEquals(oneDate, oneDateCaptor.getValue());
			assertEquals(elDeporte, elDeporteCaptor.getValue());
		}
		catch(Exception e) {
			fail("NO HAY QUE ENTRAR AQUI");
		} finally {

		}

	}
	
	@Test
	//@DisplayName("No existe evento en esa fecha")
	public void test2() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			try {			
				oneDate = sdf.parse("13/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			Mockito.when(daoMock.gertaerakSortu(eventText, oneDate, elDeporte)).thenReturn(true);
			
			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(q); 
			
			ArgumentCaptor<String> eventTextCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> oneDateCaptor = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> elDeporteCaptor = ArgumentCaptor.forClass(String.class);
			
			Mockito.verify(daoMock,Mockito.times(1)).gertaerakSortu(eventTextCaptor.capture(),oneDateCaptor.capture(), elDeporteCaptor.capture());
		
			
			
			assertEquals(eventText, eventTextCaptor.getValue());
			assertEquals(oneDate, oneDateCaptor.getValue());
			assertEquals(elDeporte, elDeporteCaptor.getValue());
			
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
	//@DisplayName("Evento con esa descripción existe en la BD en esa fecha")
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

			Mockito.when(daoMock.gertaerakSortu(eventText, oneDate, elDeporte)).thenReturn(true);
			
			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(q); 
			
			
			ArgumentCaptor<String> eventTextCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> oneDateCaptor = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> elDeporteCaptor = ArgumentCaptor.forClass(String.class);
			
			Mockito.verify(daoMock,Mockito.times(1)).gertaerakSortu(eventTextCaptor.capture(),oneDateCaptor.capture(), elDeporteCaptor.capture());
		
			
			
			assertEquals(eventText, eventTextCaptor.getValue());
			assertEquals(oneDate, oneDateCaptor.getValue());
			assertEquals(elDeporte, elDeporteCaptor.getValue());
			
			//Añado el evento para la segunda vez y esa vez devuelve el mockito false.
			
			Mockito.when(daoMock.gertaerakSortu(eventText, oneDate, elDeporte)).thenReturn(false);
			
			Boolean p=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(!p); 
			
			
			ArgumentCaptor<String> eventTextCaptor2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> oneDateCaptor2 = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> elDeporteCaptor2 = ArgumentCaptor.forClass(String.class);
			
			Mockito.verify(daoMock,Mockito.times(2)).gertaerakSortu(eventTextCaptor2.capture(),oneDateCaptor2.capture(), elDeporteCaptor2.capture());
		
			
			
			assertEquals(eventText, eventTextCaptor2.getValue());
			assertEquals(oneDate, oneDateCaptor2.getValue());
			assertEquals(elDeporte, elDeporteCaptor2.getValue());
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			
		} finally {
			          
		}
	}
	
	@Test
	//@DisplayName("Evento con esa descripción no existe en la BD pero existe eventos en esa fecha")
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

			Mockito.when(daoMock.gertaerakSortu(eventText, oneDate, elDeporte)).thenReturn(true);
			
			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			assertTrue(q); 
			
			
			ArgumentCaptor<String> eventTextCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> oneDateCaptor = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> elDeporteCaptor = ArgumentCaptor.forClass(String.class);
			
			Mockito.verify(daoMock,Mockito.times(1)).gertaerakSortu(eventTextCaptor.capture(),oneDateCaptor.capture(), elDeporteCaptor.capture());
		
			
			
			assertEquals(eventText, eventTextCaptor.getValue());
			assertEquals(oneDate, oneDateCaptor.getValue());
			assertEquals(elDeporte, elDeporteCaptor.getValue());
			
			//Añado el evento para la segunda vez y esa vez devuelve el mockito false.
			
			Mockito.when(daoMock.gertaerakSortu(eventText2, oneDate, elDeporte)).thenReturn(true);
			
			Boolean p=sut.gertaerakSortu(eventText2, oneDate, elDeporte);
			assertTrue(p); 
			
			
			ArgumentCaptor<String> eventTextCaptor2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> oneDateCaptor2 = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> elDeporteCaptor2 = ArgumentCaptor.forClass(String.class);
			
			Mockito.verify(daoMock,Mockito.times(2)).gertaerakSortu(eventTextCaptor2.capture(),oneDateCaptor2.capture(), elDeporteCaptor2.capture());
		
			
			
			assertEquals(eventText2, eventTextCaptor2.getValue());
			assertEquals(oneDate, oneDateCaptor2.getValue());
			assertEquals(elDeporte, elDeporteCaptor2.getValue());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			
		} finally {
			
		}
	}
	
	@Test
	//@DisplayName("No existe evento en esa fecha")
	public void test5() throws ParseException {

		//define paramaters
		String eventText="Real_Madrid-Barcelona";
		//Date fecha = new Date();
		String elDeporte = "Futbol";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			try {			
				oneDate = sdf.parse("13/09/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//Mockito.when(daoMock.gertaerakSortu(eventText, oneDate, elDeporte)).thenReturn(true);
			
			Boolean q=sut.gertaerakSortu(eventText, oneDate, elDeporte);
			fail();
			
			
			
		}
		catch (EventFinished e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			assertTrue(true);
			//System.out.println(e.toString());
		} finally {
			
		}
	}
	
}
