package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacade;
import businessLogic.BusinessLogicFactory;
import businessLogic.ExtendedIterator;
import configuration.ConfigXML;
import domain.Event;

public class MainPatronIterator {

	public static void main(String[] args) {
		ConfigXML c = ConfigXML.getInstance();

		BLFacade blFacade = BusinessLogicFactory.createBusinessLogicObject(c);

		SimpleDateFormat sdf = new	SimpleDateFormat("dd/MM/yyyy");

		Date date;

		try {

			date = sdf.parse("17/12/2022");
			ExtendedIterator<Event> i = blFacade.getEventsIterator(date);
			Event e;
			System.out.println("_______________________________");
			System.out.println("Recorrido hacía atrás");
			i.goLast();

			while (i.hasPrevious()) {
				e = i.previous();
				System.out.println(e.toString());
			}

			System.out.println();
			System.out.println("_______________________________");
			System.out.println("Recorrido hacía delante");
			i.goFirst();

			while(i.hasNext()) {
				e = i.next();
				System.out.println(e.toString());
			}
		} catch (ParseException e1) {
			System.out.println("Problems with date?? "+ "17/12/2020");


		}
	}

}
