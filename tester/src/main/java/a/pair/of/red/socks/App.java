package a.pair.of.red.socks;

import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.BoardType;
import a.pair.of.red.socks.factory.BoardFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) {
		logger.debug("args: {}",args);
		BoardFactory factory = new BoardFactory();
		Board board = factory.initBoard(BoardType.StandardBoard);
    }
}
