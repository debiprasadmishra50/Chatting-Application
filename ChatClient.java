import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;
public class ChatClient extends JFrame  implements ActionListener , Runnable
{
	JTextField tf1;
	JTextArea ta1;
	JScrollPane p1;
	JButton b1;
	
	ServerSocket ss ;
	Socket s1; 
	PrintWriter pw ; 
	BufferedReader br;
	
	public ChatClient() 
	{
		// GUI 
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(Color.orange);
		setTitle("Chat Client ");
		
		tf1 = new JTextField();
		ta1 = new JTextArea();
		p1 = new JScrollPane(ta1);
		b1 = new JButton("Send");
		
		add(p1);
		add(b1);
		add(tf1);
		
		tf1.setBounds(30, 40, 120, 30);
		b1.setBounds(160, 40, 80, 30);
		p1.setBounds(30, 80, 210, 200);
		
		setVisible(true);
		setSize(300, 350);
		
		// Networking 
		try 
		{
			//ss = new ServerSocket(5000); // Only for server 
			//s1 = ss.accept();// Only for server 
			
			s1 = new Socket (InetAddress.getByName("localhost"),5000); // Only for Client 
			pw = new PrintWriter(s1.getOutputStream(),true);
			br= new BufferedReader
					(new InputStreamReader(s1.getInputStream()));
			
		} 
		catch (Exception e) 
		{
		}
		b1.addActionListener(this);
		tf1.addActionListener(this);
		
		new Thread(this).start();
	}
	// Read data from buffer 
	public void run()
	{
		while(true)
		{
			try 
			{
				String text = br.readLine();
				ta1.append(text +" - Sender " + new Date() +"\n"); // write sender message into own TextArea 				
			} 
			catch (Exception e) 
			{
			}
		}
	}
	// Event Handling code when click on button 
	public void actionPerformed(ActionEvent e) 
	{
		String text = tf1.getText();
		pw.println(text); // Write text into buffer 
		ta1.append(text +" - Me  " + new Date() +"\n"); // write text into own TextArea 
		tf1.setText(""); // Clear the input field 
	}
	public static void main(String[] args) throws Exception 
	{
		new ChatClient();
		
	}	
}







