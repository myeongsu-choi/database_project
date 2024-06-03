package Movie_Reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Reservation_Movie extends JPanel {
	JPanel displayPanel, spacePanel, namePanel, seatPanel, labelPanel, backPanel, reservePanel;
	JLabel movie_name;
	Font font;
	JButton back, reserve;
	float fontSize;
	int cnt = 0, count = 0;
	String movieName_;
	int paymentAmount = 0;
	LinkedList<JButton> selectedButtons = new LinkedList<>();
	int screening_ID, reservation_id, compare_screeningID;
	String member_ID, seat_ID;
	MyFrame mf2;
	
	Reservation_Movie() {}
	
	Reservation_Movie(MyFrame mf, String movieName, int movieID, String memberID) {
		mf2 = mf;
		member_ID = memberID;
		
		movieName_ = movieName;
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("     "));
        back = new JButton("뒤로 가기");
        backPanel.add(back);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mf.showReservationScreen();
			}
        	
        });
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        namePanel = new JPanel();
        namePanel.setOpaque(false); 
        namePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(namePanel);
        
		movie_name = new JLabel(movieName);
		font = movie_name.getFont();
        fontSize = font.getSize() + 20;
        movie_name.setFont(font.deriveFont(fontSize));
        namePanel.add(movie_name);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", memberID, memberID);
			System.out.println("DB 연결 완료");
			
			stmt = conn.createStatement();

			String query = ""
					+ "select screening_day, screening_schedule_id "
					+ "from screening_schedule "
					+ "where movie_id = " + movieID + ';';
			srs = stmt.executeQuery(query);
			System.out.println("Generated query: " + query);
			
			printData(srs, "screening_day");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류");
			e.printStackTrace();
		} finally {
			try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (srs != null) srs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
		
		reservePanel = new JPanel();
		reservePanel.setOpaque(false); 
		reservePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
		reservePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        reserve = new JButton("예매하기");
        reserve.setBackground(Color.white);
        reserve.setPreferredSize(new Dimension(100, 50));
        reservePanel.add(reserve);
        reservePanel.add(new JLabel("         "));
        add(reservePanel);
        
        reserve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				Statement stmt = null;
				ResultSet srs = null;
				try {
					java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
					java.time.LocalTime currentTime = java.time.LocalTime.now();
					java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss");
					String formattedTime = currentTime.format(formatter);
					String dateTime = currentDate + " " + formattedTime;
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", memberID, memberID);
					System.out.println("DB 연결 완료");
					
					stmt = conn.createStatement();
					
					String query = "insert into reservation(payment_method, payment_status, payment_amount, member_id, payment_date) values('카드', 1, " + paymentAmount + ", '" + memberID + "', '" + dateTime + "');";
					System.out.println("insert reservation 쿼리 실행 완료");
					stmt.executeUpdate(query);
					
					String query4 = "select reservation_id from reservation where payment_date = '"+ dateTime + "';";
					srs = stmt.executeQuery(query4);
					while(srs.next()) {
						reservation_id = srs.getInt("reservation_id");
					}
					
					for(JButton button : selectedButtons) {
						String text = button.getText();
						String extractedText = text.substring(0, text.indexOf('-'));
						int extractedText2 = Integer.parseInt(extractedText);
						System.out.println(extractedText2);
						String query2 = "insert into reserved_seat(seat_id, screening_schedule_id, theater_id) values('" + text + "', " + screening_ID + ", " + extractedText2 + ");";
						stmt.executeUpdate(query2);
						System.out.println("insert reserved_seat 쿼리 실행 완료");
					}
					
					for(JButton button : selectedButtons) {
						String text = button.getText();
						String extractedText = text.substring(0, text.indexOf('-'));
						int extractedText2 = Integer.parseInt(extractedText);
						String query3 = "insert into ticket(screening_schedule_id, theater_id, seat_id, reservation_id, issuance_status, selling_price, standard_price) values(" + screening_ID + ", " +  extractedText2 + ", '" + text + "', " + reservation_id + ", " + 1 + ", " + 16000 + ", " + 16000 + ");";
						System.out.println(query3);
						stmt.executeUpdate(query3);
						System.out.println("insert ticket 쿼리 실행 완료");
					}
					
					for(JButton button : selectedButtons) {
						button.setEnabled(false);
						button.setBackground(Color.gray);
					}
					selectedButtons.clear();
					revalidate();
					repaint();
					JOptionPane.showMessageDialog(Reservation_Movie.this, "예매 되었습니다!!!!");
					
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
			}
        	
        });
	}
	
	Reservation_Movie(MyFrame mf, String movieName, int movieID, String memberID, String seatID, int screeningID, int theaterID, ReservedScreen reservedScreen) {
		member_ID = memberID;
		seat_ID = seatID;
		compare_screeningID = screeningID;
		movieName_ = movieName;
		mf2 = mf;
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("     "));
        back = new JButton("뒤로 가기");
        backPanel.add(back);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				reservedScreen.setVisible(true);
				reservedScreen.showDifferentMovie(1, seatID, screeningID);
			}
        	
        });
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        namePanel = new JPanel();
        namePanel.setOpaque(false); 
        namePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(namePanel);
        
		movie_name = new JLabel(movieName);
		font = movie_name.getFont();
        fontSize = font.getSize() + 20;
        movie_name.setFont(font.deriveFont(fontSize));
        namePanel.add(movie_name);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", memberID, memberID);
			System.out.println("DB 연결 완료");
			
			stmt = conn.createStatement();

			String query = ""
					+ "select screening_day, screening_schedule_id "
					+ "from screening_schedule "
					+ "where movie_id = " + movieID + ';';
			srs = stmt.executeQuery(query);
			System.out.println("Generated query: " + query);
			
			printData2(srs, "screening_day");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류");
			e.printStackTrace();
		} finally {
			try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (srs != null) srs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
		
		reservePanel = new JPanel();
		reservePanel.setOpaque(false); 
		reservePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
		reservePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        reserve = new JButton("예매하기");
        reserve.setBackground(Color.white);
        reserve.setPreferredSize(new Dimension(100, 50));
        reservePanel.add(reserve);
        reservePanel.add(new JLabel("         "));
        add(reservePanel);
        
        reserve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				Statement stmt = null;
				ResultSet srs = null;
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", memberID, memberID);
					System.out.println("DB 연결 완료");
					
					stmt = conn.createStatement();
					
					for(JButton button : selectedButtons) {
						String text = button.getText();
						String extractedText = text.substring(0, text.indexOf('-'));
						int extractedText2 = Integer.parseInt(extractedText);
						String query = "update reserved_seat set screening_schedule_id = " + screening_ID + ", seat_id = '" + text + "', theater_id = " +  extractedText2 + " where screening_schedule_id = " + compare_screeningID + " and seat_id = '" + seat_ID + "';";
						stmt.executeUpdate(query);
						System.out.println(query);
						System.out.println("update reserved_seat 쿼리 실행 완료");
					}
							
					for(JButton button : selectedButtons) {
						String text = button.getText();
						String extractedText = text.substring(0, text.indexOf('-'));
						int extractedText2 = Integer.parseInt(extractedText);
						String query3 = "update ticket set screening_schedule_id = " + screening_ID + ", seat_id = '" + text + "', theater_id = " +  extractedText2 + " where screening_schedule_id = " + compare_screeningID + " and seat_id = '" + seat_ID + "';";
						stmt.executeUpdate(query3);
						System.out.println(query3);
						System.out.println("update ticket 쿼리 실행 완료");
					}
					
					for(JButton button : selectedButtons) {
						if(button.getText().equals(seat_ID)) {
							button.setEnabled(true);
							button.setBackground(UIManager.getColor("Button.background"));
						}
						else {
							button.setEnabled(false);
							button.setBackground(Color.blue);
						}
					}
					
					
					selectedButtons.clear();
					revalidate();
					repaint();
					JOptionPane.showMessageDialog(Reservation_Movie.this, "예매가 변경 되었습니다!!!!");
					
					removeAll();
					mf2.showReserved();
					setVisible(false);
					
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
			}
        	
        });
	}
	
	private void printData(ResultSet srs, String col1) throws SQLException {
		
		displayPanel = new JPanel();
		displayPanel.setOpaque(false); 
		displayPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
		displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(displayPanel);
		
		while (srs.next()) {
			if (!col1.equals("")) {
				String data = srs.getString(col1);
				JButton date = new JButton(data);
				
				date.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent error) {
						Connection conn = null;
						Statement stmt = null;
						ResultSet srs = null;
						String query;
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", member_ID, member_ID);
							System.out.println("DB 연결 완료");
							
							stmt = conn.createStatement();
					
							query = ""
									+ "select theater_id, screening_schedule_id "
									+ "from screening_schedule "
									+ "where screening_day = " + "'" + data + "'" + ';';
							
							srs = stmt.executeQuery(query);
							System.out.println("Generated query: " + query);
							
							int theaterID = -1;
							int screeningID = -1;
							while(srs.next()) {
								theaterID = srs.getInt("theater_id");
								screeningID = srs.getInt("screening_schedule_id");
								screening_ID = screeningID;
							}
							
							query = ""
									+ "select * "
									+ "from theater "
									+ "where theater_id = " + theaterID + ";";
							srs = stmt.executeQuery(query);
							System.out.println("Generated query: " + query);
							
							showSeats(srs, "number_of_seats", "theater_id", "row_seat", screeningID);
							
						} catch (ClassNotFoundException e) {
							System.out.println("JDBC 드라이버 로드 오류");
						} catch (SQLException e) {
							System.out.println("SQL 실행 오류");
							e.printStackTrace();
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
				
				JLabel label = new JLabel("            ");
				displayPanel.add(date);
				displayPanel.add(label);
			}
			else 
				System.out.println();
		}
	}
	
	private void printData2(ResultSet srs, String col1) throws SQLException {
		
		displayPanel = new JPanel();
		displayPanel.setOpaque(false); 
		displayPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
		displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(displayPanel);
		
		while (srs.next()) {
			if (!col1.equals("")) {
				String data = srs.getString(col1);
				JButton date = new JButton(data);
				
				date.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent error) {
						Connection conn = null;
						Statement stmt = null;
						ResultSet srs = null;
						String query;
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", member_ID, member_ID);
							System.out.println("DB 연결 완료");
							
							stmt = conn.createStatement();
					
							query = ""
									+ "select theater_id, screening_schedule_id "
									+ "from screening_schedule "
									+ "where screening_day = " + "'" + data + "'" + ';';
							
							srs = stmt.executeQuery(query);
							System.out.println("Generated query: " + query);
							
							int theaterID = -1;
							int screeningID = -1;
							while(srs.next()) {
								theaterID = srs.getInt("theater_id");
								screeningID = srs.getInt("screening_schedule_id");
								screening_ID = screeningID;
							}
							
							query = ""
									+ "select * "
									+ "from theater "
									+ "where theater_id = " + theaterID + ";";
							srs = stmt.executeQuery(query);
							System.out.println("Generated query: " + query);
							
							showSeats2(srs, "number_of_seats", "theater_id", "row_seat", screeningID);
							
						} catch (ClassNotFoundException e) {
							System.out.println("JDBC 드라이버 로드 오류");
						} catch (SQLException e) {
							System.out.println("SQL 실행 오류");
							e.printStackTrace();
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
				
				JLabel label = new JLabel("            ");
				displayPanel.add(date);
				displayPanel.add(label);
			}
			else 
				System.out.println();
		}
	}
	
	private void showSeats(ResultSet srs, String col1, String col2, String col3, int con) throws SQLException {
		int seatNumber, theaterID;
		int x, y;
		Font font;
		float fontSize;
		
		if(cnt != 0) {
			this.remove(seatPanel);
			this.remove(labelPanel);
		}
		
		while(srs.next()) {
			seatNumber = srs.getInt(col1);
			theaterID = srs.getInt(col2);
			x = srs.getInt(col3);
			y = seatNumber / x;
			
			labelPanel = new JPanel();
			labelPanel.setOpaque(false); 
			labelPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
			
			JLabel theater = new JLabel(theaterID + " theater");
			font = theater.getFont();
	        fontSize = font.getSize() + 10;
	        theater.setFont(font.deriveFont(fontSize));
	        
	        labelPanel.add(theater);
			add(labelPanel);
			
			seatPanel = new JPanel();
			seatPanel.setOpaque(false); 
			seatPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 700)); 
			seatPanel.setLayout(new GridLayout(x, y, 20, 20));
	        add(seatPanel);
	        
	        for(int i = 0; i < x * y; i++) {
	        	JButton btn = new JButton(theaterID + "-" + (i + 1));
	        	String seatID = btn.getText();
	 
	     
	        	btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							int response = JOptionPane.showConfirmDialog(null, "영화명 : " + movieName_ + "\n좌석 번호 : " + seatID + "\n가격 : 16,000", "선택 하시겠습니까?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(response == JOptionPane.YES_OPTION) {
					    		btn.setEnabled(false); 
		                        btn.setBackground(Color.red);
		                        paymentAmount += 16000;
		                        System.out.println(paymentAmount);
		                        selectedButtons.add(btn);
					    		revalidate();
					    		repaint();
						}
					}
	        	});
	        	
	        	Connection conn = null;
	    		Statement stmt = null;
	    		ResultSet srs1 = null;
	    		try {
	    			Class.forName("com.mysql.cj.jdbc.Driver"); 
	    			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", member_ID, member_ID); 
	    			System.out.println("DB 연결 완료");
	    			
	    			stmt = conn.createStatement(); 
	    			
	    			String query = "select * from reserved_seat where screening_schedule_id = " + con + ";";
	    			System.out.println(query);
	    			srs1 = stmt.executeQuery(query);
	    			
	    			String seatid = "";
	    			while(srs1.next()) {
	    				seatid = srs1.getString("seat_id");
	    				if(seatid.equals(seatID)) {
	    					btn.setEnabled(false);
	    					btn.setBackground(Color.GRAY);
	    					if(seatid.equals(seat_ID) && screening_ID == compare_screeningID) {
	    						btn.setBackground(Color.BLUE);
	    						selectedButtons.add(btn);
	    					}
	                        break;
		    			}
	    			}
	    			
	    		} catch (ClassNotFoundException e) {
	    			System.out.println("JDBC 드라이버 로드 오류");
	    		} catch (SQLException e) {
	    			System.out.println("SQL 실행 오류");
	    			e.printStackTrace();
	    		} finally {
					try {
		                if (stmt != null) stmt.close();
		                if (conn != null) conn.close();
		                if (srs1 != null) srs1.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				}
	    		seatPanel.add(btn);
	        }
		}
		revalidate();
		repaint();
		cnt++;
	}
	
	private void showSeats2(ResultSet srs, String col1, String col2, String col3, int con) throws SQLException {
		int seatNumber, theaterID;
		int x, y;
		Font font;
		float fontSize;
		
		if(cnt != 0) {
			this.remove(seatPanel);
			this.remove(labelPanel);
		}
		
		while(srs.next()) {
			seatNumber = srs.getInt(col1);
			theaterID = srs.getInt(col2);
			x = srs.getInt(col3);
			y = seatNumber / x;
			
			labelPanel = new JPanel();
			labelPanel.setOpaque(false); 
			labelPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
			
			JLabel theater = new JLabel(theaterID + " theater");
			font = theater.getFont();
	        fontSize = font.getSize() + 10;
	        theater.setFont(font.deriveFont(fontSize));
	        
	        labelPanel.add(theater);
			add(labelPanel);
			
			seatPanel = new JPanel();
			seatPanel.setOpaque(false); 
			seatPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 700)); 
			seatPanel.setLayout(new GridLayout(x, y, 20, 20));
	        add(seatPanel);
	        
	        for(int i = 0; i < x * y; i++) {
	        	JButton btn = new JButton(theaterID + "-" + (i + 1));
	        	String seatID = btn.getText();
	 
	     
	        	btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(count == 0) {
							int response = JOptionPane.showConfirmDialog(null, "영화명 : " + movieName_ + "\n좌석 번호 : " + seatID + "\n가격 : 16,000", "선택 하시겠습니까?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(response == JOptionPane.YES_OPTION) {
					    		btn.setEnabled(false); 
		                        btn.setBackground(Color.red);
		                        paymentAmount += 16000;
		                        System.out.println(paymentAmount);
		                        selectedButtons.add(btn);
					    		revalidate();
					    		repaint();
					    		count++;
							}
						}
						else {
							JOptionPane.showMessageDialog(Reservation_Movie.this, "좌석 하나만 선택해주세요.");
						}
					}
	        	});
	        	
	        	Connection conn = null;
	    		Statement stmt = null;
	    		ResultSet srs1 = null;
	    		try {
	    			Class.forName("com.mysql.cj.jdbc.Driver"); 
	    			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", member_ID, member_ID); 
	    			System.out.println("DB 연결 완료");
	    			
	    			stmt = conn.createStatement(); 
	    			
	    			String query = "select * from reserved_seat where screening_schedule_id = " + con + ";";
	    			System.out.println(query);
	    			srs1 = stmt.executeQuery(query);
	    			
	    			String seatid = "";
	    			while(srs1.next()) {
	    				seatid = srs1.getString("seat_id");
	    				if(seatid.equals(seatID)) {
	    					btn.setEnabled(false);
	    					btn.setBackground(Color.GRAY);
	    					if(seatid.equals(seat_ID) && screening_ID == compare_screeningID) {
	    						btn.setBackground(Color.BLUE);
	    						selectedButtons.add(btn);
	    					}
	                        break;
		    			}
	    			}
	    			
	    		} catch (ClassNotFoundException e) {
	    			System.out.println("JDBC 드라이버 로드 오류");
	    		} catch (SQLException e) {
	    			System.out.println("SQL 실행 오류");
	    			e.printStackTrace();
	    		} finally {
					try {
		                if (stmt != null) stmt.close();
		                if (conn != null) conn.close();
		                if (srs1 != null) srs1.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				}
	    		seatPanel.add(btn);
	        }
		}
		revalidate();
		repaint();
		cnt++;
	}
}
