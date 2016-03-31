package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.BoardType;
import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.board.StandardBoard;
import a.pair.of.red.socks.factory.BoardFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class KnightsTest {

	private static final Logger logger = LoggerFactory.getLogger(KnightsTest.class);

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void findAllMoves() throws Exception {
		StandardBoard board = (StandardBoard) BoardFactory.initBoard(BoardType.StandardBoard);
		board.setWhitePawnBoard(0L);
		board.setBlackPawnBoard(0L);
		logger.debug("Nytt bräde {}", board.toString());
//		logger.debug("Nytt bräde {}", board.moves());
	}

	@Test
	public void knightsMove() {
		String expected = "33413352335433453325331433123321";
		long knightsBoard = Long.parseLong("000000000" + "00000000" + "00000000" + "00000000" + "00001000" + "00000000" + "00000000" + "00000000", 2);
		Knights knights = new Knights(Colour.WHITE, knightsBoard,0L,0L);
		String actual = knights.findAllMoves(~knightsBoard);
		assertEquals("Felaktiga drag för springare.", expected, actual);
	}

	@Test
	public void whiteKnightsAttackOnlyBlack() {
		String expected = "3321416033524162335441533345332541223314412033123321";
		long knightsBoardWhite = Long.parseLong("000000000" + "00000000" + "00000000" + "00000000" + "00001000" + "00000000" + "00010000" + "00000000", 2);
		long knightsBoardBlack = Long.parseLong("000000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000100" + "00000000", 2);
		Knights knights = new Knights(Colour.BLACK, knightsBoardWhite,knightsBoardWhite,knightsBoardBlack);
		String actual = knights.findAllMoves(~(knightsBoardWhite|knightsBoardBlack));
		assertEquals("Felaktiga drag för springare.", expected, actual);
	}

	@Test
	public void knightsDontMoveOutsideBoard() {
		String expected = "0715072677567765";
		long knightsBoardWhite = makeLong("100000001" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000");
		logger.debug("Hörnen: {}",Long.toBinaryString(knightsBoardWhite));
//		long knightsBoardBlack = Long.parseLong("000000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000", 2);
		Knights knights = new Knights(Colour.BLACK, knightsBoardWhite,knightsBoardWhite,0L);
		String actual = knights.findAllMoves(~(knightsBoardWhite));
		assertEquals("Felaktiga drag för springare.", expected, actual);
	}

	private static long makeLong(String input) {
		if(input.substring(0,1).equals("1")) {
			return -1 * (Long.MAX_VALUE - Long.parseLong(input.substring(1), 2) + 1);
		} else {
			return Long.parseLong(input, 2);
		}
	}

}