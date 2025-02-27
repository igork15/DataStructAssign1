package correlator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;







public class CorrelatorApp {
	public static void main(String[] args) throws FileNotFoundException{
		
		
		HashSetBJP<String, Integer> hashSetAtlantis = new HashSetBJP<String, Integer>();
		HashSetBJP<String, Integer> hashSetHamlet = new HashSetBJP<String, Integer>();
		
		HeapPriorityQueue<String, Integer> heapAtlantis = new HeapPriorityQueue<String, Integer>(new MyComp1());
		HeapPriorityQueue<String, Integer> heapHamlet = new HeapPriorityQueue<String, Integer>(new MyComp1());
		
		
		
		Scanner inputFile = new Scanner(new File("./myfiles/the-new-atlantis.txt"));
		int insertionCount = 0;
		
		while(inputFile.hasNext()){
			String key = inputFile.next().toLowerCase();
			
			String c= key;
	        Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
	        Matcher match = pt.matcher(c);
	        if(match.find())
	        {
	            String s= match.group();
	            c=c.replaceAll("\\"+s, "");
	            
	        } 

			if(hashSetAtlantis.get(c) != null){
				int val = hashSetAtlantis.get(c)+1;
				hashSetAtlantis.remove(c);
				hashSetAtlantis.put(c, val);
				

			}else{
				hashSetAtlantis.put(c, 1);	
				
			}
			insertionCount++;
			
		}
		inputFile.close();
		System.out.println("Atlantis insertions: " + insertionCount);

		Scanner inputFile2 = new Scanner(new File("./myfiles/hamlet.txt"));
		int insertionCount2 = 0;
		
		while(inputFile2.hasNext()){
			String key = inputFile2.next().toLowerCase();
			
			String c = key;
	        Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
	        Matcher match = pt.matcher(c);
	        if(match.find())
	        {
	            String s= match.group();
	            c=c.replaceAll("\\"+s, "");
	            
	        } 

			if(hashSetHamlet.get(c) != null){
				int val = hashSetHamlet.get(c)+1;
				hashSetHamlet.remove(c);
				hashSetHamlet.put(c, val);
				

			}else{
				hashSetHamlet.put(c, 1);	
				
			}
			insertionCount2++;
		}
		inputFile2.close();
		System.out.println("Hamlet insertions: " + insertionCount2);
		
	

	
	
	Iterator<String> itr = hashSetAtlantis.iterator();
	
	while(itr.hasNext()){
		
		String key = itr.next();
		int value = hashSetAtlantis.get(key);
		
		HeapEntry<String, Integer> entry = new HeapEntry<String, Integer>(key, value, null);
		heapAtlantis.add(entry);

		
	}
	
	
	
	Iterator<String> itr2 = hashSetHamlet.iterator();
	
	
	while(itr2.hasNext()){
		
		String key2 = itr2.next();
		int value2 = hashSetHamlet.get(key2);
		
		HeapEntry<String, Integer> entry2 = new HeapEntry<String, Integer>(key2, value2, null);
		heapHamlet.add(entry2);
		
	}
	
		
		PrintStream outputFile = new PrintStream(new File("./myfiles/stats.csv"));
		outputFile.println("Atlantis Top 10 words");
		
		
		for(int i = 0; i < 10; i++){
			HeapEntry<String, Integer> removeAtlantis = heapAtlantis.remove();

			outputFile.println(removeAtlantis.data + ',' + removeAtlantis.value);
	
		}
		outputFile.println();
		outputFile.println("Hamlet Top 10 words");
		for(int j = 0; j < 10; j++){
			HeapEntry<String, Integer> removeHamlet = heapHamlet.remove();
			outputFile.println(removeHamlet.data + ',' + removeHamlet.value);
		}
		outputFile.println();
		outputFile.println("Correlation of The New Atlantis and Hamlet:, " 
				+ ((1.0 * hashSetHamlet.intersection(hashSetAtlantis)) / (1.0 * hashSetHamlet.union(hashSetAtlantis))));
		
		Iterator<String> itr3 = hashSetAtlantis.iterator();
		outputFile.println();
		outputFile.println("Normalized frequency Atlantis");
		while(itr3.hasNext()){
			String comp = itr3.next();
			int value = hashSetAtlantis.get(comp);
			
			double result = (value * 1.0) / (heapAtlantis.size() * 1.0);
			
			if(result > .01 || result < .0001){
				
			}else {
				outputFile.println(comp + "," + result );;
			}
		
			
		}
		
		Iterator<String> itr4 = hashSetHamlet.iterator();
		outputFile.println();
		outputFile.println("Normalized frequency Hamlet");
		while(itr4.hasNext()){
			String comp = itr4.next();
			int value = hashSetHamlet.get(comp);
			
			double result = (value * 1.0) / (heapHamlet.size() * 1.0);
			
			if(result > .01 || result < .0001){
				
			}else {
				outputFile.println(comp + "," + result );;
			}
		
			
		}
		
		
		
		outputFile.close();
}
    static class MyComp1 implements Comparator<Integer>
    {
       public int compare(Integer x, Integer y)
       {
            return y - x;
       }
    }

}
