package rankingLortuTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registered;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;


public class RankingLortuInt {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;
	static TestDataAccess testDA;


	@BeforeClass
	public static void setUpClass() {
		//sut= new BLFacadeImplementation();

		// you can parametrize the DataAccess used by BLFacadeImplementation
		//DataAccess da= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		DataAccess da= new DataAccess();

		sut=new BLFacadeImplementation(da);

		testBL= new TestFacadeImplementation();
		
		testDA= new TestDataAccess();
		
		
	}

	@Test
	@DisplayName("Hay dos usuarios, el primer usuario tiene más dinero que el otro")
	public void test1() {
		List<Registered> losRegistrados = testDA.getRegisters();
		testDA.removeRegistered();

		Registered usr = new Registered("Jon_Iturrioz", "123", 1234, false); 
		Registered usr2 = new Registered("Andrea_García", "123", 1234, false);
		
		testDA.addRegistered(usr.getUsername(), usr.getPassword(), usr.getBankAccount(), false);
		testDA.addRegistered(usr2.getUsername(), usr2.getPassword(), usr2.getBankAccount(), false);
		
		double r1= testDA.setIrabazitakoa(usr, 12.0);
		System.out.println(r1);
		double r2=testDA.setIrabazitakoa(usr2, 10.0);
		System.out.println(r2);

		try {
			//invoke System Under Test (sut)  
			List<Registered> q = sut.rankingLortu();
			assertEquals(q.get(0).getIrabazitakoa(), 12.0, 0);
			assertEquals(q.get(1).getIrabazitakoa(), 10.0, 0);

		}
		catch(Exception e) {
			fail("NO HAY QUE ENTRAR AQUI");
		} finally {
			for (Registered r: losRegistrados) {
				System.out.println(r.toString());
				testDA.addRegistered(r.getUsername(), r.getPassword(), r.getBankAccount(), r.isAdmin());
			}
		}

	}

	@Test
	@DisplayName("Hay dos usuarios, el segundo usuario tiene más dinero que el primero")
	
	public void test2(){
		List<Registered> losRegistrados = testDA.getRegisters();
		testDA.removeRegistered();

		Registered usr = new Registered("Jon_Iturrioz", "123", 1234, false); 
		Registered usr2 = new Registered("Andrea_García", "123", 1234, false);
	
		
		testDA.addRegistered(usr.getUsername(), usr.getPassword(), usr.getBankAccount(), false);
		testDA.addRegistered(usr2.getUsername(), usr2.getPassword(), usr2.getBankAccount(), false);
		
		double r1= testDA.setIrabazitakoa(usr, 12.0);
		System.out.println(r1);
		double r2=testDA.setIrabazitakoa(usr2, 10.0);
		System.out.println(r2);

		try {
			//invoke System Under Test (sut)  
			List<Registered> q = sut.rankingLortu();
			assertEquals(q.get(0).getIrabazitakoa(), 12.0, 0);
			assertEquals(q.get(1).getIrabazitakoa(), 10.0, 0);

		}
		catch(Exception e) {
			fail("NO HAY QUE ENTRAR AQUI");
		} finally {
			for (Registered r: losRegistrados) {
				System.out.println(r.toString());
				testDA.addRegistered(r.getUsername(), r.getPassword(), r.getBankAccount(), r.isAdmin());
			}
		}

	}

}

