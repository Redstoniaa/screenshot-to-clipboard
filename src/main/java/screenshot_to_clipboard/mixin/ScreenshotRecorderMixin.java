package screenshot_to_clipboard.mixin;

import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.File;
import java.util.function.Consumer;

import static screenshot_to_clipboard.ScreenshotToClipboardClient.*;

@Mixin(ScreenshotRecorder.class)
public abstract class ScreenshotRecorderMixin {
    @Inject(method = "saveScreenshotInner",
            at = @At(value = "INVOKE", target = "Ljava/util/concurrent/ExecutorService;execute(Ljava/lang/Runnable;)V"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void captureLocals(File gameDirectory, String fileName, Framebuffer framebuffer, Consumer<Text> messageReceiver, CallbackInfo ci, NativeImage nativeImage, File file, File file2) {
        // file2 represents the File that the screenshot was written to; the rest of the values here are irrelevant.
        holdScreenshotFile(file2);
    }

    @ModifyArg(method = "saveScreenshotInner",
               at = @At(value = "INVOKE", target = "Ljava/util/concurrent/ExecutorService;execute(Ljava/lang/Runnable;)V"))
    private static Runnable sendToClipboard(Runnable screenshotSaver) {
        return () -> {
            screenshotSaver.run();
            sendHeldImageToClipboard();
        };
    }
}
