package main.java.conway.domain;

public interface IGrid {
	public int getRows();	// sarebbe natural, non possono essere negativo
    public int getCols();
    public ICell getCell(int row, int col);
    public int countAliveNeighbors(int row, int col);	// non primitiva, utility
    public void clear(); // Utile per il requisito "pulire la configurazione"
}
