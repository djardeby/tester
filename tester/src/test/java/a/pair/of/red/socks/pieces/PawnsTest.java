package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.board.StandardBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class PawnsTest {

	private static final Logger logger = LoggerFactory.getLogger(PawnsTest.class);

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void movesShouldReturnAllValidMovesInOrderWhtie() throws Exception {
		String expected = "0605161526253635464556556665767506041614262436344644565466647674";
		long whiteBoard = Long.parseLong("000000000" + "11111111" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000", 2);
		Pawns sut = new Pawns(Colour.WHITE, whiteBoard ,0L,0L);
		StandardBoard board = new StandardBoard();
		board.setWhitePawnBoard(whiteBoard);
		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar inte rätt drag för vitt.", expected, actual);
	}

	@Test
	public void whitePawnsMoveOnlyOneStepWhenNotOnRank2() throws Exception {
		String expected = "05041514252435344544555465647574";
		long whiteBoard = Long.parseLong("000000000" + "00000000" + "11111111" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000", 2);
		Pawns sut = new Pawns(Colour.WHITE, whiteBoard,0L,0L);
		StandardBoard board = new StandardBoard();
		board.setWhitePawnBoard(whiteBoard);
		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar inte rätt drag för vitt.", expected, actual);
	}

	@Test
	public void blackPawnsMoveOnlyOneStepWhenNotOnRank7() throws Exception {
		String expected = "05061516252635364546555665667576";
		long blackBoard = Long.parseLong("000000000" + "00000000" + "11111111" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000", 2);
		Pawns sut = new Pawns(Colour.BLACK, blackBoard,0L,0L);
		StandardBoard board = new StandardBoard();
		board.setBlackPawnBoard(blackBoard);

		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar inte rätt drag för svart.", expected, actual);
	}

	@Test
	public void movesShouldReturnAllValidMovesInOrderBlack() throws Exception {
		String expected = "0102111221223132414251526162717201031113212331334143515361637173";
		long blackBoard = Long.parseLong("000000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "11111111" + "00000000", 2);
		Pawns sut = new Pawns(Colour.BLACK, blackBoard,0L,0L);
		StandardBoard board = new StandardBoard();
		board.setBlackPawnBoard(blackBoard);
		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar inte rätt drag för svart.", expected, actual);
	}

	@Test
	public void noMovesWhenPawnsAreBlockedWhite() throws Exception {
		String expected = "";
		long blackPawns = Long.parseLong("000000000" + "00000000" + "10000000" + "00000000" + "00000000" + "00000000" + "00000001" + "00000000", 2);
		long whitePawns = Long.parseLong("000000000" + "10000000" + "00000000" + "00000000" + "00000000" + "00000001" + "00000000" + "00000000", 2);
		Pawns sut = new Pawns(Colour.WHITE, whitePawns, blackPawns, 0L);
		StandardBoard board = new StandardBoard();
		board.setWhitePawnBoard(whitePawns);
		board.setBlackPawnBoard(blackPawns);

		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar inte rätt drag för blockerad vit.", expected, actual);
	}

	@Test
	public void noMovesWhenPawnsAreBlockedBlack() throws Exception {
		String expected = "";
		long blackPawns = Long.parseLong("000000000" + "00000000" + "10000000" + "00000000" + "00000000" + "00000000" + "00000001" + "00000000", 2);
		long whitePawns = Long.parseLong("000000000" + "10000000" + "00000000" + "00000000" + "00000000" + "00000001" + "00000000" + "00000000", 2);
		Pawns sut = new Pawns(Colour.BLACK, blackPawns, whitePawns, 0L);
		StandardBoard board = new StandardBoard();
		board.setWhitePawnBoard(whitePawns);
		board.setBlackPawnBoard(blackPawns);

		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar inte rätt drag för blockerad svart.", expected, actual);
	}

	@Test
	public void pawnsAttackSidewaysWhite() {
		String expected = "0201242302117665";
		long blackPawns = Long.parseLong("000000000" + "00000001" + "11000000" + "00000000" + "00000000" + "00000000" + "00000010" + "10000000", 2);
		long whitePawns = Long.parseLong("000000000" + "10000000" + "00000000" + "00000100" + "00000000" + "00000001" + "00000000" + "00000000", 2);
		Pawns sut = new Pawns(Colour.WHITE, whitePawns,whitePawns, blackPawns);
		StandardBoard board = new StandardBoard();
		board.setWhitePawnBoard(whitePawns);
		board.setBlackPawnBoard(blackPawns);

		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar inte attack för blockerad vit.", expected, actual);
	}

	@Test
	public void pawnsAttackSidewaysBlack() {
		String expected = "535401127566";
		long whitePawns = Long.parseLong("000000000" + "11000001" + "00000000" + "00000000" + "00000000" + "00000011" + "00000010" + "10000000", 2);
		long blackPawns = Long.parseLong("000000000" + "00000000" + "10000000" + "00000000" + "00100000" + "00000000" + "00000001" + "00000000", 2);
		Pawns sut = new Pawns(Colour.BLACK, blackPawns,blackPawns, whitePawns);
		StandardBoard board = new StandardBoard();
		board.setWhitePawnBoard(whitePawns);
		board.setBlackPawnBoard(blackPawns);

		String actual = sut.findAllMoves(board);
		assertEquals("Returnerar attack drag för blockerad svart.", expected, actual);
	}
	@Test
	public void test() {
		long whitePawns = makeLong("000000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000001");
		StandardBoard board = new StandardBoard();
		board.setBlackKnightBoard(whitePawns);
		logger.debug(board.toString());
		Pawns sut = new Pawns(Colour.BLACK, 1L, 1L, 0L);
		logger.debug("{}",sut.findAllMoves(board));
	}
	private static long makeLong(String input) {
		if(input.substring(0,1).equals("1")) {
			return -1 * (Long.MAX_VALUE - Long.parseLong(input.substring(1), 2) + 1);
		} else {
			return Long.parseLong(input, 2);
		}
	}
}