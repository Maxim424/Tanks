import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JDialog {
    private JPanel contentPane;
    private JButton AboutButton;
    private JButton LevelChooseButton;
    private JButton FreeGameButton;

    public Menu() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        FreeGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FreeChooseMenu freeChooseMenu = new FreeChooseMenu();
                int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
                int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
                int thisheight = 250;
                int thiswidth = 400;
                freeChooseMenu.setLocation((screenwidth - thiswidth) / 2 + 20, 90);
                freeChooseMenu.setSize(thiswidth, thisheight);
                freeChooseMenu.setVisible(true);
            }
        });
        LevelChooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LevelChooseMenu levelChooseMenu = new LevelChooseMenu(false);
                int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
                int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
                int thisheight = 300;
                int thiswidth = 300;
                levelChooseMenu.setLocation((screenwidth - thiswidth) / 2 + 20, 90);
                levelChooseMenu.setSize(thiswidth, thisheight);
                levelChooseMenu.setVisible(true);
            }
        });
        AboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Maxim Kuznetsov \nKirill Mazurets \n2020-2021");
            }
        });
    }

    public static void main(String[] args) {
        Menu dialog = new Menu();
        int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int thisheight = 550;
        int thiswidth = 400;
        dialog.setLocation((screenwidth - thiswidth) / 2, 50);
        dialog.setSize(thiswidth, thisheight);
        dialog.setVisible(true);
    }

    private void createUIComponents() {

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 3, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBackground(new Color(-1382436));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-7434870));
        label1.setForeground(new Color(-7434870));
        label1.setText("Main menu");
        contentPane.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FreeGameButton = new JButton();
        FreeGameButton.setBackground(new Color(-1474444));
        FreeGameButton.setText("Free play");
        contentPane.add(FreeGameButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LevelChooseButton = new JButton();
        LevelChooseButton.setBackground(new Color(-1474444));
        LevelChooseButton.setText("Campaign");
        contentPane.add(LevelChooseButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AboutButton = new JButton();
        AboutButton.setBackground(new Color(-1474444));
        AboutButton.setText("About");
        contentPane.add(AboutButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        contentPane.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
