package java2.lesson4.DOP_DZ_1;

import javax.swing.*;
import java.awt.*;


public class SwingWindow extends JFrame {
    JTextField jtxt1;
    JTextField jtxt2;
    JTextField jtxt3;

    SwingWindow(){
        super("Первое окно");
        JFrame.setDefaultLookAndFeelDecorated(true);
        setSize(new Dimension(350,180));
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(4,1);
        panel.setLayout(gridLayout);
        jtxt1 = addTextField("Фамилия", false);
        jtxt2 = addTextField("Имя", false);
        jtxt3 = addTextField("Отчество", false);
        JButton jbtn = new JButton("Ввести данные");
        jbtn.addActionListener((e)->SwingUtilities.invokeLater(()->new swFrame()));
        panel.add(jtxt1); panel.add(jtxt2); panel.add(jtxt3); panel.add(new JPanel().add(jbtn));
        add(panel);
        setVisible(true);
    }

    class swFrame extends JFrame{
        swFrame(){
            super("Второе окно");
            setSize(new Dimension(350,180));
            JPanel panel = new JPanel();
            GridLayout gridLayout = new GridLayout(4,1);
            panel.setLayout(gridLayout);
            JTextField jtxt1 = addTextField("Фамилия", true);
            JTextField jtxt2 = addTextField("Имя", true);
            JTextField jtxt3 = addTextField("Отчество", true);
            setResizable(false);
            JButton jbtn = new JButton("OK");
            jbtn.addActionListener((e)->{
                SwingWindow.this.jtxt1.setText(jtxt1.getText());
                SwingWindow.this.jtxt2.setText(jtxt2.getText());
                SwingWindow.this.jtxt3.setText(jtxt3.getText());
                setVisible(false);
                dispose();
            });
            panel.add(jtxt1); panel.add(jtxt2); panel.add(jtxt3); panel.add(jbtn);
            add(panel);
            setVisible(true);
        }
    }

    private JTextField addTextField(String name, boolean Editable){
        JTextField jtxt = new JTextField();
        jtxt.setToolTipText(name);
        jtxt.setHorizontalAlignment(JTextField.CENTER);
        jtxt.setEditable(Editable);
        return jtxt;
    }

}
