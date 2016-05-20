/**
 *	A wrapper for creating excess 
 *	 bootstrap stuff for new threads
 *	
 *	@author Akshay Nair<phenax5@gmail.com>
 */
public class Container implements Runnable {
	protected volatile boolean exit = false;

	/**
	 * Stuff goes here
	 */
	public void run() {}

	/**
	 * Stops running the thread
	 */
	public void stop() {
		exit = true;
	}
}