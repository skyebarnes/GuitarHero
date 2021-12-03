package pkg;

import java.util.Scanner;

public class GuitarHero {
	public static void main(String args[]) {
		
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		GuitarString[] strings = new GuitarString[37];
		char[] keys = keyboard.toCharArray();
		
		int time = 1000;
		double[] array1 = new double[time];
		double[] array2 = new double[time];		
		int count = 0;
		
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(-1, 1);
				
		for(int i = 0; i < time; i++) {
			array1[i] = i+1;
		}		
		
		
		for (int i = 0; i < strings.length; i++) {
			strings[i] = new GuitarString(440 * Math.pow(1.05956, i - 24));
		}
		
		while (true) {
				
			if (StdDraw.hasNextKeyTyped()) {
				 
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                if (keyboard.contains("" + key)) {
                	strings[keyboard.indexOf(key)].pluck();
                }
            }

            // compute the superposition of the samples
			
			double sample = 0;
			for(int i = 0; i < strings.length; i++)
				sample += strings[i].sample();            

            // send the result to standard audio
            StdAudio.play(sample);
            
            if (count == 1000) {
            StdDraw.clear();
            StdDraw.polygon(array1, array2);
            count = 0;
            }        
            array2[count] = sample;
            count++;
      
            // advance the simulation of each guitar string by one step
            for(int i = 0; i < strings.length; i++)
				strings[i].tic();  
         
		
		}
	}

}
