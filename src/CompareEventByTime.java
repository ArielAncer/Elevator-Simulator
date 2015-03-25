/**
 * @author Ariel Ancer
 * @date 12/18/2013
 * 
 * Elevator simulation - CompareEventByTime Class.
 */
import java.util.Comparator;

/**
 * This class assists the priority queue in comparing the events that
 * are to be called by calling the events taking up less time first.
 *
 */
public class CompareEventByTime implements Comparator<Event> {

	@Override
	public int compare(Event ev1, Event ev2) {
		
		if(ev1.getTime() < ev2.getTime()){
			return -1;
		}
		if(ev1.getTime() > ev2.getTime()){
			return 1;
		}
		return 0;
	}

}
