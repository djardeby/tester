package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static a.pair.of.red.socks.utils.Constants.FILE_ATTACK;
import static a.pair.of.red.socks.utils.Constants.LOWER;
import static a.pair.of.red.socks.utils.Constants.UPPER;

public class Rooks extends Piece {
	private static final Logger logger = LoggerFactory.getLogger(Rooks.class);
	private long rooksBoard;

	public Rooks(final Colour colour, final  long rooksBoard, long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
		this.rooksBoard=rooksBoard;
	}

	protected String findAllMoves(long empty) {
		return "";
	}

	long lineAttacks(long occ, int line, int row) {
		long upper = FILE_ATTACK[row][line][UPPER] & occ;
		logger.debug("upper: {}",upper); // 524296
		long lower = FILE_ATTACK[row][line][LOWER] & occ;
		logger.debug("lower: {}",lower); // 578712552117108736
		long ls1b  = Long.highestOneBit(upper);// & -upper;
		logger.debug("ls1b: {}",ls1b); // 524288
		long orLower = lower | 72057594037927936L;
		logger.debug("orLower: {}",orLower);// 650770146155036672
		long mMs1b = Long.lowestOneBit(orLower);// 0x8000000000000000L >> Long.numberOfLeadingZeros (lower | 1);
		logger.debug("mMs1b: {}",mMs1b); // 2251799813685248
		long odiff = 2*mMs1b - ls1b;
		logger.debug("odiff: {}",odiff); // 63331869744103424
		long occupied = FILE_ATTACK[line][row][0] | FILE_ATTACK[line][row][1];
		logger.debug("occupied: {}",occupied);
		long returnValue = occupied & odiff;
		logger.debug("Returnvalue: {}", returnValue); // 2260596041449472
		return returnValue;
	}
}
