class TimeEvent extends Event {
	public Container svr;
	public Thread eventThread;

	TimeEvent(Container svr, Thread eventThread, String name, Callback cb) {
		super(name, cb);
		this.eventThread= eventThread;
		this.svr= svr;
	}
}