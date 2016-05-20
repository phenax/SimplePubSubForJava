/**
 * Class to test all the events
 *	
 *	@author Akshay Nair<phenax5@gmail.com>
 */
public class Test {
	public static void main(String[] args) {

		/* Creating an eventhandler instance of PubSub */
		PubSub events= new PubSub();


		/* Subscribing a callback to the event 'funtime' */
		events.on("funtime", new Callback() {
			public void run() {
				System.out.println("ITS FUN TIME");
			}
		});

		/* Subscribing a callback to the event 'awesomeness' */
		events.on("awesomeness", new Callback() {
			public void run() {
				System.out.println("WOWOWOWOWOWOW");
			}
		});

		/* Overwriting an event */
		events.on("awesomeness", new Callback() {
			public void run() {
				System.out.println("AWESOMENESS WOWOWOWOWOWOW");
			}
		});

		events.on("wow", new Callback() {
			public void run() {
				System.out.println("WOWOWOWOWOWOW");
			}
		});

		events.emit("wow");


		System.out.println("What time is it?");

		/* Publishing the event 'funtime' */
		events.emit("funtime");

		System.out.println("\nHow is it?");

		/* Publishing the event 'awesomeness' */
		events.emit("awesomeness");


		/* Unsubscribing to 'funtime' */
		events.off("funtime");

		/* Publishing 'funtime' wont do anything because it is now unsubbed */
		events.emit("funtime");

		/* Unsubscribing to 'funtime' */
		events.off("awesomeness");




		/* Creating a timer */
		events.onTime(3000, "waiting", new Callback() {
			public void run() {
				System.out.println("3 seconds are done bruh");
			}
		});

		/* Start the timer */
		/* Waits for 3000ms before executing callback */
		events.emitTime("waiting");

		/* To stop the timeout event */
		events.offTime("waiting");

		System.out.println("Gonna wait for 3 seconds here...");

	}
}