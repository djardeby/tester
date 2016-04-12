package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.board.StandardBoard;

import static a.pair.of.red.socks.utils.Constants.*;

public class Pawns extends Piece {

	private long pawnBoard;


	public Pawns(Colour colour, long pawnBoard,long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
		this.setOtherPieces(otherPieces);
		this.setPawnBoard(pawnBoard);
	}

	public String findAllMoves(StandardBoard board) {
		return movePawnsOneStep(board.getEmpty()).append(movePawnsTwoSteps(board.getEmpty())).append(pawnsAttackEast()).append(pawnsAttackWest()).toString();
	}

	private StringBuilder movePawnsOneStep(final long empty) {
		long pawnMoves = (Colour.WHITE.equals(colour) ? ((getPawnBoard() >> 8)) : (getPawnBoard() << 8)) & empty;
		return boardToMoves(0, pawnMoves, colour.getDirection());
	}

	private StringBuilder movePawnsTwoSteps(final long empty) {
		long pawnMoves = (Colour.WHITE.equals(colour) ? (getPawnBoard() >> 16) & RANK_4 & empty >> 8
				: (getPawnBoard() << 16) & RANK_5 & empty << 8) & empty;

		return boardToMoves(0, pawnMoves, 2 * colour.getDirection());
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


	public long getPawnBoard() {
		return pawnBoard;
	}

	public void setPawnBoard(long pawnBoard) {
		this.pawnBoard = pawnBoard;
	}

}
