package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.BoardType;
import a.pair.of.red.socks.factory.BoardFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		Board board = BoardFactory.initBoard(BoardType.StandardBoard);

logger.debug("Nytt bräde {}", board.toString());
	}


}