import java.util.ArrayList;
import java.util.Iterator;


/*
 * PubSub:: Interface for subscribing and publishing events
 */
public class PubSub {
	protected ArrayList<Event> events;

	PubSub() {
		this.events= new ArrayList<Event>();
	}

	/*
	 *	on:: Used to subscribe to events.
	 *
	 *	@param eventname 	(String) The name of the event to subscribe to.
	 *	@param cb 			(Callback) Callback that is executed when the event occurs.
	 */
	public void on(String eventname, Callback cb) {
		this.events.add(new Event(eventname, cb));
	}

	/*
	 *	emit:: Used to publish an event
	 *
	 *	@param name (String) The name of the event to publish.
	 */
	public void emit(String name) {
		Iterator iterator= this.events.iterator();

		while(iterator.hasNext()) {
			Event event= (Event) iterator.next();

			/* If event is found, execute callback */
			if(event.name == name) {
				event.callback.event();
				return;
			}
		}
	}


	/*
	 *	off:: Used to unsubscribe an event.
	 *
	 *	@param name	(String) The name of the event to unsubscribe.
	 */
	public void off(String name) {
		int index= 0;
		boolean found_event= false;
		Iterator iterator= this.events.iterator();

		while(iterator.hasNext()) {
			Event event= (Event) iterator.next();

			/* If event is found, break out of the loop */
			if(event.name == name) {
				found_event= true;
				break;
			}

			index++;
		}

		/* Remove event if found */
		if(found_event)
			this.events.remove(index);
	}
}