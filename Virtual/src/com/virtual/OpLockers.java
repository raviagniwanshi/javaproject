package com.virtual;
import java.io.File;
import java.util.Scanner;
	
	
public class OpLockers extends Lockers{
	
	private  static String lockerpath; 
	public OpLockers() {
		lockerpath=getLockerpath();
		}
	
	public  String getLockerpath() {
		return lockerpath;
	}

	public  void setLockerpath(String lockerpath) {
		OpLockers.lockerpath = lockerpath;
	}
			
	protected void display() {
		System.out.println("\n\n\t*******************************************************************************************");
		System.out.println("\t\t\t\t     "+lockerpath);
		System.out.println("\t*******************************************************************************************");
		System.out.println("\n");
		//System.out.println("\t-------------------------------------------------------------------------------------------"); 
		System.out.println("\t\t1. Display Files ");
		System.out.println("\t\t2. Business Operations");
		//System.out.println("\t\t\t*  ADD NEW FILE");
		//System.out.println("\t\t\t*  DELETE A FILE");
		//System.out.println("\t\t\t*  SEARCH A FILE");
		System.out.println("\t\t3. Exit Locker");
			
	}
	
	protected void display(int menu, String menuname) {	// method overloaded
		System.out.println("\t*******************************************************************************************");
		System.out.println("\t\t\t\t"+menu+". "+ menuname);
		System.out.println("\t-------------------------------------------------------------------------------------------");
	}
	
	protected void createIt() {	
		final String lockername;
		lockerpath= getLockerpath(); 
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter a Name for your Locker: ");
		lockername = sc.nextLine();
												
		File file= new File(lockerpath, lockername); //if lockername==null user enters the Generic locker//may write code for it
		try {
			if(file.mkdir()==true) { 			
				System.out.println("New Locker Allotted!!");
			}
			else {
				System.out.println("Locker Allocated!!");
			}	
		lockerpath= file.getPath();
		setLockerpath(lockerpath); 
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	protected void deleteIt(String nameit) {	
		//can be implemented to delete a named locker
	}
	
	protected boolean searchIt(String pathit) {				//search a named locker in the root directory
		File file= new File(lockerpath, pathit);
			if(file.isDirectory()==true) {
				if(file.list()!=null)
					System.out.println("Found Locker "+ file.getParent()+"/"+file.getName());
				return true;
			}else
				return false;
	}

}	
