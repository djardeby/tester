package a.pair.of.red.socks.board;

import a.pair.of.red.socks.factory.BoardFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void makeMove() throws Exception {
		sut.makeMove("a2a4");
		logger.debug("sut.toString(): {}",sut.toString());
	}

	@Test
	public void undoMove() throws Exception {
		sut.makeMove("c2c4");
		sut.makeMove("a7a5");
		sut.makeMove("d1b3");

		sut.undoMove();
		assertTrue("Det ska vara vits tur.",sut.isWhiteToMove());
		logger.debug("sut.toString(): {}",sut.toString());

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
	public void perftInit3() throws Exception {
		String moves = sut.moves();
		String moreMoves = "";
		String evenMoreMoves = "";
		int numberOfMoves=0;
		for (int i = 0; i < moves.length(); i += 4) {
			sut.makeMove(moves.substring(i, i + 4));
			moreMoves = sut.moves();
			for (int j = 0; j < moreMoves.length(); j += 4) {
				sut.makeMove(moreMoves.substring(j, j + 4));
				evenMoreMoves = sut.moves();
				sut.undoMove();
				numberOfMoves+=evenMoreMoves.length()/4;
			}
			sut.undoMove();
		}

		assertEquals("Felaktigt antal drag", 8902, numberOfMoves);
	}
	@Test
	public void perftInit4() throws Exception {
		sut = null;
		sut = new StandardBoard();
		sut.setBlackPawnBoard(137439478784L);
		sut.setBlackRookBoard(2147483648L);
		sut.setBlackKingBoard(549755813888L);
		sut.setWhitePawnBoard(22517998170406912L);
		sut.setWhiteRookBoard(8589934592L);
		sut.setWhiteKingBoard(16777216L);
//		logger.debug("sut.toString(): {}",sut.toString());
		String moves = sut.moves();
		logger.debug("moves: {}",moves);
		assertEquals("Felaktigt antal drag", (14), moves.length()/4);
		String moreMoves = "";
		String beforeBoard=sut.toString();
		for (int i = 0; i < moves.length(); i += 4) {
			logger.debug("======================================================================");
			logger.debug("sut.toString(): {}",sut.toString());

			String nextMove = moves.substring(i, i + 4);
			logger.debug("nextMove: {}",nextMove);
			sut.makeMove(nextMove);
//			logger.debug("sut.toString(): {}",sut.toString());
			String newMoves = sut.moves();
			logger.debug("newMoves: {}",newMoves);
			logger.debug("antal newMoves: {}",newMoves.length()/4);
			moreMoves += newMoves;
			sut.undoMove();
			assertEquals("Brädet ska återställas",beforeBoard,sut.toString());
		}
		assertEquals("Felaktigt antal drag", 191, moreMoves.length() / 4);
/*
		String moreMoves = "";
		String evenMoreMoves = "";
		int numberOfMoves=0;
		for (int i = 0; i < moves.length(); i += 4) {
			sut.makeMove(moves.substring(i, i + 4));
			moreMoves = sut.moves();
			for (int j = 0; j < moreMoves.length(); j += 4) {
				sut.makeMove(moreMoves.substring(j, j + 4));
				evenMoreMoves = sut.moves();
				sut.undoMove();
				numberOfMoves+=evenMoreMoves.length()/4;
			}
			sut.undoMove();
		}

		assertEquals("Felaktigt antal drag", 8902, numberOfMoves);
*/
	}
}