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
	private long whiteKnightBoard = 0L;
	private long blackKnightBoard = 0L;
	private boolean whiteToMove = true;
	private boolean hasMoved = true;
	private long blackPieces;
	private final Pawns whitePawns = new Pawns(Colour.WHITE, whitePawnBoard, 0L, 0L);
	private long whitePieces;
	private final Pawns blackPawns = new Pawns(Colour.BLACK, blackPawnBoard, 0L, 0L);

	public StandardBoard(long... boards) {
		setWhitePawnBoard(boards[0]);
		setBlackPawnBoard(boards[1]);
		setWhiteKnightBoard(boards[2]);
		setBlackKnightBoard(boards[3]);
	}

	public String moves() {
		StringBuilder moves = new StringBuilder();
		Pawns pawns = isWhiteToMove() ? whitePawns : blackPawns;
		Knights knights = new Knights(Colour.WHITE,whiteKnightBoard,0L,0L);
		moves.append(pawns.findAllMoves(getEmpty()));
		moves.append(knights.findAllMoves(getEmpty()));
		logger.debug("Moves: {}", moves.toString());
		return moveToAlgebra(moves.toString());
	}



	public String moveToAlgebra(final String move) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < move.length(); i += 4) {
			int start = (Character.getNumericValue(move.charAt(i+1)) * 8) + Character.getNumericValue(move.charAt(i));
			int end = (Character.getNumericValue(move.charAt(i + 3)) * 8) + Character.getNumericValue(move.charAt(i + 2));
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
		whitePawns.setPawnBoard(whitePawnBoard);
		whitePawns.setOtherPieces(getBlackPieces());
		blackPawns.setOtherPieces(getWhitePieces());
	}

	public long getBlackPawnBoard() {
		return blackPawnBoard;
	}

	public void setBlackPawnBoard(long blackPawnBoard) {
		this.blackPawnBoard = blackPawnBoard;
		blackPawns.setPawnBoard(blackPawnBoard);
		blackPawns.setOtherPieces(getWhitePieces());
		whitePawns.setOtherPieces(getBlackPieces());
	}

	public boolean isWhiteToMove() {
		return whiteToMove;
	}

	public void setWhiteToMove(boolean whiteToMove) {
		this.whiteToMove = whiteToMove;
	}

	private long getEmpty() {
		if (hasMoved)
			EMPTY = ~(getWhitePawnBoard() | getBlackPawnBoard());
		return EMPTY;
	}

	private long getOccupied() {
		if (hasMoved)
			OCCUPIED = (getWhitePieces() | getBlackPieces());
		return OCCUPIED;
	}

	public long getBlackPieces() {
		if (hasMoved)
			blackPieces = blackPawnBoard;
		return blackPieces;
	}

	public long getWhitePieces() {
		if (hasMoved)
			whitePieces = whitePawnBoard;
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
/*			if (((WB >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "B";
			}
			if (((WR >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "R";
			}
			if (((WQ >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "Q";
			}
			if (((WK >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "K";
			}*/
			if (((blackPawnBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "p";
			}
			if (((blackKnightBoard >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "n";
			}
/*			if (((BB >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "b";
			}
			if (((BR >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "r";
			}
			if (((BQ >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "q";
			}
			if (((BK >> i) & 1) == 1) {
				chessBoard[i / 8][i % 8] = "k";
			}*/
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
