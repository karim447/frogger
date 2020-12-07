package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game _game;
	private Case _leftPosition;
	private boolean _leftToRight;
	private int _length;
	private final Color _colorLtR = Color.BLACK;
	private final Color _colorRtL = Color.BLUE;

	// Constructor
	public Car(Game game, Case beforeFirstCase, boolean leftToRight) {
		// TODO Auto-generated constructor stub
		this._game = game;
		this._leftToRight = leftToRight;
		this._leftPosition = beforeFirstCase;
		this._length = (int) (Math.random() * 3);
	}

	/**
	 * A getter returning the leftmost position of the car
	 * 
	 * @return
	 */
	public Case getPos() {
		return this._leftPosition;
	}

	/**
	 * 
	 * @param newPos
	 */
	public void setPos(Case newPos) {
		this._leftPosition = newPos;
	}

	// TODO : ajout de methodes

	/**
	 * A function adding car to the screen / moving it
	 * 
	 * @param move : a boolean value indicating whether the car has to be moved
	 */
	public void move(boolean move) {
		if (move) {
			int y = _leftPosition.ord;
			int x = _leftPosition.absc;
			if (this._leftToRight) {
				x++;
				this._leftPosition = new Case(x, y);
			} else {
				x--;
				this._leftPosition = new Case(x, y);
			}
		}
		addToGraphics();
	}

	/**
	 * 
	 */
	public void move() {
		int y = _leftPosition.ord;
		int x = _leftPosition.absc;
		if (this._leftToRight) {
			x++;
			this._leftPosition = new Case(x, y);
		} else {
			x--;
			this._leftPosition = new Case(x, y);
		}
		addToGraphics();
	}

	/*
	 * Fourni : addToGraphics() permettant d'ajouter un element graphique
	 * correspondant a la voiture
	 */
	private void addToGraphics() {
		for (int i = 0; i < _length; i++) {
			Color color = _colorRtL;
			if (this._leftToRight) {
				color = _colorLtR;
			}
			_game.getGraphic().add(new Element(_leftPosition.absc + i, _leftPosition.ord, color));
		}
	}

	/**
	 * A function indicating if the considered car(instance) covers a given case For
	 * this we see if any parts of the car is set in the given case We assume that
	 * the given case is on the right lane (the y coordinate of the cars correspond
	 * to the lane ord attribute)
	 * 
	 * @param c : the considered case
	 * @return true if the car covers the case, false otherwise
	 */
	public boolean coversCase(Case c) {
		int x = _leftPosition.absc;
		for (int i = 0; i < _length; i++) {
			x += i;
			if (x == c.absc)
				return true;
		}
		return false;
	}

	/**
	 * A function indicating whether the considered cars(instance) appears in the
	 * bounds For this we see if any part of the car appears in the bounds
	 * 
	 * @return true if the car appears in the board or else false
	 */
	public boolean appearsInBounds() {
		int x = _leftPosition.absc;
		for (int i = 0; i <= _length; i++) {
			if (x >= -1 && x <= _game.width)
				return true;
			x++;
		}
		return false;
	}

	/**
	 * A function returning the car length
	 * 
	 * @return the car length
	 */
	public int getLength() {
		return this._length;
	}
}
