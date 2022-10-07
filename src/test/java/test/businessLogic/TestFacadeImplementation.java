package test.businessLogic;


import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import exceptions.EventFinished;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public boolean removeEvent(String eventText, Date oneDate, String sport) {
			
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(eventText, oneDate, sport);
			dbManagerTest.close();
			return b;
			
		}
		
		public Event addEventWithQuestion(String desc, Date d, String q, float qty) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEventWithQuestion(desc,d,q, qty);
			dbManagerTest.close();
			return o;

		}
		
		public boolean gertaerakSortu(String description,Date eventDate, String sport) throws EventFinished{
	    	if(new Date().compareTo(eventDate)>0)
				throw new EventFinished("Gertaera honen data dagoeneko pasa da");
	    	
	    	dbManagerTest.open();
	    	boolean b = dbManagerTest.gertaerakSortu(description, eventDate, sport);
	    	dbManagerTest.close();
	    	return b;
	    }
		
		public boolean existEvent(String event, Date d, String sport) {
			dbManagerTest.open();
			boolean b = dbManagerTest.existEvent(event, d, sport);
			return b;
		}
}
