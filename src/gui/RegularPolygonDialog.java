package gui;


import javax.swing.*;
import java.awt.*;

public class RegularPolygonDialog extends JDialog {

    private int sideNum = 5;
    private JTextField sideNumField;

    RegularPolygonDialog(JFrame parent) {
        super(parent, "Set number of sides");
        sideNumField = new JTextField("5");
        sideNumField.selectAll();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                int tempNum = Integer.parseInt(sideNumField.getText());
                if (tempNum < 3)
                    throw new IllegalArgumentException("Number of sides should be greater 2");
                sideNum = tempNum;
                setVisible(false);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Input Error. Check the data");
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, exception);
            }
        });
        add(sideNumField);
        add(okButton, BorderLayout.SOUTH);
        Point dialogLocation = parent.getLocation();
        dialogLocation.translate(50, 50);
        setLocation(dialogLocation);
        getRootPane().setDefaultButton(okButton);
        setSize(150, 80);
        setModal(true);
        setResizable(false);
    }

    public void showDialog() {
        Point dialogLocation = getParent().getLocation();
        dialogLocation.translate(50, 50);
        setLocation(dialogLocation);
        setSideNum();
        setVisible(true);
    }

    public int getSideNum() {
        return sideNum;
    }

    private void setSideNum() {
        sideNumField.setText(Integer.toString(sideNum));
        sideNumField.requestFocus();
        sideNumField.selectAll();
    }
}
