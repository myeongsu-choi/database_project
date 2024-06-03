package Movie_Reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UpdateDataScreen extends JPanel {
	
	JPanel spacePanel, conditionPanel, backPanel;
	Font font;
	JLabel label;
	JTextArea condition;
	JButton submit, back;
	float fontSize;
	ManagerScreen screen;
	
	UpdateDataScreen(ManagerScreen managerScreen) {
		screen = managerScreen;
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("데이터 변경");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        conditionPanel = new JPanel();
        conditionPanel.setOpaque(false); 
        conditionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        conditionPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 350)); 
        
        label = new JLabel("          ");	
        conditionPanel.add(label);
        label = new JLabel("조건식 입력");
        condition = new JTextArea(20, 100);
        condition.setBorder(BorderFactory.createLineBorder(Color.black));
        conditionPanel.add(label); conditionPanel.add(condition);
        
        add(conditionPanel);
        
        conditionPanel = new JPanel();
        conditionPanel.setOpaque(false); 
        conditionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        conditionPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
       
        submit = new JButton("조건식 전송");
        conditionPanel.add(submit);
        label = new JLabel("          ");	
        conditionPanel.add(label);
        
        submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String query = condition.getText();
				Connection conn = null;
				Statement stmt = null;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234");
					System.out.println("DB 연결 완료");
					stmt = conn.createStatement();
					if (query.trim().toLowerCase().startsWith("update")) {
		                stmt.executeUpdate(query);
		            } else {
		                JOptionPane.showMessageDialog(null, "쿼리는 update로 시작해야 합니다.", "경고", JOptionPane.WARNING_MESSAGE);
		            }
					
				} catch (ClassNotFoundException e2) {
					System.out.println("JDBC 드라이버 로드 오류");
				} catch (SQLException e2) {
					System.out.println("SQL 실행 오류");
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					int confirm = JOptionPane.showConfirmDialog(null, "그래도 실행하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							
							stmt = conn.createStatement();
							stmt.execute("SET foreign_key_checks = 0;");
							stmt.executeUpdate(query);
							stmt.execute("SET foreign_key_checks = 1;");
							System.out.println("삭제 명령 실행 완료");
						} catch (ClassNotFoundException e3) {
							System.out.println("JDBC 드라이버 로드 오류");
						} catch (SQLException e3) {
							System.out.println("SQL 실행 오류");
							e3.printStackTrace();
						} finally {
							try {
				                if (stmt != null) stmt.close();
				                if (conn != null) conn.close();
				            } catch (SQLException ex) {
				                ex.printStackTrace();
				            }
						}

					}
				} finally {
					try {
		                if (stmt != null) stmt.close();
		                if (conn != null) conn.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				}
			}
        	
        });
        
        add(conditionPanel);
        
        conditionPanel = new JPanel();
        conditionPanel.setOpaque(false); 
        conditionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        conditionPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
        label = new JLabel("예시");
        font = label.getFont();
        fontSize = font.getSize() + 20;
        label.setFont(font.deriveFont(fontSize));
        conditionPanel.add(label);
        add(conditionPanel);
        
        conditionPanel = new JPanel();
        conditionPanel.setOpaque(false); 
        conditionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        conditionPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); 
        
        label = new JLabel("테이블 값 변경 : update 테이블명 set column명 = 변경할 값 where 조건");
        font = label.getFont();
        fontSize = font.getSize() + 10;
        label.setFont(font.deriveFont(fontSize));
        conditionPanel.add(label);
        add(conditionPanel);
        
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
				setVisible(false);
				screen.setVisible(true);
			}
        	
        });
	}
}
