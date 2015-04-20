package fatstqAnalysis;

import ngsio.fastq.FastqEntry;
import ngsio.fastq.ReadSet;

public class GCContent extends FastqAnalysisTask {

	public GCContent(ReadSet readSet){
		super(readSet);
	}
	
	@Override
	public void performTask(){
		int[] GCDistribution = new int[101];
		FastqEntry currentEntry;
		while((currentEntry = (FastqEntry) readSet.getNextEntry())!=null){
			String DNASeq = currentEntry.getSequence();
			int GCcontent = caluclateGCcontent(DNASeq.toCharArray());
			GCDistribution[GCcontent]++;
		}
	}

	@Override
	public void makePlot() {
	}
	
	private int caluclateGCcontent(char[] bases){
		
		float GCcont=0;
		for(int i=0; i<bases.length;i++){
			if(bases[i]=='G' || bases[i]=='C'){
				GCcont++;
			}
		}
		return Math.round((GCcont/bases.length)*100);
	}
	
	public static void main(String[] args){
		ReadSet f = new ReadSet("/home/diego/ESRNA_workspace/SintomaticoWt_S4_L001_R1_001.fastq");
		new GCContent(f).performTask();
	}
}
