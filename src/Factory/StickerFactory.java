package Factory;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class StickerFactory {

    public void create(InputStream inputStream, String fileName) throws Exception {

        BufferedImage originalImage = ImageIO.read(inputStream);

        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();
        int newImageHeight = imageHeight + 150;
        BufferedImage newImage = new BufferedImage(imageWidth, newImageHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        graphics.setColor(Color.green);
        graphics.setFont(font);

        graphics.drawString("Sticker da Imersão JAVA!!", 50, newImageHeight - 70);

        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        ImageIO.write(newImage, "png", new File(fileName));
    }

}