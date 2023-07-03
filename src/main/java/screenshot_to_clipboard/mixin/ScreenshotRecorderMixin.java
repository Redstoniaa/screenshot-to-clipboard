package screenshot_to_clipboard.mixin;

import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.File;
import java.util.function.Consumer;

import static screenshot_to_clipboard.ScreenshotToClipboardClient.sendToClipboard;

@Mixin(ScreenshotRecorder.class)
public abstract class ScreenshotRecorderMixin {
    @Inject(method = "saveScreenshotInner",
            at = @At(value = "TAIL"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void captureScreenshotFile(File gameDirectory, String fileName, Framebuffer framebuffer, Consumer<Text> messageReceiver, CallbackInfo ci, NativeImage nativeImage, File file, File file2) {
        // file2 represents the File that the screenshot was written to; the rest of the values here are irrelevant.
        sendToClipboard(file2);
    }
}
