public class Container implements Runnable {
	protected volatile boolean exit = false;

	public void run() {

	}

	public void stop() {
		exit = true;
	}
}