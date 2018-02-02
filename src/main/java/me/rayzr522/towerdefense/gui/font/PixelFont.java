package me.rayzr522.towerdefense.gui.font;

import com.sagl.graphics.TexLoader;
import com.sagl.graphics.Texture;

import java.awt.*;

public class PixelFont {
    private static final int fontWidth = 16;
    private static final int fontHeight = 16;
    private static final String fontLocation = "/font/font_main.png";

    private static Letter getLetter(String text) {
        for (Letter letter : Letter.values()) {
            if (text.toUpperCase().equals(letter.name)) {
                return letter;
            }
        }
        return Letter.QUESTION_MARK;
    }

    private static String[] getStrings(String text) {
        return text.split("");
    }

    public static void drawString(String text, int x, int y, int fontSize, Graphics2D g) {
        String[] strings = getStrings(text);

        Texture fontImage = TexLoader.loadTextureRes(fontLocation, Texture.TYPE_INT_ARGB);

        int letterWidth = fontImage.getWidth() / fontWidth;
        int letterHeight = fontImage.getHeight() / fontHeight;

        for (int i = 0; i < strings.length; i++) {

            if (!strings[i].equals(" ")) {

                int pos = getLetter(strings[i]).pos;

                int fontTileX = pos % fontWidth;
                int fontTileY = pos / fontHeight;

                Texture letterImage = fontImage.getSubimage(fontTileX * letterWidth, fontTileY * letterHeight, letterWidth, letterHeight);

                g.drawImage(letterImage, x + i * fontSize, y, fontSize, fontSize, null);

            } else {

                Texture letterImage = new Texture(fontSize, fontSize, Texture.TYPE_INT_ARGB);

                g.drawImage(letterImage, x + i * fontSize, y, fontSize, fontSize, null);

            }

        }

    }

    public static void drawCenteredString(String text, int windowWidth, int y, int fontSize, Graphics2D g) {

        int x = windowWidth / 2 - (text.length() * fontSize) / 2;

        drawString(text, x, y, fontSize, g);

    }

}
