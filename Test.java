public class Test {
	public static void main(String[] args) {
		PubSub events= new PubSub(5);

		events.on("funtime", new Callback() {
			public void event() {
				System.out.println("ITS FUN TIME");
			}
		});

		events.on("fun", new Callback() {
			public void event() {
				System.out.println("WOWOWOWOWOWOW");
			}
		});

		System.out.println("What time is it?");
		events.emit("funtime");

		System.out.println("How is it?");
		events.emit("fun");



		events.off("funtime");
		events.emit("funtime");

		events.off("fun");
		events.emit("fun");
	}
}