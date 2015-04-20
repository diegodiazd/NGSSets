package fatstqAnalysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ngsio.fastq.FastqEntry;
import ngsio.fastq.ReadSet;

public class ReadLengthDistribution extends FastqAnalysisTask{

	public ReadLengthDistribution(ReadSet fastqInput) {
		super(fastqInput);
	}

	@Override
	public void performTask(){
		Map<Integer, Integer> lengthDistribution = new HashMap<Integer, Integer>();
		FastqEntry currentEntry;
		while((currentEntry = (FastqEntry) readSet.getNextEntry())!=null){
			String DNASeq = currentEntry.getSequence();
			int readLength = DNASeq.length();
			if(lengthDistribution.get(readLength)==null){
				lengthDistribution.put(Integer.valueOf(readLength), Integer.valueOf(1));
			}else{
				int tmp = lengthDistribution.get(readLength)+1;
				lengthDistribution.put(Integer.valueOf(readLength), Integer.valueOf(tmp));
			}
		}
		
		Iterator<Entry<Integer, Integer>> it = lengthDistribution.entrySet().iterator();

	    while (it.hasNext()) {
	        Map.Entry e = (Map.Entry)it.next();
	        System.out.println("["+e.getKey() + "=" + e.getValue()+"]");
	   }
	}

	@Override
	public void makePlot() {
	}

	public static void main(String[] args){
		ReadSet f = new ReadSet("/home/diego/ESRNA_workspace/SintomaticoWt_S4_L001_R1_001.fastq");
		new ReadLengthDistribution(f).performTask();
	}
}
