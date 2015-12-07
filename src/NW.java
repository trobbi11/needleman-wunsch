import java.io.*;
import java.util.*;

public class NW {
	public static void main(String[] args) throws Exception {
		String blosumFile = "/home/tyler/git/needleman-wunsch/src/Blosum50.txt";
		FileReader blosumFReader = new FileReader(blosumFile);
		BufferedReader blosumBReader = new BufferedReader(blosumFReader);
		
		blosumBReader.readLine();
		
		int lineCount = 0;
		String line = "";
		String[] numbers = new String[21];
		int [][] matrix = new int[21][21];
		String residues = "ARNDCQEGHILKMFPSTWYV";
		
		while((line = blosumBReader.readLine()) != null && lineCount < 20){
			numbers = line.split("\\s+");
			System.out.println(numbers.length);
			for(int i = 1; i < 21; i++){
				matrix[lineCount][i] = Integer.parseInt(numbers[i]);
				//System.out.print(matrix[lineCount][21] + " ");
			}
			System.out.println("");
			lineCount++;
		}
		
		blosumBReader.close();
	}
	
}