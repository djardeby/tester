package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;

import static a.pair.of.red.socks.utils.Constants.FILE_A;
import static a.pair.of.red.socks.utils.Constants.FILE_H;

/**
 * Created by nrkdja on 2016-04-12.
 */
public class Kings extends Piece {

	private long kingBoard;

	public Kings(Colour colour, long kingBoard, long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
		this.kingBoard = kingBoard;
	}

	public String findAllMoves(long empty) {
		long tmpBoard = kingBoard;
		long moveBoard = ((tmpBoard & ~FILE_H) << 1) | ((tmpBoard & ~FILE_A) >> 1);
		tmpBoard |= moveBoard;
		moveBoard |= (tmpBoard >> 8) | (tmpBoard << 8);
		return boardToMoves(1, moveBoard&empty, 1).toString();
	}
}
