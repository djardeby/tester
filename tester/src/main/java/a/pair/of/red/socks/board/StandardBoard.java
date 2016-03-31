package a.pair.of.red.socks.board;

import a.pair.of.red.socks.pieces.Knights;
import a.pair.of.red.socks.pieces.Pawns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class StandardBoard implements Board {
	private static final Logger logger = LoggerFactory.getLogger(StandardBoard.class);
	private long EMPTY = 0L;
	private long OCCUPIED = 0L;
	private long whitePawnBoard = 0L;

	private long blackPawnBoard = 0L;

	private long whiteRookBoard = 0L;
	private long blackRookBoard = 0L;
	private long whiteKnightBoard = 0L;
	private long blackKnightBoard = 0L;
	private long whiteBishopBoard = 0L;
	private long blackBishopBoard = 0L;
	private long whiteQueenBoard = 0L;
	private long blackQueenBoard = 0L;
	private long whiteKingBoard = 0L;
	private long blackKingBoard = 0L;
	private boolean whiteToMove = true;
	private boolean hasMoved = true;
	private long blackPieces;
	private long whitePieces;

	public StandardBoard(long... boards) {
		whitePawnBoard = (boards[0]);
		whiteRookBoard = (boards[1]);
		whiteKnightBoard = (boards[2]);
		whiteBishopBoard = (boards[3]);
		whiteQueenBoard = (boards[4]);
		whiteKingBoard = (boards[5]);
		blackPawnBoard = (boards[6]);
		blackRookBoard = (boards[7]);
		blackKnightBoard = (boards[8]);
		blackBishopBoard = (boards[9]);
		blackQueenBoard = (boards[10]);
		blackKingBoard = (boards[11]);
	}

	public String moves() {
		StringBuilder moves = new StringBuilder();
		Pawns whitePawns = new Pawns(Colour.WHITE, whitePawnBoard, getWhitePieces(), getBlackPieces());
		Pawns blackPawns = new Pawns(Colour.BLACK, blackPawnBoard, getBlackPieces(), getWhitePieces());
		Pawns pawns = isWhiteToMove() ? whitePawns : blackPawns;
		Knights knights = new Knights(Colour.WHITE, whiteKnightBoard, getWhitePieces(), getBlackPieces());
		moves.append(pawns.findAllMoves(getEmpty()));
		moves.append(knights.findAllMoves(getEmpty()));
		return moveToAlgebra(moves.toString());
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
			blackPieces = blackPawnBoard | blackRookBoard | blackKnightBoard | blackBishopBoard | blackQueenBoard | blackKingBoard;
		return blackPieces;
	}

	private long getWhitePieces() {
		if (hasMoved)
			whitePieces = whitePawnBoard | whiteRookBoard | whiteKnightBoard | whiteBishopBoard | whiteQueenBoard | whiteKingBoard;
		return whitePieces;
	}

	@Override
	public String toString() {
		String chessBoard[][] = new String[8][8];
		for (int i = 0; i < 64; i++) {
			chessBoard[i / 8][i % 8] = " ";
		}
		for (int i = 0; i < 64; i++) {
			if (((whitePawnBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "P";
			}
			if (((whiteKnightBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "N";
			}
			if (((whiteBishopBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "B";
			}
			if (((whiteRookBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "R";
			}
			if (((whiteQueenBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "Q";
			}
			if (((whiteKingBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "K";
			}
			if (((blackPawnBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "p";
			}
			if (((blackKnightBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "n";
			}
			if (((blackBishopBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "b";
			}
			if (((blackRookBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "r";
			}
			if (((blackQueenBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "q";
			}
			if (((blackKingBoard >> i) & 1) == 1) {
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
		return whiteKnightBoard;
	}

	public void setWhiteKnightBoard(long whiteKnightBoard) {
		this.whiteKnightBoard = whiteKnightBoard;
	}

	public long getBlackKnightBoard() {
		return blackKnightBoard;
	}

	public void setBlackKnightBoard(long blackKnightBoard) {
		this.blackKnightBoard = blackKnightBoard;
	}
}
