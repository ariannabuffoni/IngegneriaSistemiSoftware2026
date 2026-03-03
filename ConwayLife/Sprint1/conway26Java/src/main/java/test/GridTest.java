package main.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.conway.domain.Grid;
import main.java.conway.domain.IGrid;



public class GridTest {
	private IGrid g;
    private final int ROWS = 5;
    private final int COLS = 5;
    
	@Before
	public void setup() {
		System.out.println("GridTest | setup");	
		g = new Grid(ROWS, COLS);
	}

	@After
	public void down() {
		System.out.println("GridTest | down");
	}
	
	@Test
	public void testDims() {
		System.out.println("testDims ---------------------" );
		int nr = g.getRows();
		int nc = g.getCols();
		assertTrue( nr==ROWS && nc==COLS);
	}
	@Test
	public void testCGridCellValue() {
		System.out.println("testCGridCellValue ---------------------" );
		g.getCell(0,0).setStatus(true);
		assertTrue(   g.getCell(0,0).isAlive() );
		assertFalse(  g.getCell(0,1).isAlive() );
	}
	@Test
	public void testGridRep() {
		System.out.println("testGridRep ---------------------" );
 		System.out.println(""+g);
		assertTrue( g.toString().startsWith(". . . . ."));
	}
	@Test
    public void testInitialization() {
		System.out.println("GridTest | doing initialization");
        assertEquals(ROWS, g.getRows());
        assertEquals(COLS, g.getCols());
        // Verifica che inizialmente siano tutte morte
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                assertFalse(g.getCell(r, c).isAlive());
            }
        }
    }
	@Test
	public void testGetCell() {
		System.out.println("GridTest | doing getCell");
		// semantica: x e y devono essere compresi tra 0 e getRows o getCols -1
		// mi aspetto exception o null ??
	}
    @Test
    public void testClear() {
    	System.out.println("GridTest | doing clear");
        // Accendo una cella e poi pulisco
        g.getCell(1, 1).setStatus(true);
        g.clear();
        assertFalse(g.getCell(1, 1).isAlive());
    }
    @Test
    public void testCountAliveNeighborsBasic() {
    	System.out.println("GridTest | doing count alive neighbors BASIC");
        /*
          Configurazione (al centro):
          M V M
          M C M
          V M V
          C = Cella sotto esame (2,2). Vicini vivi attesi: 3
        */
        g.getCell(1, 2).setStatus(true);
        g.getCell(3, 1).setStatus(true);
        g.getCell(3, 3).setStatus(true);

        assertEquals(3, g.countAliveNeighbors(2, 2));
    }
    @Test
    public void testCountAliveNeighborsEdges() {
    	System.out.println("GridTest | doing count alive neighbors EDGES");
        // Test agli angoli (0,0)
        g.getCell(0, 1).setStatus(true);
        g.getCell(1, 0).setStatus(true);
        g.getCell(1, 1).setStatus(true);

        // La cella 0,0 ha 3 vicini vivi
        assertEquals(3, g.countAliveNeighbors(0, 0));
    }
}

