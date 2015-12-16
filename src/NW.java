import java.io.*;
import java.util.*;

public class NW {
	public static void main(String[] args) throws Exception {
		String blosumFile = "/home/tyler/git/needleman-wunsch/src/Blosum50.txt";
		FileReader blosumFReader = new FileReader(blosumFile);
		BufferedReader blosumBReader = new BufferedReader(blosumFReader);
		
		String seq1 = "ADE";
		String seq2 = "ELL";
		
		blosumBReader.readLine();
		
		int lineCount = 0;
		String line = "";
		String[] numbers = new String[21];
		int [][] matrix = new int[20][20];
		String residues = "ARNDCQEGHILKMFPSTWYV";
		
		char resArray[] = residues.toCharArray();
		char seq1Array[] = seq1.toCharArray();
		char seq2Array[] = seq2.toCharArray();
		
		while((line = blosumBReader.readLine()) != null && lineCount < 20){
			numbers = line.split("\\s+");
			//System.out.println(numbers[1]);
			for(int i = 1; i < 21; i++){
				matrix[lineCount][i-1] = Integer.parseInt(numbers[i]);
				System.out.print(matrix[lineCount][i-1] + " ");
			}
			System.out.println("");
			lineCount++;
		}
		
		// Close blosum matrix reader
		blosumBReader.close();
		
		int [][] seqMatrix = new int[seq1.length()+1][seq2.length()+1];
		
		// fill in vertical matrix
		
		for (int y=0; y< seqMatrix[0].length; y++){
			seqMatrix[0][y] = y * -8;
		}
		
		for (int x=0; x < seqMatrix.length; x++){
			seqMatrix[x][0] = x * -8;
		}
		
		// fill in scoring matrix
		for(int y = 1; y < seqMatrix[0].length; y++){
			for(int x = 1; x < seqMatrix.length; x++){
				//System.out.println(residues.indexOf(seq2Array[y]));
				seqMatrix[x][y] = matrix[residues.indexOf(seq1Array[x-1])][residues.indexOf(seq2Array[y-1])];
				System.out.print(seq1Array[x-1]);
				System.out.print(seq2Array[y-1]);		
				System.out.print(seqMatrix[x][y] + " ");
			}
			System.out.println();
		}
		System.out.print(seqMatrix[0][0] + " " + seqMatrix[1][0]);
		
		//for(char s1: seq1Array){
		//	System.out.println(residues.indexOf(s1));
		//}
		Cell[][] cell = new Cell[seq1.length()+1][seq2.length()+2];
	}
	private static class Cell{
		String direction;
		float score;
		
		public Cell(String direction,float score){
			this.direction = direction;
			this.score = score;
		}
	}
	
}