package rankingLortuTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Registered;
import test.dataAccess.TestDataAccess;

public class RankingLortuDAW {

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
	//@DisplayName("Hay dos usuarios, el primer usuario tiene más dinero que el otro")
	public void test1(){

		//usuarios.add(ad1); 		usuarios.add(reg1); 	usuarios.add(reg2); 	usuarios.add(reg3); 	usuarios.add(reg4); 
		Registered usr = new Registered("Jon_Iturrioz", "123", 1234, false); 
		Registered usr2 = new Registered("Andrea_García", "123", 1234, false);
		
		testDA.removeRegistered();
		testDA.addRegistered(usr.getUsername(), usr.getPassword(), usr.getBankAccount(), false);
		testDA.addRegistered(usr2.getUsername(), usr2.getPassword(), usr2.getBankAccount(), false);
		
		double r1= testDA.setIrabazitakoa(usr, 12.0);
		System.out.println(r1);
		double r2=testDA.setIrabazitakoa(usr2, 10.0);
		System.out.println(r2);
		
		try {

			//invoke System Under Test (sut)  
			List<Registered> q=sut.rankingLortu();
			assertEquals(q.get(0).getIrabazitakoa(), 12.0, 0);
			assertEquals(q.get(1).getIrabazitakoa(), 10.0, 0);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
		} finally {
			testDA.open(); 
			testDA.removeRegistered();
			for (Registered r: losRegistrados) {
				System.out.println(r.toString());
				testDA.addRegistered(r.getUsername(), r.getPassword(), r.getBankAccount(), r.isAdmin());
			}
		}
	}
	
	@Test
	//@DisplayName("Hay dos usuarios, el segundo usuario tiene más dinero que el primero")
	public void test2(){

		//usuarios.add(ad1); 		usuarios.add(reg1); 	usuarios.add(reg2); 	usuarios.add(reg3); 	usuarios.add(reg4); 
		Registered usr = new Registered("Jon_Iturrioz", "123", 1234, false); 
		Registered usr2 = new Registered("Andrea_García", "123", 1234, false);
		
		testDA.removeRegistered();
		testDA.addRegistered(usr.getUsername(), usr.getPassword(), usr.getBankAccount(), false);
		testDA.addRegistered(usr2.getUsername(), usr2.getPassword(), usr2.getBankAccount(), false);
		
		double r1= testDA.setIrabazitakoa(usr, 10.0);
		System.out.println(r1);
		double r2=testDA.setIrabazitakoa(usr2, 12.0);
		System.out.println(r2);
		
		try {

			//invoke System Under Test (sut)  
			List<Registered> q=sut.rankingLortu();
			assertEquals(q.get(0).getIrabazitakoa(), 12.0, 0);
			assertEquals(q.get(1).getIrabazitakoa(), 10.0, 0);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
		} finally {
			testDA.open(); 
			testDA.removeRegistered();
			for (Registered r: losRegistrados) {
				System.out.println(r.toString());
				testDA.addRegistered(r.getUsername(), r.getPassword(), r.getBankAccount(), r.isAdmin());
			}
		}
	}

	


}

