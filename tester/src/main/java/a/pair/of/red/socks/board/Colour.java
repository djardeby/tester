package a.pair.of.red.socks.board;

/**
 * Created by djardeby on 2016-03-30.
 */
public enum Colour {
	BLACK(-1),WHITE(1);

	private final int direction;

	Colour(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}
}
