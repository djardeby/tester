package a.pair.of.red.socks.board;

import a.pair.of.red.socks.pieces.*;
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
	private long[] queensBoard = { 0L, 0L};
	private long[] kingBoard = { 0L, 0L};
	private boolean whiteToMove = true;
	private boolean hasMoved = true;
	private long blackPieces;
	public long whitePieces;
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
		Kings whiteKings = getWhiteKing();
		Kings blackKings = getBlackKing();
		Kings kings = isWhiteToMove()?whiteKings:blackKings;
		moves.append(kings.findAllMoves(this));
		Pawns whitePawns = new Pawns(Colour.WHITE, getWhitePawnBoard(), getWhitePieces(), getBlackPieces());
		Pawns blackPawns = new Pawns(Colour.BLACK, getBlackPawnBoard(), getBlackPieces(), getWhitePieces());
		Pawns pawns = isWhiteToMove() ? whitePawns : blackPawns;
		moves.append(pawns.findAllMoves(this));
		Knights whiteKnights = new Knights(Colour.WHITE, getWhiteKnightBoard(), getWhitePieces(), getBlackPieces());
		Knights blackKnights = new Knights(Colour.BLACK, getBlackKnightBoard(), getBlackPieces(), getWhitePieces());
		Knights knights = isWhiteToMove() ? whiteKnights : blackKnights;
		moves.append(knights.findAllMoves(this));
		Rooks whiteRooks = new Rooks(Colour.WHITE,rookBoard[whiteIndex],getWhitePieces(),getBlackPieces());
		Rooks blackRooks = new Rooks(Colour.BLACK,rookBoard[blackIndex],getBlackPieces(),getWhitePieces());
		Rooks rooks = isWhiteToMove()?whiteRooks:blackRooks;
		moves.append(rooks.findAllMoves(this));
		Bishops whiteBishops = new Bishops(Colour.WHITE,bishopBoard[whiteIndex],getWhitePieces(),getBlackPieces());
		Bishops blackBishops = new Bishops(Colour.BLACK,bishopBoard[blackIndex],getBlackPieces(),getWhitePieces());
		Bishops bishops = isWhiteToMove()?whiteBishops:blackBishops;
		moves.append(bishops.findAllMoves(this));
		Queens whiteQueens = new Queens(Colour.WHITE,queensBoard[whiteIndex],getWhitePieces(),getBlackPieces());
		Queens blackQueens = new Queens(Colour.BLACK,queensBoard[blackIndex],getBlackPieces(),getWhitePieces());
		Queens queens = isWhiteToMove()?whiteQueens:blackQueens;
		moves.append(queens.findAllMoves(this));
String legalMoves = legalMoves(moveToAlgebra(moves.toString()));
		return legalMoves;
	}

	private String legalMoves(String moves) {
		String legal="";
		for (int i = 0; i < moves.length(); i+=4) {
			String move = moves.substring(i, i + 4);
//			lastMoveStart = algebraToBoard(startSquare);
//			String destinationSquare = moves.substring(i + 2, i + 4);
			logger.debug("move: {}",move);
//			logger.debug("destinationSquare: {}",destinationSquare);
//			lastMoveDestination = algebraToBoard(destinationSquare);
			long tmpDestination = lastMoveDestination;
			long tmpStart = lastMoveStart;
			makeMove(move);
			if(move.equals("h4g4"))
			logger.debug("toString(): {}",toString());
			long king = kingBoard[isWhiteToMove() ? blackIndex:whiteIndex];
			long unsafe = !isWhiteToMove()?getWhiteKing().unsafeForWhite(this):getBlackKing().unsafeForBlack(this);
//			logger.debug("move: {}",move);
			if ((king & unsafe)==0L) {
				logger.debug("move: {}",move);
				logger.debug("king: {}",king);
				logger.debug("unsafe: {}",unsafe);


				legal+= move;
			} else {
				logger.debug("king: {}",king);
				logger.debug("unsafe: {}",unsafe);

			}
			undoMove();
			lastMoveDestination= tmpDestination;
			lastMoveStart=tmpStart ;

		}
		return legal;
	}

	public void makeMove(String move) {


			lastMoveDestination = algebraToBoard(move.substring(2, 4));
		lastMoveStart = algebraToBoard(move.substring(0, 2));
		boolean successs = whiteToMove ? movePieces(lastMoveStart,whiteIndex) : movePieces(lastMoveStart,blackIndex);
		if (successs)
			whiteToMove = !whiteToMove;
		if("b4f4".equals(move)) {
			logger.debug("algebraToBoard(move.substring(2, 4)): {}",algebraToBoard(move.substring(2, 4)));
			logger.debug("algebraToBoard(move.substring(0, 2)): {}",algebraToBoard(move.substring(0, 2)));
			logger.debug("rookBoard: {}",rookBoard[whiteIndex]);
		}
	}

/*	private boolean capture() {
		if ((pawnBoard[blackIndex] & lastMoveDestination) != 0L) {
//			pawnBoard[colourToMove] ^= lastMoveStart;
			pawnBoard[blackIndex] ^= lastMoveDestination;
		}
		return true;
	}*/

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
		} else if ((queensBoard[colourToMove] & checkAgainst) != 0L) {
			queensBoard[colourToMove] ^= lastMoveStart;
			queensBoard[colourToMove]^= lastMoveDestination;
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

	protected boolean isWhiteToMove() {
		return whiteToMove;
	}

	public void setWhiteToMove(boolean whiteToMove) {
		this.whiteToMove = whiteToMove;
	}

	public long getEmpty() {
//		if (hasMoved)
			EMPTY = ~(getWhitePieces() | getBlackPieces());
		return EMPTY;
	}

	public long getOccupied() {
//		if (hasMoved)
			OCCUPIED = (getWhitePieces() | getBlackPieces());
		return OCCUPIED;
	}

	public long getBlackPieces() {
		if (hasMoved)
			blackPieces = getBlackPawnBoard() | getBlackRookBoard() | getBlackKnightBoard() | getBlackBishopBoard() | getBlackQueenBoard() | getBlackKingBoard();
		return blackPieces;
	}

	public long getWhitePieces() {
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
		return queensBoard[whiteIndex];
	}

	public void setWhiteQueenBoard(long whiteQueenBoard) {
		this.queensBoard[whiteIndex] = whiteQueenBoard;
	}

	public long getBlackQueenBoard() {
		return queensBoard[blackIndex];
	}

	public void setBlackQueenBoard(long blackQueenBoard) {
		this.queensBoard[blackIndex] = blackQueenBoard;
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

	public Kings getWhiteKing() {
		return new Kings(Colour.WHITE,kingBoard[whiteIndex],getWhitePieces(),getBlackPieces());
	}
	public Kings getBlackKing() {
		return new Kings(Colour.BLACK,kingBoard[blackIndex],getBlackPieces(),getWhitePieces());
	}
}
