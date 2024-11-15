/*  
Name: Tsugoii
Date: 10/10/2021
Description: Main
*/

// Import file and add the edges to a directed graph that defines these class dependencies
// Call both Hierarch and ParenthesizedList representations as well as displaying unreachables classes
// 1. Circular 2. Not Circular 3. Unreachable 4. not Unreachable

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Project4 extends JFrame implements ActionListener {

    // GUI
    JMenuBar mb;
    JMenu file;
    JMenuItem open;
    JTextArea ta;
    DirectedGraph<String> graph;

    // File Opener+GUI
    Project4() {
        open = new JMenuItem("Open File");
        open.addActionListener(this);
        file = new JMenu("File");
        file.add(open);
        mb = new JMenuBar();
        mb.setBounds(0, 0, 800, 20);
        mb.add(file);
        ta = new JTextArea(800, 800);
        ta.setBounds(0, 20, 800, 800);
        add(mb);
        add(ta);
    }

    // The only Driver code that matters
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                String filepath = f.getPath();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(filepath));
                    List<String[]> lines = new ArrayList<String[]>();
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        lines.add(line.split("\\s"));
                    }
                    graph = new DirectedGraph<String>(lines);
                    Hierarchy<String> hierarchy = new Hierarchy<String>();
                    ParenthesizedList<String> parenthesized = new ParenthesizedList<String>();
                    graph.depthFirstSearch(hierarchy);
                    ta.setText("Hierarchal Representation:\n");
                    ta.append(hierarchy.toString());
                    ta.append("\nParenthesized Representation:\n");
                    graph.depthFirstSearch(parenthesized);
                    ta.append(parenthesized.toString());
                    ta.append("\nUnreachable: " + graph.displayUnreachable());
                    br.close();
                } catch (Exception exception) {
                    System.out.println(exception);
                    JOptionPane.showMessageDialog(null, exception.toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        // Just do the GUI
        Project4 om = new Project4();
        om.setSize(800, 800);
        om.setLayout(null);
        om.setVisible(true);
        om.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}