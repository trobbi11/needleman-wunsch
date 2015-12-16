import java.io.*;
import java.util.*;

public class NW {
	public static void main(String[] args) throws Exception {
		String blosumFile = "/home/tyler/git/needleman-wunsch/src/Blosum50.txt";
		FileReader blosumFReader = new FileReader(blosumFile);
		BufferedReader blosumBReader = new BufferedReader(blosumFReader);
		
		String seq1 = "HEA";
		String seq2 = "HA";
		
		blosumBReader.readLine();
		
		int lineCount = 0;
		String line = "";
		String[] numbers = new String[21];
		int [][] matrix = new int[20][20];
		String residues = "ARNDCQEGHILKMFPSTWYV";
		
		char resArray[] = residues.toCharArray();
		char seq1Array[] = seq1.toCharArray();
		char seq2Array[] = seq2.toCharArray();
		
		Cell[][] cell = new Cell[seq1.length()+1][seq2.length()+1];
		
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
		
		//for (int y=0; y< seqMatrix[0].length; y++){
		//	seqMatrix[0][y] = y * -8;
		//}
		
		// Initialize top left cell
		cell[0][0] = new Cell("FIRST",0);
		
		// Fill in gap penalties
		for (int x=1; x <= seq1.length(); x++){
			cell[x][0] = new Cell("LEFT",-8*x);
			System.out.print(cell[x][0].score);
		}
		System.out.println();
		for (int x=1; x <= seq2.length(); x++){
			cell[0][x] = new Cell("UP",-8*x);
			System.out.println(cell[0][x].score);
			//seqMatrix[x][0] = x * -8;
		}
		System.out.println();
		
		// fill in scoring matrix
		for(int y = 1; y < seq2.length(); y++){
			for(int x = 1; x < seq1.length(); x++){
				//System.out.println(residues.indexOf(seq2Array[y]));
				//seqMatrix[x][y] = matrix[residues.indexOf(seq1Array[x-1])][residues.indexOf(seq2Array[y-1])];
				
				System.out.print(seq1Array[x-1]);
				System.out.print(seq2Array[y-1]);		
				//System.out.print(seqMatrix[x][y] + " ");
			}
			System.out.println();
		}
		//System.out.print(seqMatrix[0][0] + " " + seqMatrix[1][0]);
		
		//for(char s1: seq1Array){
		//	System.out.println(residues.indexOf(s1));
		//}
		
	}
	private static class Cell{
		String direction;
		float score;
		public Cell(int score){
			this.score = score;
		}
		public Cell(String direction,float score){
			this.direction = direction;
			this.score = score;
		}
	}
	
}