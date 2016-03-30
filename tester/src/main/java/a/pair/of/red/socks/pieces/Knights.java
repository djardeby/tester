package a.pair.of.red.socks.pieces;


import a.pair.of.red.socks.board.Colour;

public class Knights extends Piece {
	private final Colour colour;
	private long knightsBoard = 0L;

	public Knights(Colour colour,final long knightsBoard) {
		this.colour = colour;
		this.knightsBoard = knightsBoard;
	}

	public String findAllMoves(long empty) {
		return moveSouthEast().toString();
	}

	private StringBuilder moveSouthEast() {
		StringBuilder moves = new StringBuilder();
long knightsMoves = knightsBoard >> 18;
		return boardToMoves(1,knightsMoves,-2);
	}

	public Colour getColour() {
		return colour;
	}
}
