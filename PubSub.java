import java.util.ArrayList;


/*
 * PubSub:: For Subscribing, Unsubscribing and Publishing of events.
 */
public class PubSub {

	protected ArrayList<Event> 		events= 	 new ArrayList<Event>();
	protected ArrayList<TimeEvent>  timeEvents=  new ArrayList<TimeEvent>();


	/*
	 *	addEventAt:: Add a event to the `index` index position.
	 *	
	 *	@param index  (int) The index position to add the event to.
	 *	@param event  (TimeEvent) The event.
	 */
	public void addEventAt(int index, Event event) {
		if(index < this.events.size())
			this.events.set(index, event);
		else
			this.events.add(event);
	}


	/*
	 *	addTimeEventAt:: Add a time event to the `index` index position.
	 *	
	 *	@param index  (int) The index position to add the event to.
	 *	@param event  (TimeEvent) The time event.
	 */
	public void addTimeEventAt(int index, TimeEvent event) {
		if(index < this.timeEvents.size())
			this.timeEvents.set(index, event);
		else
			this.timeEvents.add(event);
	}


	/*
	 *	on:: Used to subscribe to events.
	 *
	 *	@param eventname 	(String) The name of the event to subscribe to.
	 *	@param cb 			(Callback) Callback that is executed when the event occurs.
	 *
	 */
	public void on(String eventname, Callback cb) {

		int index= 0;

		/* Create a new event */
		Event newEvent= new Event(eventname, cb);

		/* If event already exists, return false */
		for(Event event: this.events) {

			if(event.getName() == eventname)
				break;

			index++;
		}

		/* Adds a new event */
		addEventAt(index, newEvent);
	}

	/*
	 *	emit:: Used to publish an event.
	 *
	 *	@param name (String) The name of the event to publish.
	 */
	public void emit(String name) {

		for(Event event: this.events) {

			/* If event is found... */
			if(event.getName() == name) {

				/* Execute the callback */
				event.getCallback().event();
				
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

		for(Event event: this.events) {

			/* If event is found... */
			if(event.getName() == name) {
				
				/* Remove event if found */
				this.events.remove(index);

				break;
			}

			index++;
		}
	}



	/*
	 *	onTime:: Used to subscribe to time events.
	 *	 that are published after `time`ms i.e. create a thread.
	 *
	 *	@param time (long) The time delay before the event is published. 
	 *	@param name (String) The name of the event to subscribe to.
	 *	@param cb 	(Callback) Callback that is executed when the event occurs.
	 */
	public void onTime(final long time, String name, final Callback cb) {

		int index= 0;

		/* Creating a new thread to run the callback */
		Container wrapper= new Container() {

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

		/* Create a new thread with the wrapper */
		Thread timeThread= new Thread(wrapper, name);

		/* Create a new time event */
		TimeEvent newEvent= new TimeEvent(wrapper, timeThread, name, cb);

		/* If event already exists, return false */
		for(TimeEvent event: this.timeEvents) {

			if(event.getName() == name)
				break;

			index++;
		}

		/* Adds a new event */
		addTimeEventAt(index, newEvent);
	}


	/*
	 *	emitTime:: Used to publish a time event i.e.
	 *	 start the thread.
	 *
	 *	@param name (String) The name of the event to publish.
	 */
	public void emitTime(String name) {

		for(TimeEvent event: this.timeEvents) {

			if(event.getName() == name) {

				/* Starts the thread */
				event.getThread().start();

				return;
			}
		}
	}


	/*
	 *	offTime:: Used to unsubscribe to a time event i.e.
	 *	 stop the thread.
	 *
	 *	@param name	(String) The name of the event to unsubscribe.
	 */
	public void offTime(String name) {
		int index= 0;
		
		for(TimeEvent event: this.timeEvents) {

			if(event.getName() == name) {

				/* Stop the thread */
				event.getContainer().stop();

				/* Remove event if found */
				this.timeEvents.remove(index);

				return;
			}
			

			index++;
		}
	}
}