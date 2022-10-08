package rankingLortuTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dataAccess.DataAccess;
import domain.Registered;
import test.dataAccess.TestDataAccess;

class RankingLortuDAB {

	//sut:system under test
	static DataAccess sut=new DataAccess();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();

//	//private boolean ev;
//	ArrayList<Registered> usuarios = new ArrayList<>();
//	Registered ad1=new Registered("admin", "123", 1234,true);
//	Registered reg1 =new Registered("registered", "123", 1234, false);
//	Registered reg2 = new Registered("andrea", "123", 1111, false);
//	Registered reg3 = new Registered("markel", "123", 1111, false);
//	Registered reg4 = new Registered("mikel", "123", 1111, false);
	
	List<Registered> losRegistrados = testDA.getRegisters();


	@Test
	@DisplayName("No hay usuarios registrados")
	public void test1(){

		//usuarios.add(ad1); 		usuarios.add(reg1); 	usuarios.add(reg2); 	usuarios.add(reg3); 	usuarios.add(reg4); 
		testDA.removeRegistered();
		try {

			//invoke System Under Test (sut)  
			List<Registered> q=sut.rankingLortu();
			assertTrue(q.isEmpty());


		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
		} finally {
			testDA.open();      
			for (Registered r: losRegistrados) {
				System.out.println(r.toString());
				testDA.addRegistered(r.getUsername(), r.getPassword(), r.getBankAccount(), r.isAdmin());
			}
		}
	}

	@Test
	@DisplayName("Hay usuarios registrados pero getIrabazitakoa es null")
	public void test2(){

		Registered usr = new Registered("Jon_Iturrioz", "123", 1234, false); 
		testDA.addRegistered(usr.getUsername(), usr.getPassword(), usr.getBankAccount(), false);
		Boolean res = testDA.ponerANullIrabazitakoa(usr);
		System.out.println(res);

		try {

			List<Registered> q=sut.rankingLortu();
			assertTrue(!q.isEmpty());
			

		}
		catch (Exception e) {
			fail();
		} finally {
			testDA.removeRegistered();     
			for (Registered r: losRegistrados) {
				System.out.println(r.toString());
				testDA.addRegistered(r.getUsername(), r.getPassword(), r.getBankAccount(), r.isAdmin());
			}

		}
	}


}

