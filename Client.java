package chat_Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Client {

	private JFrame frame;
	private JTextArea msgArea;
	private JScrollPane scroll;
	static JTextArea chatArea;
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dout;
	static AESExample aesenc;
	
	public static void main(String[] args) {
		
           Client start = new Client();
           aesenc = new AESExample("lv39eptlvuhaqqsr");
           
           try {
  			 String str = ""; 
  		      s = new Socket("localhost",7989);
  		      dis = new DataInputStream(s.getInputStream());
  		      dout = new DataOutputStream(s.getOutputStream());
  			    while(!str.equals("exit"))
  			    { 
  			    	str = (String) dis.readUTF();
  				    chatArea.setText(chatArea.getText() +"\n" +str); 
  				   
  				    }
  		}
  		catch (IOException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
	}
	
	public Client() { 
		init();
	}

private void init() {
		
		frame = new JFrame();
		frame.setSize(400,505);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.pink);
		frame.setUndecorated(true);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBounds(0, 0, 400, 55);
		header.setBackground(new Color(0, 136, 204));
		frame.add(header);
		
		ImageIcon icon = new ImageIcon("C:/Users/Pradeep/eclipse-workspace/chat_Application/src/chat_Application/icons/back-arrow.png");
		Image image= icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon backIcon = new ImageIcon(image);
		JLabel l1 = new JLabel(backIcon);
		l1.setLocation(10, 10);
		l1.setSize(40, 40);
		header.add(l1);
		l1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{ 
				try {
					dout.close();
					s.close();
				}catch(Exception e5) {
					e5.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		ImageIcon i2 = new ImageIcon("C:/Users/Pradeep/eclipse-workspace/chat_Application/src/chat_Application/icons/a.png");
		Image i= i2.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon profileIcon = new ImageIcon(i);
		JLabel l2 = new JLabel(profileIcon);
		l2.setLocation(10, 10);
		l2.setSize(40, 40);
		header.add(l2);
		
		ImageIcon i3 = new ImageIcon("C:/Users/Pradeep/eclipse-workspace/chat_Application/src/chat_Application/icons/b.png");
		Image i1= i3.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon profile = new ImageIcon(i1);
		JLabel l3 = new JLabel(profile);
		l3.setLocation(70, 10);
		l3.setSize(40, 40);
		header.add(l3);
		l3.addMouseListener(new MouseAdapter()  {
		  public void mouseClicked(MouseEvent e)
		  {
			  frame.setVisible(false);
			  try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			  frame.setVisible(true);
		  }
		});
		
		JLabel name = new JLabel("Bintu Sharma");
		name.setForeground(Color.white);
		name.setFont(new Font("San_Serif",Font.PLAIN,20));
		name.setBounds(140, 10, 150, 20);
		header.add(name);
		
		JLabel status = new JLabel("Active now");
		status.setForeground(Color.white);
		status.setFont(new Font("San_Serif",Font.PLAIN,15));
		status.setBounds(152, 32, 100, 20);
		header.add(status);
		
		ImageIcon i4 = new ImageIcon("C:/Users/Pradeep/eclipse-workspace/chat_Application/src/chat_Application/icons/aes.png");
		Image img = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon encyrpt = new ImageIcon(img);
		JLabel l4 = new JLabel(encyrpt);
		l4.setLocation(290, 10);
		l4.setSize(40, 40);
		header.add(l4);
		l4.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(MouseEvent e )
					{
						String data = msgArea.getText();
						try { 
							String set = aesenc.encrypt(data);
							msgArea.setText("E:- "+set);
							
						}catch(Exception e9) {
							e9.printStackTrace();
						}
						
					}
				});
		
		ImageIcon i5 = new ImageIcon("C:/Users/Pradeep/eclipse-workspace/chat_Application/src/chat_Application/icons/key.png");
		Image i6 = i5.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon key = new ImageIcon(i6);
		JLabel l5 = new JLabel(key);
		l5.setLocation(345, 10);
		l5.setSize(40, 40);
		header.add(l5);
		l5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				try {
					
					String coded = chatArea.getSelectedText();
					String decoded = aesenc.decrypt(coded);
					chatArea.replaceSelection(decoded);
					chatArea.replaceSelection(decoded);

					}catch(Exception dec)
				{
					dec.printStackTrace();	
				}
			}
		});
		
		
		msgArea = new JTextArea();
		msgArea.setFont(new Font("Sans_Serif",Font.PLAIN,20));
		msgArea.setLineWrap(true);
		scroll = new JScrollPane(msgArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10,470,270,30);
		frame.add(scroll);	
	
		JButton send = new JButton("Send");
		send.setBounds(290,470,80, 28);
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					String msg = "";
				   msg = msgArea.getText();
				   dout.writeUTF(msg);
				  chatArea.setText(chatArea.getText() +"\n" +"\t\t" +msg);
				   msgArea.setText("");
				}
				catch(Exception e3){
					e3.printStackTrace();
				}
			}
		});
		frame.add(send);
		
		chatArea = new JTextArea();
		chatArea.setBackground(Color.white);
		chatArea.setEditable(false);
		chatArea.setBounds(2,56,396,413);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setFont(new Font("Sans_Serif",Font.PLAIN,20));
		frame.add(chatArea);
		frame.setVisible(true);
		
}
}

