package a.pair.of.red.socks.board;

public interface Board {
	String moves();

	void makeMove(String move);

	void undoMove();
}
