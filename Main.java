package com.train;

import java.util.*;

public class Main {
	
	public static void bookTicket(Passenger p) {
		
		TicketBooker booker = new TicketBooker();
		
		if(TicketBooker.availableWaitingList==0) {
			System.out.println("No Tickets Availble");
			return;
		}
		if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerths>0) || p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths>0 || p.berthPreference.equals("U") && TicketBooker.availableUpperBerths>0)
		{
			System.out.println("prefered Birth Available");
			if(p.berthPreference.equals("L")) {
				
				System.out.println("Lower Berth Goiven ");
				booker.bookTicket(p, TicketBooker.lowerBerthPositions.get(0), "L");
				
				TicketBooker.lowerBerthPositions.remove(0);
				TicketBooker.availableLowerBerths--;
			}
			
			else if(p.berthPreference.equals("M")) {
				
				System.out.println("Middle Berth Given");
				booker.bookTicket(p, TicketBooker.middleBethPositons.get(0), "M");
				
				TicketBooker.upperBerthPositions.remove(0);
				TicketBooker.availableMiddleBerths--;
			}
			else if(p.berthPreference.equals("U")) {
				
				System.out.println("UPPER Berth Given");
				booker.bookTicket(p,TicketBooker.upperBerthPositions.get(0) , "U");
				
				TicketBooker.upperBerthPositions.remove(0);
				TicketBooker.availableUpperBerths--;
				
			}
		}
		
		// preferecnce not available  --> book availabe berth
		
		else if(TicketBooker.availableLowerBerths>0) {
			
			System.out.println("Lower Berth Giver");
			booker.bookTicket(p, TicketBooker.lowerBerthPositions.get(0), "L");
			
			TicketBooker.lowerBerthPositions.remove(0);
			TicketBooker.availableLowerBerths--;
		}
		else if(TicketBooker.availableMiddleBerths>0) {
			
			System.out.println("Middle Berth given");
			booker.bookTicket(p, TicketBooker.middleBethPositons.get(0), "M");
			
			TicketBooker.middleBethPositons.remove(0);
			TicketBooker.availableMiddleBerths--;
		}
		else if(TicketBooker.availableUpperBerths>0) {
			System.out.println("Upper Berth Goven");
			booker.bookTicket(p, TicketBooker.upperBerthPositions.get(0), "U");
			
			TicketBooker.upperBerthPositions.remove(0);
			TicketBooker.availableUpperBerths--;
			
		}
		else if(TicketBooker.availableRacTickets>0) {
			
			System.out.println("RAC Berth Given");
			booker.bookTicket(p, TicketBooker.racBerthPostions.get(0), "RAC");
			
			TicketBooker.racBerthPostions.remove(0);
			TicketBooker.availableRacTickets--;
		}
		else if(TicketBooker.availableWaitingList>0) {
			
			System.out.println("Adding to Waiting List ");
			booker.bookTicket(p, TicketBooker.waitingBerthPositons.get(0), "WL");
			
			TicketBooker.waitingBerthPositons.remove(0);
			TicketBooker.availableWaitingList--;
			
		}
	}
	//cancel ticket funtion
	public static void cancelTicket(int id) {
		TicketBooker booker = new TicketBooker();
		
		if(!booker.passengers.containsKey(id)) {
			System.out.println("Passenger detail Unknown");
		}
		else {
			
			booker.cancelTicket(id);
		}
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		boolean loop = true;
		while(loop) {
			
			System.out.println("1.Book Ticket \n 2.Cancel Ticket \n 3.Available Tickets \n 4.Booked Tickets \n 5.Exit ");
			int choice = sc.nextInt();
			switch(choice) {
			
			case 1:
			{
				// get the details form passenger 
				System.out.println("Enter Passenger name , age and berth preference (L,M,U");
				String name =sc.next();
				int age = sc.nextInt();
				String berthPreference = sc.next();
				Passenger p = new Passenger(name,age,berthPreference);
				
				bookTicket(p);
			}
			break;
			
			// cancel ticket
			case 2:{
				
				System.out.println("Enter passenger ID to cancel");
				int id =sc.nextInt();
				cancelTicket(id);
			}
			break;
			//available tickets print
			
			case 3:{
				
				TicketBooker booker = new TicketBooker();
				booker.printAvailable();
			}
			break;
			// occupied tickets print
			
			case 4:{
				
				TicketBooker booker =new TicketBooker();
				booker.printPassenger();
						
			}
			break;
			// exit
			case 5:{
				
				loop=false;
			}
			break;
			default:
				break;
				
			}
		}

	}

}
