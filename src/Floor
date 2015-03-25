/**
 * @author Ariel Ancer
 * @date 12/18/2013
 * 
 * Elevator simulation - Floor Class.
 */
import java.util.ArrayList;

public class Floor {
	public ArrayList<Person> people;
	public int floorNum = 0;

	/**
	 * The Floor object's constructor with the parameter of floorNum
	 */
	public Floor(int floorNum) {
		this.floorNum = floorNum;
		this.people = new ArrayList<Person>();
	}

	/**
	 * The accessor for the ArrayList of people on the floor.
	 * 
	 * @return the people on the floor
	 */
	public ArrayList<Person> getPeople() {
		return people;
	}

	/**
	 * The addPeople method takes a parameter that is one person and adds that
	 * person to the ArrayList of the floor.
	 * 
	 * @param floorMember
	 *            the person to be added to the people ArrayList.
	 */
	public void addPeople(Person floorMember) {
		if (floorMember != null) {
			people.add(floorMember);
		}
	}

}
