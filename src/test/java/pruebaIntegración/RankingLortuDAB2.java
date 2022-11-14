package pruebaIntegración;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import dataAccess.DataAccessRankingLortu;
import domain.Registered;
import test.dataAccess.TestDataAccess;

public class RankingLortuDAB2 {

	//sut:system under test
	static DataAccessRankingLortu sut=new DataAccessRankingLortu();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();


	
	List<Registered> losRegistrados = testDA.getRegisters();


	@Test
	//@DisplayName("No hay usuarios registrados")
	public void test1(){

	
		testDA.removeRegistered();
		try {

			List<Registered> q=sut.rankingLortu();
			assertTrue(q.isEmpty());


		} catch (Exception e) {
		
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
	//@DisplayName("Hay usuarios registrados pero getIrabazitakoa es null")
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

