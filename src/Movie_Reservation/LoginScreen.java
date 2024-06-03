package Movie_Reservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginScreen extends JPanel {
	JRadioButton btn[] = new JRadioButton[2];
	ButtonGroup group = new ButtonGroup();
	JTextField id, password;
	JPanel input, radioPanel;
	JLabel logo;
	Font font;
	float fontSize;
	BufferedImage backgroundImage;
	JButton loginButton;
	boolean selected = false;
	int loginMode = -1;
	String member_ID;
	
	public LoginScreen(MyFrame mf) {
		
		// 배치관리자 지정 X
		setLayout(null);
		setBackground(Color.white);
		
		logo = new JLabel("영화 예매 프로그램");
		/*
			로고 라벨 폰트 크기 키우기
		*/
		font = logo.getFont();
        fontSize = font.getSize() + 20;
        logo.setFont(font.deriveFont(fontSize)); 
        
        
        /*
         	로고 위치와 크기 지정
        */
		logo.setLocation(470, 150);
		logo.setSize(300, 300);
		add(logo);
		
		
		/*
		 	아이디, 비번 입력 패널 꾸미기
		*/
		input = new JPanel(new GridLayout(2, 2, -150, 0));
		input.add(new JLabel("아이디 : "));
		id = new JTextField(20);
		input.add(id);
		input.add(new JLabel("비밀번호 : "));
		password = new JPasswordField(20);
		input.add(password);
		input.setLocation(390, 400);
		input.setSize(350, 100);
		input.setBackground(new Color(0, 0, 0, 0));
		add(input);
		
		/*
		 	관리자, 일반회원 권한 부여 버튼 꾸미기
		 */
        radioPanel = new JPanel();
        btn[0] = new JRadioButton("관리자"); btn[0].setBackground(new Color(231, 231, 231));
        btn[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton selectedRight = (JRadioButton) e.getSource();
				if(selectedRight.isSelected()) {
					selected = true;
				}
			}
        	
        });
        btn[1] = new JRadioButton("일반 회원"); btn[1].setBackground(new Color(231, 231, 231)); btn[1].setSelected(true);
        btn[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton selectedRight = (JRadioButton) e.getSource();
				if(selectedRight.isSelected()) {
					selected = false;
				}
			}
        	
        });
        group.add(btn[0]);
        group.add(btn[1]); 
        radioPanel.add(btn[0]);
        radioPanel.add(btn[1]);
        radioPanel.setLocation(400, 530);
        radioPanel.setSize(400, 30);
        radioPanel.setBackground(new Color(0, 0, 0, 0));
        add(radioPanel);
        
        
        /*
        	로그인 버튼 추가
         */
        loginButton = new JButton("로그인");
		loginButton.setBounds(520, 580, 150, 30);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String enterID = id.getText();
				String enterPassword = password.getText();
				String selectRight = group.getSelection().getActionCommand();
				
				Connection conn = null;
				Statement stmt = null;
				ResultSet srs = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234");
					System.out.println("DB 연결 완료");
					stmt = conn.createStatement();
					String query = "select member_id from member;";
					srs = stmt.executeQuery(query);
					
					while(srs.next()) {
						member_ID = srs.getString("member_id");
						System.out.println(member_ID);
						if(!enterID.isEmpty() && !enterPassword.isEmpty()) {
							if(enterID.equals("root") && enterPassword.equals("1234") && selected == true) {
								loginMode = 0;
		                    }
							else if(enterID.equals(member_ID) && enterPassword.equals(member_ID) && selected == false){
								loginMode = 1;
								break;
							}
							else {
								loginMode = 2;
							}
						}
					}
				} catch (ClassNotFoundException e2) {
					System.out.println("JDBC 드라이버 로드 오류");
				} catch (SQLException e2) {
					System.out.println("SQL 실행 오류");
					e2.printStackTrace();
				} finally {
					try {
		                if (stmt != null) stmt.close();
		                if (conn != null) conn.close();
		                if (srs != null) srs.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				}
				
				
				
				if(loginMode == 0) {
					JOptionPane.showMessageDialog(LoginScreen.this, "관리자로 로그인합니다.");
					mf.showManagerScreen();
                }
				else if(loginMode == 1){
					JOptionPane.showMessageDialog(LoginScreen.this, "일반 회원으로 로그인합니다.");
					mf.member_ID = member_ID;
					mf.showMemberScreen();
				}
				else {
					JOptionPane.showMessageDialog(LoginScreen.this, "아이디나 비밀번호 정보가 맞지 않습니다.");
				}
			
			}
			
		});
		add(loginButton);
		
        
        /*
         	배경이미지 가져오기
         	
         	출처 : https://kr.freepik.com/free-psd/3d-cinema-blank-banner-background_76607485.htm#fromView=search&page=1&position=4&uuid=6dd30e61-e0b7-4653-becd-391917750a56
         */
        backgroundImage = loadImage("images/background.jpg");
        
        /*
         	Enter 키를 누르면 로그인 시도
         */
        KeyListener enterKeyListener = new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        			loginButton.doClick();
        		}
        	}
        };
        id.addKeyListener(enterKeyListener);
        password.addKeyListener(enterKeyListener);
        btn[0].addKeyListener(enterKeyListener);
        btn[1].addKeyListener(enterKeyListener);
        requestFocus();
		setFocusable(true);
    }
	
	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
}


