/*
 * Used to create an time event
 *
 *	@param name		(Container) The callback with the extra bootstrapping
 *	@param cb		(Thread) The thread on which the timeout and the callback runs
 *	@param name		(String) The name of the event
 *	@param cb		(Callback) The callback for the event
 */
class TimeEvent extends Event {
	private Container wrapper;
	private Thread eventThread;

	TimeEvent(Container wrapper, Thread eventThread, String name, Callback cb) {
		super(name, cb);
		this.eventThread= eventThread;
		this.wrapper= wrapper;
	}


	/*
	 *	getContainer:: Getter for the field `wrapper`
	 */
	public Container getContainer() {
		return this.wrapper;
	}


	/*
	 *	setContainer:: Setter for the field `wrapper`
	 *
	 *	@param wrapper (Container) The container for the callback
	 */
	public void setContainer(Container wrapper) {
		this.wrapper= wrapper;
	}


	/*
	 *	getThread:: Getter for the field `eventThread`
	 */
	public Thread getThread() {
		return this.eventThread;
	}


	/*
	 *	setThread:: Setter for the field `eventThread`
	 *
	 *	@param eventThread (Thread) The thread that the time event runs on
	 */
	public void setThread(Thread eventThread) {
		this.eventThread= eventThread;
	}
}