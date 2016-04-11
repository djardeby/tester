package a.pair.of.red.socks.pieces;

import static a.pair.of.red.socks.utils.Constants.LINE_ATTACKS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import a.pair.of.red.socks.board.Colour;

public class Bishops extends Piece {
	private static final Logger logger = LoggerFactory.getLogger(Bishops.class);
	private long bishopsBoard;

	public Bishops(final Colour colour, final  long bishopsBoard, long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
		this.bishopsBoard=bishopsBoard;
	}

	public String findAllMoves(long empty) {
		String moves="";
		long tmpBoard = bishopsBoard;
		long possibility = tmpBoard & ~(tmpBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			int startFile = index%8;
			int startRank = index/8;
			String startSquare = "" + startFile + startRank;
			moves += newBoardToMoves(lineAttacks((getOwnPieces() | getOtherPieces()), 2, index), startSquare).toString();
			moves += newBoardToMoves(lineAttacks((getOwnPieces() | getOtherPieces()), 3, index), startSquare).toString();
			tmpBoard &= ~possibility;
			possibility = tmpBoard & ~(tmpBoard - 1);
		}
		return moves;
	}
	protected long lineAttacks(final long occ, final int  line, final int sq) {
		long lower = LINE_ATTACKS[sq][line][0] & occ;
		long upper = LINE_ATTACKS[sq][line][1] & occ;
		long ms1b  = 0x8000000000000000L >>> Long.numberOfLeadingZeros (lower | 1);
		long ls1b  = upper & -upper;
		long odiff = 2*ls1b - ms1b;
		return (LINE_ATTACKS[sq][line][0]|LINE_ATTACKS[sq][line][1]) & odiff&~getOwnPieces();
	}

	protected StringBuilder newBoardToMoves(long pieceBoard, String startSquare) {
		StringBuilder moves = new StringBuilder();
		long possibility = pieceBoard & ~(pieceBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves.append(startSquare);
			moves.append(index % 8);
			moves.append(index / 8);
			pieceBoard &= ~possibility;
			possibility = pieceBoard & ~(pieceBoard - 1);
		}
		return moves;
	}

}
