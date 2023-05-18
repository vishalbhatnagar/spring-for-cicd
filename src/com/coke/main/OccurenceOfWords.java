package com.coke.main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import static java.util.Map.Entry.comparingByValue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class OccurenceOfWords {

	private static Map<String,Integer> wordCountMap;
	private static Map<String, Integer> wordCountSortedMap;
	public static void main(String[] args) throws FileNotFoundException, IOException {

		//Read the input file in resource folder, invert it, and write it in another file in resource folder. 
		invertFile();

		// Read the inverted File and add each word and its count in a map.
		readInvertedFileMap();

		// Remove the common words from the map
		excludeCommonWords();


		// Sort the map in descending order by map values and print top 100 values.
		sortMapByValues();

		//Print first 100 most common words in csv format with count first..
		printInCsvFormat();

	}

	private static void invertFile() {
		try {
			Path path = Paths.get("C:\\Users\\hp\\Documents\\workspace\\CokeAssignment\\src\\resources\\DeclarationOfIndependenceFile.txt");
			List<String> allLines = Files.readAllLines(path);
			ListIterator<String> itr = allLines.listIterator(allLines.size());
			FileWriter writer = new FileWriter("C:\\Users\\hp\\Documents\\workspace\\CokeAssignment\\src\\resources\\DeclarationOfIndependenceFileInverted.txt");
			while (itr.hasPrevious()) 
			{
				//Print on console
				//System.out.println(itr.previous());
				
				//Write in file
				writer.write(itr.previous() + System.lineSeparator());
			}
			writer.close();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}

	}

	private static void printInCsvFormat() {
		int count =0;
		for (Map.Entry<String, Integer> entry : wordCountSortedMap.entrySet()) {

			if (count <100) {
				System.out.println(entry.getValue() +" , "+entry.getKey()  );
				count++;
			}
		}
	}

	private static void sortMapByValues() {
		wordCountSortedMap =

				wordCountMap
				.entrySet() .stream() 
				.sorted(comparingByValue(Comparator.reverseOrder())) 
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
	}

	private static void excludeCommonWords() {
		Set<String> set = new HashSet<> ();
		set.add("of");
		set.add("the");
		set.add("to");
		set.add("for");
		set.add("and");
		set.add("");
		wordCountMap.keySet().removeAll(set);
	}

	private static void readInvertedFileMap() throws IOException {
		FileReader fr= new FileReader("C:\\Users\\hp\\Documents\\workspace\\CokeAssignment\\src\\resources\\DeclarationOfIndependenceFileInverted.txt");
		try (BufferedReader br = new BufferedReader(fr)) {
			String st;
			wordCountMap = new HashMap<>();
			while ((st = br.readLine()) != null)
			{
				String[] arrOfStr = st.split(" ");

				for (String word : arrOfStr) {
					String newWord = word.replaceAll("\\p{Punct}", "");
					Integer integer = wordCountMap.get(newWord.toLowerCase());
					if (integer == null)
						wordCountMap.put(newWord.toLowerCase(), 1);
					else {
						wordCountMap.put(newWord.toLowerCase(), integer + 1);
					}
				} 
			}
		}

	}
}