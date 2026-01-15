package qmApp.qmDesktopApp.qmCustomControl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

import qmInfrastructure.AppStyle;

public class qmPatLabel extends JLabel{
    public qmPatLabel(){
        customizeComponent();
    }
    public qmPatLabel(String text){
        setText(text);
        customizeComponent();
    }
    private void customizeComponent(){
        setCustomizeComponent(getText(), AppStyle.FONT, AppStyle.COLOR_FONT_LIGHT, AppStyle.ALIGNMENT_LEFT);
    }
    public void setCustomizeComponent(String text, Font  font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(alignment);
        //setIcon(new ImageIcon(iconPath));
    }
}