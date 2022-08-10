package com.virtual;
import java.io.File;

public abstract class Lockers {
	private  String lockerpath;//static
	private final String ROOTDIR= "LOCKEDME.COM";
	abstract void createIt();
	abstract void deleteIt(String nameit);
	abstract boolean searchIt(String nameit); 
	abstract void display();
		
	public Lockers() {
		String lockerpath= ROOTDIR;
		File file= new File(lockerpath);
			try {							//creating root directory
				if(file.mkdir()== true) { 	//the Generic locker "Lockedme.Com" 
				}							//if not already present
		lockerpath= file.getPath();	
		setLockerpath(lockerpath);
			if(file.list()!=null) {
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public  String getLockerpath() {
		return lockerpath;
	}
	public  void setLockerpath(String lockerpath) { //static
		this.lockerpath = lockerpath;//
	}
	
		/////may7 at 10.30: i have remomved static from lockerpath. 
	//2. generated getter setter for plocker lockerpath, removed ststic also 
	//3.and made oplockers as parent class of opfiles
}
