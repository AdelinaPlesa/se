package dise;

import java.io.*;
import java.util.Arrays;

public class Service{

	
	public String getContentOfTheFile(File file) throws IOException {
	
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new FileReader(String.valueOf(file)));
		  
		  String st;
		  while ((st = br.readLine()) != null)
		    sb.append(st);

	
	    return sb.toString();
	}

	public void consolePercentage(int[] array, int numberOfContracts) {
		int sumOfAllEncounteredPatterns = Arrays.stream(array).sum();

		int percengate;

		for(int i = 0; i < array.length; i++) {
			percengate = 100*array[i]/numberOfContracts;

			System.out.print("\n");
			System.out.print("Percentage for P" + i +" is present " + percengate + " %");
			System.out.print("\n");

		}

	}

}