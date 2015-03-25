/**
 * @author Ariel Ancer
 * @date 12/18/2013
 * 
 * Elevator simulation - Elevator Class.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Elevator {

	public Person[] riders;
	public int fnum;
	public int waiting;
	public int numPeople;
	public boolean dir = false;

	public Elevator(int fnum) {
		riders = new Person[10];
		this.fnum = fnum;
	}

	/**
	 * The act method perform's operations for the simulator based upon the
	 * event that it is being called on from the priority queue and it's
	 * parameters. Each event then adds a corresponding event to the priority
	 * queue and sets the time it will be called at depending on it's operation.
	 * 
	 * The OPEN operation prints the floor the elevator doors open on and creates 
	 * the let off event, sets it's time to 2 multiplied by the number of of riders
	 * that need to get off and adds the time to that number. The LETOFF event is then 
	 * added to the priority queue.
	 * 
	 * The LETOFF operation iterates through the riders in the elevator and if it finds
	 * a rider whose destination is the current floor it calls the setNumPeople method 
	 * sets it to one plus the current count of people who have left the elevator and calls
	 * the setWaitingTime method which gets the current total waiting times and adds the
	 * person getting off's waiting time to it by subtracting the riders time he/she was 
	 * initialized at by the current time when he leaves the elevator (used in the eventual 
	 * simulation average time calculation). Makes the riders index for that person null 
	 * and prints out that he has left the elevator at this floor. The LETON operation is 
	 * added to the priority queue.
	 * 
	 * The LETON operation iterates over the people in the current floor and if the person's
	 * starting floor is the current floor then the elevator is checked for a null (empty slot)
	 * and if one is found the person enters the elevator and is removed from the floor. The 
	 * CLOSE operation is added to the priority queue.
	 * 
	 * The CLOSE operation prints out a message to detail that the elevator doors are closing and 
	 * adds the MOVE operation to the priority queue.
	 * 
	 * The MOVE operation checks whether the elevator has hit the top or bottom floor of the building,
	 * if so the elevator calls the switchDirection method and the floor number is either increased or
	 * decreased depending on the direction of the elevator. The elevator and floor are then checked for
	 * either potential passengers or passengers that need to leave the elevator and if found the OPEN
	 * operation is added , if none are found it adds the MOVE operation to the priority queue.
	 * 
	 * 
	 * @param e
	 *            the event to be acted out.
	 * @param pq
	 *            the priority queue that is being used.
	 * @param floors
	 *            the floors array parameter.
	 * @param time
	 *            the current index or time in the simulation.
	 */
	public void act(Event e, PriorityQueue<Event> pq, Floor[] floors, int time) {

		switch (e.type) {
		case OPEN:
			System.out.println("Elevators doors open at " + getFloorNum());

			Event ev_off = new Event(EventType.LETOFF, this);
			ev_off.setTime((2 * numRiderDest()) + time);
			pq.add(ev_off);
			break;
		case LETOFF:
			for (int i = 0; i < riders.length; i++) {
				if (riders[i] != null
						&& riders[i].getFloorEnd() == getFloorNum()) {
					setNumPeople(getNumPeople() + 1);
					setWaitingTime(getWaitingTime()
							+ (time - riders[i].getwaitTime()));
					riders[i] = null;
					System.out.println("A rider has left the elevator at "
							+ getFloorNum());
				}
			}

			Event ev_on = new Event(EventType.LETON, this);
			ev_on.setTime((numMembers(floors) * 3) + time);
			pq.add(ev_on);
			break;
		case LETON:
			ArrayList<Person> flr_memb2 = floors[getFloorNum()].getPeople();
			for (int j = 0; j < flr_memb2.size(); j++) {
				if (flr_memb2.get(j).floorStart == getFloorNum()) {
					int index = Arrays.asList(riders).indexOf(null);
					System.out.println("A person enters the elevator at "
							+ getFloorNum());
					riders[index] = flr_memb2.get(j);
					flr_memb2.remove(j);
				}
			}
			Event ev_close = new Event(EventType.CLOSE, this);
			ev_close.setTime(1 + time);
			pq.add(ev_close);
			break;
		case CLOSE:
			System.out.println("Elevators doors close");
			Event ev_move = new Event(EventType.MOVE, this);
			ev_move.setTime(2 + time);
			pq.add(ev_move);
			break;
		case MOVE:

			if (getFloorNum() == 17) {
				dir = switchDirection(dir);
			} else if (getFloorNum() == 0) {
				dir = switchDirection(dir);
			}

			if (dir) {
				setFloorNum(getFloorNum() + 1);
			} else {
				setFloorNum(getFloorNum() - 1);
			}

			if (numRiderDest() == 0 && numMembers(floors) == 0) {
				Event ev_mve = new Event(EventType.MOVE, this);
				ev_mve.setTime(2 + time);
				pq.add(ev_mve);
			} else {
				Event ev_open = new Event(EventType.OPEN, this);
				ev_open.setTime(1 + time);
				pq.add(ev_open);
			}

			break;
		}
	}

	/**
	 * The numMembers method checks how many people need to enter the elevator
	 * at the current floor.
	 * 
	 * @param floors
	 *            the array containing all of the floors.
	 * @return a counter variable.
	 */
	public int numMembers(Floor floors[]) {
		int cnt = 0;
		ArrayList<Person> flr_memb = floors[getFloorNum()].getPeople();
		for (int j = 0; j < flr_memb.size(); j++) {
			if (flr_memb.get(j).floorStart == getFloorNum()) {
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * The numRiderDest method checks how many people riding the elevator that
	 * need to get off at the current floor.
	 * 
	 * @return a counter variable
	 */
	public int numRiderDest() {
		int cnt = 0;
		for (int i = 0; i < riders.length; i++) {
			if (riders[i] != null && riders[i].floorEnd == getFloorNum()) {
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * This method takes a boolean parameter and gives the opposite as it's
	 * result. e.g true will result in false and false will result in true.
	 * 
	 * @param dir
	 *            boolean variable signifying direction ( true = upwards , false
	 *            = downwards).
	 * @return the boolean true value.
	 */
	public boolean switchDirection(boolean dir) {
		if (dir) {
			return false;
		}
		return true;
	}

	/**
	 * This is the accessor method for the current floor number of the elevator.
	 * 
	 * @return the current floor number.
	 */
	public int getFloorNum() {
		return fnum;
	}

	/**
	 * This is the mutator method for setting the current floor number.
	 * 
	 * @param fnum
	 *            the current floor number.
	 */
	public void setFloorNum(int fnum) {
		this.fnum = fnum;
	}

	/**
	 * This is the accessor method for the total waiting time's for people who
	 * used the elevator.
	 * 
	 * @return the current total waiting time.
	 */
	public int getWaitingTime() {
		return waiting;
	}

	/**
	 * This is the mutator method for the total waiting time's for people who
	 * used the elevator.
	 * 
	 * @param waiting
	 *            input parameter of the total waiting time's for people who
	 *            used the elevator.
	 */
	public void setWaitingTime(int waiting) {
		this.waiting = waiting;
	}

	/**
	 * This is the accessor method for the number of people in this elevator.
	 * 
	 * @return the number of people in the elevator.
	 */
	public int getNumPeople() {
		return numPeople;
	}

	/**
	 * This is the mutator method for the number of people in this elevator.
	 * 
	 * @param numPeople
	 *            input parameter of current number of people.
	 */
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
}
