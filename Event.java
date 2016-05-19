/*
 * Used to create an event
 *
 *	@param name		(String) The name of the event
 *	@param cb		(Callback) The callback for the event
 */
class Event {
	private String name;
	private Callback callback;

	Event(String name, Callback cb) {
		this.name= name;
		this.callback= cb;
	}


	/*
	 *	getName:: Getter for the field `name`
	 */
	public String getName() {
		return this.name;
	}


	/*
	 *	setName:: Setter for the field `name`
	 *
	 *	@param name (String) The name of the event
	 */
	public void setName(String name) {
		this.name= name;
	}


	/*
	 *	getCallback:: Getter for the field `callback`
	 */
	public Callback getCallback() {
		return this.callback;
	}


	/*
	 *	setCallback:: Setter for the field `callback`
	 *
	 *	@param callback (Callback) The callback for the event
	 */
	public void setCallback(Callback callback) {
		this.callback= callback;
	}
}