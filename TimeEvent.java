/*
 * Used to create an time event
 *
 *	@param name		(Container) The callback with the extra bootstrapping
 *	@param cb		(Thread) The thread on which the timeout and the callback runs
 *	@param name		(String) The name of the event
 *	@param cb		(Callback) The callback for the event
 */
class TimeEvent extends Event {
	public Container wrapper;
	public Thread eventThread;

	TimeEvent(Container wrapper, Thread eventThread, String name, Callback cb) {
		super(name, cb);
		this.eventThread= eventThread;
		this.wrapper= wrapper;
	}
}