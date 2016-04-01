package a.pair.of.red.socks.board;

import a.pair.of.red.socks.pieces.Knights;
import a.pair.of.red.socks.pieces.Pawns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class StandardBoard implements Board {
	private static final Logger logger = LoggerFactory.getLogger(StandardBoard.class);
private static final int whiteIndex=0;
private static final int blackIndex=1;

	private long EMPTY = 0L;
	private long OCCUPIED = 0L;
	private long[] pawnBoard = { 0L, 0L};
	private long[] rookBoard = { 0L, 0L};
	private long[] knightBoard = { 0L, 0L};
	private long[] bishopBoard = { 0L, 0L};
	private long[] queenBoard = { 0L, 0L};
	private long[] kingBoard = { 0L, 0L};
	private boolean whiteToMove = true;
	private boolean hasMoved = true;
	private long blackPieces;
	private long whitePieces;
	private long lastMoveStart;
	private long lastMoveDestination;

	public StandardBoard() {

	}

	public StandardBoard(long... boards) {
		setWhitePawnBoard((boards[0]));
		setWhiteRookBoard((boards[1]));
		setWhiteKnightBoard((boards[2]));
		setWhiteBishopBoard((boards[3]));
		setWhiteQueenBoard((boards[4]));
		setWhiteKingBoard((boards[5]));
		setBlackPawnBoard((boards[6]));
		setBlackRookBoard((boards[7]));
		setBlackKnightBoard((boards[8]));
		setBlackBishopBoard((boards[9]));
		setBlackQueenBoard((boards[10]));
		setBlackKingBoard((boards[11]));
	}

	public String moves() {
		StringBuilder moves = new StringBuilder();
		Pawns whitePawns = new Pawns(Colour.WHITE, getWhitePawnBoard(), getWhitePieces(), getBlackPieces());
		Pawns blackPawns = new Pawns(Colour.BLACK, getBlackPawnBoard(), getBlackPieces(), getWhitePieces());
		Pawns pawns = isWhiteToMove() ? whitePawns : blackPawns;
		Knights whiteKnights = new Knights(Colour.WHITE, getWhiteKnightBoard(), getWhitePieces(), getBlackPieces());
		Knights blackKnights = new Knights(Colour.BLACK, getBlackKnightBoard(), getBlackPieces(), getWhitePieces());
		Knights knights = isWhiteToMove() ? whiteKnights : blackKnights;
		moves.append(pawns.findAllMoves(getEmpty()));
		moves.append(knights.findAllMoves(getEmpty()));
		return moveToAlgebra(moves.toString());
	}

	public void makeMove(String move) {
		lastMoveDestination = algebraToBoard(move.substring(2, 4));
		lastMoveStart = algebraToBoard(move.substring(0, 2));
		boolean successs = whiteToMove ? movePieces(lastMoveStart,whiteIndex) : movePieces(lastMoveStart,blackIndex);
		if (successs)
			whiteToMove = !whiteToMove;

	}

	private boolean movePieces(final long checkAgainst, int colourToMove) {
		if ((pawnBoard[colourToMove] & checkAgainst) != 0L) {
			pawnBoard[colourToMove] ^= lastMoveStart;
			pawnBoard[colourToMove] ^= lastMoveDestination;
		} else if ((rookBoard[colourToMove] & checkAgainst) != 0L) {
			rookBoard[colourToMove] ^=lastMoveStart;
			rookBoard[colourToMove] ^=lastMoveDestination;
		} else if ((knightBoard[colourToMove] & checkAgainst) != 0L) {
			knightBoard[colourToMove] ^= lastMoveStart;
			knightBoard[colourToMove] ^= lastMoveDestination;
		} else if ((bishopBoard[colourToMove] & checkAgainst) != 0L) {
			bishopBoard[colourToMove]^= lastMoveStart;
			bishopBoard[colourToMove]^= lastMoveDestination;
		} else if ((queenBoard[colourToMove] & checkAgainst) != 0L) {
			queenBoard[colourToMove] ^= lastMoveStart;
			queenBoard[colourToMove]^= lastMoveDestination;
		} else if ((kingBoard[colourToMove] & checkAgainst) != 0L) {
			kingBoard[colourToMove] ^= lastMoveStart;
			kingBoard[colourToMove] ^= lastMoveDestination;
		}
		return true;
	}

	public void undoMove() {
		boolean successs = whiteToMove ?  movePieces(lastMoveDestination,blackIndex) : movePieces(lastMoveDestination,whiteIndex);
		if (successs)
			whiteToMove = !whiteToMove;

	}

	private String moveToAlgebra(final String move) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < move.length(); i += 4) {
			int start = (Character.getNumericValue(move.charAt(i + 1)) * 8) + Character.getNumericValue(move.charAt(i));
			int end = (Character.getNumericValue(move.charAt(i + 3)) * 8) + Character.getNumericValue(move.charAt(i + 2));
			builder.append((char) ('a' + (start % 8)));
			builder.append((char) ('8' - (start / 8)));
			builder.append((char) ('a' + (end % 8)));
			builder.append((char) ('8' - (end / 8)));
		}

		return builder.toString();
	}

	private boolean isWhiteToMove() {
		return whiteToMove;
	}

	public void setWhiteToMove(boolean whiteToMove) {
		this.whiteToMove = whiteToMove;
	}

	private long getEmpty() {
		if (hasMoved)
			EMPTY = ~(getWhitePieces() | getBlackPieces());
		return EMPTY;
	}

	private long getOccupied() {
		if (hasMoved)
			OCCUPIED = (getWhitePieces() | getBlackPieces());
		return OCCUPIED;
	}

	private long getBlackPieces() {
		if (hasMoved)
			blackPieces = getBlackPawnBoard() | getBlackRookBoard() | getBlackKnightBoard() | getBlackBishopBoard() | getBlackQueenBoard() | getBlackKingBoard();
		return blackPieces;
	}

	private long getWhitePieces() {
		if (hasMoved)
			whitePieces = getWhitePawnBoard() | getWhiteRookBoard() | getWhiteKnightBoard() | getWhiteBishopBoard() | getWhiteQueenBoard() | getWhiteKingBoard();
		return whitePieces;
	}

	@Override
	public String toString() {
		String chessBoard[][] = new String[8][8];
		for (int i = 0; i < 64; i++) {
			chessBoard[i / 8][i % 8] = " ";
		}
		for (int i = 0; i < 64; i++) {
			if (((getWhitePawnBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "P";
			}
			if (((getWhiteKnightBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "N";
			}
			if (((getWhiteBishopBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "B";
			}
			if (((getWhiteRookBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "R";
			}
			if (((getWhiteQueenBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "Q";
			}
			if (((getWhiteKingBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "K";
			}
			if (((getBlackPawnBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "p";
			}
			if (((getBlackKnightBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "n";
			}
			if (((getBlackBishopBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "b";
			}
			if (((getBlackRookBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "r";
			}
			if (((getBlackQueenBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "q";
			}
			if (((getBlackKingBoard() >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "k";
			}
		}
		String stringRepresentation = "\n";
		for (int i = 0; i < 8; i++) {
			stringRepresentation += Arrays.toString(chessBoard[i]) + "\n";
		}
		return stringRepresentation;
	}

	public long getWhiteKnightBoard() {
		return knightBoard[whiteIndex];
	}

	public void setWhiteKnightBoard(long whiteKnightBoard) {
		this.knightBoard[whiteIndex] = whiteKnightBoard;
	}

	public long getBlackKnightBoard() {
		return knightBoard[blackIndex];
	}

	public void setBlackKnightBoard(long blackKnightBoard) {
		this.knightBoard[blackIndex] = blackKnightBoard;
	}

	private long algebraToBoard(String move) {
		int from = 64 - ((8 - (Character.getNumericValue(move.charAt(0))) % 10) + (8 * (Character.getNumericValue(move.charAt(1)) - 1)));
		return 1L << from;
	}

	public long getWhitePawnBoard() {
		return pawnBoard[whiteIndex];
	}

	public void setWhitePawnBoard(long whitePawnBoard) {
		this.pawnBoard[whiteIndex] = whitePawnBoard;
	}

	public long getBlackPawnBoard() {
		return pawnBoard[blackIndex];
	}

	public void setBlackPawnBoard(long blackPawnBoard) {
		this.pawnBoard[blackIndex] = blackPawnBoard;
	}

	public long getWhiteRookBoard() {
		return rookBoard[whiteIndex];
	}

	public void setWhiteRookBoard(long whiteRookBoard) {
		this.rookBoard[whiteIndex] = whiteRookBoard;
	}

	public long getBlackRookBoard() {
		return rookBoard[blackIndex];
	}

	public void setBlackRookBoard(long blackRookBoard) {
		this.rookBoard[blackIndex] = blackRookBoard;
	}

	public long getWhiteBishopBoard() {
		return bishopBoard[whiteIndex];
	}

	public void setWhiteBishopBoard(long whiteBishopBoard) {
		this.bishopBoard[whiteIndex] = whiteBishopBoard;
	}

	public long getBlackBishopBoard() {
		return bishopBoard[blackIndex];
	}

	public void setBlackBishopBoard(long blackBishopBoard) {
		this.bishopBoard[blackIndex] = blackBishopBoard;
	}

	public long getWhiteQueenBoard() {
		return queenBoard[whiteIndex];
	}

	public void setWhiteQueenBoard(long whiteQueenBoard) {
		this.queenBoard[whiteIndex] = whiteQueenBoard;
	}

	public long getBlackQueenBoard() {
		return queenBoard[blackIndex];
	}

	public void setBlackQueenBoard(long blackQueenBoard) {
		this.queenBoard[blackIndex] = blackQueenBoard;
	}

	public long getWhiteKingBoard() {
		return kingBoard[whiteIndex];
	}

	public void setWhiteKingBoard(long whiteKingBoard) {
		this.kingBoard[whiteIndex] = whiteKingBoard;
	}

	public long getBlackKingBoard() {
		return kingBoard[blackIndex];
	}

	public void setBlackKingBoard(long blackKingBoard) {
		this.kingBoard[blackIndex] = blackKingBoard;
	}
}
