package a.pair.of.red.socks.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardBoard implements Board {
	private static final Logger logger = LoggerFactory.getLogger(StandardBoard.class);
	private static final long RANK_4 = 1095216660480L;
	private static final long RANK_5 = 4278190080L;
	private static final long FILE_A = 72340172838076673L;
	private static final long FILE_H = -9187201950435737472L;
	private final long EMPTY = 0L;
	private final long OCCUPIED = 0L;
	private long whitePawnBoard = 0L;
	private long blackPawnBoard = 0L;
	private boolean whiteToMove = true;

	public String moves() {

		String moves = isWhiteToMove() ? moveWhitePawnsOneStep() : moveBlackPawnsOneStep();
		moves += isWhiteToMove() ? moveWhitePawnsTwoSteps() : moveBlackPawnsTwoSteps();
		moves += isWhiteToMove() ? moveWhitePawnsAttackEast() : moveBlackPawnsAttackEast();
		moves += isWhiteToMove() ? moveWhitePawnsAttackWest() :  moveBlackPawnsAttackWest();
		return moveToAlgebra(moves);
	}

	private String moveBlackPawnsOneStep() {
		String moves = "";
		long EMPTY = getEmpty();
		long pawnMoves = (blackPawnBoard << 8) & EMPTY;
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 - 1) + (index % 8) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	private String moveBlackPawnsTwoSteps() {
		String moves = "";
		long EMPTY = getEmpty();
		long pawnMoves = (blackPawnBoard << 16) & RANK_5 & EMPTY & (EMPTY << 8);
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 - 2) + (index % 8) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	private String moveBlackPawnsAttackEast() {
		String moves = "";
		long pawnMoves = (blackPawnBoard << 9) & getOccupied()&~FILE_H;
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 - 1) + (index % 8-1) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	private String moveBlackPawnsAttackWest() {
		String moves = "";
		long pawnMoves = (blackPawnBoard << 7) & getOccupied()&~FILE_A;
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 - 1) + (index % 8+1) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	private String moveWhitePawnsOneStep() {
		String moves = "";
		long EMPTY = getEmpty();
		long pawnMoves = (whitePawnBoard >> 8) & EMPTY;
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 + 1) + (index % 8) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}


	private String moveWhitePawnsTwoSteps() {
		String moves = "";
		long pawnMoves = (whitePawnBoard >> 16) & RANK_4 & EMPTY & (EMPTY >> 8);
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 + 2) + (index % 8) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	private String moveWhitePawnsAttackEast() {
		String moves = "";
		long pawnMoves = (whitePawnBoard >> 7) & blackPawnBoard&~FILE_A;
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 +1) + (index % 8-1) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	private String moveWhitePawnsAttackWest() {
		String moves = "";
		long pawnMoves = (whitePawnBoard >> 9) & blackPawnBoard&~FILE_H;
		long possibility = pawnMoves & ~(pawnMoves - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			moves += "" + (index / 8 +1) + (index % 8+1) + (index / 8) + (index % 8);
			pawnMoves &= ~possibility;
			possibility = pawnMoves & ~(pawnMoves - 1);
		}
		return moves;
	}

	public String moveToAlgebra(final String move) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < move.length(); i += 4) {
			int start = (Character.getNumericValue(move.charAt(i)) * 8) + Character.getNumericValue(move.charAt(i + 1));
			int end = (Character.getNumericValue(move.charAt(i + 2)) * 8) + Character.getNumericValue(move.charAt(i + 3));
			builder.append((char) ('a' + (start % 8)));
			builder.append((char) ('8' - (start / 8)));
			builder.append((char) ('a' + (end % 8)));
			builder.append((char) ('8' - (end / 8)));
		}
		return builder.toString();
	}

	public long getWhitePawnBoard() {
		return whitePawnBoard;
	}

	public void setWhitePawnBoard(long whitePawnBoard) {
		this.whitePawnBoard = whitePawnBoard;
	}

	public long getBlackPawnBoard() {
		return blackPawnBoard;
	}

	public void setBlackPawnBoard(long blackPawnBoard) {
		this.blackPawnBoard = blackPawnBoard;
	}

	public boolean isWhiteToMove() {
		return whiteToMove;
	}

	public void setWhiteToMove(boolean whiteToMove) {
		this.whiteToMove = whiteToMove;
	}

	private long getEmpty() {
		return ~(getWhitePawnBoard() | getBlackPawnBoard());
	}
	private long getOccupied() {
		return (getWhitePawnBoard() | getBlackPawnBoard());
	}

}
