package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.board.StandardBoard;
import a.pair.of.red.socks.utils.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static a.pair.of.red.socks.utils.Constants.*;
import static org.junit.Assert.*;

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
		Rooks rooks = new Rooks(Colour.BLACK, 0L,0L,0L);
//		rooks.lineAttacks(0L,0,0);

		StandardBoard board = new StandardBoard();
/*
		for (int i = 0; i < FILE_ATTACK.length; i++) {
			for (int j = 0; j < FILE_ATTACK[i].length; j++) {
					board.setBlackPawnBoard(FILE_ATTACK[i][j][0]);
					board.setBlackQueenBoard(FILE_ATTACK[i][j][1]);
					logger.debug(board.toString());
			}
		}
*/
		board.setBlackQueenBoard(rooks.lineAttacks((RANK_6|RANK_2|RANK_1),4,4));
		board.setWhitePawnBoard(RANK_6);
		logger.debug(board.toString());
	}

}