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
		cell[0][0] = new Cell("first",0);
		
		// Fill in gap penalties
		for (int x=1; x <= seq1.length(); x++){
			cell[x][0] = new Cell("left",-8*x);
			System.out.print(cell[x][0].score);
		}
		System.out.println();
		for (int x=1; x <= seq2.length(); x++){
			cell[0][x] = new Cell("up",-8*x);
			System.out.println(cell[0][x].score);
			//seqMatrix[x][0] = x * -8;
		}
		System.out.println();
		
		// fill in scoring matrix
		int up;
		int left;
		int diagonal;
		int max;
		for(int y = 1; y < seq2.length(); y++){
			for(int x = 1; x < seq1.length(); x++){
				up = cell[x][y-1].score - 8;
				left = cell[x-1][y].score - 8;
				diagonal = cell[x-1][y-1].score + matrix[residues.indexOf(seq1Array[x-1])][residues.indexOf(seq2Array[y-1])];
				max = 0;
				int maxArray[] = {up,left,diagonal};
				
				// determine a maximum value
				for(int i = 0; i < 3; i++){
					if(maxArray[i] > max){
						max = maxArray[i];
					}
				}
				
				Cell maxAlign = new Cell(max);
				
				// set direction for maximum alignment
				if(max==up){
					maxAlign.dir = "up";
				}else if(max==left){
					maxAlign.dir = "left";
				}else{
					maxAlign.dir = "diagonal";
				}
				
				cell[x][y] = maxAlign;
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
		String dir;
		int score;
		public Cell(int score){
			this.score = score;
		}
		public Cell(String dir,int score){
			this.dir = dir;
			this.score = score;
		}
	}
	
}