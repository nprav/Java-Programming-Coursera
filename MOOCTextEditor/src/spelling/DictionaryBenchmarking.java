package spelling;


/** A class for timing the Dictionary Implementations
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DictionaryBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 100000;		// for BST implementation testing only
	    int referenceTrials = 100;
	    double referenceScaleFactor = (double) referenceTrials/trials;
		System.out.println(referenceScaleFactor);

	    // The text to test on
	    String dictFile = "data/dict.txt";
		int maxNumOfWords = 99171;

		// The number of steps to run.  
		// You can play around with this.
//		int numSteps = 20;    // when testing LL implementation
		int numSteps = 240;    // when only testing BST implementation

		// The number of words to start with.
		// You can play around with this.
		int start = 1000;

		// The amount of words to increment each step
		// You can play around with this
		int increment = (maxNumOfWords - start)/numSteps;
		
		String notInDictionary = "notaword";
		
		for (int numToCheck = start; numToCheck < numSteps*increment + start;
				numToCheck += increment)
		{
			// Time the creation of finding a word that is not in the dictionary.
			DictionaryLL llDict = new DictionaryLL();
			DictionaryBST bstDict = new DictionaryBST();
			
//			DictionaryLoader.loadDictionary(llDict, dictFile, numToCheck);
			DictionaryLoader.loadDictionary(bstDict, dictFile, numToCheck);
			
			long startTime = System.nanoTime();
//			for (int i = 0; i < trials; i++) {
//				llDict.isWord(notInDictionary);
//			}
			long endTime = System.nanoTime();
			// divdie by 1000 to get to ms
			double timeLL = referenceScaleFactor*(endTime - startTime)/1000.0;

			startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				bstDict.isWord(notInDictionary);
			}
			endTime = System.nanoTime();
			// divide by 1000 to get ot ms
			double timeBST = referenceScaleFactor*(endTime - startTime)/1000.0;
			
			System.out.println(numToCheck + "\t" + timeLL + "\t" + timeBST);
			
		}
	
	}
	
}
