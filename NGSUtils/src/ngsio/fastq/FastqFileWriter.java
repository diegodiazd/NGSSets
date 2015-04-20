package ngsio.fastq;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FastqFileWriter extends File{

	/**
	 * Write fastq entries in a fastq file
	 */
	private static final long serialVersionUID = 8490374765286840155L;
	FileWriter fw;
	BufferedWriter bw;
	
	/**
	 * Constructor of the class, It needs the path of the file being write 
	 * @param path
	 * @throws IOException
	 */
	public FastqFileWriter(String path) throws IOException {
		super(path);
		if (!this.exists()) {
			this.createNewFile();
		}
		fw = new FileWriter(this.getAbsoluteFile());
		bw = new BufferedWriter(fw);
	}
	
	/**
	 * Write a fastq entry in the file
	 * @param entry
	 * @throws FastqEntryFormatException
	 */
	public void writeEntry(FastqEntry entry) throws FastqEntryFormatException{
		
		try {
			if(entry != null){
				bw.write(entry.getId()+"\n");
				bw.write(entry.getSequence()+"\n");
				bw.write(entry.getPlus()+"\n");
				bw.write(entry.getQuality()+"\n");
			}else{
				throw new FastqEntryFormatException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close the stream
	 */
	public void close(){
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
