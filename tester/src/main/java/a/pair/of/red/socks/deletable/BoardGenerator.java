package a.pair.of.red.socks.deletable;


import a.pair.of.red.socks.board.Board;
import a.pair.of.red.socks.board.StandardBoard;

/**
 * Created by djardeby on 2016-03-25.
 */
public class BoardGenerator {
	private static StandardBoard board;

	/*
		* PIECE=WHITE/black
		* pawn=P/p
		* kinght (horse)=N/n
		* bishop=B/b
		* rook (castle)=R/r
		* Queen=Q/q
		* King=K/k
		*/
	public BoardGenerator() {
		this.board = new StandardBoard();
	}
		public Board initiateStandardChess() {
			long WP=0L,WN=0L,WB=0L,WR=0L,WQ=0L,WK=0L,BP=0L,BN=0L,BB=0L,BR=0L,BQ=0L,BK=0L;
			String chessBoard[][]={
					{"r","n","b","q","k","b","n","r"},
					{"p","p","p","p","p","p","p","p"},
					{" "," "," "," "," "," "," "," "},
					{" "," "," "," "," "," "," "," "},
					{" "," "," "," "," "," "," "," "},
					{" "," "," "," "," "," "," "," "},
					{"P","P","P","P","P","P","P","P"},
					{"R","N","B","Q","K","B","N","R"}};
			return arrayToBitboards(chessBoard,WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK);
		}
		public  void initiateChess960() {
			long WP=0L,WN=0L,WB=0L,WR=0L,WQ=0L,WK=0L,BP=0L,BN=0L,BB=0L,BR=0L,BQ=0L,BK=0L;
			String chessBoard[][]={
					{" "," "," "," "," "," "," "," "},
					{"p","p","p","p","p","p","p","p"},
					{" "," "," "," "," "," "," "," "},
					{" "," "," "," "," "," "," "," "},
					{" "," "," "," "," "," "," "," "},
					{" "," "," "," "," "," "," "," "},
					{"P","P","P","P","P","P","P","P"},
					{" "," "," "," "," "," "," "," "}};
			//step 1:
			int random1=(int)(Math.random()*8);
			chessBoard[0][random1]="b";
			chessBoard[7][random1]="B";
			//step 2:
			int random2=(int)(Math.random()*8);
			while (random2%2==random1%2) {
				random2=(int)(Math.random()*8);
			}
			chessBoard[0][random2]="b";
			chessBoard[7][random2]="B";
			//step 3:
			int random3=(int)(Math.random()*8);
			while (random3==random1 || random3==random2) {
				random3=(int)(Math.random()*8);
			}
			chessBoard[0][random3]="q";
			chessBoard[7][random3]="Q";
			//step 4:
			int random4a=(int)(Math.random()*5);
			int counter=0;
			int loop=0;
			while (counter-1<random4a) {
				if (" ".equals(chessBoard[0][loop])) {counter++;}
				loop++;
			}
			chessBoard[0][loop-1]="n";
			chessBoard[7][loop-1]="N";
			int random4b=(int)(Math.random()*4);
			counter=0;
			loop=0;
			while (counter-1<random4b) {
				if (" ".equals(chessBoard[0][loop])) {counter++;}
				loop++;
			}
			chessBoard[0][loop-1]="n";
			chessBoard[7][loop-1]="N";
			//step 5:
			counter=0;
			while (!" ".equals(chessBoard[0][counter])) {
				counter++;
			}
			chessBoard[0][counter]="r";
			chessBoard[7][counter]="R";
			while (!" ".equals(chessBoard[0][counter])) {
				counter++;
			}
			chessBoard[0][counter]="k";
			chessBoard[7][counter]="K";
			while (!" ".equals(chessBoard[0][counter])) {
				counter++;
			}
			chessBoard[0][counter]="r";
			chessBoard[7][counter]="R";
			arrayToBitboards(chessBoard,WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK);
		}
		public  static StandardBoard importFEN(String fenString) {
			board=new StandardBoard();
			//not chess960 compatible
//			board.WP=0; board.WN=0; board.WB=0;
//			board.WR=0; board.WQ=0; board.WK=0;
//			board.BP=0; board.BN=0; board.BB=0;
//			board.BR=0; board.BQ=0; board.BK=0;
//			board.CWK=false; board.CWQ=false;
//			board.CBK=false; board.CBQ=false;
			int charIndex = 0;
			int boardIndex = 0;
			while (fenString.charAt(charIndex) != ' ')
			{
				switch (fenString.charAt(charIndex++))
				{
					case 'P':
						board.setWhitePawnBoard( board.getWhitePawnBoard() | (1L << boardIndex++));
						break;
					case 'p':
						board.setBlackPawnBoard(board.getBlackPawnBoard()|(1L << boardIndex++));
						break;
					case 'N':
						board.setWhiteKnightBoard(board.getWhiteKnightBoard()|(1L << boardIndex++));
						break;
					case 'n':
						board.setBlackKnightBoard(board.getBlackKnightBoard()|(1L << boardIndex++));
						break;
					case 'B':
						board.setWhiteBishopBoard(board.getWhiteBishopBoard()|(1L << boardIndex++));
						break;
					case 'b':
						board.setBlackBishopBoard(board.getBlackBishopBoard()|(1L << boardIndex++));
						break;
					case 'R':
						board.setWhiteRookBoard(board.getWhiteRookBoard()|(1L << boardIndex++));
						break;
					case 'r':
						board.setBlackRookBoard(board.getBlackRookBoard()|(1L << boardIndex++));
						break;
					case 'Q':
						board.setWhiteQueenBoard(board.getWhiteQueenBoard()|(1L << boardIndex++));
						break;
					case 'q':
						board.setBlackQueenBoard(board.getBlackQueenBoard()|(1L << boardIndex++));
						break;
					case 'K':
						board.setWhiteKingBoard(board.getWhiteKingBoard()|(1L << boardIndex++));
						break;
					case 'k':
						board.setBlackKingBoard(board.getBlackKingBoard()|(1L << boardIndex++));
						break;
					case '/':
						break;
					case '1': boardIndex++;
						break;
					case '2': boardIndex += 2;
						break;
					case '3': boardIndex += 3;
						break;
					case '4': boardIndex += 4;
						break;
					case '5': boardIndex += 5;
						break;
					case '6': boardIndex += 6;
						break;
					case '7': boardIndex += 7;
						break;
					case '8': boardIndex += 8;
						break;
					default:
						break;
				}
			}
			board.setWhiteToMove(fenString.charAt(++charIndex) == 'w');
			charIndex += 2;
			while (fenString.charAt(charIndex) != ' ')
			{
				switch (fenString.charAt(charIndex++))
				{
					/*					case '-':
						break;
					case 'K': board.CWK = true;
						break;
					case 'Q': board.CWQ = true;
						break;
					case 'k': board.CBK = true;
						break;
					case 'q': board.CBQ = true;
						break;*/
					default:
						break;
				}
			}
			if (fenString.charAt(++charIndex) != '-')
			{
				//board.EP = Moves.FileMasks8[fenString.charAt(charIndex++) - 'a'];
			}
			//the rest of the fenString is not yet utilized
			return board;
		}
		public Board arrayToBitboards(String[][] chessBoard, long WP, long WN, long WB, long WR, long WQ, long WK, long BP, long BN, long BB, long BR, long BQ, long BK) {
			String Binary;
			for (int i=0;i<64;i++) {
				Binary="0000000000000000000000000000000000000000000000000000000000000000";
				Binary=Binary.substring(i+1)+"1"+Binary.substring(0, i);
				switch (chessBoard[i/8][i%8]) {
					case "P": WP+=convertStringToBitboard(Binary);
						break;
					case "N": WN+=convertStringToBitboard(Binary);
						break;
					case "B": WB+=convertStringToBitboard(Binary);
						break;
					case "R": WR+=convertStringToBitboard(Binary);
						break;
					case "Q": WQ+=convertStringToBitboard(Binary);
						break;
					case "K": WK+=convertStringToBitboard(Binary);
						break;
					case "p": BP+=convertStringToBitboard(Binary);
						break;
					case "n": BN+=convertStringToBitboard(Binary);
						break;
					case "b": BB+=convertStringToBitboard(Binary);
						break;
					case "r": BR+=convertStringToBitboard(Binary);
						break;
					case "q": BQ+=convertStringToBitboard(Binary);
						break;
					case "k": BK+=convertStringToBitboard(Binary);
						break;
				}
			}

//			board.WP=WP; board.WN=WN; board.WB=WB;
//			board.WR=WR; board.WQ=WQ; board.WK=WK;
//			board.BP=BP; board.BN=BN; board.BB=BB;
//			board.BR=BR; board.BQ=BQ; board.BK=BK;
			return board;
		}
		public  long convertStringToBitboard(String Binary) {
			if (Binary.charAt(0)=='0') {//not going to be a negative number
				return Long.parseLong(Binary, 2);
			} else {
				return Long.parseLong("1"+Binary.substring(2), 2)*2;
			}
		}
	public static long destinationBoard(String move) {
		int possition=(Character.getNumericValue(move.charAt(2))*8)+(Character.getNumericValue(move.charAt(3)));
		long destination = 1L << possition;
		return destination;
	}
	public static long startingBoard(String move) {
		int from=(Character.getNumericValue(move.charAt(0))*8)+(Character.getNumericValue(move.charAt(1)));
		long destination = 1L << from;
		return destination;
	}
}
