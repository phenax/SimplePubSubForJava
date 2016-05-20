import java.util.ArrayList;


/**
 *	Acts as an event handler for Subscribing, Unsubscribing and 
 *	Publishing of events and time events.
 *	
 *	@author Akshay Nair<phenax5@gmail.com>
 */
public class PubSub {

	/**
	 *	List of events.
	 */
	protected ArrayList<Event> events= new ArrayList<Event>();

	/**
	 *	List of time events.
	 */
	protected ArrayList<TimeEvent> timeEvents=  new ArrayList<TimeEvent>();


	/**
	 *	Adds a event to a particular index position.
	 *	
	 *	@param index  The index position to add the event to.
	 *	@param event  The event.
	 */
	public void addEventAt(int index, Event event) {
		if(index < this.events.size())
			this.events.set(index, event);
		else
			this.events.add(event);
	}


	/**
	 *	Adds a time event to a particular index position.
	 *	
	 *	@param index  The index position to add the event to.
	 *	@param event  The time event.
	 */
	public void addTimeEventAt(int index, TimeEvent event) {
		if(index < this.timeEvents.size())
			this.timeEvents.set(index, event);
		else
			this.timeEvents.add(event);
	}


	/**
	 *	Creates and Subscribes(or Binds) to an event.
	 *
	 *	@param eventname The name of the event to subscribe to.
	 *	@param cb 		 Callback that is executed when the event occurs.
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


	/**
	 *	Publishes(or Emits) an event that has already been subscribed.
	 *
	 *	@param name The name of the event to publish.
	 */
	public void emit(String name) {

		for(Event event: this.events) {

			/* If event is found... */
			if(event.getName() == name) {

				/* Execute the callback */
				event.getCallback().run();
				
				return;
			
			}
		}
	}


	/**
	 *	Unsubscribes(or UnBinds) an event that has already been subscribed
	 *
	 *	@param name	The name of the event to unsubscribe.
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



	/**
	 *	Creates and Subscribes(or Binds) to a time event
	 *	 that will be published after `time`ms.
	 *
	 *	@param time The time delay before the event is published. 
	 *	@param name The name of the event to subscribe to.
	 *	@param cb 	Callback that is executed when the event occurs.
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
						cb.run();
					
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


	/**
	 *	Publishes(or Emits) a time event if already Subscribed.
	 *
	 *	@param name The name of the event to publish.
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


	/**
	 *	Unsubscribes(or UnBinds) to a time event if already Subscribed.
	 *
	 *	@param name The name of the event to unsubscribe.
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