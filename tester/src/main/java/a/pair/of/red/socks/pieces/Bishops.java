package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.StandardBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import a.pair.of.red.socks.board.Colour;

public class Bishops extends SlidingPieces {
	private static final Logger logger = LoggerFactory.getLogger(Bishops.class);
	private long bishopsBoard;

	public Bishops(final Colour colour, final  long bishopsBoard, long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
		this.bishopsBoard=bishopsBoard;
	}

	public String findAllMoves(StandardBoard board) {
		String moves="";
		long tmpBoard = bishopsBoard;
		long possibility = tmpBoard & ~(tmpBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			int startFile = index%8;
			int startRank = index/8;
			String startSquare = "" + startFile + startRank;
			moves += newBoardToMoves(lineAttacks((getOwnPieces() | getOtherPieces()), 2, index, getOwnPieces()), startSquare).toString();
			moves += newBoardToMoves(lineAttacks((getOwnPieces() | getOtherPieces()), 3, index, getOwnPieces()), startSquare).toString();
			tmpBoard &= ~possibility;
			possibility = tmpBoard & ~(tmpBoard - 1);
		}
		return moves;
	}

}
