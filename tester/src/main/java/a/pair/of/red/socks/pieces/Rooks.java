package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static a.pair.of.red.socks.utils.Constants.*;

public class Rooks extends SlidingPieces {
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

}
