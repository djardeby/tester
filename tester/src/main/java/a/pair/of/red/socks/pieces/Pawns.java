package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;

import static a.pair.of.red.socks.utils.Constants.*;

public class Pawns implements Piece {

	private final Colour colour;
	private long pawnBoard;
	private long otherPieces;

	public Pawns(Colour colour, long pawnBoard, long otherPieces) {
		this.colour = colour;
		this.setOtherPieces(otherPieces);
		this.setPawnBoard(pawnBoard);
	}

	public String findAllMoves(final long empty) {
		return movePawnsOneStep(empty).append(movePawnsTwoSteps(empty)).append(pawnsAttackEast()).append(pawnsAttackWest()).toString();
	}

	private StringBuilder movePawnsOneStep(final long empty) {
		long pawnMoves = (Colour.WHITE.equals(getColour()) ? (getPawnBoard() >> 8) : (getPawnBoard() << 8)) & empty;
		return boardToMoves(getColour().getDirection(), pawnMoves, 0);
	}

	private StringBuilder movePawnsTwoSteps(final long empty) {

		long pawnMoves = (Colour.WHITE.equals(getColour()) ? (getPawnBoard() >> 16) & RANK_4 & empty >> 8
				: (getPawnBoard() << 16) & RANK_5 & empty << 8) & empty;
		return boardToMoves(2 * getColour().getDirection(), pawnMoves, 0);
	}


	private StringBuilder pawnsAttackEast() {
		String moves = "";
		long pawnMoves = (Colour.WHITE.equals(colour) ? pawnBoard >> 7 : pawnBoard << 9) & getOtherPieces() & ~FILE_A;
		return boardToMoves(colour.getDirection(),pawnMoves,-1);
	}

	private StringBuilder pawnsAttackWest() {
		long pawnMoves = (Colour.WHITE.equals(colour) ? pawnBoard >> 9 : pawnBoard << 7) & getOtherPieces() & ~FILE_H;
		return boardToMoves(colour.getDirection(),pawnMoves,1);
	}

	private StringBuilder boardToMoves(int sidesteps, long pawnMoves, int forwardsteps) {
		StringBuilder moves = new StringBuilder();
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves.append(index % 8 + forwardsteps);
			moves.append(index / 8 + sidesteps);
			moves.append(index % 8);
			moves.append(index / 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	public Colour getColour() {
		return colour;
	}

	public long getPawnBoard() {
		return pawnBoard;
	}

	public void setPawnBoard(long pawnBoard) {
		this.pawnBoard = pawnBoard;
	}

	public long getOtherPieces() {
		return otherPieces;
	}

	public void setOtherPieces(long otherPieces) {
		this.otherPieces = otherPieces;
	}
}
