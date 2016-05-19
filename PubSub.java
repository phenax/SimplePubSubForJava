import java.util.ArrayList;
import java.util.Iterator;


/*
 * PubSub:: For Subscribing, Unsubscribing and Publishing of events
 */
public class PubSub {
	protected ArrayList<Event> 		events;
	protected ArrayList<TimeEvent>  timeEvents;

	PubSub() {
		this.events= 	  new ArrayList<Event>();
		this.timeEvents=  new ArrayList<TimeEvent>();
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


	/*
	 *	onTime:: Used to subscribe to time events 
	 *	 that are published after `time`ms i.e. create a thread
	 *
	 *	@param time (long) The time delay before the event is published 
	 *	@param name (String) The name of the event to subscribe to.
	 *	@param cb 	(Callback) Callback that is executed when the event occurs.
	 */
	public void onTime(final long time, String name, final Callback cb) {

		/* Creating a new thread to run the callback */
		Container svr= new Container() {
			public void run() {
				try {
					/* Delay of `time`ms */
					Thread.sleep(time);

					if(!this.exit) {
						/* Callback fired */
				   		cb.event();
				   }
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}  
		};

		Thread timeThread= new Thread(svr, name);

		/* Adds the thread to the array of TimeEvents */
		this.timeEvents.add(new TimeEvent(svr, timeThread, name, cb));
	}


	/*
	 *	emitTime:: Used to publish a time event 
	 *	i.e. start the thread
	 *
	 *	@param name (String) The name of the event to publish.
	 */
	public void emitTime(String name) {
		Iterator iterator= this.timeEvents.iterator();

		while(iterator.hasNext()) {
			TimeEvent event= (TimeEvent) iterator.next();

			if(event.name == name) {
				/* Starts the thread */
				event.eventThread.start();
				return;
			}
		}
	}


	/*
	 *	offTime:: Used to unsubscribe to a time event 
	 *	 i.e. stop the thread.
	 *
	 *	@param name	(String) The name of the event to unsubscribe.
	 */
	public void offTime(String name) {
		int index= 0;
		boolean found_event= false;
		Iterator iterator= this.timeEvents.iterator();

		while(iterator.hasNext()) {
			TimeEvent event= (TimeEvent) iterator.next();

			try {
				if(event.name == name) {

					/* Stop the thread */
    				event.svr.stop();

					found_event= true;
					break;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}

			index++;
		}

		/* Remove event if found */
		if(found_event)
			this.timeEvents.remove(index);
	}
}