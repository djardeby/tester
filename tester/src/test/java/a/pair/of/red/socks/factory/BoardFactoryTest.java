package a.pair.of.red.socks.factory;

import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.BoardType;
import a.pair.of.red.socks.board.StandardBoard;

import static org.junit.Assert.*;


public class BoardFactoryTest {

	@org.junit.Before
	public void setUp() throws Exception {

	}

	@org.junit.After
	public void tearDown() throws Exception {

	}

	@org.junit.Test
	public void initBoard() throws Exception {
		BoardFactory sut = new BoardFactory();
		Board actual = sut.initBoard(BoardType.StandardBoard);
		StandardBoard expected = new StandardBoard();
		assertEquals("Fel board har skapats", expected.getClass(),actual.getClass());
	}
}