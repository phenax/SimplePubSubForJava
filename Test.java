public class Test {
	public static void main(String[] args) {
		PubSub events= new PubSub();

		/* Subscribing a callback to the event 'funtime' */
		events.on("funtime", new Callback() {
			public void event() {
				System.out.println("ITS FUN TIME");
			}
		});

		/* Subscribing a callback to the event 'awesomeness' */
		events.on("awesomeness", new Callback() {
			public void event() {
				System.out.println("WOWOWOWOWOWOW");
			}
		});


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
	}
}