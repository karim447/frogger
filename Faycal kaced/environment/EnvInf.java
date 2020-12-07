package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class EnvInf implements IEnvironment {
	private ArrayList<LaneInf> lanes = new ArrayList<>();
	private Game _game;

	// Class constructor
	public EnvInf(Game game) {
		// TODO Auto-generated constructor stub
		this._game = game;
		for (int i = 0; i <= _game.height; i++) {
			LaneInf e = new LaneInf(game, i, 0.1);
			lanes.add(e);
		}
	}

	/**
	 * Return whether the frog's pos is safe or not
	 */
	public boolean isSafe(Case posFrog) {
		int index = posFrog.ord;
		LaneInf actual = (LaneInf) lanes.get(index);
		return actual.isSafe(posFrog);
	}

	/**
	 * Return whether the frog is in a winning pos or not
	 */
	public boolean isWinningPosition(Case posFrog) {
		// TODO Auto-generated method stub
		int y = posFrog.ord;
		return y >= _game.height - 1;
	}

	public void update() {
		if (lanes.get(lanes.size() - 1).getOrd() < _game.height)
			lanes.add(new LaneInf(_game, _game.height, 0.2));
		for (LaneInf l : lanes)
			l.update();
	}
}
