class TimeEvent extends Event {
	public long time;
	public Thread eventThread;

	TimeEvent(long time, Thread eventThread, String name, Callback cb) {
		super(name, cb);
		this.eventThread= eventThread;
		this.time= time;
	}
}