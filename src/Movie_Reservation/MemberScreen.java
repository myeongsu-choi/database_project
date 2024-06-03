package Movie_Reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MemberScreen extends JPanel {
	
	JPanel panel, spacePanel, backPanel;
	JButton select, history, back;
	Font font;
	float fontSize;
	BufferedImage backgroundImage;
	String memberID;
	
	MemberScreen(MyFrame mf) {
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		/*
			빈 패널 추가
		 */
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 330)); 
        add(spacePanel);
		
        /*
        	영화 조회 버튼과 예매 내역 버튼을 넣을 패널 추가
         */
		panel = new JPanel();
		panel.setOpaque(false); 
		panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100)); 
	    
		/*
			영화 조회 버튼 추가 및 꾸미기
		 */
		select = new JButton("영화 조회");
		font = select.getFont();
		fontSize = font.getSize() + 40;
		select.setFont(font.deriveFont(fontSize));
		select.setBackground(Color.white);
		select.setForeground(Color.black);
		select.setBorder(BorderFactory.createEmptyBorder());
		
		select.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				select.setBackground(new Color(224, 224, 224));
			}
			public void mouseExited(MouseEvent e) {
				select.setBackground(Color.white);
			}
			public void mouseClicked(MouseEvent e) {
				mf.showReservationScreen();
			}
		});
		
		panel.add(select);
		
		/*
			예매 내역 버튼 추가 및 꾸미기
		 */
		history = new JButton("예매 내역");
		font = history.getFont();
		fontSize = font.getSize() + 40;
		history.setFont(font.deriveFont(fontSize));
		history.setBackground(Color.white);
		history.setForeground(Color.black);
		history.setBorder(BorderFactory.createEmptyBorder());
		
		history.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				history.setBackground(new Color(224, 224, 224));
			}
			public void mouseExited(MouseEvent e) {
				history.setBackground(Color.white);
			}
			public void mouseClicked(MouseEvent e) {
				mf.member_ID = memberID;
				mf.showReserved();
			}
		});
		
		JLabel label = new JLabel("     /    ");
		font = label.getFont();
		fontSize = font.getSize() + 40;
		label.setFont(font.deriveFont(fontSize));
		panel.add(label);
		panel.add(history);
		add(panel);
		
		/*
		 	이미지로 배경 꾸미기
		 	
		 	이미지 출처 : https://kr.freepik.com/free-vector/movie-people-production_5667991.htm#fromView=search&page=1&position=10&uuid=3bc4b606-74cb-415c-884f-f42f088f8802
		 */
		backgroundImage = loadImage("images/reservation_background.jpg");
		
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 330)); 
        add(spacePanel);
		/*
		 	뒤로 가기 버튼 추가하기
		 */
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        backPanel.add(back);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mf.showLoginScreen();
			}
        	
        });
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
