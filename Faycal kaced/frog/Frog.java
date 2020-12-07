package frog;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import util.Direction;

public class Frog implements IFrog {

	private Case _postion;
	private Direction _dir;
	private Game _game;

	public Frog(Game game) {
		// TODO Auto-generated constructor stub
		this._game = game;
		this._postion = new Case(this._game.width / 2, 0);
		this._dir = null;
	}

	public Case getPosition() {
		return this._postion;
	}

	public Direction getDirection() {
		return this._dir;

	}

	public void move(Direction key) {
		this._dir = key;

		int x = this._postion.absc;
		int y = this._postion.ord;

		if (key == Direction.right && x < this._game.width - 1) {
			x++;
		}
		if (key == Direction.left && x > 0) {
			x--;
		}
		if (key == Direction.up && y < this._game.height) {
			y++;
		}
		if (key == Direction.down && y > 0) {
			y--;
		}
		this._postion = new Case(x, y);
	}

	@Override
	public Case getGraphicalPosition() {
		return this._postion;
	}

}
