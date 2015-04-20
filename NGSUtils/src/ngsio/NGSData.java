package ngsio;


public interface NGSData {
	public void addEntry(NGSEntry entry, int index);
	public void addEntry(NGSEntry currentEntry, NGSEntry nextEntry);
	public void addEntry(NGSEntry entry);
	public void removeEntry();
	public void removeEntry(NGSEntry removedEntry);
	public void removeEntry(int index);
	public NGSEntry getEntry(int index);
}
