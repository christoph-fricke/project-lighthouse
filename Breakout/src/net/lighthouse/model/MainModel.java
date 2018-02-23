package net.lighthouse.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * the main Model holding all blocks, balls the paddle and all effects.
 * 
 * @author finite
 *
 */

// TODO: there's a bunch of methods missing right now. The question is wether or
// not every add/remove/move/changestuff needs its own method in here. I would
// argue not. as long as you have a way to get the object you want you can just
// use native object methods.

public class MainModel {
	private ArrayList<BBlock> blocks;
	// We'll definitly one have one paddle so this is not an ArrayList.
	private BPaddle paddle;
	// IDK, maybe one day we'll have a gamemode/powerup where we'll have multiple
	// BBalls.
	private ArrayList<BBall> balls;
	private ArrayList<multiframe> effects;

	/**
	 * Creates a Model with all custom data. but no starting explosions :(
	 * 
	 * @param paddle
	 *            the paddle.
	 * @param ball
	 *            the ball.
	 * @param blocks
	 *            the blocks.
	 */
	public MainModel(BPaddle paddle, BBall ball, ArrayList<BBlock> blocks) {
		this.paddle = paddle;
		balls.add(ball);
		this.blocks = blocks;
	}

	/**
	 * Creates a MainModel with preconfigured Blocks, paddle and blocks.
	 */
	public MainModel() {
		int[] atm = { 1, 1 };
		balls.add(new BBall(7, 14, Color.BLUE, 1, atm));
		paddle = new BPaddle(7, 27, Color.WHITE, 1);
		blocks = new ArrayList<BBlock>();
	}

	public void addExplosion(int x, int y, Color color) {
		effects.add(new BExplosion(x, y, color, 1));

	}

	/**
	 * adds an object to the model.
	 * 
	 * @param object
	 *            the object to add.
	 */
	public void addObject(BObject object) {
		if (object instanceof BBall) {
			balls.add((BBall) object);

		} else if (object instanceof BBlock) {
			blocks.add((BBlock) object);

		} else if (object instanceof multiframe) {
			effects.add((multiframe) object);

		} else if (object instanceof BPaddle) {
			paddle = (BPaddle) object;
			
			// Ich glaube nicht dass es einen usecase gibt wo man absichtlich das paddle
			// durch ein neues ersetzt, deshalb die Warnung.
			System.out.println("Warning on addObject: paddle has just been replaced.");
		}
	}

	public ArrayList<BBlock> getBlocks() {
		return blocks;
	}

	public int getNumberOfBalls() {
		return balls.size();
	}

	public ArrayList<BBall> getAllBalls() {
		return balls;
	}

	public BBall getBall(int ballIndex) {
		return balls.get(ballIndex);
	}

	public BPaddle getPaddle() {
		return paddle;
	}

	public void movePaddle(int paddleNewX) {
		paddle.setX(paddleNewX);
	}
}