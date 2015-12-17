import java.io.*;
import java.util.*;

public class NW {
	public static void main(String[] args) throws Exception {
		String blosumFile = "/home/tyler/git/needleman-wunsch/src/Blosum50.txt";
		FileReader blosumFReader = new FileReader(blosumFile);
		BufferedReader blosumBReader = new BufferedReader(blosumFReader);
		
		String fastaFile = "/home/tyler/git/needleman-wunsch/src/twoSeqs.txt";
		List<FastaSequence> fastaList = FastaSequence.readFastaFile(fastaFile);
		
		
		String seq1 = fastaList.get(0).getSequence();
		String seq2 = fastaList.get(1).getSequence();
		int seq1Length = seq1.length();
		int seq2Length = seq2.length();
		
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
			for(int i = 1; i < 21; i++){
				matrix[lineCount][i-1] = Integer.parseInt(numbers[i]);
				//System.out.print(matrix[lineCount][i-1] + " ");
			}
			//System.out.println("");
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
		cell[0][0] = new Cell("last",0);
		//System.out.print(cell[0][0].score);
		// Fill in gap penalties
		for (int x=1; x <= seq1.length(); x++){
			cell[x][0] = new Cell("left",-8*x);
			//System.out.print(cell[x][0].score);
		}
		System.out.println();
		for (int x=1; x <= seq2.length(); x++){
			cell[0][x] = new Cell("up",-8*x);
			//System.out.println(cell[0][x].score);
			//seqMatrix[x][0] = x * -8;
		}
		//System.out.println();
		
		// fill in scoring matrix
		int up;
		int left;
		int diagonal;
		int max;
		for(int y = 1; y <= seq2.length(); y++){
			for(int x = 1; x <= seq1.length(); x++){
				up = cell[x][y-1].score - 8;
				left = cell[x-1][y].score - 8;
				diagonal = cell[x-1][y-1].score + matrix[residues.indexOf(seq1Array[x-1])][residues.indexOf(seq2Array[y-1])];
				//System.out.println(diagonal);
				max = 0;
				int maxArray[] = {up,left,diagonal};
				
				// determine a maximum value
				max = Math.max(up, Math.max(left, diagonal));
				//System.out.println(max);
				//for(int i = 0; i < 3; i++){
				//	System.out.println(maxArray[i]);
				//	if(maxArray[i] > max){
				//		max = maxArray[i];
				//	}
				//}
				//System.out.println(max);
				Cell maxAlign = new Cell(max);
				
				// set direction for maximum alignment
				if(max==up){
					maxAlign.dir = "up";
				}else if(max==left){
					maxAlign.dir = "left";
				}else{
					maxAlign.dir = "diagonal";
				}
				
				// cell for 
				cell[x][y] = maxAlign;
				
				//System.out.println(residues.indexOf(seq2Array[y]));
				//seqMatrix[x][y] = matrix[residues.indexOf(seq1Array[x-1])][residues.indexOf(seq2Array[y-1])];
				
				//System.out.print(seq1Array[x-1]);
				//System.out.print(seq2Array[y-1]);		
				//System.out.print(seqMatrix[x][y] + " ");
			}
			//System.out.println();
		}
		
		Cell prev = cell[seq1.length()][seq2.length()];
		//System.out.println(prev.score);
		StringBuilder seqOneAlign = new StringBuilder();
		StringBuilder seqTwoAlign = new StringBuilder();
		int score = prev.score;
		
		while(!prev.dir.equals("last")){
			if(prev.dir.equals("up")){
				--seq2Length;
				seqOneAlign.append("-");
				seqTwoAlign.append(seq2.charAt(seq2Length));
			}else if(prev.dir.equals("left")){
				--seq1Length;
				seqOneAlign.append(seq1.charAt(seq1Length));
				seqTwoAlign.append("-");
			}else{
				--seq1Length;
				--seq2Length;
				seqOneAlign.append(seq1.charAt(seq1Length));
				seqTwoAlign.append(seq2.charAt(seq2Length));
			}
			prev = cell[seq1Length][seq2Length];
		}
		System.out.println(seqOneAlign.reverse());
		System.out.println(seqTwoAlign.reverse());
		
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
