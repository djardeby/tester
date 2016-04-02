package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.board.StandardBoard;
import a.pair.of.red.socks.utils.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
//		rooks.lineAttacks(0L,0,0);

		StandardBoard board = new StandardBoard();
/*		for (int i = 0; i < FILE_ATTACK.length; i++) {
			for (int j = 0; j < FILE_ATTACK[i].length; j++) {
					board.setBlackPawnBoard(FILE_ATTACK[i][j][0]);
					board.setBlackQueenBoard(FILE_ATTACK[i][j][1]);
					logger.debug(board.toString());
			}
		}*/
		board.setBlackRookBoard(0x8100000000000081L);//-1L);// 34359738368L);
		board.setWhitePawnBoard(6389270908594971944L);
		logger.debug("Board: {}",board.toString());
		//Rooks rooks = new Rooks(Colour.BLACK, 0L,0L,0L);
		board.setWhiteToMove(false);
		//board.moves();
		logger.debug("Moves: {}",board.moves());
	}


	@Test
	public void arrayTest() {
		StandardBoard board = new StandardBoard();
		int square = 7;
		board.setWhiteKingBoard(LINE_ATTACKS[square][0][0]|LINE_ATTACKS[square][0][1]);
		board.setBlackKingBoard(LINE_ATTACKS[square][1][1]|LINE_ATTACKS[square][1][0]);
		logger.debug(board.toString());
		logger.debug("line_attacks: {}",LINE_ATTACKS.length);
		logger.debug("line_attacks: {}",LINE_ATTACKS[0].length);
		logger.debug("line_attacks: {}",LINE_ATTACKS[0][0].length);
	}

}