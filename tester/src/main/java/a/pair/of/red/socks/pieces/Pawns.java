package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;

import static a.pair.of.red.socks.utils.Constants.*;

public class Pawns extends Piece {

	private final Colour colour;
	private long pawnBoard;


	public Pawns(Colour colour, long pawnBoard,long ownPieces, long otherPieces) {
		super(ownPieces, otherPieces);
		this.colour = colour;
		this.setOtherPieces(otherPieces);
		this.setPawnBoard(pawnBoard);
	}

	public String findAllMoves(final long empty) {
		return movePawnsOneStep(empty).append(movePawnsTwoSteps(empty)).append(pawnsAttackEast()).append(pawnsAttackWest()).toString();
	}

	private StringBuilder movePawnsOneStep(final long empty) {
		long pawnMoves = (Colour.WHITE.equals(getColour()) ? (getPawnBoard() >> 8) : (getPawnBoard() << 8)) & empty;
		return boardToMoves(0, pawnMoves, getColour().getDirection());
	}

	private StringBuilder movePawnsTwoSteps(final long empty) {

		long pawnMoves = (Colour.WHITE.equals(getColour()) ? (getPawnBoard() >> 16) & RANK_4 & empty >> 8
				: (getPawnBoard() << 16) & RANK_5 & empty << 8) & empty;
		return boardToMoves(0, pawnMoves, 2 * getColour().getDirection());
	}


	private StringBuilder pawnsAttackEast() {
		String moves = "";
		long pawnMoves = (Colour.WHITE.equals(colour) ? pawnBoard >> 7 : pawnBoard << 9) & getOtherPieces() & ~FILE_A;
		return boardToMoves(-1,pawnMoves,colour.getDirection());
	}

	private StringBuilder pawnsAttackWest() {
		long pawnMoves = (Colour.WHITE.equals(colour) ? pawnBoard >> 9 : pawnBoard << 7) & getOtherPieces() & ~FILE_H;
		return boardToMoves(1,pawnMoves,colour.getDirection());
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

}
