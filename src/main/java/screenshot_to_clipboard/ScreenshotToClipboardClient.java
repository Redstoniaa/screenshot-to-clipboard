package screenshot_to_clipboard;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screenshot_to_clipboard.util.ClipboardUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenshotToClipboardClient
		implements ClientModInitializer {
	public static final String MOD_ID = "screenshot-to-clipboard";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		System.setProperty("java.awt.headless", "false");
	}

	public static void sendToClipboard(File imageFile) {
		try {
			BufferedImage image = ImageIO.read(imageFile);
			ClipboardUtil.copyToClipboard(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
