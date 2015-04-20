package fatstqAnalysis;

import java.util.HashMap;

import ngsio.fastq.FastqEntry;
import ngsio.fastq.ReadSet;
import ngsutils.FastqUtils;

public class PerBaseContent extends FastqAnalysisTask{

	public PerBaseContent(ReadSet readSet) {
		super(readSet);
	}

	@Override
	public void performTask() {
		makePerbaseDistribution();
	}

	@Override
	public void makePlot() {
	
	}
	
	public void makePerbaseDistribution(){
		int max = FastqUtils.getMaxRead(readSet);
		HashMap<Integer, int[]> perBaseContent = new HashMap<Integer,int[]>();
		FastqEntry currentEntry;
		for(int i=0;i<max;i++){
			int[] bases = new int[4];
			float totalBasesChecked=0;
			char[] tmpBases;
			while((currentEntry = (FastqEntry) readSet.getNextEntry())!=null){
				tmpBases = currentEntry.getSequence().toCharArray();
				if(tmpBases.length <= i){
					continue;
				}else{
				
					if(tmpBases[i] == 'A'){
						bases[0]++;
						totalBasesChecked++;
					}
				
					if(tmpBases[i] == 'C'){
						bases[1]++;
						totalBasesChecked++;
					}

					if(tmpBases[i] == 'T'){
						bases[2]++;
						totalBasesChecked++;
					}

					if(tmpBases[i] == 'G'){
						bases[3]++;
						totalBasesChecked++;
					}
					if(tmpBases[i] == 'N'){
						totalBasesChecked++;
					}
				}
			}
			
			for(int j=0;j<4;j++){
				bases[j] = Math.round((bases[j]/totalBasesChecked)*100);
			}
			
			//System.out.println("Position: "+(i+1)+" distribution "+bases[0]+" "+bases[1]+" "+bases[2]+" "+bases[3]);
			perBaseContent.put(Integer.valueOf(i+1), bases);
			readSet.resetList();
		}
		
	}
	
	public static void main(String[] args){
		ReadSet f = new ReadSet("/home/diego/7093KO_trim.fastq");
		new PerBaseContent(f).performTask();
	}
}
