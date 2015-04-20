package fatstqAnalysis;

import java.util.HashMap;
import java.util.Map;

import ngsio.fastq.FastqEntry;
import ngsio.fastq.ReadSet;

public class PerSequenceQuality extends FastqAnalysisTask {

	public PerSequenceQuality(ReadSet fastqInput) {
		super(fastqInput);
	}

	@Override
	public void performTask(){
		Map<Integer, Integer> lengthDistribution = new HashMap<Integer, Integer>();
		FastqEntry currentEntry;
		while((currentEntry = (FastqEntry) readSet.getNextEntry())!=null){
			String quality = currentEntry.getQuality();
			int meanQuality = getMeanQuality(quality);
			if(lengthDistribution.get(meanQuality)==null){
				lengthDistribution.put(Integer.valueOf(meanQuality), Integer.valueOf(1));
			}else{
				int tmp = lengthDistribution.get(meanQuality)+1;
				lengthDistribution.put(Integer.valueOf(meanQuality), Integer.valueOf(tmp));
			}
		}
	}
	
	private int getMeanQuality(String quality){
		char[] perBaseQuality = quality.toCharArray();
		int readLength = perBaseQuality.length;
		int totQuality = 0;
		for(int i=0;i<readLength;i++){
			totQuality = totQuality + ((int)perBaseQuality[i]);
		}
		return totQuality/readLength;
	}

	@Override
	public void makePlot() {
	}
	
	public static void main(String[] args){
		ReadSet f = new ReadSet("/home/diego/7093KO_trim.fastq");
		new PerSequenceQuality(f).performTask();
	}
}
