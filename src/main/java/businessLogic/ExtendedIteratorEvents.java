package businessLogic;
import java.util.List;

import domain.Event;

public class ExtendedIteratorEvents implements ExtendedIterator<Object> {

	List<Event> eventos;
	int position;
	
	public ExtendedIteratorEvents(List<Event> events) {
		this.eventos = events;
		this.position = 0;
	}
	
	
	@Override
	public boolean hasNext() {
		
		return position < eventos.size();
	}

	@Override
	public Object next() {
		
		Event evento = eventos.get(position);
		position +=1;
		return evento;
	}

	@Override
	public Object previous() {
		
		if (position>0) {
			Event evento = eventos.get(position-1);
			position -=1;
			return evento;
		
		}
		
		else
			return null;
	}

	@Override
	public boolean hasPrevious() {
		return position-1 != -1; 
	}

	@Override
	public void goFirst() {
		position=0;
	}

	@Override
	public void goLast() {
		position=eventos.size();

	}

}
