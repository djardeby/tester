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

	public String findAllMoves(long empty) {
		String moves="";
		long tmpBoard = rooksBoard;
		long possibility = tmpBoard & ~(tmpBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			int column =index % 8;
			int row = 7-(index / 8);
			moves += boardToMoves(0, lineAttacks((getOwnPieces() | getOtherPieces()), column, row), 0).toString();
			tmpBoard &= ~possibility;
			possibility = tmpBoard & ~(tmpBoard - 1);
		}
		return moves;
	}

	protected long lineAttacks(long occ, int column, int row) {
		long upper = FILE_ATTACK[row][column][UPPER] & occ;
		long lower = FILE_ATTACK[row][column][LOWER] & occ;
		long ls1b  = Long.highestOneBit(upper);
		long mMs1b = Long.lowestOneBit(lower);
		int numberOfTrailingZeros = Long.numberOfTrailingZeros(ls1b);
		int numberOfLeadingZeros = Long.numberOfLeadingZeros(mMs1b);
		long odiff = (0xffffffffffffffffL<< (numberOfTrailingZeros+numberOfLeadingZeros)) >>> numberOfLeadingZeros;
		long occupied = FILE_ATTACK[row][column][UPPER] | FILE_ATTACK[row][column][LOWER];
		long returnValue = occupied & odiff & ~getOwnPieces();
		return returnValue;
	}
}
