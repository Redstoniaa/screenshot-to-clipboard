package screenshot_to_clipboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screenshot_to_clipboard.util.ClipboardUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenshotToClipboardMod {
	public static final String MOD_ID = "screenshot-to-clipboard";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static File screenshotFile;

	public static void onInitialize() {
		// cause clipboard stuff won't work with headless
		System.setProperty("java.awt.headless", "false");
	}

	public static void holdScreenshotFile(File file) {
		screenshotFile = file;
	}

	public static void sendHeldImageToClipboard() {
		try {
			BufferedImage image = ImageIO.read(screenshotFile);
			ClipboardUtil.copyToClipboard(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
