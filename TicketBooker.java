package com.train;
import java.util.*;

public class TicketBooker {
	
	
	static int availableLowerBerths =1;
	static int availableMiddleBerths =1;
	static int availableUpperBerths =1;
	static int availableRacTickets =1;
	static int availableWaitingList =1;
	
	static Queue<Integer> waitingList = new LinkedList<>();
	static Queue<Integer> racList = new LinkedList<>();
	static List<Integer> bookedTickedList = new ArrayList<>();
	
	static List<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1));
	static List<Integer> middleBethPositons = new ArrayList<>(Arrays.asList(1));
	static List<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1));
	static List<Integer> racBerthPostions = new ArrayList<>(Arrays.asList(1));
	static List<Integer> waitingBerthPositons= new ArrayList<>(Arrays.asList(1));
	
	static Map<Integer , Passenger> passengers = new HashMap<>();
	
	
	public void bookTicket(Passenger p , int berthInfo , String allotedBerth) {
		// assign the seat no and berth type
		p.number =berthInfo;
		p.alloted = allotedBerth;
		//add pasenger to map
		passengers.put(p.passengerId, p);
		
		// add pasenger id to list of booked tickets 
		
		bookedTickedList.add(p.passengerId);
		
		System.out.println(".......BOOKED SUCCESSFULLY ........");
		
		
	}
	
	// adding to RAC
	
	public void addToRac(Passenger p , int racInfo , String allotedRac) {
		
		p.number=racInfo;
		p.alloted=allotedRac;
		
		// add to passengers 
		passengers.put(p.passengerId, p);
		
		// add to rac to list
		
		racList.add(p.passengerId);
		
		// decrease available RAC tickets by 1
		
		availableRacTickets--;
		
		// remove the positon that was alloted to the passenger
		racBerthPostions.remove(0);
		
		System.out.println(".....ADD TO RAC SUCCESSFULLY");
		
		
		
	}
	
	// adding to WL
	
	public void addtoWL(Passenger p, int wlInfo , String allotedWl) {
		
		p.passengerId=wlInfo;
		p.alloted=allotedWl;
		
		// add to passengerss
		passengers.put(p.passengerId, p);
		
		// add to wl list
		
		waitingBerthPositons.add(p.passengerId);

		// decrease availbe wl ;
		availableWaitingList--;
		
		//remove the postion that was alloted to  the passenger
		waitingBerthPositons.remove(0);
		
		System.out.println(".....ADDED TO WAITINGLIST SUCCESSFULLY ");
		
		
	}
	
	// cancel ticket
	
	public void cancelTicket(int passengerId) {
		
		// remove the passenge from the map
		Passenger p =  passengers.get(passengerId);
		
		passengers.remove(Integer.valueOf(passengerId));
		
		// remove from the booked ticket list
		bookedTickedList.remove(Integer.valueOf(passengerId));
		
		// take the booked position which is now free
		int postionBooked =p.number;
		
		System.out.println("..... CANCELD SUCCESSFULLY....");
		
		// add the free postion
		if(p.alloted.equals('L')){
				availableLowerBerths++;
				lowerBerthPositions.add(postionBooked);
		}
		else if(p.alloted.equals('M')) {
			
			availableMiddleBerths++;
			middleBethPositons.add(postionBooked);
			
		
			
		}
		else if(p.alloted.equals('U')) {
			availableUpperBerths++;
			upperBerthPositions.add(postionBooked);
			
			
			
		}
		
		// check if any rac is there
		
		if(racList.size()>0) {
			
			
			Passenger passengerFromrac = passengers.get(racList.poll());
			int positionrac = passengerFromrac.number;
			racBerthPostions.add(positionrac);
			
			racList.remove(Integer.valueOf(passengerFromrac.passengerId));
			availableRacTickets++;
			
			
			// check wl is there
			if(waitingList.size()>0) {
				
				Passenger passengerFromWL = passengers.get(waitingList.poll());
				
				int positionWL = passengerFromWL.number;
				waitingBerthPositons.add(positionWL);
				
				waitingList.remove(Integer.valueOf(passengerFromWL.passengerId));
				
				passengerFromWL.number=racBerthPostions.get(0);
				passengerFromWL.alloted="RAC";
				racBerthPostions.remove(0);
				racList.add(passengerFromWL.passengerId);
				
				availableWaitingList++;
				availableRacTickets--;
				
				
				
				
			}
			
			// now we have a passenger from rac to whom we can book a ticket,
			Main.bookTicket(passengerFromrac);
			
		}
		
		
		
				
		
		
		
	}
	// print all aviale seats
	public void printAvailable() {
		System.out.println("Available Lower Berths "+ availableLowerBerths);
		System.out.println("Available middle Berths  "+ availableMiddleBerths);
		System.out.println("Availabe Upper Berths "+ availableUpperBerths);
		System.out.println("Avaialbe RAC berths "+  availableRacTickets);
		System.out.println("Available Waiting List "+ availableWaitingList );
		System.out.println("................................................");
	}
	
	
	// print all occupaid passengers from all types 
	
	public void printPassenger() {
		
		if(passengers.size()==0) {
			System.out.println("No deatils of passengers");
			return;
		}
		for (Passenger p: passengers.values()) {
			System.out.println("PASSENGE ID  : "+ p.passengerId);
			System.out.println("NAME  : "+p.name);
			System.out.println("AGE  :"+ p.age);
			System.out.println("STATUS  "+ p.number + p.alloted);
			System.out.println("...............................");
		}
	}
	

}
