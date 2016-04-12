package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.StandardBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by nrkdja on 2016-04-12.
 */
public class KingsTest {
	private static final Logger logger = LoggerFactory.getLogger(KingsTest.class);

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void findAllMoves() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setWhiteKingBoard(1L);
		String moves = sut.moves();
		logger.debug("moves: {}", moves.length() / 4);
		logger.debug("sut.toString(): {}", sut.toString());
	}

	@Test
	public void kingsCantMoveIntoCheckFromPawns() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setWhitePawnBoard(4352L);
		sut.setBlackKingBoard(4L);
		sut.setWhiteToMove(false);
		String moves = sut.moves();
		logger.debug("number of moves: {}", moves.length() / 4);
		logger.debug("moves: {}", moves);
		logger.debug("sut.toString(): {}", sut.toString());
		assertEquals("Kungen kan inte flytta till schack",3,moves.length()/4);
	}
	@Test
	public void whiteKingsCantMoveIntoCheckFromBlackPawns() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setBlackPawnBoard(4785074604081152L);
		sut.setWhiteKingBoard(288230376151711744L);
		String moves = sut.moves();
		logger.debug("number of moves: {}", moves.length() / 4);
		logger.debug("moves: {}", moves);
		logger.debug("sut.toString(): {}", sut.toString());
		assertEquals("Kungen kan inte flytta till schack",3,moves.length()/4);
	}
	@Test
	public void kingsCantMoveIntoCheckFromBishops() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setWhiteBishopBoard(4352L);
		sut.setBlackKingBoard(4L);
		sut.setWhiteToMove(false);
		String moves = sut.moves();
		logger.debug("number of moves: {}", moves.length() / 4);
		logger.debug("moves: {}", moves);
		logger.debug("sut.toString(): {}", sut.toString());
		assertEquals("Kungen kan inte flytta till schack",3,moves.length()/4);
	}

	@Test
	public void kingsCantMoveIntoCheckFromRooks() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setWhiteRookBoard(4303355904L);
		sut.setBlackKingBoard(134217728L);
		sut.setWhiteToMove(false);
		String moves = sut.moves();
		logger.debug("number of moves: {}", moves.length() / 4);
		logger.debug("moves: {}", moves);
		logger.debug("sut.toString(): {}", sut.toString());
		assertEquals("Kungen kan inte flytta till schack",2,moves.length()/4);
	}

	@Test
	public void whiteKingsCantMoveIntoCheckFromBlackRooks() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setBlackRookBoard(4303355904L);
		sut.setWhiteKingBoard(134217728L);
		sut.setBlackPawnBoard(4194304L);
		String moves = sut.moves();
		logger.debug("number of moves: {}", moves.length() / 4);
		logger.debug("moves: {}", moves);
		logger.debug("sut.toString(): {}", sut.toString());
		assertEquals("Kungen kan inte flytta till schack",5,moves.length()/4);
	}

	@Test
	public void kingsCantMoveIntoCheckFromQueen() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setWhiteQueenBoard(137438953472L);
		sut.setBlackKingBoard(134217728L);
		sut.setWhiteToMove(false);
		String moves = sut.moves();
		logger.debug("number of moves: {}", moves.length() / 4);
		logger.debug("moves: {}", moves);
		logger.debug("sut.toString(): {}", sut.toString());
		assertEquals("Kungen kan inte flytta till schack",3,moves.length()/4);
	}
	@Test
	public void kingsCantMoveIntoCheckFromKnights() throws Exception {
		StandardBoard sut = new StandardBoard();
		sut.setWhiteKnightBoard(1073741832L);
		sut.setBlackKingBoard(134217728L);
		sut.setWhiteToMove(false);
		String moves = sut.moves();
		logger.debug("number of moves: {}", moves.length() / 4);
		logger.debug("moves: {}", moves);
		logger.debug("sut.toString(): {}", sut.toString());
		assertEquals("Kungen kan inte flytta till schack",5,moves.length()/4);
	}

}