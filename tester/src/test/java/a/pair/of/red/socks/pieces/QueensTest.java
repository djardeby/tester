package a.pair.of.red.socks.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import a.pair.of.red.socks.board.StandardBoard;

public class QueensTest {

	private static final Logger logger = LoggerFactory.getLogger(QueensTest.class);

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void findAllMoves() throws Exception {

	}

	@Test
	public void lineAttacks() throws Exception {
//		rooks.lineAttacks(0L,0,0);
		long[] startPlatser={0x80L,0x8000000000000000L,0x1L,0x100000000000000L,134217728};
		String[] expected ={"h8h7h8h6h8e8h8f8h8g8h8g7","h1h2h1g1h1a8h1b7h1c6h1d5h1e4h1f3h1g2","a8a7a8b8a8c8a8d8a8b7a8c6a8d5a8e4a8f3a8g2a8h1","a1a2a1b1a1c1a1d1a1b2","d5d6d5d4d5a5d5b5d5c5d5e5d5f5d5a8d5b7d5c6d5e4d5f3d5g2d5h1d5f7d5e6d5c4d5b3d5a2"};
		for (int i = 0; i < startPlatser.length; i++) {

		StandardBoard board = new StandardBoard();
/*		for (int i = 0; i < LINE_ATTACKS.length; i++) {
			//for (int j = 0; j < LINE_ATTACKS[i].length; j++) {
					board.setBlackPawnBoard(LINE_ATTACKS[i][0][0]);
					board.setWhiteQueenBoard(LINE_ATTACKS[i][0][1]);
					board.setBlackKnightBoard(LINE_ATTACKS[i][1][0]);
					board.setWhiteKnightBoard(LINE_ATTACKS[i][1][1]);
					logger.debug(board.toString());
			//}
		}*/

		board.setBlackQueenBoard(startPlatser[i]);
		board.setWhitePawnBoard(6389270908594971928L);
		//board.setWhitePawnBoard(1L);
		//Rooks rooks = new Rooks(Colour.BLACK, 0L,0L,0L);
		board.setWhiteToMove(false);
		//board.moves();
			String actual = board.moves();
		//	logger.debug("Moves: {}", actual);
			assertEquals("Felaktiga drag",expected[i], actual);
		}

	}

}