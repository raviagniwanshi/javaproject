package com.virtual;
import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;
	
public class LockerMain{
		
	public static void main(String[] args) {
		
		OpLockers oplockers= new OpLockers();
		OpFiles opfiles= new OpFiles();
		Scanner sc= new Scanner(System.in);
		TreeSet<File> set = new TreeSet<File >();	
		
		boolean mainmenuflag= true;
		String action="Choose Option";
		
		display();
		
		switch(chooseLockerType()) {
		
		case 1: 
			System.out.println("Locker Allocated!!");
			break;
	
		case 2:
			oplockers.createIt(); 
			break;
	}
		int counter=0;
		do {
			oplockers.display();
			
			switch(fromConsole(action)) {
				case 1: 
					oplockers.display(1, "Display Files");
					set= opfiles.makeSet();
					if(set.isEmpty()==true)
						System.out.println("Locker:is empty ");
					else
						System.out.println("\tFiles in Locker:");
						int count=1;
						for(File st: set) {
						System.out.println("\t"+count+"."+ st.getName());
						count++;
						}
						
				break;
						
				case 2: 							//LOCKER OPERATION BEGINS
					boolean submenuflag= true;
					Scanner sc1= new Scanner(System.in);
					//try {
						do {
							opfiles.display();
							switch(fromConsole(action)) {
								case 1: 
									oplockers.display(1,"Add File");
									opfiles.createIt();	
								break;

								case 2: 
									oplockers.display(2,"Delete File");
									opfiles.deleteIt(fromConsole());
								break;
										
								case 3: 
									oplockers.display(3,"Search File");
									
								if(opfiles.searchIt(fromConsole()))
										System.out.println("Search Successful");
									else 
										System.out.println("File Not Found!");
								break;
												
								case 4: 
									oplockers.display(4,"Write To File");
									boolean newfileflag=false;
									opfiles.writeToFile(fromConsole(), newfileflag);
								break;

								case 5: 
									//back to Main menu
									submenuflag=false;
									break;
									
								default:
									submenuflag=false;
									System.out.println("Invalid Option...");
									break;
								}						// end of submenu switch
								
							}while(submenuflag==true);
							break;
				case 3: 
					System.out.println("Thank You for Choosing LockedMe.Com");
					mainmenuflag=false;
				break;
				default:
					
					System.out.println("Invalid Option...");
					counter++;
					if(counter==3) {
						mainmenuflag=false;
						System.out.println("Invalid trials beyond limit...Start Again");
					}
					break;
						
				}												//end of mainmenu switch
						
		}while(mainmenuflag==true);
	//System.exit(0);
	
	}//end of main()
			
			
	private static void display() {
		System.out.println();
		System.out.println("\t*******************************************************************************************");
		System.out.println("\t\t\t\t\tWELCOME");
		System.out.println("\r\t\t\t\t\t  tO");
		System.out.println("\r\t\t\t\t     LOCKEDME.COM");
		//System.out.println("\r");
		System.out.println("\t*******************************************************************************************");
		//System.out.println("\t-------------------------------------------------------------------------------------------");
		
		System.out.println("\t\t\tDIGITAL LOCKERS by \"LOCKERS PVT.LTD.\"");
		System.out.println("\t-------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\tDeveloper: Ravi Singh");
		System.out.println();
		System.out.println();
		
		}		
		
	private static int chooseLockerType() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Choose Locker Type\n"
								+ "1. Generic Locker [LOCKEDME.COM]\n"
								+ "2. Custom Locker [LOCKEDME.COM/<named locker>]");
		int locktype= sc.nextInt();
		return locktype;
	}
	private static String fromConsole() {
		Scanner sc= new Scanner(System.in);
		String console="";
		System.out.println("Enter the filename: ");
		console= sc.nextLine(); 
		//sc.close(); checkt this
		return console;
		
		}
	private static int fromConsole(String msg) {
		Scanner sc= new Scanner(System.in);
		int console = 0;
		System.out.println(msg);
		try {
			console= sc.nextInt();
			
		} catch (Exception e) {
			
		}
		//sc.close();
		return console;
	}	
}

