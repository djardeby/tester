package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;

public abstract class Piece {

	protected abstract String findAllMoves(long empty);

	protected abstract Colour getColour();

	protected StringBuilder boardToMoves(final int sidesteps, long pieceBoard, final int forwardsteps) {
		StringBuilder moves = new StringBuilder();
		long possibility = pieceBoard & ~(pieceBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves.append(index % 8 + forwardsteps);
			moves.append(index / 8 + sidesteps);
			moves.append(index % 8);
			moves.append(index / 8);
			pieceBoard &= ~possibility;
			possibility = pieceBoard & ~(pieceBoard - 1);
		}
		return moves;
	}
}
