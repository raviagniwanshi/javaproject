package com.virtual;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;
	
public class OpFiles extends OpLockers {
	private static String lockerpath;
	private static boolean fileflag;
	
	public OpFiles() {
		lockerpath= getLockerpath(); 
		}
	
	protected void display() {	
		System.out.println("\t*******************************************************************************************");
		System.out.println("\t\t\t\t    "+ lockerpath);
		System.out.println("\t*******************************************************************************************");
		System.out.println("\t\t\t\t2. LOCKER  OPERATION");
		System.out.println("\t-------------------------------------------------------------------------------------------");
		System.out.println("--MENU OPTIONS--");
		System.out.println("\t\t 1. Add New File");
		System.out.println("\t\t 2. Delete a File");
		System.out.println("\t\t 3. Search a File");
		System.out.println("\t\t 4. Write to File");
		System.out.println("\t\t 5. Return to Main Menu");
		System.out.println("\r");
		}
	
	protected void deleteIt(String nameit) {					//method overridden
		boolean match=false;
		TreeSet<File> set1 = new TreeSet<File >();
		set1= makeSet();
		for(File st: set1) {
			if(match=matchCase(nameit, st.getName())==true)
			break;
			}
				
		if(match==true) {	
			File file = new File(lockerpath,nameit);
			if(file.isDirectory()==true) {
				if(file.list()!=null) {
				System.out.println("Folder not Empty!");
				System.out.println("Folder '"+ file.getName()+ "' cannot be deleted!");
				}			
			}else {
					if(file.isFile()==true) {
						if(file.delete()==true)
						System.out.println("File: "+ file.getName()+" is deleted from"+ file.getParent());
					}
				}
		}else {
				System.out.println("No match found");
			}
	}
			
	public boolean matchCase(String name, String file) {
		StringBuilder sb1= new StringBuilder(name);
		StringBuilder sb2= new StringBuilder(file);
		boolean scase= true;
		if(sb1.length()==sb2.length()) {
			for(int i=0; i<=sb1.length()-1;i++) {
				if(sb1.charAt(i)!=sb2.charAt(i))
					{
						scase=false;
						break;
					}
				}
			}else {scase=false;
				}
		return scase;
	}
		
	protected  boolean searchIt(String pathit) {		//method overridden
		File file= new File(lockerpath, pathit);
		if(!(super.searchIt(pathit))) {
			if(file.isFile()==true){
				
				TreeSet<File> set = new TreeSet<File >();	
				set= makeSet();		//sort files in ascending
				
				String[] arr= new String[set.size()];
				int j=0;	
				
					for(File st: set) {
						arr[j]= st.getName();	//to access index
					j++;
					}
					int u=binarySearch(arr, pathit);
					if(u>=0)
						//System.out.println("located at position: "+ u);
						return true;
					}
		}
		return false;
	}
	 
	protected void createIt()   {							//method overridden
		boolean newfileflag= true;
		Scanner sc= new Scanner(System.in);
		lockerpath= getLockerpath();
		System.out.println("Enter the filename: ");
		String filename= sc.nextLine(); 
		File file = new File(lockerpath, filename);
		if (file.isDirectory())
			System.out.println("Function blocked\nLocker Found:"+ filename+"\n");
		else {
			if(file.isFile())//passing path of the newly created file
				System.out.println("Function blocked\n *File Already Exists *");
			else
				writeToFile(file.getPath(),newfileflag);	//initialization comment on file
		}
	}													

	protected  void writeToFile(String filepath, boolean fileflag)  {		
		FileWriter write= null;
		Scanner sc= new Scanner(System.in);
		String str;
		Date date = new Date();
		try {
			if(fileflag==true) {
				write= new FileWriter(filepath, true);
				str =("Created on: "+ date+"\r\n");	
				write.write(str);
				System.out.println("Process successful");
			}else {									//block to append to an existing file
				if(searchIt(filepath)) {
					write= new FileWriter(filepath, true);
					str=("Appended on: "+ date+"\r\n");
					write.write(str);
					System.out.println("Enter the Content to Update: ");
					str= sc.nextLine().concat("\r\n");
					write.write(str);
					System.out.println("Process successful");
				}else {
					System.out.println("File does not exist"+ filepath);
					}
			}}catch (IOException e) {
				System.out.println("Error: File process incomplete");
				e.printStackTrace();
			}finally {
				if (write!= null)
					try {
						write.close();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
	}
	
	
	protected TreeSet<File> makeSet() {
		lockerpath= getLockerpath(); 
		File file = new File(lockerpath);	
		TreeSet<File> set = new TreeSet<File >();				//declaring a set of type file
			for(File file1:file.listFiles()) {
				set.add(file1);
				}
			return set;
		}
	
	protected int binarySearch(String[] arr, String filename){
		
			int firstIndex = 0;
		    int lastIndex = arr.length- 1;
		
		while (firstIndex <= lastIndex) {
			int middleIndex = firstIndex + (lastIndex - firstIndex) / 2;

			int value = filename.compareTo(arr[middleIndex]);

			if (value == 0)	// filename present at mid
				return middleIndex;

			if (value > 0)		// If filename greater, ignore first half
				firstIndex = middleIndex + 1;

			else				// If filename is smaller, ignore second half
				lastIndex = middleIndex - 1;
		}

		return -1;
	}
	
}

