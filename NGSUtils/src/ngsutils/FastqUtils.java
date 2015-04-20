package ngsutils;

import ngsio.fastq.FastqEntry;
import ngsio.fastq.ReadSet;

public class FastqUtils {
	/**
	 * Give a subset of random reads from a given set
	 * @param reads
	 * @param nReads
	 * @return
	 */
	public static ReadSet sampleReads(ReadSet reads, int nReads){
		return null;
	}
	
	/**
	 * Reduce the coverage of a set of reads to certain value, for this, this methods shuffle the reads and store them until to reach the desired coverage 
	 * @param reads
	 * @param coverage
	 * @param referenceSize
	 * @return
	 */
	public static ReadSet downSample(ReadSet reads, int coverage, int referenceSize){
		return null;
	}
	
	
	/**
	 * Return the length of the smallest read in the set
	 * @return
	 */
	public static int getMinRead(ReadSet reads){
		ReadSet tmp = reads;
		tmp.resetList();
		FastqEntry tmpEntry = (FastqEntry) tmp.getNextEntry();
		int minRead = tmpEntry.getSequence().length();
		while(tmpEntry.getNext() !=null){
			tmpEntry = tmpEntry.getNext();
			int tmpLength = tmpEntry.getSequence().length();
			if(tmpLength<minRead){
				minRead = tmpLength;
			}
		}
		return minRead;
	}
	
	/**
	 * Return the length of the largest read in the set
	 * @param reads
	 * @return
	 */
	public static int getMaxRead(ReadSet reads){
		ReadSet tmp = reads;
		tmp.resetList();
		FastqEntry tmpEntry = (FastqEntry) tmp.getNextEntry();
		int maxRead = tmpEntry.getSequence().length();
		while(tmpEntry.getNext() != null){
			tmpEntry = tmpEntry.getNext();
			int tmpLength = tmpEntry.getSequence().length();
			if(tmpLength>maxRead){
				maxRead = tmpLength;
			}
		}
		return maxRead;
	}
}
