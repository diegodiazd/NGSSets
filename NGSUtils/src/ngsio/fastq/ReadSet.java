package ngsio.fastq;

import ngsio.NGSData;
import ngsio.NGSEntry;

public class ReadSet implements NGSData{

	/**
	 * Object that stores a set of reads in a linked list
	 */
	private FastqEntry head;
	private FastqEntry tail;
	private FastqEntry actual;
	private int length =0;
	
	/**
	 * Generate a read set from a fastq file
	 * @param file
	 */
	public ReadSet(String file){
		FastqFileReader fr = new FastqFileReader(file);
		FastqEntry tmpEntry;
		while((tmpEntry = fr.readEntry()) != null){
			addEntry(tmpEntry);
		}
	}
	
	/**
	 *  Generate a read set from a array of fastq entries
	 * @param entries
	 */
	public ReadSet(FastqEntry[] entries){
		for(int i=0;i<entries.length;i++){
			addEntry(entries[i]);
		}
	}
	
	/*
	 * Add a new entry next to last entry
	 * @see ngsio.NGSData#addEntry(ngsio.NGSEntry)
	 */
	public void addEntry(NGSEntry entry){
		if(head == null){
			head = (FastqEntry) entry;
			tail = head;
			actual = head;
		}else{
			FastqEntry currentEntry = tail;
			currentEntry.setNext(entry);
			tail = (FastqEntry) entry;
			entry.setPrev(currentEntry);
		}
		this.length++;
	}
	
	/*
	 * Add a new entry in a specific entry
	 * @see ngsio.NGSData#addEntry(ngsio.NGSEntry, ngsio.NGSEntry)
	 */
	public void addEntry(NGSEntry currentEntry, NGSEntry nextEntry){
		currentEntry.setNext(nextEntry);
		nextEntry.setPrev(currentEntry);
		this.length++;
	}
	
	/*
	 * Add a new entry in a specific position
	 * @see ngsio.NGSData#addEntry(ngsio.NGSEntry, int)
	 */
	public void addEntry(NGSEntry nextEntry, int index){
		FastqEntry currentEntry = head;
		FastqEntry prevEntry = head;
		if(index ==0){
			nextEntry.setNext(head);
			head = (FastqEntry) nextEntry;
		}else{
		
			for(int i=0;i<index;i++){
				prevEntry = currentEntry;
				currentEntry = currentEntry.getNext();
			}
			nextEntry.setNext(currentEntry);
			prevEntry.setNext(nextEntry);
			this.length++;
		}
	}
	
	/*
	 * Get an entry at specific postion
	 * @see ngsio.NGSData#getEntry(int)
	 */
	public NGSEntry getEntry(int index){
		FastqEntry currentEntry = head;
		for(int i=0;i<index;i++){
			currentEntry = currentEntry.getNext();
			if(currentEntry == null){
				break;
			}
		}
		return currentEntry;
	}
	
	/**
	 * Get the last entry inserted
	 * @return
	 */
	public NGSEntry getNextEntry(){
		NGSEntry tmp = actual;
		try{
			actual = actual.getNext();
		}catch(NullPointerException e){
			return null;
		}
		return tmp;
	}
	
	/*
	 * Remove last entry
	 * @see ngsio.NGSData#removeEntry()
	 */
	public void removeEntry(){
		if(head==null){
			//throw new exception
		}else{
			tail = (FastqEntry) tail.getPrev();
			tail.setNext(null);
			this.length--;
		}
	}
	
	/*
	 * Remove specific entry
	 * @see ngsio.NGSData#removeEntry(ngsio.NGSEntry)
	 */
	public void removeEntry(NGSEntry removedEntry){
		
		if(head ==null){
			//throw algo
		}else{
			if(removedEntry==head){
				head = head.getNext();
				head.setPrev(null);
			}else{
				FastqEntry prevEntry = (FastqEntry) removedEntry.getPrev();
				FastqEntry nextEntry = (FastqEntry) removedEntry.getNext();
				prevEntry.setNext(nextEntry);
			}
			length--;
		}
	}
	
	/*
	 * Remove an entry at given position
	 * @see ngsio.NGSData#removeEntry(int)
	 */
	public void removeEntry(int index){
		if(head == null){
			// throw algo
		}else{
			if(index==0){
				head = head.getNext();
			}else{
				FastqEntry prevEntry = head;
				for(int i=0;i<index-1;i++){
					prevEntry = prevEntry.getNext();
				}
				FastqEntry removedEntry = prevEntry.getNext();
				prevEntry.setNext(removedEntry.getNext());
			}
			this.length--;
		}
	}
	
	/**
	 * Return the number of entries
	 * @return
	 */
	public int numberOfReads(){
		return length;
	}
	
	/**
	 * Reset the pointer at the beginning 
	 */
	public void resetList(){
		actual = head;
	}
}
