import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Beschreiben Sie hier die Klasse GUI.
 * 
 * @author 
 *
 * @version (eine Versionsnummer oder ein Datum)
 */
public class GUI
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    JMenuBar menubar;
    JMenu file, edit, help;
    JMenuItem newFile, open, save, print, exit, copy, cut, paste, about;
    JEditorPane editorPane;
    JScrollPane myScrollPane;
    File selectedFile;

    /**
     * Konstruktor für Objekte der Klasse GUI
     */
    public GUI()
    {
        JFrame frame= new JFrame();// creating a Frame
        frame.setTitle("TextEditor");//setting title of the window
        Container container= frame.getContentPane();//creating container object
        container.setBackground(Color.white);//calling setBackground method
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//specifying close operation

        //Menu
        menubar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");

        newFile = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        print = new JMenuItem("Print");
        exit = new JMenuItem("Exit");
        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        about = new JMenuItem("About");

        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        help.add(about);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        frame.setJMenuBar(menubar);

        //ActionListener

        //Filechooser

        JFileChooser fileChooser = new JFileChooser();// creating a Frame
        frame.add(fileChooser);  

        newFile.addActionListener(actionEvent->{

                editorPane.setText("");

            });

        open.addActionListener(actionEvent->{

                int approve = fileChooser.showOpenDialog(frame);
                if(approve == JFileChooser.APPROVE_OPTION)
                {

                    selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    frame.setTitle("TextEditor " + fileChooser.getSelectedFile().getAbsolutePath());//setting title of the window

                    try
                    {
                        InputStream input = new FileInputStream(selectedFile);
                        editorPane.read(input,selectedFile);
                    }
                    catch (IOException ioe)
                    {
                        JOptionPane.showMessageDialog(null, "Beim Öffnen ist ein Fehler aufgetreten", "Fehler!", JOptionPane.WARNING_MESSAGE);
                        ioe.printStackTrace();
                    
                    }

                }
            });
        save.addActionListener(actionEvent->{

                JFileChooser fileSaver = new JFileChooser("C:\\Users\\marku\\Documents\\Studium\\Semester 3\\User Interface Programmierung\\Übung 2"); 
                if(selectedFile != null){

                    fileSaver.setSelectedFile(selectedFile);
                }

                int returnVal = fileChooser.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION){

                    File file = fileSaver.getSelectedFile();

                    try{
                        FileWriter fileWriter = new FileWriter(file, false);
                        fileWriter.write(editorPane.getText());
                        fileWriter.close();
                        selectedFile = file;
                        frame.setTitle("Text Editor:   " + selectedFile);

                    } catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Beim Speichern ist ein Fehler aufgetreten", "Fehler!", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();

                    } 
                }

            });
        print.addActionListener(actionEvent->{
                try{
                    editorPane.print();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Beim Speichern ist ein Fehler aufgetreten", "Fehler!", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                }   
            });

        exit.addActionListener(actionEvent->{System.exit(0);});
        copy.addActionListener(actionEvent->{editorPane.copy();});
        cut.addActionListener(actionEvent->{editorPane.cut();});
        paste.addActionListener(actionEvent->{editorPane.paste();});
        about.addActionListener(actionEvent->{JOptionPane.showMessageDialog(null, "TextEditor 1.0, 2021; Markus Skraba");});

        //Shortcuts
        String os = (System.getProperty("os.name"));
        os = os.toLowerCase();
        if(os.contains("win")){
            newFile.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.CTRL_MASK));
            open.setAccelerator(KeyStroke.getKeyStroke('O', ActionEvent.CTRL_MASK));
            save.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.CTRL_MASK));
            print.setAccelerator(KeyStroke.getKeyStroke('P', ActionEvent.CTRL_MASK));
            exit.setAccelerator(KeyStroke.getKeyStroke('Q', ActionEvent.CTRL_MASK));
            copy.setAccelerator(KeyStroke.getKeyStroke('C', ActionEvent.CTRL_MASK));
            paste.setAccelerator(KeyStroke.getKeyStroke('V', ActionEvent.CTRL_MASK));
            cut.setAccelerator(KeyStroke.getKeyStroke('X', ActionEvent.CTRL_MASK));
        } else{

            newFile.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.META_MASK));
            open.setAccelerator(KeyStroke.getKeyStroke('O', ActionEvent.META_MASK));
            save.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.META_MASK));
            print.setAccelerator(KeyStroke.getKeyStroke('P', ActionEvent.META_MASK));
            exit.setAccelerator(KeyStroke.getKeyStroke('Q', ActionEvent.META_MASK));
            copy.setAccelerator(KeyStroke.getKeyStroke('C', ActionEvent.META_MASK));
            paste.setAccelerator(KeyStroke.getKeyStroke('V', ActionEvent.META_MASK));
            cut.setAccelerator(KeyStroke.getKeyStroke('X', ActionEvent.META_MASK));

        }
        //Editor
        editorPane = new JEditorPane("text/plain", "");
        editorPane.setEditable(true);
        frame.add(editorPane);  

        //Scroll
        myScrollPane = new JScrollPane(editorPane);
        myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
        myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(myScrollPane);  

        frame.setBounds(350,100,800,600);//setting size and location of the window simultaneously
        frame.setVisible(true); // setting visibility

    }    

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public static void main(String args[] )
    {
        try{
            GUI gui = new GUI();
        } catch (Exception e){

            JOptionPane.showMessageDialog(null,"GUI konnte nicht geladen werden.","Fehler!",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }

}
