package PA1;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Please enter commands!");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    
    public Client() {

       
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

       
        textField.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to simple server",
            JOptionPane.QUESTION_MESSAGE);
    }

    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a username:",
            "Follow the Appendix",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    
    private String getPass() {
        return JOptionPane.showInputDialog(
            frame,
            "give a password:",
            "Follow the Appendix",
            JOptionPane.PLAIN_MESSAGE);
    }

    
    private void run() throws IOException {

        
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 4119);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            }   else if (line.startsWith("PASSWORD")) {
                    out.println(getPass());

            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }
    

    
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}
