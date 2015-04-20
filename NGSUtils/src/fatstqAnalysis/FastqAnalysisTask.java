package fatstqAnalysis;

import javax.swing.JPanel;

import ngsio.fastq.ReadSet;

public abstract class FastqAnalysisTask {
	
	protected ReadSet readSet;
	protected JPanel plot;
	
	public FastqAnalysisTask(ReadSet fastqInput){
		this.readSet = fastqInput;
	}
	
	public abstract void performTask();
	public abstract void makePlot();
}
