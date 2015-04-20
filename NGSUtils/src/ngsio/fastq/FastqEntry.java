package ngsio.fastq;

import ngsio.NGSEntry;


public class FastqEntry implements NGSEntry{
	
	private String id;
	private String seq;
	private String plus;
	private String qual;
	private FastqEntry next;
	private FastqEntry prev;
	
	public FastqEntry(String[] fields) throws FastqEntryFormatException{
		
		//checkFields();
		
		this.id = fields[0];
		this.seq = fields[1];
		this.plus = fields[2];
		this.qual = fields[3];
	}
	
	public String getId(){
		return id;
	}
	
	public String getSequence(){
		return seq;
	}
	
	public String getQuality(){
		return qual;
	}
	
	public String getPlus(){
		return plus;
	}
	
	public FastqEntry getNext(){
		return next;
	}
	
	public void setNext(NGSEntry next){
		this.next = (FastqEntry) next;
	}
	
	public void setPrev(NGSEntry prev){
		this.prev = (FastqEntry) prev;
	}
	
	public NGSEntry getPrev() {
		return this.prev;
	}
	
	private void checkFields() throws FastqEntryFormatException{
		throw new FastqEntryFormatException();
	}

}