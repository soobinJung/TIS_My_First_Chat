package Chatting;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

import Chatting.Chatting_ChatBox;
import Chatting.Chatting_TableData;

public class GUIClient extends JFrame implements ActionListener,Runnable{ 
   JPanel mainPan, firstPan, twoPan;  

   JLabel  info;
   JButton btn_connect, btn_send, btn_exit, btn_export, btn_signUp;
   JTextField txt_server_ip, txt_name, txt_input;
   TextArea txt_list;
   CardLayout cardlayout; 
   JPasswordField passwordField;
   Chatting_TableData data_cheak = new Chatting_TableData();
   String ip_txt;                            
   Socket sock;
   final int PORT=7500;
   PrintWriter pw=null;                
   BufferedReader br=null;            
   OutputStream os=null;
   
   public GUIClient()    {
      setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Mtest\\Chatting Project\\RainBow.jpg"));
    this.setTitle("InsideOut");
      serverConnect();
      ChatPane();
      
      //card-----------------------------
      mainPan = new JPanel();
      cardlayout = new CardLayout();
      mainPan.setLayout(cardlayout);
      
      mainPan.add(firstPan,"����â");
      mainPan.add(twoPan,"ä��â");
      cardlayout.show(mainPan, "����â"); //�⺻���� 
      //----------------------------------
      getContentPane().add(mainPan);
      setBounds(200,200,800,500);
      setVisible(true);
      
      super.setDefaultCloseOperation(EXIT_ON_CLOSE);
      //�̺�Ʈó��-----------------------
      btn_connect.addActionListener(this);
      btn_export.addActionListener(this);
      btn_exit.addActionListener(this);
      btn_send.addActionListener(this);
      btn_signUp.addActionListener(this);
      txt_input.addActionListener(this);
      //----------------------------------
   } //end
   
