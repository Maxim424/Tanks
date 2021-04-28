import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageHelper {

    private static BufferedImage[] frames = new BufferedImage[100];
    private static BufferedImage texture = null;

    private static void loadTexture(String fileName) {
        File f = new File(fileName);

        try {
            texture = ImageIO.read(f);
        } catch (Exception e) {
            System.out.println("Файл не загружен " + fileName);
            System.exit(0);
        }
    }

    public static void crop(int row, int col, int code) {
        if (texture == null) {
            loadTexture("sprite.png");
        }
        frames [code ] = texture.getSubimage(col * Map.BLOCK_SIZE, row * Map.BLOCK_SIZE, Map.BLOCK_SIZE, Map.BLOCK_SIZE);
    }

    public static void paint (Graphics g, int block, int screenX, int screenY) {
        g.drawImage( frames[block], screenX, screenY, null );
    }

}
