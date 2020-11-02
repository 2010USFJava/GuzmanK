package com.revature.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DogFile {
	
	public static final String DogFile = "dogList.txt";
	
	// Write method
	public static void writeDogFile (List<Dog> dList) {
		
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream (new FileOutputStream (DogFile));
			objectOut.writeObject(dList);
			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Read Method
	
	@SuppressWarnings ("unchecked")
	public static void readDogFile() {
		
		try {
			@SuppressWarnings("resource")
			ObjectInputStream objectIn = new ObjectInputStream (new FileInputStream(DogFile));
			
			DogList.dogList = (ArrayList<Dog>)objectIn.readObject();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		

}
