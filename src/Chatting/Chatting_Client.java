package Chatting;

import java.awt.Component;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Chatting_Client implements ActionListener,Runnable{
	public static final String SERVER_IP = "192.168.0.73";
	public static final int SERVER_PORT = 9090;
	public static Socket socket;
	private Chatting_ChatBox cb;
	
	public void setCb(Chatting_ChatBox cb) {
		this.cb = cb;
	}

	//Client 기본 생성자
	public Chatting_Client() {
		
	}
	
	public static void main(String[] args) throws IOException{
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String serverResponse = input.readLine();
		
		Chatting_Client cc = new Chatting_Client();
		JOptionPane.showMessageDialog(null, "서버 연결 성공! ( 아이피 : "+SERVER_IP+" )");
		cc.run();
		System.out.println("서버 연결 해제"); 
	}

	public void run() {
		Chatting_Login ca = new Chatting_Login();
		ca.LoginScreen();
	}
	
	public void goChatBox() {
		cb.ChatBoxScreen();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}