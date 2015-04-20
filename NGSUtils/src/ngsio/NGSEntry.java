package ngsio;

public interface NGSEntry {
	public String getId();
	public NGSEntry getNext();
	public NGSEntry getPrev();
	public void setNext(NGSEntry entry);
	public void setPrev(NGSEntry entry);
}
