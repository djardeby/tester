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

	long lineAttacks(long occ, int row, int line) {
		long upper = FILE_ATTACK[line][row][UPPER] & occ;
		logger.debug("upper: {}",upper);
		long lower = FILE_ATTACK[line][row][LOWER] & occ;
		logger.debug("lower: {}",lower);
		long ls1b  = upper & -upper;
		logger.debug("ls1b: {}",ls1b);
		long mMs1b = 0x8000000000000000L >> Long.numberOfLeadingZeros (lower | 1);
		logger.debug("mMs1b: {}",mMs1b);
		long odiff = 2*(ls1b) - mMs1b;
		logger.debug("odiff: {}",odiff);
		long returnValue = (FILE_ATTACK[row][line][0] | FILE_ATTACK[row][line][1]) & odiff;
		logger.debug("Returnvalue: {}", returnValue);
		return returnValue;
	}
}
