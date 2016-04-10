package a.pair.of.red.socks.utils;

import a.pair.of.red.socks.board.StandardBoard;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static a.pair.of.red.socks.utils.Constants.LINE_ATTACKS;
import static org.junit.Assert.*;

/**
 * Created by djardeby on 2016-04-05.
 */
public class ConstantsTest {
private static final Logger logger = LoggerFactory.getLogger(ConstantsTest.class);
	@Test
	public void arrayTest() {
		StandardBoard board = new StandardBoard();
		for (int square = 7; square < LINE_ATTACKS.length; square+=8) {
			logger.debug("Square: {}",square);
			board.setWhiteKingBoard(LINE_ATTACKS[square][0][0] | LINE_ATTACKS[square][0][1]);
			board.setBlackKingBoard(LINE_ATTACKS[square][1][0] | LINE_ATTACKS[square][1][1]);
			board.setBlackQueenBoard(LINE_ATTACKS[square][2][0] | LINE_ATTACKS[square][2][1]);
			board.setWhiteQueenBoard(LINE_ATTACKS[square][3][0] | LINE_ATTACKS[square][3][1]);
			logger.debug(board.toString());
			assertEquals("Felaktigt antal rutor", 64,LINE_ATTACKS.length);
			assertEquals("Felaktigt antal linjetyper", 4,LINE_ATTACKS[square].length);
			for (int i = 0; i < LINE_ATTACKS[square].length; i++) {
				assertEquals("Felaktigt antal upper och lower", 2,LINE_ATTACKS[square][i].length);
			}
		}
		logger.debug("line_attacks: {}", LINE_ATTACKS.length);
		logger.debug("line_attacks: {}", LINE_ATTACKS[2].length);
		logger.debug("line_attacks: {}", LINE_ATTACKS[0][0].length);
	}


}