   public void serverConnect() {//Ŭ���̾�Ʈ�� ������ ���� & ���α׷� ����
     firstPan = new JPanel();
    ImagePanel pn = new ImagePanel(new ImageIcon("C:/Mtest/Chatting Project/insideOut1.jpg").getImage());
    pn.setLayout(null);
    
    firstPan.setLayout(new BorderLayout());
    firstPan.add(pn,BorderLayout.NORTH);
    
    JLabel lb1 = new JLabel("IP :");
    lb1.setHorizontalAlignment(SwingConstants.RIGHT);
    lb1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
    lb1.setBounds(295, 129, 60, 20);
    pn.add(lb1);
    txt_server_ip = new JTextField("192.168.0.2", 15);
    txt_server_ip.setForeground(new Color(105, 105, 105));
    txt_server_ip.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));
    txt_server_ip.setBounds(365, 129, 110, 20);
    pn.add(txt_server_ip);
    txt_name = new JTextField("Enter ID",15);
    txt_name.setForeground(new Color(105, 105, 105));
    txt_name.setFont(new Font("Calibri", Font.BOLD, 15));
    txt_name.setBounds(365, 69, 110, 20);
    pn.add(txt_name);
    JLabel lb2 = new JLabel("ID :");
    lb2.setHorizontalAlignment(SwingConstants.RIGHT);
    lb2.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
    lb2.setBounds(295, 69, 60, 20);
    pn.add(lb2);
    
    JLabel pw = new JLabel("PW :");
    pw.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
    pw.setHorizontalAlignment(SwingConstants.RIGHT);
    pw.setBounds(295, 99, 60, 20);
    pn.add(pw);
    
    passwordField = new JPasswordField();
    passwordField.setFont(new Font("Calibri", Font.BOLD, 15));
    passwordField.setBounds(365, 99, 110, 21);
    pn.add(passwordField);
    
    btn_connect = new JButton("LOG IN");
    btn_connect.setForeground(new Color(0, 0, 102));
    btn_connect.setBackground(new Color(255, 255, 204));
    btn_connect.setFont(new Font("Arial Unicode MS", Font.PLAIN, 10));
    btn_connect.setBounds(315, 155, 75, 20);
    pn.add(btn_connect);
    
    btn_signUp = new JButton("SIGN UP");
    btn_signUp.setForeground(new Color(0, 0, 102));
    btn_signUp.setBackground(new Color(255, 255, 204));
    btn_signUp.setFont(new Font("Arial Unicode MS", Font.PLAIN, 10));
    btn_signUp.setBounds(395, 155, 80, 20);
    pn.add(btn_signUp);

   } //end
   
   public void ChatPane(){
      twoPan = new JPanel();
      JPanel  pn = new JPanel();
      pn.setBackground(new Color(230, 230, 250));
      txt_list = new TextArea();
      txt_list.setBackground(new Color(255, 255, 240));
      txt_input = new JTextField("",40);
      btn_send = new JButton("Send");
      btn_send.setForeground(new Color(255, 255, 255));
      btn_send.setBackground(new Color(138, 43, 226));
      btn_export = new JButton("Export");
      btn_export.setForeground(new Color(255, 255, 255));
      btn_export.setBackground(new Color(138, 43, 226));
      btn_exit = new JButton("Exit");
      btn_exit.setForeground(new Color(255, 255, 255));
      btn_exit.setBackground(new Color(138, 43, 226));

      pn.add(txt_input); pn.add(btn_send); pn.add(btn_export); pn.add(btn_exit);
      
      twoPan.setLayout(new BorderLayout());
      twoPan.add(txt_list, "Center");
      twoPan.add(pn, "South");
   } //end
   
   int[] color = new int[6];
   
   
   public void char_button( int[] color ) {
      
      btn_send.setBackground  (new Color(color[0],color[1],color[2]));
      btn_export.setBackground(new Color(color[0],color[1],color[2]));
      btn_exit.setBackground  (new Color(color[0],color[1],color[2]));
      
      btn_send.setForeground  (new Color(color[3],color[4],color[5]));
      btn_export.setForeground(new Color(color[3],color[4],color[5]));
      btn_exit.setForeground  (new Color(color[3],color[4],color[5]));
      
      
   }

   public void actionPerformed(ActionEvent e) {
	      Object ob = e.getSource();
	      
	       // ���̵� ���� ����
	     String idcheak   = txt_name.getText();
	      // ��й�ȣ ���� ����
	     String passcheak = passwordField.getText();
	          if(ob == btn_connect){ 
	             
	            String back_char = data_cheak.idCheck(idcheak, passcheak);
	            
	            System.out.println(back_char);
	            if(back_char.equals("Joy"))           { // ���
	               color[0] = 255 ; color[1] = 255 ; color[2] = 51;
	               color[3] = 255 ; color[4] = 204 ; color[5] = 153; 
	                
	                
	            }else if (back_char.equals("Sadness")) { // �Ķ�
	               
	               color[0] = 153; color[1] =204; color[2] = 255;
	               color[3] = 0;   color[4] = 51; color[5] = 255; 
	                
	                
	         }else if (back_char.equals("Anger"))           { // ����
	            
	               color[0] = 255; color[1] = 0;   color[2] = 51;
	               color[3] = 255; color[4] = 204; color[5] = 154; 
	            
	             
	         }else if (back_char.equals("Fear"))    { // �ʷ�
	            
	                 color[0] = 204; color[1] = 204; color[2] = 255;
	               color[3] = 102; color[4] = 0;   color[5] = 51; 
	            
	             
	         }else                                          { // ����
	                 color[0] = 153; color[1] = 255; color[2] = 153;
	               color[3] = 0;   color[4] = 153; color[5] = 0; 
	            
	         }
	      
	            char_button(color);
	           
	            if( back_char != null ) {
	            
	                cardlayout.show(mainPan, "ä��â");
	                this.setTitle("�������̸�:" + txt_name.getText());
	                ip_txt = txt_server_ip.getText(); 
	           } else {
	              
	            JOptionPane.showMessageDialog(null,"�Է��Ͻ� ������ Ʋ�Ƚ��ϴ�.");
	            
	          }            
	            Thread th = new Thread(this);
	            th.start();
	         }
	         
	         if(ob == btn_signUp) { 
	            Chatting_signUp su = new Chatting_signUp(); 
	            su.SignupScreen();}
	         if(ob == btn_send) { send( ); }
	         if(ob == txt_input) { send( ); }
	         if(ob == btn_export) {export();}
	         if(ob == btn_exit){
	            pw.println( txt_name.getText() +"�� �����մϴ� client btn_exit \n");
	            pw.flush();
	            System.exit(1);
	         }
	   }   //actionPerformed end

   
       public void export() {
         String chat = txt_list.getText();
         String path = "C:/Mtest/Chatting.txt";
         try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(chat);
            txt_list.append("        #��ȭ������ ����Ǿ����#\n");
            System.out.println(path + "�������� ����");
            bw.close(); // �ʼ� 
         } catch (Exception ex) {
            System.out.println("�������" + ex);
           }
    }//end
  
      public void send( ){
         String text=txt_input.getText();
         if(text.equals("exit")){
           text="ä�ù��� �����մϴ�";
           pw.println( txt_name.getText() +"�� �����մϴ� client exit�Է� send( )�޼ҵ�\n");
           pw.flush();
           System.out.println("ä������ ���α׷��� ����˴ϴ�");
           System.exit(1);
         }
      
         txt_input.setText("");
         txt_input.requestFocus(); 
         pw.println(text);
         pw.flush();
      } //end
   
      
   public void run() {
      try{
         sock = new Socket(ip_txt, PORT);
         String nickname=txt_name.getText();
         Chatting_Transaction t = new Chatting_Transaction();
         Chatting_TransactionBuilder tb = new Chatting_TransactionBuilder(t);         
         os=sock.getOutputStream();
         pw=new PrintWriter(new OutputStreamWriter(os));
         pw.println(nickname);           
         pw.flush();
         InputStream is=sock.getInputStream();
         br=new BufferedReader(new InputStreamReader(is));
         
         String str;
         while(true)   {
            str=br.readLine();
            txt_list.append(str + "\n");
         } //while end         
      }catch(IOException e){  }      
   
   } //run end
   
    public static void main(String[] args) {
       new GUIClient();
    } //end
}//GUIChatClient class END

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