# Elevator Simulation Project

An application that simulates elevators in a building.

Parameters (testing defaults in parenthesis):
Floors(18)
Elevators(3)
Elevator capacity (10)
Time between floors(2 sec)
Time to open doors (1 sec)
Time to close doors (1 sec)
Time to exit elevator (2 sec per person)
Time to enter elevator (3 sec per person)
Number of people (200)
Length of simulation (7200 secs)

Goal of simulation: calculate average wait time for the elevator.

Elevator Program Processing:

Generate random button-pushing events ( people on random floors pushing buttons to go to random floors. )
Initialize elevators to their respective floors. 

First algorithm:
Elevators start on top, bottom, and middle (random) floors moving up or down. Once they cannot move in a particular direction, they reverse direction.

If the elevator passes a floor with people going the elevator's way, the elevator opens the doors, and people come in.

Second algorithm:
When a person pushes a button on a floor an elevator is assigned to this person in the following order of priorities:
Closest unassigned elevator going toward the person in the direction the person wants to travel
Closest assigned elevator going toward the person in the direction the person wants to travel
Closest unassigned elevator. Elevator changes course toward the person.
Furthers assigned elevator

Events:
1.person pushes button
2.elevator opens doors
3.elevator closes doors (elevator leaves floor)
4.elevator arrives at floor (door beginning to open or maybe not, depending on algorithm used)
5.elevator doors beginning to close
etc.
