package screenshot_to_clipboard;

import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screenshot_to_clipboard.util.ClipboardUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Consumer;

public class ScreenshotToClipboardMod {
	public static final String MOD_ID = "screenshot-to-clipboard";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static File screenshotFile;
	public static Consumer<Text> messageReceiver;

	public static void onInitialize() {
		// cause clipboard stuff won't work with headless
		System.setProperty("java.awt.headless", "false");
	}

	public static void captureRelevantLocals(File file, Consumer<Text> receiver) {
		screenshotFile = file;
		messageReceiver = receiver;
	}

	public static void sendHeldImageToClipboard() {
		try {
			if (!GraphicsEnvironment.isHeadless()) {
				BufferedImage image = ImageIO.read(screenshotFile);
				ClipboardUtil.copyToClipboard(image);
				messageReceiver.accept(new LiteralText("and copied to clipboard!").formatted(Formatting.GREEN));
			} else {
				messageReceiver.accept(new LiteralText("Lost my head somewhere, please give it back!").formatted(Formatting.RED));
			}
		} catch (Exception e) {
			e.printStackTrace();
			messageReceiver.accept(new LiteralText("Failed to copy screenshot to clipboard.").formatted(Formatting.RED));
		}
	}
}
