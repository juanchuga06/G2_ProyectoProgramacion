package BusinessLogicComponent.entities.Utilities;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtilities {
    // El parametro format debe ser "png", "jpg"
    public static byte[] imageIconToBytes(ImageIcon icon, String format) {
        try {
            Image img = icon.getImage();
            BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            bufferedImage.getGraphics().drawImage(img, 0, 0, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, format, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon bytesToImageIcon(byte[] imageBytes) {
        if (imageBytes != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(bais);
                return new ImageIcon(bufferedImage);
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        return null;
    }



}
