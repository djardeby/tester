package a.pair.of.red.socks.board;

import a.pair.of.red.socks.factory.BoardFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class StandardBoardTest {

	private static final Logger logger = LoggerFactory.getLogger(StandardBoardTest.class);

	private StandardBoard sut;

	@Before
	public void setUp() throws Exception {
		sut = (StandardBoard) BoardFactory.initBoard(BoardType.StandardBoard);

	}

	@After
	public void tearDown() throws Exception {
		sut = null;
	}

	@Test
	public void perftInit1() throws Exception {
		String moves = sut.moves();
		assertEquals("Felaktigt antal drag", 20, moves.length() / 4);
	}

	@Test
	public void perftInit2() throws Exception {
		String moves = sut.moves();
		String moreMoves = "";
		for (int i = 0; i < moves.length(); i += 4) {
			sut.makeMove(moves.substring(i, i + 4));
			moreMoves += sut.moves();
			sut.undoMove();
		}
		assertEquals("Felaktigt antal drag", 400, moreMoves.length() / 4);
	}

	@Test
	@Ignore
	public void perftInit3() throws Exception {
		String moves = sut.moves();
		String moreMoves = "";
		for (int i = 0; i < moves.length(); i += 4) {
			sut.makeMove(moves.substring(i, i + 4));
			moreMoves += sut.moves();
			sut.undoMove();
		}
		String evenMoreMoves = "";
		for (int i = 0; i < moreMoves.length(); i += 4) {
			sut.makeMove(moreMoves.substring(i, i + 4));
			evenMoreMoves += sut.moves();
			sut.undoMove();
		}
		assertEquals("Felaktigt antal drag", 8902, evenMoreMoves.length() / 4);
	}
}