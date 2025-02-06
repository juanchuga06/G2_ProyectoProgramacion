package UserInterfaceComponent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;

import javax.swing.SwingConstants;

public abstract class BibliotecaStyle {
   public static final Color COLOR_FONT = new Color(200, 200, 200); //(218, 8, 40)
    public static final Color COLOR_FONT_LIGHT = new Color(100, 100, 100);
    public static final Color COLOR_CURSOR = Color.black;
    public static final Color COLOR_BORDER = Color.lightGray;
    public static final Font  FONT         = new Font("JetBrains Mono", Font.PLAIN, 14);
    public static final Font  FONT_BOLD    = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14);
    public static final Font  FONT_SMALL   = new Font("JetBrains Mono", Font.PLAIN| Font.PLAIN, 10);

    public static final int ALIGNMENT_LEFT  = SwingConstants.LEFT;
    public static final int ALIGNMENT_RIGHT = SwingConstants.RIGHT;
    public static final int ALIGNMENT_CENTER= SwingConstants.CENTER;

    public static final Cursor CURSOR_HAND    = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    
    //public static final URL URL_SPLASH= BibliotecaStyle.class.getResource(");
    public static final URL URL_LOGO = BibliotecaStyle.class.getResource("/UserInterface/Resource/MRBOOKLG.png");
    //public static final URL URL_MAIN = BibliotecaStyle.class.getResource();
}
