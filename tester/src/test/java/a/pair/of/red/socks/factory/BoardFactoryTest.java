package a.pair.of.red.socks.factory;

import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.BoardType;
import a.pair.of.red.socks.board.StandardBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardFactoryTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	@Ignore
	public void initBoard() throws Exception {
		BoardFactory sut = new BoardFactory();
		Board actual = sut.initBoard(BoardType.StandardBoard);
		StandardBoard expected = new StandardBoard();
		assertEquals("Fel board har skapats", expected.getClass(), actual.getClass());
	}
}