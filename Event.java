/**
 *	Used to create an event
 *	
 *	@author Akshay Nair<phenax5@gmail.com>
 */
public class Event {
	
	/**
	 *	The event name
	 */
	private String name;

	/**
	 *	The event callback
	 */
	private Callback callback;


	/**
	 *	Used to create an event
	 *
	 *	@param name	 The name of the event
	 *	@param cb	 The callback for the event
	 */
	Event(String name, Callback cb) {
		this.name= name;
		this.callback= cb;
	}


	/**
	 *	Getter for the field `name`
	 */
	public String getName() {
		return this.name;
	}


	/**
	 *	Setter for the field `name`
	 *
	 *	@param name The name of the event
	 */
	public void setName(String name) {
		this.name= name;
	}


	/**
	 *	Getter for the field `callback`
	 */
	public Callback getCallback() {
		return this.callback;
	}


	/**
	 *	Setter for the field `callback`
	 *
	 *	@param callback The callback for the event
	 */
	public void setCallback(Callback callback) {
		this.callback= callback;
	}
}