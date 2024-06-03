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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ReservedScreen extends JPanel {
	
	JPanel spacePanel, historyPanel, displayPanel, infoPanel, backPanel, contentPanel, selectPanel;
	JLabel history, movieName_, directorName_, actorName_, genre_;
	JTextField movieName, directorName, actorName, genre;
	Font font;
	float fontSize;
	JButton back;
	String memberID, seatID;
	JScrollPane scrollPane;
	MyFrame mf2;
	String member_id, screeningDay, price;
	int theaterID, screeningID;
	ReservationScreen reservationScreen;
	Reservation_Movie reservationMovie;
	
	ReservedScreen() {}
	ReservedScreen(MyFrame mf, String memberID) {		
		mf2 = mf;
		member_id = memberID;
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        historyPanel = new JPanel();
        historyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        historyPanel.setOpaque(true);
        historyPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100)); 
        
        history = new JLabel("나의 예매 내역");
        font = history.getFont();
		fontSize = font.getSize() + 40;
		history.setFont(font.deriveFont(fontSize)); 
		historyPanel.add(history);
		add(historyPanel);
		
		contentPanel = new JPanel();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20)); 
        contentPanel.add(spacePanel);
		
		Connection conn = null;
		Statement stmt = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Statement stmt5 = null;
		ResultSet srs = null;
		ResultSet srs2 = null;
		ResultSet srs3 = null;
		ResultSet srs4 = null;
		ResultSet srs5 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", memberID, memberID); 
			
			System.out.println("DB 연결 완료");
			
			stmt = conn.createStatement();
			stmt2 = conn.createStatement(); 
			stmt3 = conn.createStatement(); 
			stmt4 = conn.createStatement();
			stmt5 = conn.createStatement(); 
			
			srs = stmt.executeQuery(""
					+ "select reservation_id "
					+ "from reservation "
					+ "where member_id = '" + memberID + "';");
			while(srs.next()) {
				int reservationID = srs.getInt("reservation_id");
				srs2 = stmt2.executeQuery(""
						+ "select distinct screening_schedule_id "
						+ "from ticket "
						+ "where reservation_id = " + reservationID + ";");
				
				while(srs2.next()) {
					screeningID = srs2.getInt("screening_schedule_id");
					srs3 = stmt3.executeQuery(""
							+ "select movie_id, screening_day "
							+ "from screening_schedule "
							+ "where screening_schedule_id = " + screeningID + ";");
					
					while(srs3.next()) {
						int movieID = srs3.getInt("movie_id");
						screeningDay = srs3.getString("screening_day");
						
						srs4 = stmt4.executeQuery(""
								+ "select movie_name "
								+ "from movie_info "
								+ "where movie_id = " + movieID + ";");
						while(srs4.next()) {
							String movieName = srs4.getString("movie_name");
							System.out.println(movieName + " " + screeningDay);
							showData(movieName, screeningDay);
							
							srs5 = stmt5.executeQuery(""
									+ "select theater_id, seat_id, selling_price "
									+ "from ticket "
									+ "where reservation_id = " + reservationID + " and screening_schedule_id = " + screeningID + ";");
							while(srs5.next()) {
								theaterID = srs5.getInt("theater_id");
								seatID = srs5.getString("seat_id");
								price = srs5.getString("selling_price");
								System.out.println(theaterID + " " + seatID + " " + price);
								showData2(theaterID, seatID, price, screeningID, reservationID);
							}
							srs5.close();
							spacePanel = new JPanel();
					        spacePanel.setOpaque(false); 
					        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
					        contentPanel.add(spacePanel);
						}
						srs4.close();
					}
					srs3.close();
				}
				srs2.close();
			}
			srs.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("SQL 실행오류");
			e.printStackTrace();
		} finally {
			try {
                if (stmt != null) stmt.close();
                if (stmt2 != null) stmt.close();
                if (stmt3 != null) stmt.close();
                if (stmt4 != null) stmt.close();
                if (stmt5 != null) stmt.close();
                if (conn != null) conn.close();
                if (srs != null) srs.close();
                if (srs2 != null) srs.close();
                if (srs3 != null) srs.close();
                if (srs4 != null) srs.close();
                if (srs5 != null) srs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
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
				mf.showMemberScreen();
			}
        	
        });
	}
	
	private void showData(String movieName, String screeningDay){
		displayPanel = new JPanel();
		displayPanel.setOpaque(false); 
		displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		displayPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		
		JLabel movieLabel = new JLabel(movieName);
		font = movieLabel.getFont();
		fontSize = font.getSize() + 7;
		movieLabel.setFont(font.deriveFont(fontSize));
		
		JLabel day = new JLabel(" / 상영일 : " + screeningDay);
		font = day.getFont();
		fontSize = font.getSize() + 7;
		day.setFont(font.deriveFont(fontSize));
		
		displayPanel.add(movieLabel);
		displayPanel.add(day);
		contentPanel.add(displayPanel);
	}
	
	
	private void showData2(int theaterID, String seatID, String price, int screeningID, int reservationID) {
		JLabel reservationid = new JLabel("예매 번호 : " + reservationID);
		font = reservationid.getFont();
		fontSize = font.getSize() + 7;
		reservationid.setFont(font.deriveFont(fontSize));
		
		JLabel theaterid = new JLabel(", 상영관 번호 " + theaterID);
		font = theaterid.getFont();
		fontSize = font.getSize() + 7;
		theaterid.setFont(font.deriveFont(fontSize));
		
		JLabel seatid = new JLabel(", 좌석 번호 " + seatID);
		font = seatid.getFont();
		fontSize = font.getSize() + 7;
		seatid.setFont(font.deriveFont(fontSize));
		
		JLabel price_ = new JLabel(", 가격 " + price);
		font = price_.getFont();
		fontSize = font.getSize() + 7;
		price_.setFont(font.deriveFont(fontSize));
		
		infoPanel = new JPanel();
		infoPanel.setOpaque(false); 
		infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		infoPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		
		infoPanel.add(reservationid);
		infoPanel.add(theaterid);
		infoPanel.add(seatid);
		infoPanel.add(price_);
		
		JButton btn = new JButton("상세보기");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showDetail();
			}
			
		});
		JButton btn2 = new JButton("예매 취소");
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteReserved(screeningID ,theaterID, seatID, reservationID);
			}
			
		});
		JButton btn3 = new JButton("예매 변경");
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showDifferentMovie(1, seatID, screeningID);
			}
			
		});;
		
		infoPanel.add(new JLabel("   "));
		infoPanel.add(btn);
		infoPanel.add(btn3);
		infoPanel.add(btn2);
		contentPanel.add(infoPanel);
	}
	
	void showDifferentMovie(int mode, String seatID, int screeningID) {
		removeAll();
		repaint();
		revalidate();
		
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
				System.out.println(memberID);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); 
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", member_id, member_id); 
					System.out.println(member_id + "DB 연결 완료");
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
					printData(srs, "movie_name", "movie_id", seatID, screeningID, theaterID);
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
				removeAll();
				mf2.showReserved();
				setVisible(false);
			}
        	
        });
	}
	
	private void printData(ResultSet srs, String col1, String col2, String seatID, int screeningID, int theaterID) throws SQLException {
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
						reservationMovie = new Reservation_Movie(mf2, data, movie_id, member_id, seatID, screeningID, theaterID, ReservedScreen.this);
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
		mf2.add(reservationMovie);
		reservationMovie.setVisible(true);
	}
	
	void showDetail() {
		JOptionPane.showMessageDialog(ReservedScreen.this, "상영일정 : " + screeningDay + "\n 상영관 : " + theaterID + "\n 좌석 번호 : " + seatID + "\n 가격 : " + price, "티켓 정보", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void deleteReserved(int screeningID, int theaterID, String seatID, int reservationID) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		String query;
		int cnt = 0;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", member_id, member_id); 
			stmt = conn.createStatement();
			query = "delete from ticket where screening_schedule_id = " + screeningID + " AND theater_id = " + theaterID + " AND seat_id = '" + seatID + "';";
			stmt.executeUpdate(query);
			
			query = "select * from ticket where reservation_id = " + reservationID + ";";
			srs = stmt.executeQuery(query);
			while(srs.next()) {
				cnt++;
			}
			if(cnt == 0) {
				query = "delete from reservation where reservation_id = " + reservationID + ";";
				stmt.executeUpdate(query);
			}
			
			String query2 = "delete from reserved_seat where screening_schedule_id = " + screeningID + " AND theater_id = " + theaterID + " AND seat_id = '" + seatID + "';";
			stmt.executeUpdate(query2);
			
			JOptionPane.showMessageDialog(ReservedScreen.this, "예매가 취소되었습니다.");
			setVisible(false);
			mf2.showReserved();
			
			System.out.println("DB 연결 완료");
        } catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("SQL 실행오류");
			e.printStackTrace();
		} finally {
			try {
                if(conn != null) conn.close();
                if(stmt != null) stmt.close();
                if(srs != null) srs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
        
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
				mf2.showReserved();
			}
        	
        });
	}
}
