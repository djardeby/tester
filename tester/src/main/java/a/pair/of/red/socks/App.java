package a.pair.of.red.socks;

import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.BoardType;
import a.pair.of.red.socks.factory.BoardFactory;

public class App
{
    public static void main( String[] args )
    {
		BoardFactory factory = new BoardFactory();
		Board board = factory.initBoard(BoardType.StandardBoard);
    }
}
