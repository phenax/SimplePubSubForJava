/**
 *	Used to create a time event
 *	
 *	@author Akshay Nair<phenax5@gmail.com>
 */
class TimeEvent extends Event {

	/**
	 *	The container that runs in the event thread
	 */
	private Container wrapper;

	/**
	 *	The thread that executes the callback
	 */
	private Thread eventThread;


	/**
	 *	Used to create a time event
	 *
	 *	@param name	  The callback with the extra bootstrapping
	 *	@param cb	  The thread on which the timeout and the callback runs
	 *	@param name	  The name of the event
	 *	@param cb	  The callback for the event
	 */
	TimeEvent(Container wrapper, Thread eventThread, String name, Callback cb) {
		super(name, cb);
		this.eventThread= eventThread;
		this.wrapper= wrapper;
	}


	/**
	 *	Getter for the field `wrapper`
	 */
	public Container getContainer() {
		return this.wrapper;
	}


	/**
	 *	Setter for the field `wrapper`
	 *
	 *	@param wrapper The container for the callback
	 */
	public void setContainer(Container wrapper) {
		this.wrapper= wrapper;
	}


	/**
	 *	Getter for the field `eventThread`
	 */
	public Thread getThread() {
		return this.eventThread;
	}


	/**
	 *	Setter for the field `eventThread`
	 *
	 *	@param eventThread The thread that the time event runs on
	 */
	public void setThread(Thread eventThread) {
		this.eventThread= eventThread;
	}
}