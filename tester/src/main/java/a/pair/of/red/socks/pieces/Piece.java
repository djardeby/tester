package a.pair.of.red.socks.pieces;

import a.pair.of.red.socks.board.Colour;

public interface Piece {

	String findAllMoves(long empty);
	Colour getColour();
}
