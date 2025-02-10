package UserInterfaceComponent.CustomerControl;
import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


class FiltroNumerico extends DocumentFilter {
    
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches("\\d+")) { 
            super.insertString(fb, offset, string, (javax.swing.text.AttributeSet) attr);
        }
    }

    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("\\d+")) { 
            super.replace(fb, offset, length, text, (javax.swing.text.AttributeSet) attrs);
        }
    }
}
