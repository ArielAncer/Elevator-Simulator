/**
 * @author Ariel Ancer
 * @date 12/18/2013
 * 
 *       Elevator simulation - Person Class.
 */
public class Person {

	public int floorStart;
	public int floorEnd;
	public int waitTime;

	/**
	 * The Person object's constructor with parameters starting floor, final
	 * floor and starting time.
	 * 
	 * @param floorStart
	 *            the person's starting floor
	 * @param floorEnd
	 *            the person's final floor
	 * @param waitTime
	 *            the person's starting time
	 */
	public Person(int floorStart, int floorEnd, int waitTime) {

		this.floorStart = floorStart;
		this.floorEnd = floorEnd;
		this.waitTime = waitTime;

	}

	/**
	 * The person's starting floor accessor.
	 * 
	 * @return the person's starting floor
	 */
	public int getFloorStart() {
		return floorStart;
	}

	/**
	 * The person's final floor accessor.
	 * 
	 * @return the person's final floor
	 */
	public int getFloorEnd() {
		return floorEnd;
	}

	/**
	 * The person's starting time accessor.
	 * 
	 * @return the person's starting time
	 */
	public int getwaitTime() {
		return waitTime;
	}

	/**
	 * The person's starting floor mutator.
	 * 
	 * @param floorStart
	 *            the person's new starting floor
	 */
	public void setFloorStart(int floorStart) {
		this.floorStart = floorStart;
	}

	/**
	 * The person's final floor mutator.
	 * 
	 * @param floorEnd
	 *            the person's new final floor
	 */
	public void setFloorEnd(int floorEnd) {
		this.floorEnd = floorEnd;
	}

	/**
	 * The person's starting time mutator.
	 * 
	 * @param waitTime
	 *            the person's new starting time
	 */
	public void setwaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

}
