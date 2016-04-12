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
		logger.debug("moves: {}",moves.length()/4);
logger.debug("sut.toString(): {}",sut.toString());
	}

}