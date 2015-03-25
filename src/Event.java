/**
 * @author Ariel Ancer
 * @date 12/18/2013
 * 
 *       Elevator simulation - Event Class.
 */
public class Event {

	private Elevator elevator;
	protected EventType type;
	private int time;

	/**
	 * The Event object's constructor with the type of event and elevator
	 * parameters.
	 * 
	 * @param type
	 *            the type of event.
	 * @param elevator
	 *            the elevator that the event will be called on.
	 */
	public Event(EventType type, Elevator elevator) {

		this.type = type;
		this.elevator = elevator;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Elevator getElevator() {
		return elevator;
	}

}
