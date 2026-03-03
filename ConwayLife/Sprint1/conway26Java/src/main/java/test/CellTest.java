package main.java.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.conway.domain.Cell;
import main.java.conway.domain.ICell;

public class CellTest {
	private ICell c; 
	@Before
	public void setup() {
		System.out.println("CellTest | setup");	
		c = new Cell();		// cell nascono morte
	}

	@After
	public void down() {
		System.out.println("CellTest | down");
	}
	
	@Test
	public void TestCostruttori() {
		System.out.println("CellTest | doing alive");
		ICell cDefault = new Cell();
		assertFalse(cDefault.isAlive());
		ICell cViva = new Cell(true);
		assertTrue(cViva.isAlive());
	}
	
	@Test
	public void TestCellAlive() {
		System.out.println("CellTest | doing alive");
		c.setStatus(true);
		boolean r = c.isAlive();
		assertTrue(r);
	}
	
	@Test
	public void TestCellDead() {
		System.out.println("CellTest | doing dead");
		c.setStatus(false);
		boolean r = c.isAlive();
		assertTrue(!r);	// oppure assertFalse(r);
	}
	
	@Test
	public void TestSwitchStatus() {
		System.out.println("CellTest | doing switch status");
		c.setStatus(true);
		c.switchStatus();
		assertFalse(c.isAlive());
		c.switchStatus();
		assertTrue(c.isAlive());
	}
}
