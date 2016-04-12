package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;

import static a.pair.of.red.socks.utils.Constants.LINE_ATTACKS;

/**
 * Created by nrkdja on 2016-04-11.
 */
public abstract class SlidingPieces extends Piece {
	public SlidingPieces(Colour colour, long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
	}

	protected long lineAttacks(final long occ, final int line, final int sq, long pieces) {
		long lower = LINE_ATTACKS[sq][line][0] & occ;
		long upper = LINE_ATTACKS[sq][line][1] & occ;
		long ms1b  = 0x8000000000000000L >>> Long.numberOfLeadingZeros (lower | 1);
		long ls1b  = upper & -upper;
		long odiff = 2*ls1b - ms1b;
		return (LINE_ATTACKS[sq][line][0]|LINE_ATTACKS[sq][line][1]) & odiff&~pieces;
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
