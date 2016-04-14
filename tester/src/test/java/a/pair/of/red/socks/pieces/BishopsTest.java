package a.pair.of.red.socks.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import a.pair.of.red.socks.board.StandardBoard;

public class BishopsTest {

	private static final Logger logger = LoggerFactory.getLogger(BishopsTest.class);

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
//		bishops.lineAttacks(0L,0,0);
		long[] startPlatser={0x200L,0x100000000000000L,0x8000000000000000L,0x40000000000L};
		String[] expected ={"b7a8b7c6b7d5b7e4b7f3b7g2b7h1b7c8b7a6","a1b2","h1a8h1b7h1c6h1d5h1e4h1f3h1g2","c3b4c3d2c3d4c3b2"};
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

		board.setBlackBishopBoard(startPlatser[i]);
		board.setWhitePawnBoard(6389270908594955544L);  //6389270908594971928L);
		//board.setWhitePawnBoard(1L);
		//logger.debug("Board: {}", board.toString());
		//Bishops bishops = new Bishops(Colour.BLACK, 0L,0L,0L);
		board.setWhiteToMove(false);
		//board.moves();
			String actual = board.moves();
			//logger.debug("Moves: {}", actual);
			assertEquals("Felaktiga drag",expected[i], actual);
		}

	}

}