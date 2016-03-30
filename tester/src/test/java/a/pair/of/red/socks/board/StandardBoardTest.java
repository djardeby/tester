package a.pair.of.red.socks.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class StandardBoardTest {

	private static final Logger logger = LoggerFactory.getLogger(StandardBoardTest.class);

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void movesShouldReturnAllValidMovesInOrderWhtie() throws Exception {
		StandardBoard sut = new StandardBoard();
		String expected = "a2a3b2b3c2c3d2d3e2e3f2f3g2g3h2h3a2a4b2b4c2c4d2d4e2e4f2f4g2g4h2h4";
		long test = Long.parseLong("000000000" + "11111111" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000", 2);
		sut.setWhitePawnBoard(test);
		String actual = sut.moves();
		assertEquals("Returnerar inte rätt drag för vitt.", expected, actual);
	}

	@Test
	public void whitePawnsMoveOnlyOneStepWhenNotOnRank2() throws Exception {
		StandardBoard sut = new StandardBoard();
		String expected = "a3a4b3b4c3c4d3d4e3e4f3f4g3g4h3h4";
		long test = Long.parseLong("000000000" + "00000000" + "11111111" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000", 2);
		sut.setWhitePawnBoard(test);
		String actual = sut.moves();
		assertEquals("Returnerar inte rätt drag för vitt.", expected, actual);
	}

	@Test
	public void blackPawnsMoveOnlyOneStepWhenNotOnRank7() throws Exception {
		StandardBoard sut = new StandardBoard();
		String expected = "a3a4b3b4c3c4d3d4e3e4f3f4g3g4h3h4";
		long test = Long.parseLong("000000000" + "00000000" + "11111111" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000", 2);
		sut.setWhitePawnBoard(test);
		String actual = sut.moves();
		assertEquals("Returnerar inte rätt drag för vitt.", expected, actual);
	}

	@Test
	public void movesShouldReturnAllValidMovesInOrderBlack() throws Exception {
		StandardBoard sut = new StandardBoard();
		String expected = "a7a6b7b6c7c6d7d6e7e6f7f6g7g6h7h6a7a5b7b5c7c5d7d5e7e5f7f5g7g5h7h5";
		long test = Long.parseLong("000000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "11111111" + "00000000", 2);
		sut.setBlackPawnBoard(test);
		sut.setWhiteToMove(false);
		String actual = sut.moves();
		assertEquals("Returnerar inte rätt drag för svart.", expected, actual);
	}

	@Test
	public void noMovesWhenPawnsAreBlocked() throws Exception {
		StandardBoard sut = new StandardBoard();
		String expected = "";
		long blackPawn = Long.parseLong("000000000" + "00000000" + "10000000" + "00000000" + "00000000" + "00000000" + "00000001" + "00000000", 2);
		long whitePawn = Long.parseLong("000000000" + "10000000" + "00000000" + "00000000" + "00000000" + "00000001" + "00000000" + "00000000", 2);
		sut.setBlackPawnBoard(blackPawn);
		sut.setWhitePawnBoard(whitePawn);
		String actual = sut.moves();
		assertEquals("Returnerar inte rätt drag för blockerad vit.", expected, actual);
		sut.setWhiteToMove(false);
		actual = sut.moves();
		assertEquals("Returnerar inte rätt drag för blockerad svart.", expected, actual);
	}

	@Test
	public void pawnsAttackSidewaysWhite() {
		StandardBoard sut = new StandardBoard();
		String expected = "a6a7c4c5a6b7h2g3";
		long blackPawn = Long.parseLong("000000000" + "00000001" + "11000000" + "00000000" + "00000000" + "00000000" + "00000010" + "10000000", 2);
		long whitePawn = Long.parseLong("000000000" + "10000000" + "00000000" + "00000100" + "00000000" + "00000001" + "00000000" + "00000000", 2);
		sut.setBlackPawnBoard(blackPawn);
		sut.setWhitePawnBoard(whitePawn);
		String actual = sut.moves();
		assertEquals("Returnerar inte attack för blockerad vit.", expected, actual);
	}

	@Test
	public void pawnsAttackSidewaysBlack() {
		StandardBoard sut = new StandardBoard();
		String expected = "f5f4a7b6h3g2";
		long whitePawn = Long.parseLong("000000000" + "11000001" + "00000000" + "00000000" + "00000000" + "00000011" + "00000010" + "10000000", 2);
		long blackPawn = Long.parseLong("000000000" + "00000000" + "10000000" + "00000000" + "00100000" + "00000000" + "00000001" + "00000000", 2);
		sut.setBlackPawnBoard(blackPawn);
		sut.setWhitePawnBoard(whitePawn);
		sut.setWhiteToMove(false);
		String actual = sut.moves();
		assertEquals("Returnerar attack drag för blockerad svart.", expected, actual);
	}
}