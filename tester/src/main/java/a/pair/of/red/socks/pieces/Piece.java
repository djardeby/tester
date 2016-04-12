package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.board.StandardBoard;

public abstract class Piece {

	public final Colour colour;

	private long otherPieces;
	private long ownPieces;

	public Piece(final Colour colour, long ownPieces, long otherPieces) {
		this.colour = colour;
		this.ownPieces= ownPieces;
		this.otherPieces= otherPieces;
	}

	protected abstract String findAllMoves(StandardBoard board);

	protected StringBuilder boardToMoves(final int sidesteps, long pieceBoard, final int forwardsteps) {
		StringBuilder moves = new StringBuilder();
		long possibility = pieceBoard & ~(pieceBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves.append(index % 8 + sidesteps);
			moves.append(index / 8 + forwardsteps);
			moves.append(index % 8);
			moves.append(index / 8);
			pieceBoard &= ~possibility;
			possibility = pieceBoard & ~(pieceBoard - 1);
		}
		return moves;
	}


	public long getOtherPieces() {
		return otherPieces;
	}

	public void setOtherPieces(long otherPieces) {
		this.otherPieces = otherPieces;
	}

	public long getOwnPieces() {
		return ownPieces;
	}

	public void setOwnPieces(long ownPieces) {
		this.ownPieces = ownPieces;
	}
}
