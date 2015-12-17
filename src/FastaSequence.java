import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FastaSequence {
	private final String header;
	private final String sequence;
	private float gc = 0;

	public FastaSequence(String header, String sequence, float gc){
		this.header = header;
		this.sequence = sequence;
		this.gc = gc;
	}
	
	// returns the header of the sequence without the ">"
	public String getHeader(){
		return this.header;
	}
	
	// returns the dna sequence of this fastasequence
	public String getSequence(){
		return this.sequence;
	}
	
	// returns the number of G's and C's divided by the length of this sequence
	public float getGCRatio(){
		return this.gc;
	}

	public static List<FastaSequence> readFastaFile(String string) throws IOException{
		List<FastaSequence> sequenceList = new ArrayList<FastaSequence>();
		String head = "";
		String seq = "";
		float gc = 0f;
		float total = 0f;
		int count = 1;
		BufferedReader reader = new BufferedReader(new FileReader(string));
		for (string = reader.readLine();
		string!=null;
		string=reader.readLine())
		{
			if (string.startsWith(">"))
			{
				if(count > 1){
					total = seq.length();
					gc = (seq.length() - seq.replaceAll("[GC]", "").length());
					gc /= total;
					FastaSequence sequenceObject = new FastaSequence(head,seq,gc);
					sequenceList.add(sequenceObject);
					seq="";
				}
				head=string;
				head=head.replace("> ", "");
				count=1;
			}
			else
			{
				count += 1;
				seq += string;
			}
		}
		if(count > 1){
			FastaSequence sequenceObject = new FastaSequence(head,seq,gc);
			sequenceList.add(sequenceObject);
			seq="";
		}
		reader.close();
		return sequenceList;
	}
}