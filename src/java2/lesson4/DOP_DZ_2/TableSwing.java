package java2.lesson4.DOP_DZ_2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class TableSwing extends JFrame{

    private Object[][] array = new String [][]{ {"Алексей", "20", "40000"},
            {"Василий", "25", "35000"},
            {"Петр", "23", "42000"}};
    private Object[] columnsHeader = new String[]{"Имя","Возраст","З/П"};

    TableSwing(){
        //создаем стандартную модель, переопределяем метод определения типа данных хранимых в столбце (getColumnClass)
        DefaultTableModel tm = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex){
                    case 0: return String.class;
                    case 1:
                    case 2: return Number.class;
                    default: return Object.class;
                }
            }
        };
        tm.setColumnIdentifiers(columnsHeader);         //определение столбцов
        for (Object[] row: array) {tm.addRow(row);}     //наполнение модели данными
        JTable table = new JTable(tm);
        table.setRowSorter(new TableRowSorter<>(tm)); //создаем объект RowSorter для модели, устанавливаем соответствие объектов RowSorter и JTable
        table.setBackground(Color.orange);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setSize(new Dimension(200,150));
        setVisible(true);
    }
}
