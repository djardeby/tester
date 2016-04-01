package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Knights extends Piece {
	private static final Logger logger = LoggerFactory.getLogger(Knights.class);
	private long knightsBoard = 0L;

	public Knights(Colour colour, final long knightsBoard, long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
		this.knightsBoard = knightsBoard;
	}

	public String findAllMoves(long empty) {
		StringBuilder moves = moveNorthEast();
		moves.append(moveEastNorth());
		moves.append(moveEastSouth());
		moves.append(moveSouthEast());
		moves.append(moveSouthWest());
		moves.append(moveWestSouth());
		moves.append(moveWestNorth());
		moves.append(moveNorthWest());
		return moves.toString();
	}

	private StringBuilder moveNorthEast() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_H | Constants.RANK_78)) >>> 15 & ~getOwnPieces();
		return boardToMoves(-1, knightsMoves, 2);
	}

	private StringBuilder moveEastNorth() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_GH | Constants.RANK_8)) >>> 6 & ~getOwnPieces();
		return boardToMoves(-2, knightsMoves, 1);
	}

	private StringBuilder moveEastSouth() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_GH | Constants.RANK_1)) << 10 & ~getOwnPieces();
		return boardToMoves(-2, knightsMoves, -1);
	}

	private StringBuilder moveSouthEast() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_H | Constants.RANK_12)) << 17 & ~getOwnPieces();
		return boardToMoves(-1, knightsMoves, -2);
	}

	private StringBuilder moveSouthWest() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_A | Constants.RANK_12)) << 15 & ~getOwnPieces();
		return boardToMoves(1, knightsMoves, -2);
	}

	private StringBuilder moveWestSouth() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_AB | Constants.RANK_1)) << 6 & ~getOwnPieces();
		return boardToMoves(2, knightsMoves, -1);
	}

	private StringBuilder moveWestNorth() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_AB | Constants.RANK_8)) >>> 10 & ~getOwnPieces();
		return boardToMoves(2, knightsMoves, 1);
	}

	private StringBuilder moveNorthWest() {
		long knightsMoves = (knightsBoard & ~(Constants.FILE_A | Constants.RANK_78)) >>> 17 & ~getOwnPieces();
		return boardToMoves(1, knightsMoves, 2);
	}

}
