# Screenshot to Clipboard!
_This is unrelated to the [mod of the same name by comp500](https://github.com/comp500/ScreenshotToClipboard). I- I didn't check to see if there was already a mod that accomplished the same functionality before I started work on this one, so ehm- **now we have two!**_

The purpose of this mod is self-explanatory: it sends any screenshots you take straight to your clipboard. This is useful if you, for example, are the type of person who likes to take screenshots and then share them to your friends straight away.

## Mild caution is advised!
_I'm definitely not familiar with AWT and stuff, so forgive me if I say some stuff wrong._

Minecraft runs Java in headless mode by default (I don't know what this even means though, but yeah), which makes the AWT's clipboard functionality just... not work. I had to use the following code to make it **not** headless (headful?) so that this could work.

```java
System.setProperty("java.awt.headless", "false");
```

This seems to work alright on its own, and with the mods I use, but apparently this may not work well with other mods that use the AWT, soo- proceed with mild caution.

Also, you're probably not supposed to edit this value anyway, so I'm probably breaking **some** unwritten rule somewhere.