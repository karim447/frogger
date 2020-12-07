package environment;

import java.util.ArrayList;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;

public class LaneInf {
	public static int seed = 1234;
	public static Random GenRdm = new Random(seed);

	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int timer;

	private int oldPos;

	// TODO : Constructeur(s)
	public LaneInf(Game game, int y) {
		this.game = game;
		this.ord = y;
		this.density = 0.01;
		this.speed = 2;
		this.timer = 0;
		this.leftToRight = ((ord * game.randomGen.nextInt()) % 3 == 0);
	}

	public LaneInf(Game game, int y, double density) {
		this.game = game;
		this.ord = y;
		this.density = density;
		this.speed = this.game.minSpeedInTimerLoops + (int) (((GenRdm.nextDouble() * 2) - 1) * 3);
		this.timer = 0;
		this.leftToRight = ((ord * game.randomGen.nextInt()) % 4 == 0);
		this.oldPos = game.getFrogLane();
	}

	public int getOrd() {
		return this.ord;
	}

	// Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
	// d'horloge" égal à leur vitesse
	// Notez que cette méthode est appelée à chaque tic d'horloge

	// Les voitures doivent etre ajoutes a l interface graphique meme quand
	// elle ne bougent pas

	// A chaque tic d'horloge, une voiture peut être ajoutée

	public void update() {
		int frogPos = this.game.getFrogLane();
		int up = this.oldPos - frogPos;
		this.ord += up;

		for (int i = 0; i < cars.size(); i++) {
			Case actPos = cars.get(i).getPos();
			cars.get(i).setPos(new Case(actPos.absc, ord));
		}

		if (ord > 2 || frogPos > 2)
			mayAddCar();

		this.timer++;
		boolean move = (timer % speed) == 0;
		moveCars(move);
		removeOldCars();
		this.oldPos = frogPos;
	}

	/**
	 * A function moving all cars / showing them to the screen
	 * 
	 * @param move : if true move all car and add them to screen, else simply add
	 *             them to screen
	 */
	private void moveCars(boolean move) {
		/*
		 * for (int i = 0; i < cars.size(); i++) { cars.get(i).move(move); }
		 */
		for (Car c : cars)
			c.move(move);
	}

	/**
	 * A function removing cars that are out the game bound
	 */
	private void removeOldCars() {
		for (int i = 0; i < cars.size(); i++) {
			if (!cars.get(i).appearsInBounds())
				cars.remove(i);
		}
	}

	/**
	 * A function telling whether a case is safe or not (whether a car is already
	 * present)
	 * 
	 * @param firstCase the case we want to know whether its safe or not
	 * @return true if the case is safe, else false
	 */
	public boolean isSafe(Case firstCase) {
		for (Car c : cars) {
			if (c.coversCase(firstCase))
				return false;
		}
		return true;
	}

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la densité,
	 * si la première case de la voie est vide
	 */
	private void mayAddCar() {
		Car c = new Car(game, getBeforeFirstCase(), leftToRight);
		boolean addCar = false;
		if (leftToRight) {
			for (int i = 0; i <= c.getLength(); i++) {
				Case carParts = new Case(getBeforeFirstCase().absc + i, getBeforeFirstCase().ord);
				if (isSafe(carParts)) {
					addCar = true;
				} else {
					addCar = false;
				}
			}
		} else
			addCar = isSafe(getBeforeFirstCase()) && isSafe(getFirstCase());

		if (addCar) {
			// if(Math.random() < density) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(c);
			}
		}
	}

	/**
	 * A function returning the first case of the lane depending of its dir
	 * 
	 * @return the first case of the lane
	 */
	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	/**
	 * A function returning the case before the first case of the lane once again
	 * depending on the direction of the lane
	 * 
	 * @return the case before the first case of the lane
	 */
	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-2, ord);
		} else
			return new Case(game.width, ord);
	}

}
