package Chatting;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class Chatting_Login implements ActionListener{
	
   private JFrame frame;
   private JTextField textField_ID;
   private JPasswordField passwordField;
   private JTextField textField_IP;
   private JLabel IncorrectLabel;
   private JButton login_btn;
   private JButton signUp_btn;

   //���� �޼ҵ�
   public static void LoginScreen() {
	   
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	Chatting_Login window = new Chatting_Login();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   
   //�⺻������
   public Chatting_Login() {
      initialize();
   }
   
   //��������� ���� �޼ҵ�
   private void initialize() {
      frame = new JFrame();
      ImagePanel welcomePanel = new ImagePanel(new ImageIcon("C:\\Mtest\\workJava\\Chatting\\src\\Chatting\\image\\inside.jpg").getImage());
      welcomePanel.setForeground(Color.WHITE);
      // ����� ���� �� �� ���� ���̿� ���� ���̸� ���Ѵ�.
      frame.setSize(welcomePanel.getWidth(), welcomePanel.getHeight());
      frame.getContentPane().add(welcomePanel, BorderLayout.NORTH);
      welcomePanel.setLayout(null);
      
      //��_ �α���
      JLabel LOGIN = new JLabel("L O G I N");
      LOGIN.setHorizontalAlignment(SwingConstants.CENTER);
      LOGIN.setFont(new Font("Lucida Handwriting", Font.BOLD, 50));
      LOGIN.setBounds(810, 170, 300, 50);
      welcomePanel.add(LOGIN);
      
      //�� _ ���̵�
      JLabel idLabel = new JLabel("ID :");
      idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      idLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 30));
      idLabel.setBounds(810, 240, 70, 40);
      welcomePanel.add(idLabel);
      
      //�ؽ�Ʈ�ʵ� _ ���̵�
      textField_ID = new JTextField();
      textField_ID.setForeground(Color.GRAY);
      textField_ID.setFont(new Font("Calibri", Font.BOLD, 25));
      textField_ID.setText(" Enter ID");
      //�׼Ǹ����� _ ���̵�
      textField_ID.addMouseListener(new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			textField_ID.setText("");
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
      });
      
      //textField_ID.setToolTipText("ID Enter");
      textField_ID.setBounds(900, 245, 200, 30);
      welcomePanel.add(textField_ID);
      textField_ID.setColumns(10);
      
      
      //�ؽ�Ʈ�ʵ�_��й�ȣ
      passwordField = new JPasswordField("");
      passwordField.setFont(new Font("Calibri", Font.PLAIN, 25));
      passwordField.setBounds(900, 290, 200, 30);
      welcomePanel.add(passwordField);
      
      
      //�� _ ��й�ȣ
      JLabel pwLabel = new JLabel("PW :");
      pwLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      pwLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 30));
      pwLabel.setBounds(810, 285, 70, 40);
      welcomePanel.add(pwLabel);
      
      //��ư _ �α��� 
      login_btn = new JButton(" LOGIN ");
      login_btn.setBackground(new Color(255, 255, 153));
      login_btn.setForeground(new Color(0, 0, 51));
      login_btn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
      login_btn.setToolTipText("LOGIN ");
      login_btn.setBounds(830, 390, 120, 35);
      welcomePanel.add(login_btn);
      login_btn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		Chatting_ChatBox cb = new Chatting_ChatBox();
      		cb.ChatBoxScreen();
      		welcomePanel.setVisible(false);
      		frame.setVisible(false);
      	}
      });
      login_btn.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					login_btn.setBackground(new Color(186, 85, 211));
		      login_btn.setForeground(new Color(255, 255, 0));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					login_btn.setBackground(new Color(255, 255, 153));
		      login_btn.setForeground(new Color(0, 0, 51));
				}
      });
      
      
      
      
      
      //�� _ ������
      JLabel ipLabel = new JLabel("IP :");
      ipLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      ipLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 30));
      ipLabel.setBounds(810, 330, 70, 40);
      welcomePanel.add(ipLabel);
      
      
      //��ư _ ȸ������
      signUp_btn = new JButton("SIGN UP");
      signUp_btn.setBackground(new Color(255, 255, 153));
      signUp_btn.setForeground(new Color(0, 0, 51));
      signUp_btn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		Chatting_signUp su = new Chatting_signUp();
      		su.SignupScreen();
                       //welcomePanel.setVisible(false);
      		//frame.setVisible(false);


      	}
      });
      signUp_btn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
      signUp_btn.setToolTipText("\\uD68C \\uC6D0 \\uAC00 \\uC785");
      signUp_btn.setBounds(960, 390, 120, 35);
      welcomePanel.add(signUp_btn);
      signUp_btn.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					signUp_btn.setBackground(new Color(186, 85, 211));
					signUp_btn.setForeground(new Color(255, 255, 0));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					signUp_btn.setBackground(new Color(255, 255, 153));
					signUp_btn.setForeground(new Color(0, 0, 51));
				}
      });
      
      //�ؽ�Ʈ�ʵ� _ ������
      Chatting_Client CC = new Chatting_Client();
      textField_IP = new JTextField(CC.SERVER_IP, 15);
      textField_IP.setForeground(Color.GRAY);
      textField_IP.setFont(new Font("Calibri", Font.PLAIN, 25));
      textField_IP.setBounds(900, 335, 200, 30);
      welcomePanel.add(textField_IP);
      textField_IP.setColumns(10);
      
      // ������ ���� ����
      frame.setResizable(true);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      //�׼� ������ ===========================================
      login_btn.addActionListener(this);
      signUp_btn.addActionListener(this);
      //==================================================
   }

	public void actionPerformed(ActionEvent e) {
		Object ob = new Object();
		
		if(ob==login_btn) {
			Chatting_Client cc = new Chatting_Client();
			cc.goChatBox();
			
		if(ob==signUp_btn) {
			Chatting_signUp su = new Chatting_signUp();
			su.SignupScreen();
		}
	}
}   

class ImagePanel extends JPanel {
	
   private Image img;
   
   public ImagePanel( Image img ) {
      this.img = img;
      setSize( new Dimension(img.getWidth(null), img.getHeight(null)));
      setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
      setLayout(null);
   }
   // �̹����� ���� ���̸� ���Ѵ�.
   public int getWidth() {
      return img.getWidth(null);
   }
   // �̹����� ���� ���̸� ���Ѵ�.
   public int getHeight() {
      return img.getHeight(null);
   }
   
   public void paintComponent( Graphics g ) {
      g.drawImage( img, 0, 0, null);
   }
}

}







