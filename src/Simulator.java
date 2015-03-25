/**
 * @author Ariel Ancer
 * @date 12/18/2013
 * 
 * Elevator simulation - Simulator Class.
 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulator {

	public Floor[] floors;
	public Random rand;
	Comparator<Event> comparator;
	public PriorityQueue<Event> pq;
	public Elevator e1, e2, e3;
	public Event ev1, ev2, ev3;

	/**
	 * The simulator constructor instantiates all of the variables for use
	 * in the simulation including: rand( a random generator object), floors(
	 * an array of 18 cells), the comparator object, the priority queue, the three 
	 * elevators, three initial events and their addition to the priority queue to 
	 * for when the simulation begins.
	 */
	public Simulator() {

		rand = new Random();
		floors = new Floor[18];
		comparator = new CompareEventByTime();
		pq = new PriorityQueue<Event>(3, comparator);

		e1 = new Elevator(1);
		e2 = new Elevator(8);
		e3 = new Elevator(16);

		ev1 = new Event(EventType.MOVE, e1);
		ev2 = new Event(EventType.MOVE, e2);
		ev3 = new Event(EventType.MOVE, e3);

		pq.add(ev1);
		pq.add(ev2);
		pq.add(ev3);

	}
/**
 * The main method creates the simulator object, calls the buildFloors method
 * and the run method.
 * @param args
 */
	public static void main(String[] args) {

		Simulator sim = new Simulator();
		sim.buildFloors();
		sim.run();
	}
/**
 * The run method defines the total running time of the simulation of 7200 and
 * randomly(1 in 36 chance) creates calls the generatePeople method with the 
 * current index as a parameter. The event with the lowest running time is found
 * and compared with the current index and if they are equivalent the event is
 * taken off the priority queue and the act method is called upon it. After the
 * simulation has commenced the Waiting method is called. 
 */
	public void run() {

			for (int i = 0; i < 7200; i++) {
				int rnd = rand.nextInt(36) + 1;
				if (rnd == 36) {
					generatePeople(i);
				}
				
			while (pq.peek().getTime() == i) {
				Event ev = pq.poll();
				ev.getElevator().act(ev, pq, floors, i);
			}

		}
		Waiting();
	}
/**
 * The Waiting method retrieves the number of people and cumulative waiting time for 
 * each elevator and adds them up and then divides the total waiting time by the total 
 * people the leave the elevator to find the Waiting time and print it out.
 */
	public void Waiting() {
		int NumPeople =e1.getNumPeople() + e2.getNumPeople() + e3.getNumPeople();
		int WaitingTime =e1.getWaitingTime() + e2.getWaitingTime() + e3.getWaitingTime();
		System.out.println("");
		System.out.println("Waiting time: " + WaitingTime / NumPeople);
	}
/**
 * The buildFloors method creates a floor to be put into each cell of the floors array.	
 */
	public void buildFloors() {
		for (int i = 0; i < floors.length; i++) {
			floors[i] = new Floor(i);
		}
	}
/**
 * The generatePeople method randomly generates a starting floor and end floor for the 
 * current person and adds him/her to a random floor in the simulation. If the randomly 
 * generated starting and end floors are the same the method is called again to randomize
 * them again.  
 * 
 * @param i the current index or point in time in the simulation.
 */
	public void generatePeople(int i) {

		int rnd = rand.nextInt(17) + 1;
		int rnd2 = rand.nextInt(17) + 1;
		if (rnd != rnd2) {
			Person p = new Person(rnd, rnd2, i);
			floors[rnd].addPeople(p);

		} else {
			generatePeople(i);
		}
	}

}
