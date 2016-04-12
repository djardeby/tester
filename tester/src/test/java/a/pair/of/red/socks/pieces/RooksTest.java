package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.StandardBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static a.pair.of.red.socks.utils.Constants.LINE_ATTACKS;
import static org.junit.Assert.assertEquals;

public class RooksTest {

	private static final Logger logger = LoggerFactory.getLogger(RooksTest.class);

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
		long[] startPlatser={0x80L,0x8000000000000000L,0x1L,0x100000000000000L,134217728,512L,8796093022208L};
		String[] expected ={"c3c2e3e2c3d2e3f2c3b2e3d2h8h7h8h6h8e8h8f8h8g8","c3c2e3e2c3d2e3f2c3b2e3d2h1h2h1g1","c3c2e3e2c3d2e3f2c3b2e3d2a8a7a8b8a8c8a8d8","c3c2e3e2c3d2e3f2c3b2e3d2a1a2a1b1a1c1a1d1","c3c2e3e2c3d2e3f2c3b2e3d2d5d6d5d4d5a5d5b5d5c5d5e5d5f5","c3c2e3e2c3d2e3f2c3b2e3d2b7b8b7b6b7a7b7c7","c3c2e3e2c3d2e3f2c3b2e3d2d3d4d3d2"};
		for (int i = 6; i < startPlatser.length; i++) {

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

		board.setBlackRookBoard(startPlatser[i]);
		board.setWhitePawnBoard(6389270908594971928L);
			board.setBlackPawnBoard(21990232555520L);
		//board.setWhitePawnBoard(1L);
		logger.debug("Board: {}", board.toString());
		//Rooks rooks = new Rooks(Colour.BLACK, 0L,0L,0L);
		board.setWhiteToMove(false);
		//board.moves();
			String actual = board.moves();
			logger.debug("Moves: {}", actual);
			assertEquals("Felaktiga drag",expected[i], actual);
		}

	}

}