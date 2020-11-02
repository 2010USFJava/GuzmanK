package com.revature.beans;

public class Dog {
	
	private static final long serialVersionUID = 
			private String name;
			private String breed;
			private String howFluffy;
			private int weight;
			
			public Dog() {
				super();
				
				DogList.dogList.add(this);
				DogFile.writeDogFile (DogList.dogList);
				
			}
			
			public Dog (String name, String breed, String howFluffy, int weight) {
				super();
				
				this.name = name;
				this.breed = breed;
				this.howFluffy = howFluffy;
				this.weight = weight;
				
				DogList.dogList.add(this);
				DogFile.writeDogFile(DogList.dogList);
				LogThis.LogIt("A new dog, " + this.name + " , has arrived!");
				
			}

			// Getters amd setters
			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getBreed() {
				return breed;
			}

			public void setBreed(String breed) {
				this.breed = breed;
			}

			public String getHowFluffy() {
				return howFluffy;
			}

			public void setHowFluffy(String howFluffy) {
				this.howFluffy = howFluffy;
			}

			public int getWeight() {
				return weight;
			}

			public void setWeight(int weight) {
				this.weight = weight;
			}

			// toString
			@Override
			public String toString() {
				return "Dog [name=" + name + ", breed=" + breed + ", howFluffy=" + howFluffy + ", weight=" + weight
						+ "]";
			}
			
			
			
			

}
