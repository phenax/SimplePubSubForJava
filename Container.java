/*
 *	Container::   A wrapper for creating excess 
 *	 bootstrapping stuff for new threads
 */
public class Container implements Runnable {
	protected volatile boolean exit = false;

	/* Stuff goes here */
	public void run() {}

	/* Stop the thread */
	public void stop() {
		exit = true;
	}
}