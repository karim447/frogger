package frog;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import util.Direction;

public class FrogInf implements IFrog {

	private Case pos;
	private Direction dir;
	private Game game;

	public FrogInf(Game game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.pos = new Case(this.game.width / 2, 2);
		this.dir = null;
	}

	@Override
	public Case getGraphicalPosition() {
		Case c = new Case(this.pos.absc, 2);
		return c;
	}

	@Override
	public Case getPosition() {
		return this.pos;
	}

	@Override
	public Direction getDirection() {
		return this.dir;

	}

	@Override
	public void move(Direction key) {
		this.dir = key;

		int x = this.pos.absc;
		int y = this.pos.ord;

		if (key == Direction.right && x < this.game.width - 1) {
			x++;
		}
		if (key == Direction.left && x > 0) {
			x--;
		}
		if (key == Direction.up) {
			y++;
		}
		if (key == Direction.down) {
			y--;
		}
		this.pos = new Case(x, y);
	}

}
