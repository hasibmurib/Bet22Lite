package rankingLortuTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registered;

@RunWith(MockitoJUnitRunner.class)
public class RankingLortuMockInt {

	DataAccess daoMock=Mockito.mock(DataAccess.class);
	// Event mockedEvent=Mockito.mock(Event.class);
	//EntityManager mockedDB = Mockito.mock(EntityManager.class);

	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(daoMock);
	
	

	@Test
	//@DisplayName("Hay dos usuarios, el primer usuario tiene más dinero que el otro")
	public void test1(){

		//usuarios.add(ad1); 		usuarios.add(reg1); 	usuarios.add(reg2); 	usuarios.add(reg3); 	usuarios.add(reg4); 
		Registered usr = new Registered("Jon_Iturrioz", "123", 1234, false); 
		Registered usr2 = new Registered("Andrea_García", "123", 1234, false);
		usr.setIrabazitakoa(12.00);
		usr2.setIrabazitakoa(10.0);
		List<Registered> registro = new ArrayList<>();
		registro.add(usr);
		registro.add(usr2);
		
		//Ordenamos la lista porque el método de rankingLortu de la clase DATA ACCESS ya hace esa función.
		System.out.println(registro);
		registro.sort((o1, o2) -> o2.getIrabazitakoa().compareTo(o1.getIrabazitakoa()));
		System.out.println(registro);
		try {
	
			Mockito.when(daoMock.rankingLortu()).thenReturn(registro);
			//invoke System Under Test (sut) 
			
			List<Registered> q = sut.rankingLortu();
			assertEquals(q.get(0).getIrabazitakoa(), 12.0, 0);
			assertEquals(q.get(1).getIrabazitakoa(), 10.0, 0);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
		} finally {

		}
	}

	@Test
	//@DisplayName("Hay dos usuarios, el segundo usuario tiene más dinero que el primero")
	public void test2(){

		//usuarios.add(ad1); 		usuarios.add(reg1); 	usuarios.add(reg2); 	usuarios.add(reg3); 	usuarios.add(reg4); 
		Registered usr = new Registered("Jon_Iturrioz", "123", 1234, false); 
		Registered usr2 = new Registered("Andrea_García", "123", 1234, false);
		usr.setIrabazitakoa(10.00);
		usr2.setIrabazitakoa(12.0);
		List<Registered> registro = new ArrayList<>();
		registro.add(usr);
		registro.add(usr2);
		System.out.println(registro);
		
		//Ahora ordenamos la lista de mayor menor ya que esta función lo realizaba el DataAccess 
		//al cual le hemos definido un doble y no tenemos acceso a ello.
		
		registro.sort((o1, o2) -> o2.getIrabazitakoa().compareTo(o1.getIrabazitakoa()));
		
		System.out.println(registro);
		
		
		try {
	
			Mockito.when(daoMock.rankingLortu()).thenReturn(registro);
			
			//invoke System Under Test (sut)  
			List<Registered> q = sut.rankingLortu();
			assertEquals(q.get(0).getIrabazitakoa(), 12.0, 0);
			assertEquals(q.get(1).getIrabazitakoa(), 10.0, 0);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
		} finally {

		}
	}

}
