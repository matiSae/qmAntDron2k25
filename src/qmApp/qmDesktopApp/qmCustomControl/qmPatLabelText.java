package qmApp.qmDesktopApp.qmCustomControl;

import javax.swing.*;

import qmInfrastructure.AppStyle;

import java.awt.*;

public class qmPatLabelText extends JPanel{
    private qmPatLabel    lblEtiqueta = new qmPatLabel();
    private qmPatTextBox  txtContenido= new qmPatTextBox();

    public qmPatLabelText(String etiqueta) {
        setLayout(new BorderLayout());

        lblEtiqueta.setCustomizeComponent(  etiqueta, 
                                            AppStyle.FONT_SMALL, 
                                            AppStyle.COLOR_FONT_LIGHT, 
                                            AppStyle.ALIGNMENT_LEFT); 
        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
    }
}
