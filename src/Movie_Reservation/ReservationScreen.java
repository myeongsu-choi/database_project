package Movie_Reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservationScreen extends JPanel {
	
	JPanel selectPanel, spacePanel, displayPanel, backPanel;
	JTextField movieName, directorName, actorName, genre; 
	JLabel movieName_, directorName_, actorName_, genre_; 
	JButton back;
	Reservation_Movie resrvationMovie;
	MyFrame mf2;
	String memberID;
	
	ReservationScreen(MyFrame mf) {
		mf2 = mf;
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        /*
        	selectPanel에 검색 창 붙이기
         */
		selectPanel = new JPanel();
		selectPanel.setOpaque(false); 
		selectPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(selectPanel);
		
        /*
         	영화 검색 기능 구현하기
         */
		movieName_ = new JLabel("영화명 : ");
		movieName = new JTextField(10);
		selectPanel.add(movieName_);
		selectPanel.add(movieName);
		
		directorName_ = new JLabel("감독명 : ");
		directorName = new JTextField(10);
		selectPanel.add(directorName_);
		selectPanel.add(directorName);
		
		actorName_ = new JLabel("배우명 : ");
		actorName = new JTextField(10);
		selectPanel.add(actorName_);
		selectPanel.add(actorName);
		
		genre_ = new JLabel("장르 : ");
		genre = new JTextField(10);
		selectPanel.add(genre_);
		selectPanel.add(genre);
		
		JButton select = new JButton("검색");
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent error) {
				Connection conn = null;
				Statement stmt = null;
				ResultSet srs = null;
				try {
					System.out.println(memberID);
					Class.forName("com.mysql.cj.jdbc.Driver"); 
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", memberID, memberID); 
					System.out.println(memberID + "DB 연결 완료");
					stmt = conn.createStatement(); 
					
					String name = movieName.getText();
					String director = directorName.getText();
					String actor = actorName.getText();
					String genreName = genre.getText();
					
					String query = "select * from movie_info where";
					if(!name.isEmpty()) {
						query += " movie_name = '" + name + "'";
					}
					if(!director.isEmpty()) {
						if(name.isEmpty())
							query += " director_name = '" + director + "'";
						else
							query += " and director_name = '" + director + "'";
					}
					if(!actor.isEmpty()) {
						if(name.isEmpty() && director.isEmpty())
							query += " actor_name = '" + actor + "'";
						else
							query += " and actor_name = '" + actor + "'";
					}
					if(!genreName.isEmpty()) {
						if(name.isEmpty() && director.isEmpty() && actor.isEmpty())
							query += " genre = '" + genreName + "';";
						else
							query += " AND genre = '" + genreName + "';";
					}
					System.out.println("Generated query: " + query);
					
					srs = stmt.executeQuery(query);
					printData(srs, "movie_name", "movie_id");
				} catch (ClassNotFoundException e) {
					System.out.println("JDBC 드라이버 로드 오류");
				} catch (SQLException e) {
					System.out.println("SQL 실행오류");
				} finally {
					try {
		                if (stmt != null) stmt.close();
		                if (conn != null) conn.close();
		                if (srs != null) srs.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				}
			}
		});
		selectPanel.add(new JLabel("       "));
		selectPanel.add(select);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
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
				mf.showMemberScreen();
			}
        	
        });
	}
	
	private void printData(ResultSet srs, String col1, String col2) throws SQLException {
		Font font;
		float fontSize;
		
		this.removeAll();
        this.add(spacePanel);
		this.add(selectPanel);
		
		while (srs.next()) {
			if (!col1.equals("")) {
				
				displayPanel = new JPanel();
				displayPanel.setOpaque(false); 
				displayPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 70)); 
				this.add(displayPanel);
				
				String data = srs.getString(col1);
				int movie_id = srs.getInt(col2);
				
				JLabel label = new JLabel(data + "            ");
				font = label.getFont();
		        fontSize = font.getSize() + 20;
		        label.setFont(font.deriveFont(fontSize));
		        
		        JButton btn = new JButton(data + " 예매 하러 가기");
		        font = btn.getFont();
		        fontSize = font.getSize() + 20;
		        btn.setFont(font.deriveFont(fontSize));
		        btn.setBackground(new Color(224,255,255));
		        
		        btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						resrvationMovie = new Reservation_Movie(mf2, data, movie_id, memberID);
						show_Reservation_MovieScreen();
					}
		        	
		        });
		        
		        displayPanel.add(label);
		        displayPanel.add(btn);
			}
			else 
				System.out.println();
		}
		this.revalidate();
		this.repaint();
		this.add(backPanel);
	}
	
	public void show_Reservation_MovieScreen() {
		this.setVisible(false);
		mf2.add(resrvationMovie);
		resrvationMovie.setVisible(true);
	}
}

