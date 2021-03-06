package net.lighthouse.model;

import java.awt.Color;

/**
 * Stores information about text that can be displayed in the client view.
 *
 * @author Christoph Fricke, finite
 */
public class BText extends BObject {
	private String text;

	/**
	 * Creates a new text object which can be rendered on the screen. The default
	 * color is white and opacity 1.
	 *
	 * @param x
	 *            X coordinate of the lower left corner.
	 * @param y
	 *            Y coordinate of the lower left corner.
	 * @param text
	 *            Text to display.
	 *
	 * @throws IllegalArgumentException
	 *             if the position is less than 0 or the text is null or empty.
	 */
	public BText(int x, int y, String text) {
		super(x, y, 100, 100, Color.WHITE);
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException(
					"Text can not be positioned on negative coordinates!" + " Was: x: " + x + " y: " + y);
		} else if (text == null || text.equals("")) {
			throw new IllegalArgumentException("Text can not be null or empty!");
		}

		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
