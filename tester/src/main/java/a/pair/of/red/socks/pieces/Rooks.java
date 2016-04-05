package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static a.pair.of.red.socks.utils.Constants.*;

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
			int startFile = index%8;
			int startRank = index/8;
			String startSquare = "" + startFile + startRank;
			moves += newBoardToMoves(lineAttacks((getOwnPieces() | getOtherPieces()), 0, index), startSquare).toString();
			moves += newBoardToMoves(lineAttacks((getOwnPieces() | getOtherPieces()), 1, index), startSquare).toString();
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
