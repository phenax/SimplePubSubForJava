/*
 * Used to create an event
 *
 *	@param name		(String) The name of the event
 *	@param cb		(Callback) The callback for the event
 */
class Event {
	public String name;
	public Callback callback;

	Event(String name, Callback cb) {
		this.name= name;
		this.callback= cb;
	}
}