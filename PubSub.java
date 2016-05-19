import java.util.ArrayList;
import java.util.Iterator;

class Event {
	public String name;
	public Callback callback;

	Event(String name, Callback cb) {
		this.name= name;
		this.callback= cb;
	}
}

public class PubSub {
	protected ArrayList<Event> events;

	PubSub(int numOfEvents) {
		events= new ArrayList<Event>();
	}

	public void on(String eventname, Callback cb) {
		events.add(new Event(eventname, cb));
	}

	public void emit(String name) {
		Iterator itr= events.iterator();

		while(itr.hasNext()) {
			Event ev= (Event)itr.next();

			if(ev.name == name) {
				ev.callback.event();
				return;
			}
		}
	}

	public void off(String name) {
		int index= 0;
		Iterator itr= events.iterator();

		while(itr.hasNext()) {
			Event ev= (Event)itr.next();

			if(ev.name == name)
				break;

			index++;
		}

		events.remove(index);
	}
}