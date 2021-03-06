package net.lighthouse.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import net.lighthouse.settings.Settings;

/**
 * This class handles scaling GCanvasas to lighthouse Proportions. It is used by
 * both Viewport rewrite and the legacy Viewport.
 * 
 * @author finite
 *
 */
public class DarkhouseScaler {
	// Building a new LighthouseHandler for each Frame would be kinda... not
	// intelligent. So we build one once and then save it as an instance var.
	LighthouseHandler handler;

	// indicates wether scaled render outputs should be saved. Accessing the
	// settings each update would be uncool, so we save it as an instance var.
	private boolean save_Framebuffer = false;
	// the number of the frame to save, starts at 0, iterates so we don't overwrite
	// previous frames. Is class var so one instance of this class doesn't overwrite
	// imgs of another one.
	private static int imgNumber = 0;

	/**
	 * Constructs a new DarkhouseScaler.
	 */
	public DarkhouseScaler() {
		handler = new LighthouseHandler();
	}

	/**
	 * initializes the handler and sets debug var according to settings.
	 */
	public void init() {
		handler.init();
		if (Settings.getSetting("save-framebuffer").equals("true")) {
			save_Framebuffer = true;
		}
	}

	/**
	 * Updates the lighhouse by taking a screenshot of the GCanvas, scalling that
	 * down and sending the data.
	 * 
	 * @param top
	 *            the GCanvas to screenshot.
	 */
	public void update(GCanvas top) {
		// takes the screenshot
		BufferedImage captureImage = new BufferedImage(560, 840, BufferedImage.TYPE_4BYTE_ABGR);
		top.paint(captureImage.getGraphics());

		downsample(captureImage);
	}

	/**
	 * Updates the lighthouse by painting the given GComound to an image, scaling
	 * that down and sending the data.
	 * 
	 * @param top
	 *            the gCompound to screenshot.
	 */
	public void update(GCompound top) {
		// takes the screenshot
		BufferedImage captureImage = new BufferedImage(560, 840, BufferedImage.TYPE_4BYTE_ABGR);
		top.paint(captureImage.getGraphics());

		downsample(captureImage);
	}

	/**
	 * samples a BufferedImage down and sends it to the lighthouse.
	 * 
	 * @param captureImage
	 *            the BufferedImage to downsample.
	 */
	private void downsample(BufferedImage captureImage) {
		Image downsample = captureImage.getScaledInstance(28, 14, Image.SCALE_SMOOTH);
		GImage gDownsample = new GImage(downsample);

		// if save_Framebuffer == true speichern wir jeden gerenderten Frame als png in
		// den bin ordner.
		handler.update(gDownsample);
		if (save_Framebuffer)
			try {
				BufferedImage iDownsample = new BufferedImage(28, 14, BufferedImage.TYPE_4BYTE_ABGR);
				gDownsample.paint(iDownsample.getGraphics());
				ImageIO.write(iDownsample, "png", new File("img" + imgNumber++ + ".png"));
			} catch (IOException e) {
				System.out.println("framebuffer save frame " + imgNumber + " failed!");
			}
	}
}
