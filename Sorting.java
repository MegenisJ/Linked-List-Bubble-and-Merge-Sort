import java.io.*;
import java.util.*;;
class Sorting{
	public static int wordcount;
	public static int comparisons;
	public static void main( String[] args){
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter number of words to sort");
		int count = reader.nextInt();
		Sorting.wordcount = count;
		ArrayList<String> words = parseData();
		System.out.println(words.size());
		
		System.out.println("Number of items: " + Sorting.wordcount);
		
		comparisons = 0;
		long start = System.nanoTime();
		ArrayList<String> SortedWords = BubbleSort(words);
		long duration = System.nanoTime() - start;
		double seconds = ((double)duration / 1000000000);	
		System.out.format("time in seconds to bubble sort : %f seconds ",seconds );
		System.out.println("");
		System.out.println("Number of comparisons :  " + Sorting.comparisons);	
		
		comparisons = 0;
		start = System.nanoTime();
		SortedWords = MergeSort(words);
		duration = System.nanoTime() - start;
		seconds = ((double)duration / 1000000000);	
		System.out.format("time in seconds to merge sort : %f seconds ",seconds );
		System.out.println("");
		System.out.println(",Number of comparisons : " + Sorting.comparisons);
		System.out.println("");	
	}
	
	
	public static ArrayList<String> parseData(){
		ArrayList<String> words = new ArrayList<String>();
		Scanner file = null;
		try{
			file = new Scanner(new File("data.txt"),"UTF-8");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		while (file.hasNextLine()){
			String[] linewords = file.nextLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
				for(String word : linewords){
					if (word.length() > 3 && words.size() < Sorting.wordcount){
						words.add(word);
					}
				}			
		}	
		return (words) ;
	}

	public static ArrayList<String> BubbleSort(ArrayList<String> list){
		boolean swapmade; //using a swap boolean to avoid unnescesary iterations
		for(int x = 0;x < list.size();x++){//iterates through the whole arraylist
			swapmade = false;
			for( int i = 0; i < list.size()-x-1;i++){ //iterates through only the unsorted items 
				if ( list.get(i).compareTo(list.get(i+1)) > 0){ //if i is lower alphabetically than i + 1 
					String temp = list.get(i); //next 3 lines swap i and i + 1 
					list.set(i,list.get(i+1));
					list.set(i+1,temp);
					swapmade = true; 
					Sorting.comparisons++;
				}
			}
			if (swapmade == false){ //if no swaps were made then the list is sorted
				break;
			}
		}
		return(list);
	}

	public static ArrayList<String> MergeSort(ArrayList<String> whole){
		ArrayList<String>left = new ArrayList<String>();//creates two new arraylists for each half of the arraylist
		ArrayList<String>right = new ArrayList<String>();
		int Center;
		if (whole.size() == 1){
			return whole;
		}
		else{
			Center = whole.size() / 2; //finds the mid point of the arraylist
			for(int x = 0; x < Center;x++){ //fills the arraylist for each half
				left.add(whole.get(x));
			}
			for(int x = Center; x < whole.size();x++){
				right.add(whole.get(x));
			}
			left = MergeSort(left); //sorts each half
			right  = MergeSort(right);

			merge(left,right,whole);
		}
		return whole;
	}

	public static void merge(ArrayList<String> left, ArrayList<String> right,ArrayList<String> whole){
		int leftIndex = 0;
		int rightIndex = 0;
		int wholeIndex = 0;
		//takes the lowest alphabeticalls item from either list and adding it to whole 
		while(leftIndex < left.size() && rightIndex < right.size()){
			if ((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0){
				whole.set(wholeIndex,left.get(leftIndex));
				leftIndex++;
				Sorting.comparisons++;
			}
			else{
				whole.set(wholeIndex,right.get(rightIndex));
				rightIndex++;
				Sorting.comparisons++;
			}
			wholeIndex++;
		}
		//finds out which index has items left
		ArrayList<String> rest;
		int restIndex;
		if (leftIndex >= left.size()){
			rest = right;
			restIndex = rightIndex;
		}
		else{
			rest = left;
			restIndex = leftIndex;
		}

		for(int i = restIndex;i < rest.size();i++){ //copies the end of whichever arraylist has items left into whole
			whole.set(wholeIndex,rest.get(i));
			wholeIndex++;
		}
	}
}

