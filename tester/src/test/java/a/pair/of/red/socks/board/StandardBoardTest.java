package a.pair.of.red.socks.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class StandardBoardTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void movesShouldReturnAllValidMovesInOrder() throws Exception {
Board sut = new StandardBoard();
		String expected = "a2a3";
		String actual = sut.moves();
		assertEquals("Returnerar inte rätt drag.", expected, actual);
	}
}