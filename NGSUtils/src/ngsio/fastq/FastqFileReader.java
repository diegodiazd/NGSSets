package ngsio.fastq;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FastqFileReader {

	/**
	 * Class for read fastq entries from a fastq file
	 */
	private String inputFilePath;
	private BufferedReader buffer;
	private String sCurrentLine;
	
	/**
	 * Constructor of the class, It needs the path of file that will be read 
	 * @param path
	 */
	public FastqFileReader(String path){
		this.inputFilePath = path;
		try{
			buffer = new BufferedReader(new FileReader(inputFilePath));
		}catch(Exception e){
			
		}
	}
	/**
	 * read a fastq entry from a fastq file
	 * @return
	 */
	public FastqEntry readEntry(){
		try {
			int count=0;
			String[] entryData = new String[4];
			while ((sCurrentLine = buffer.readLine()) != null) {
				entryData[count] = sCurrentLine;
				count++;
				if(count == 4){
					count = 0;
					FastqEntry newEntry = new FastqEntry(entryData);
					return newEntry;
				}
			}
 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Close the stream
	 * @throws IOException
	 */
	public void close() throws IOException{
		buffer.close();
	}
	
	public static void main(String[] args){
		ReadSet rd = new ReadSet("/home/diego/7093KO_trim.fastq");
		rd.removeEntry();
		try {
			FastqFileWriter ffw = new FastqFileWriter("/home/diego/test.fastq");
			for(int i=0;i<rd.numberOfReads();i++){
				ffw.writeEntry((FastqEntry) rd.getNextEntry());
			}
			ffw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FastqEntryFormatException e) {
			e.printStackTrace();
		}
	}
}
