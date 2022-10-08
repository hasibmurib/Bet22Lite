package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.Registered;
import domain.Sport;
import domain.Team;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();
	


	public TestDataAccess()  {

		System.out.println("Creating TestDataAccess instance");

		open();

	}


	public void open(){

		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
			return false;
	}

	public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev=null;
		db.getTransaction().begin();
		try {
			ev=new Event(desc,d, null, null);
			ev.addQuestion(question, qty);
			db.persist(ev);
			db.getTransaction().commit();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return ev;
	}
	public boolean existQuestion(Event ev,Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			return e.DoesQuestionExists(q.getQuestion());
		} else 
			return false;

	}

	public boolean gertaerakSortu(String desc, Date d, String sport) {
		System.out.println(">> DataAccess: addEvent");

		boolean b = true;
		db.getTransaction().begin();
		Sport spo =db.find(Sport.class, sport);
		if(spo!=null) {
			TypedQuery<Event> Equery = db.createQuery("SELECT e FROM Event e WHERE e.getEventDate() =?1 ",Event.class);
			Equery.setParameter(1, d);
			for(Event ev: Equery.getResultList()) {
				if(ev.getDescription().equals(desc)) {
					b = false;
				}
			}
			if(b) {
				String[] taldeak = desc.split("-");
				Team lokala = new Team(taldeak[0]);
				Team kanpokoa = new Team(taldeak[1]);
				Event e = new Event(desc, d, lokala, kanpokoa);
				e.setSport(spo);
				spo.addEvent(e);
				db.persist(e);
			}
		}
		else {
			return false;
		}

		db.getTransaction().commit();
		return b;
	}

	public boolean existEvent(String eventText,Date oneDate, String elDeporte) {
		boolean b = false;
		db.getTransaction().begin();
		Sport spo =db.find(Sport.class, elDeporte);
		if(spo!=null) {
			TypedQuery<Event> Equery = db.createQuery("SELECT e FROM Event e WHERE e.getEventDate() =?1 ",Event.class);
			Equery.setParameter(1, oneDate);
			for(Event ev: Equery.getResultList()) {
				if(ev.getDescription().equals(eventText))
					b = true;
			}
		}
		return b;
	}

	public boolean removeEvent(String description, Date eventDate, String sport) {
		boolean b = false;
		db.getTransaction().begin();
		Sport spo =db.find(Sport.class, sport);
		if(spo!=null) {
			TypedQuery<Event> Equery = db.createQuery("SELECT e FROM Event e WHERE e.getEventDate() =?1 ",Event.class);
			Equery.setParameter(1, eventDate);
			for(Event ev: Equery.getResultList()) {
				if(ev.getDescription().equals(description)) {
					b = true;
					db.remove(ev);
				}
			}
		}
		else {
			return false;
		}
		db.getTransaction().commit();
		return b;
	}


	public boolean addSport(String elDeporte) {
		boolean b = false;
		Sport deporte = new Sport(elDeporte);
		db.getTransaction().begin();
		Sport spo = db.find(Sport.class, elDeporte);
		if (spo==null) {
			db.persist(deporte);
			b = true;
		}
		db.getTransaction().commit();
		return b;
	}

	public boolean addRegistered(String userName, String passwd, Integer bankAcc, boolean isAdmin) {
		boolean b = false;
		Registered usuario = new Registered(userName, passwd, bankAcc, isAdmin);
		db.getTransaction().begin();
		Registered usr = db.find(Registered.class, usuario);
		if (usr==null) {
			db.persist(usuario);
			System.out.println("Añadiendo usuario: "+userName);
			b=true;
		}
		db.getTransaction().commit();
		return b;

	}


	public int removeRegistered() {
		db.getTransaction().begin();
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
		List<Registered> listR = Rquery.getResultList();
		System.out.println("Eliminando registros: "+listR.toString());

		for(Registered r: listR) {
			System.out.println(r.getIrabazitakoa());
			db.remove(r);
		}
		db.getTransaction().commit();
		return listR.size();

	}


	public Boolean ponerANullIrabazitakoa(Registered user) {
		boolean b = false;
		db.getTransaction().begin();
		Registered usr = db.find(Registered.class, user);
		if (usr!=null) {
			usr.setIrabazitakoa(null);
			b=true;
		}
		db.getTransaction().commit();
		return b;
	}


	public List<Registered> getRegisters() {
		db.getTransaction().begin();
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
		List<Registered> listR = Rquery.getResultList();
		db.getTransaction().commit();
		return listR;
		
	}


	public double setIrabazitakoa(Registered usr, double d) {
		db.getTransaction().begin();
		Registered r = (Registered) db.find(Registered.class, usr.getUsername());
		if (r!=null) {
			r.setIrabazitakoa(d);
			db.getTransaction().commit();
			return d;
		}
		else
		{
			db.getTransaction().commit();
			return -1;
		}
		
		
		
		
		
	}


	public void eliminarSport(String elDeporte) {
		System.out.println(">> DataAccess: eliminarSport");
		boolean b = false;
		Sport deporte = new Sport(elDeporte);
		db.getTransaction().begin();
		Sport spo = db.find(Sport.class, elDeporte);
		if (spo!=null) {
			db.remove(deporte);
			b = true;
		}
		db.getTransaction().commit();
		
		
	}
}

