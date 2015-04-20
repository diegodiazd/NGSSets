package fatstqAnalysis;

import java.util.ArrayList;
import java.util.HashMap;

import ngsio.fastq.FastqEntry;
import ngsio.fastq.ReadSet;
import ngsutils.FastqUtils;

public class PerBaseQuality extends FastqAnalysisTask{

	public PerBaseQuality(ReadSet fastqInput) {
		super(fastqInput);
	}

	@Override
	public void performTask(){
		makePerbaseDistribution();
	}
	
	/*
	 * Get quality percentiles for each position in the reads set
	 */
	public void makePerbaseDistribution(){
		int max = FastqUtils.getMaxRead(readSet);
		HashMap<Integer, double[]> perBaseQualDist = new HashMap<Integer,double[]>();
		FastqEntry currentEntry;
		
		for(int i=0;i<max;i++){
			
			char[] tmpIDarray;
			ArrayList<Integer>tmpValues = new ArrayList<Integer>();
			
			// get the array of quality values for each position
			while((currentEntry = (FastqEntry) readSet.getNextEntry())!=null){
				tmpIDarray = currentEntry.getQuality().toCharArray();
				if(tmpIDarray.length <= i){
					continue;
				}else{
					tmpValues.add((int)tmpIDarray[i]-33);
				}
			}
			
			Integer[] qualPerPos = new Integer[tmpValues.size()];
			qualPerPos = tmpValues.toArray(qualPerPos);
			
			// Estimate percentiles for the array of qualities 
			double q1 = getTiles(qualPerPos,25);
			double q2 = getTiles(qualPerPos,50);
			double q3 = getTiles(qualPerPos,75);
			
			// Get upper and lower inner fences
			double h = 1.5*(q3 - q1);
			double LIF = q1-h;
			double UIF = q3+h;
			perBaseQualDist.put(Integer.valueOf(i+1), new double[]{LIF,q1,q2,q3,UIF});
			readSet.resetList();
		}
	}
	
	/*
	 * Estimate p percentile given an array. Implementation of algorithm of Hyndman and Fan (1996)
	 */
	private double getTiles(Integer[] array, double p){
		quickSort(array);
		int n = array.length;
		double pos;
		if(n==1){
			return array[n];
		}else{
			pos = (n+(1/3f))*(p/100)+(1/3);
			if(pos<1){
				return array[0];
			}else{
				if(pos>=n){
					return array[n];
				}else{
					double q = array[(int)Math.floor(pos)] + (pos - Math.floor(pos))*(array[(int)Math.floor(pos)+1]-array[(int)Math.floor(pos)]);
					return q;
				}
			}
		}
	}
	
    public static void quickSort(Integer[] array) {
        recursiveQuickSort(array, 0, array.length - 1);
    }

    public static void recursiveQuickSort(Integer[] array, int startIdx, int endIdx) {
     
        int idx = partition(array, startIdx, endIdx);

        if (startIdx < idx - 1) {
            recursiveQuickSort(array, startIdx, idx - 1);
        }

        // Recursively call quick sort with right part of the partitioned array
        if (endIdx > idx) {
            recursiveQuickSort(array, idx, endIdx);
        }
    }
    
    public static int partition(Integer[] array, int left, int right) {
        int pivot = array[left]; // taking first element as pivot

        while (left <= right) {
            //search number which is greater than pivot, bottom up
            while (array[left] < pivot) {
                left++;
            }
            //searching number which is less than pivot, top down
            while (array[right] > pivot) {
                right--;
            }

            // swap the values
            if (left <= right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;

                //increment left index and decrement right index
                left++;
                right--;
            }
        }
        return left;
    }

	@Override
	public void makePlot() {
	}
	
	public static void main(String[] args){
		ReadSet f = new ReadSet("/home/diego/7093KO_trim.fastq");
		new PerBaseQuality(f).performTask();
	}

}
