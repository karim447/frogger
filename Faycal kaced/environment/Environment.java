package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

	private ArrayList<Lane> lanes = new ArrayList<>();
	private Game _game;

	public Environment(Game game) {
		// TODO Auto-generated constructor stub
		this._game = game;
		for (int i = 0; i <= _game.height; i++) {
			Lane e = new Lane(game, i, 0.1);
			lanes.add(e);
		}
	}

	@Override
	/**
	 * Return whether the frog's pos is safe or not
	 */
	public boolean isSafe(Case posFrog) {
		// TODO Auto-generated method stub
		int index = posFrog.ord;
		Lane actual = (Lane) lanes.get(index);
		return actual.isSafe(posFrog);
	}

	@Override
	/**
	 * Return whether the frog is in a winning pos or not
	 */
	public boolean isWinningPosition(Case posFrog) {
		// TODO Auto-generated method stub
		int y = posFrog.ord;
		return y >= _game.height - 1;
	}

	@Override
	public void update() {
		for (int i = 0; i < lanes.size(); i++) {
			lanes.get(i).update();
		}
	}

}
