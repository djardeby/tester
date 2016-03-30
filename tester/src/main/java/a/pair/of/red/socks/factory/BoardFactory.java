package a.pair.of.red.socks.factory;

import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.BoardType;
import a.pair.of.red.socks.board.StandardBoard;

/**
 * Created by nrkdja on 2016-03-30.
 */
public class BoardFactory {
	public Board initBoard(BoardType type) {
		if (BoardType.StandardBoard.equals(type)) {
			return new StandardBoard();
		}
		return null;
	}
}
