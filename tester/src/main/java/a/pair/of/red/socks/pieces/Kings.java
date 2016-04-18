package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;
import a.pair.of.red.socks.board.StandardBoard;
import a.pair.of.red.socks.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static a.pair.of.red.socks.utils.Constants.FILE_A;
import static a.pair.of.red.socks.utils.Constants.FILE_H;

/**
 * Created by nrkdja on 2016-04-12.
 */
public class Kings extends SlidingPieces {
private static final Logger logger = LoggerFactory.getLogger(Kings.class);
	private long kingBoard;

	public Kings(Colour colour, long kingBoard, long ownPieces, long otherPieces) {
		super(colour, ownPieces, otherPieces);
		this.kingBoard = kingBoard;
	}

	public String findAllMoves(StandardBoard board) {
		long tmpBoard = kingBoard;
		long moveBoard = ((tmpBoard & ~FILE_H) << 1) | ((tmpBoard & ~FILE_A) >> 1);
		tmpBoard |= moveBoard;
		moveBoard |= (tmpBoard >> 8) | (tmpBoard << 8);
		long attacks = moveBoard & ~getOwnPieces() & (Colour.WHITE.equals(colour)?~attackedByWhite(board):~attackedByBlack(board));
		int index = Long.numberOfTrailingZeros(kingBoard);
		int startFile = index%8;
		int startRank = index/8;
		String startSquare = "" + startFile + startRank;
		return newBoardToMoves(attacks,startSquare ).toString();
	}

	public long attackedByBlack(StandardBoard board) {
		long unsafe = ((board.getWhitePawnBoard() >>> 7) & ~FILE_A);
		unsafe |= ((board.getWhitePawnBoard() >>> 9) & ~FILE_H);
		long tmpBoard = board.getWhiteBishopBoard()|board.getWhiteQueenBoard();
		long possibility = tmpBoard & ~(tmpBoard - 1);
		long blackPieces = board.getWhitePieces()|board.getBlackPieces();
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			unsafe |= lineAttacks(blackPieces, 2, index, getOtherPieces());
			unsafe |= lineAttacks(blackPieces, 3, index, getOtherPieces());
			tmpBoard &= ~possibility;
			possibility = tmpBoard & ~(tmpBoard - 1);
		}
		tmpBoard = board.getWhiteRookBoard()|board.getWhiteQueenBoard();
		possibility = tmpBoard & ~(tmpBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			unsafe |= lineAttacks(blackPieces, 0, index, getOtherPieces());
			unsafe |= lineAttacks(blackPieces, 1, index, getOtherPieces());
			tmpBoard &= ~possibility;
			possibility = tmpBoard & ~(tmpBoard - 1);
		}
		
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_H | Constants.RANK_78)) >>> 15 ;
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_GH | Constants.RANK_8)) >>> 6 ;
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_GH | Constants.RANK_1)) << 10 ;
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_H | Constants.RANK_12)) << 17 ;
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_A | Constants.RANK_12)) << 15 ;
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_AB | Constants.RANK_1)) << 6 ;
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_AB | Constants.RANK_8)) >>> 10 ;
		unsafe|=(board.getWhiteKnightBoard() & ~(Constants.FILE_A | Constants.RANK_78)) >>> 17 ;
		
		return unsafe;
	}

	public long attackedByWhite(StandardBoard board) {
		long unsafe = ((board.getBlackPawnBoard() << 9) & ~FILE_A);
		unsafe |= ((board.getBlackPawnBoard() << 7) & ~FILE_H);
		long tmpBoard = board.getBlackBishopBoard()|board.getBlackQueenBoard();
		long possibility = tmpBoard & ~(tmpBoard - 1);
		long whitePieces = board.getWhitePieces()|board.getBlackPieces();
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			unsafe |= lineAttacks(whitePieces, 2, index, getOtherPieces());
			unsafe |= lineAttacks(whitePieces, 3, index, getOtherPieces());
			tmpBoard &= ~possibility;
			possibility = tmpBoard & ~(tmpBoard - 1);
		}
		tmpBoard = board.getBlackRookBoard()|board.getBlackQueenBoard();
		possibility = tmpBoard & ~(tmpBoard - 1);
		while (possibility != 0) {
			int index = Long.numberOfTrailingZeros(possibility);
			unsafe |= lineAttacks(whitePieces, 0, index, getOtherPieces());
			unsafe |= lineAttacks(whitePieces, 1, index, getOtherPieces());
			tmpBoard &= ~possibility;
			possibility = tmpBoard & ~(tmpBoard - 1);
		}

		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_H | Constants.RANK_78)) >>> 15 ;
		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_GH | Constants.RANK_8)) >>> 6 ;
		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_GH | Constants.RANK_1)) << 10 ;
		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_H | Constants.RANK_12)) << 17 ;
		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_A | Constants.RANK_12)) << 15 ;
		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_AB | Constants.RANK_1)) << 6 ;
		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_AB | Constants.RANK_8)) >>> 10 ;
		unsafe|=(board.getBlackKnightBoard() & ~(Constants.FILE_A | Constants.RANK_78)) >>> 17 ;
		return unsafe;
	}



}
