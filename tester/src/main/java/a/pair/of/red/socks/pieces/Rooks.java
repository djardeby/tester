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

	long lineAttacks(long occ, int sq, int line) {
		long upper = FILE_ATTACK[0][line][UPPER];// & occ;
		logger.debug("upper: {}",Long.toBinaryString(upper));
		long lower = FILE_ATTACK[0][0][LOWER] & occ;
		logger.debug("upper: {}",Long.toBinaryString(lower));
		long mMs1b = 0x8000000000000000L >> Long.numberOfLeadingZeros (lower | 1);
		long ls1b  = upper & -upper;
		long odiff = 2*ls1b + mMs1b;
		return FILE_ATTACK[sq][line][1] & odiff;
	}
}
