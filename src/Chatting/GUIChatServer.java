package Chatting;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUIChatServer extends JFrame implements ActionListener{
	  TextArea txt_list;
	  JButton btn_exit;
	  ServerSocket ss=null;	
	  Vector user;    //필드전역변수  인원수 카운트
	
	public GUIChatServer() 	{
		super("Chatting Server");
		
		txt_list = new TextArea();
		btn_exit = new JButton("서버종료");
		
		//txt_list.setBackground(Color.CYAN);
		txt_list.setBackground(Color.YELLOW);
		add(txt_list, "Center");
		add(btn_exit,"South");
		setSize(450,800);
		setVisible(true);
		
		//이벤트처리-----------------------
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btn_exit.addActionListener(this);
		user=new Vector();   
		serverStart();         
	} //end
	
	public void serverStart() {
		final int PORT=7500;
		try{
			ss=new ServerSocket(PORT);
			System.out.println("ServerSocket start ~~~~\n");
			txt_list.append("ServerSocket start ~~~~\n");
			txt_list.setFont(new Font("궁서체", Font.BOLD, 14));
			while(true)	{
				Socket sock=ss.accept();
				String str=sock.getInetAddress().getHostAddress();
				txt_list.append(str);
				
				ChatHandle ch=new ChatHandle(this,sock);
				user.addElement(ch); 
				ch.start();    
			}
		}catch(IOException e){	}
	}  //end
	
	public void setMsg(String string) {
		txt_list.append(string);	
	} //end
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_exit)
			System.exit(0);
	} //end
	
	public static void main(String[] args) {
		new GUIChatServer();
	} //end
} //GUIChatServer class END=========================



class ChatHandle extends Thread { //13장 쓰레드
	GUIChatServer server=null;
	Socket sock=null;
	PrintWriter pw=null;
	BufferedReader br=null;
	
	public ChatHandle(GUIChatServer server, Socket sock)	{
		this.server=server;
		this.sock=sock;
		
		try{
			InputStream is=sock.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			
			OutputStream os=sock.getOutputStream();
			pw=new PrintWriter(new OutputStreamWriter(os));
		}catch(IOException e){
			e.printStackTrace();
		}
	} //end
	
	public void run() 	{
		String nickname=null;
		
		try{
			nickname=br.readLine();
			server.setMsg("["+nickname+"]님 입장  서버에게 알림 11111\n"); 
			broadcast("["+nickname+"]님 입장   접속된 client에게 알림 22222\n");
			
			//대화시작-----------------------------------
			while(true){
				try{
					String text=br.readLine();
					server.setMsg("\n"+nickname + ": " + text +"\n");
					broadcast("\n"+nickname + ": " + text);
				}catch(IOException e){	}
			} //while end
			//대화시작 끝-----------------------------------
		}catch(IOException e){
			e.printStackTrace();
		}finally{	}
	} //end
	
	
	// 모든 접속자에게  메세지를 보내줌 
	private void broadcast(String string) {
			int s=server.user.size();    
			for(int i=0; i<s; i++){
				ChatHandle ch=(ChatHandle)server.user.elementAt(i);
				ch.pw.println(string);
				ch.pw.flush();
			}
	} //end
} //ChatHandle class END