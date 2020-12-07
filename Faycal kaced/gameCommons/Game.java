package gameCommons;

import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilisés
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	double startingTime = System.nanoTime();

	/**
	 * 
	 * @param graphic             l'interface graphique
	 * @param width               largeur en cases
	 * @param height              hauteur en cases
	 * @param minSpeedInTimerLoop Vitesse minimale, en nombre de tour de timer avant
	 *                            déplacement
	 * @param defaultDensity      densite de voiture utilisee par defaut pour les
	 *                            routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel est
	 * le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		// TODO
		Case pos = frog.getPosition();
		if (environment.isSafe(pos))
			return false;
		else {
			double endTime = System.nanoTime();
			double timeSpent = (Math.round(endTime - startingTime) / 1000000) / 1000;
			this.graphic.endGameScreen(
					"You've lost... So, so sad. Score : " + (getFrogLane() - 2) + " Time : " + timeSpent + " s");
			return true;
		}

	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié si tel est
	 * le cas
	 * 
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		// TODO
		Case pos = frog.getPosition();
		if (environment.isWinningPosition(pos)) {
			this.graphic.endGameScreen("You've... won? Not bad, I guess");
			return true;
		} else
			return false;
	}

	/**
	 * A function returning the actual lane of the frog
	 * 
	 * @return the frog's ord coordinate
	 */
	public int getFrogLane() {
		return this.frog.getPosition().ord;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getGraphicalPosition(), Color.GREEN));
		testLose();
		// Le testWin() est à uncomment si on joue en mode de finie et pas infini
		// testWin();
	}

}
