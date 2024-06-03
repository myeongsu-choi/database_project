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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertDataScreen extends JPanel {
	
	JPanel spacePanel, insertPanel, btnPanel, backPanel;
	JLabel table, column, label;
	JButton insertBtn1, insertBtn2, insertBtn3, insertBtn4, insertBtn5, insertBtn6, insertBtn7, insertBtn8, back;
	JTextField memberID, memberName, phoneNum, email;
	JTextField movieName, screeningTime, screeningGrade, directorName, actorName, genre, movieIntro, releaseDate, rating;
	JTextField movieID, theaterID, screening_start_date, screening_day, screening_num, screening_start_time;
	JTextField number_of_seats, row_seat, column_seat, screening_room_availability;
	JTextField seat_id, theater_id, seat_availability;
	JTextField payment_method, payment_status, payment_amount, member_id, payment_date;
	JTextField screening_schedule_id, theater_ID, seatID, reservation_id, issuance_status, selling_price, standard_price;
	JTextField reserved_id, seatid, screeningid, theaterid;
	/*
	 * reserved_seat 도 추가 해야함.
	 */
	Font font;
	float fontSize;
	
	InsertDataScreen(ManagerScreen mangerSceen) {
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); 
        add(spacePanel);
        
        JLabel head = new JLabel("데이터 삽입");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
		
		table = new JLabel("member 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("회원 아이디 : ");
		insertPanel.add(column);
		memberID = new JTextField(8);
		insertPanel.add(memberID);
		
		column = new JLabel("회원 이름 : ");
		insertPanel.add(column);
		memberName = new JTextField(8);
		insertPanel.add(memberName);
		
		column = new JLabel("핸드폰 번호 : ");
		insertPanel.add(column);
		phoneNum = new JTextField(8);
		insertPanel.add(phoneNum);
		
		column = new JLabel("회원 이메일 : ");
		insertPanel.add(column);
		email = new JTextField(8);
		insertPanel.add(email);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    
		insertBtn1 = new JButton("삽입");
		
		insertBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String ID, Name, PhoneNum, Email;
				ID = memberID.getText(); Name = memberName.getText(); PhoneNum = phoneNum.getText(); Email = email.getText();
				if(ID.isEmpty() || Name.isEmpty() || PhoneNum.isEmpty() || Email.isEmpty() ) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					Connection conn;
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into member value('" + ID + "', '" + Name + "', '" + PhoneNum + "', '" + Email + "');";
							
							stmt.executeUpdate(query);
							System.out.println("새로운 회원 데이터 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnPanel.add(insertBtn1);
		
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 200)); 
		
		table = new JLabel("movie_info 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("영화 이름 : ");
		insertPanel.add(column);
		movieName = new JTextField(8);
		insertPanel.add(movieName);
		
		column = new JLabel("상영 시간 : ");
		insertPanel.add(column);
		screeningTime = new JTextField(8);
		insertPanel.add(screeningTime);
		
		column = new JLabel("상영 등급 : ");
		insertPanel.add(column);
		screeningGrade = new JTextField(8);
		insertPanel.add(screeningGrade);
		
		column = new JLabel("감독명 : ");
		insertPanel.add(column);
		directorName = new JTextField(8);
		insertPanel.add(directorName);
		
		column = new JLabel("배우명 : ");
		insertPanel.add(column);
		actorName = new JTextField(8);
		insertPanel.add(actorName);
		
		column = new JLabel("장르 : ");
		insertPanel.add(column);
		genre = new JTextField(8);
		insertPanel.add(genre);
		
		column = new JLabel("영화 소개 : ");
		insertPanel.add(column);
		movieIntro = new JTextField(8);
		insertPanel.add(movieIntro);
		
		column = new JLabel("개봉 일자 : ");
		insertPanel.add(column);
		releaseDate = new JTextField(8);
		insertPanel.add(releaseDate);
		
		column = new JLabel("평점 정보 : ");
		insertPanel.add(column);
		rating = new JTextField(8);
		insertPanel.add(rating);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		insertBtn2 = new JButton("삽입");
		
		insertBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String Name, Time, Grade, d_Name, a_Name, Genre, Intro, Date, Rate;
				Name = movieName.getText(); Time = screeningTime.getText(); Grade = screeningGrade.getText(); d_Name = directorName.getText();
				a_Name = actorName.getText(); Genre = genre.getText(); Intro = movieIntro.getText(); Date = releaseDate.getText();
				Rate = rating.getText();
				
				if(Name.isEmpty() || Time.isEmpty() || Grade.isEmpty() || d_Name.isEmpty() || a_Name.isEmpty() 
						|| Genre.isEmpty() || Intro.isEmpty() || Date.isEmpty() || Rate.isEmpty()) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					Connection conn = null;
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into movie_info(movie_name, screening_time, screening_grade, director_name, actor_name, genre, movie_introduction, release_date, rating_information)"
									+ " values(" + "'" + Name + "', '" + Time + "', '" + Grade + "', '" + d_Name + "', '" + a_Name + "', '" + Genre + "', '" + Intro + "', '" + Date + "', '" + Rate + "');"; 
							
							stmt.executeUpdate(query);
							System.out.println("새로운 영화 정보 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
			                if (stmt != null) stmt.close();
			                if (conn != null) conn.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					}
				}
			}
			
		});
		
		btnPanel.add(insertBtn2);
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
		
		table = new JLabel("screening_schedule 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("영화 번호 : ");
		insertPanel.add(column);
		movieID = new JTextField(8);
		insertPanel.add(movieID);
		
		column = new JLabel("상영관 번호 : ");
		insertPanel.add(column);
		theaterID = new JTextField(8);
		insertPanel.add(theaterID);
		
		column = new JLabel("상영시작일 : ");
		insertPanel.add(column);
		screening_start_date = new JTextField(8);
		insertPanel.add(screening_start_date);
		
		column = new JLabel("상영요일 : ");
		insertPanel.add(column);
		screening_day = new JTextField(8);
		insertPanel.add(screening_day);
		
		column = new JLabel("상영회차 : ");
		insertPanel.add(column);
		screening_num = new JTextField(8);
		insertPanel.add(screening_num);
		
		column = new JLabel("상영시작시간 : ");
		insertPanel.add(column);
		screening_start_time = new JTextField(8);
		insertPanel.add(screening_start_time);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		insertBtn3 = new JButton("삽입");
		
		insertBtn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String m_ID, t_ID, s_StartDay, s_Day, s_Num, s_StartTime;
				m_ID = movieID.getText(); t_ID = theaterID.getText(); s_StartDay = screening_start_date.getText(); s_Day = screening_day.getText();
				s_Num = screening_num.getText(); s_StartTime = screening_start_time.getText();
				
				if(m_ID.isEmpty() || t_ID.isEmpty() || s_StartDay.isEmpty() || s_Day.isEmpty() || s_Num.isEmpty() 
						|| s_StartTime.isEmpty()) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					int m_ID2 = Integer.parseInt(m_ID);
					int t_ID2 = Integer.parseInt(t_ID);
					int s_Num2 = Integer.parseInt(s_Num);
					Connection conn = null;
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into screening_schedule(movie_id, theater_id, screening_start_date, screening_day, screening_num, screening_start_time)"
									+ " values(" + "'" + m_ID2 + "', '" + t_ID2 + "', '" + s_StartDay + "', '" + s_Day + "', '" + s_Num2 + "', '" + s_StartTime + "');"; 
							
							stmt.executeUpdate(query);
							System.out.println("새로운 상영 정보 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
			                if (stmt != null) stmt.close();
			                if (conn != null) conn.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					}
				}
			}
		});
		
		btnPanel.add(insertBtn3);
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
		
		table = new JLabel("theater 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("총 좌석 수 : ");
		insertPanel.add(column);
		number_of_seats = new JTextField(8);
		insertPanel.add(number_of_seats);
		
		column = new JLabel("가로 좌석 수 : ");
		insertPanel.add(column);
		row_seat = new JTextField(8);
		insertPanel.add(row_seat);
		
		column = new JLabel("세로 좌석 수 : ");
		insertPanel.add(column);
		column_seat = new JTextField(8);
		insertPanel.add(column_seat);
		
		column = new JLabel("상영관사용여부 : ");
		insertPanel.add(column);
		screening_room_availability = new JTextField(8);
		insertPanel.add(screening_room_availability);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		insertBtn4 = new JButton("삽입");
		
		insertBtn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String total, row, column, avail;
				total = number_of_seats.getText(); row = row_seat.getText(); column = column_seat.getText(); avail = screening_room_availability.getText();
				if(total.isEmpty() || row.isEmpty() || column.isEmpty() || avail.isEmpty()) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					int total2 = Integer.parseInt(total);
					int row2 = Integer.parseInt(row);
					int column2 = Integer.parseInt(column);
					int avail2 = Integer.parseInt(avail);
					Connection conn = null;
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into theater(number_of_seats, row_seat, column_seat, screening_room_availability)"
									+ " values(" + "'" + total2 + "', '" + row2 + "', '" + column2 + "', '" + avail2 + "');"; 
							
							stmt.executeUpdate(query);
							System.out.println("새로운 상영 정보 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
			                if (stmt != null) stmt.close();
			                if (conn != null) conn.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					}
				}
			}
		});
		
		btnPanel.add(insertBtn4);
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
		
		table = new JLabel("seat 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("좌석 번호 : ");
		insertPanel.add(column);
		seat_id = new JTextField(8);
		insertPanel.add(seat_id);
		
		column = new JLabel("상영관 번호 : ");
		insertPanel.add(column);
		theater_id = new JTextField(8);
		insertPanel.add(theater_id);
		
		column = new JLabel("좌석 사용 여부 : ");
		insertPanel.add(column);
		seat_availability = new JTextField(8);
		insertPanel.add(seat_availability);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		insertBtn5 = new JButton("삽입");
		
		insertBtn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s_ID, t_ID, avail;
				s_ID = seat_id.getText(); t_ID = theater_id.getText(); avail = seat_availability.getText();
				if(s_ID.isEmpty() || t_ID.isEmpty() || avail.isEmpty()) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					int t_ID2 = Integer.parseInt(t_ID);
					int avail2 = Integer.parseInt(avail);
					Connection conn = null;
					
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into seat"
									+ " values(" + "'" + s_ID + "', '" + t_ID2 + "', '" + avail2 + "');"; 
							
							stmt.executeUpdate(query);
							System.out.println("새로운 좌석 정보 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
			                if (stmt != null) stmt.close();
			                if (conn != null) conn.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					}
				}
			}
		});
		
		btnPanel.add(insertBtn5);
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
		
		table = new JLabel("reservation 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("결제 방법 : ");
		insertPanel.add(column);
		payment_method = new JTextField(8);
		insertPanel.add(payment_method);
		
		column = new JLabel("결제 상태 : ");
		insertPanel.add(column);
		payment_status = new JTextField(8);
		insertPanel.add(payment_status);
		
		column = new JLabel("결제 금액 : ");
		insertPanel.add(column);
		payment_amount = new JTextField(8);
		insertPanel.add(payment_amount);
		
		column = new JLabel("회원 아이디 : ");
		insertPanel.add(column);
		member_id = new JTextField(8);
		insertPanel.add(member_id);
		
		column = new JLabel("결제 일자 : ");
		insertPanel.add(column);
		payment_date = new JTextField(8);
		insertPanel.add(payment_date);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		insertBtn6 = new JButton("삽입");
		
		insertBtn6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String method, status, amount, m_ID, date;
				method = payment_method.getText(); status = payment_status.getText(); amount = payment_amount.getText(); 
				m_ID = member_id.getText(); date = payment_date.getText();
				if(method.isEmpty() || status.isEmpty() || amount.isEmpty() ||
						m_ID.isEmpty() || date.isEmpty()) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					int status2 = Integer.parseInt(status);
					int amount2 = Integer.parseInt(amount);
					Connection conn = null;
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into reservation(payment_method, payment_status, payment_amount, member_id, payment_date)"
									+ " values(" + "'" + method + "', '" + status2 + "', '" + amount2 + "', '" + m_ID + "', '" + date + "');"; 
							
							stmt.executeUpdate(query);
							System.out.println("새로운 예매 정보 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
			                if (stmt != null) stmt.close();
			                if (conn != null) conn.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					}
				}
			}
		});
		
		btnPanel.add(insertBtn6);
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 200)); 
		
		table = new JLabel("ticket 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("상영일정번호 : ");
		insertPanel.add(column);
		screening_schedule_id = new JTextField(8);
		insertPanel.add(screening_schedule_id);
		
		column = new JLabel("상영관번호 : ");
		insertPanel.add(column);
		theater_ID = new JTextField(8);
		insertPanel.add(theater_ID);
		
		column = new JLabel("좌석번호 : ");
		insertPanel.add(column);
		seatID = new JTextField(8);
		insertPanel.add(seatID);
		
		column = new JLabel("예매번호 : ");
		insertPanel.add(column);
		reservation_id = new JTextField(8);
		insertPanel.add(reservation_id);
		
		column = new JLabel("발권여부 : ");
		insertPanel.add(column);
		issuance_status = new JTextField(8);
		insertPanel.add(issuance_status);
		
		column = new JLabel("표준가격 : ");
		insertPanel.add(column);
		selling_price = new JTextField(8);
		insertPanel.add(selling_price);
		
		column = new JLabel("판매가격 : ");
		insertPanel.add(column);
		standard_price = new JTextField(8);
		insertPanel.add(standard_price);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		insertBtn7 = new JButton("삽입");
		
		insertBtn7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s_ID, t_ID, seat_ID, r_ID, status, selling_price1, standard_price1;
				s_ID = screening_schedule_id.getText(); t_ID = theater_ID.getText(); seat_ID = seatID.getText(); r_ID = reservation_id.getText();
				status = issuance_status.getText(); selling_price1 = selling_price.getText(); standard_price1 = standard_price.getText(); 
				
				if(s_ID.isEmpty() || t_ID.isEmpty() || seat_ID.isEmpty() || r_ID.isEmpty() || status.isEmpty() 
						|| selling_price1.isEmpty() || standard_price1.isEmpty()) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					int s_id = Integer.parseInt(s_ID);
					int t_id = Integer.parseInt(t_ID);
					int r_id = Integer.parseInt(r_ID);
					int status2 = Integer.parseInt(status);
					int selling_price2 = Integer.parseInt(selling_price1);
					int standard_price2 = Integer.parseInt(standard_price1);
					
					Connection conn = null;
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into ticket(screening_schedule_id, theater_id, seat_id, reservation_id, issuance_status, selling_price, standard_price)"
									+ " values(" + "'" + s_id + "', '" + t_id + "', '" + seat_ID + "', '" + r_id + "', '" + status2 + "', '" + selling_price2 + "', '" + standard_price2 + "');"; 
							
							stmt.executeUpdate(query);
							System.out.println("새로운 티켓 정보 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
			                if (stmt != null) stmt.close();
			                if (conn != null) conn.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					}
				}
			}
			
		});
		
		btnPanel.add(insertBtn7);
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); 
        add(spacePanel);
        
        insertPanel = new JPanel();
        insertPanel.setOpaque(false); 
        insertPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 70)); 
		
		table = new JLabel("reserved_seat 테이블 >>> ");
		insertPanel.add(table);
		
		column = new JLabel("예약된 좌석 번호 : ");
		insertPanel.add(column);
		seatid = new JTextField(8);
		insertPanel.add(seatid);
		
		column = new JLabel("상영번호 : ");
		insertPanel.add(column);
		screeningid = new JTextField(8);
		insertPanel.add(screeningid);
		
		column = new JLabel("상영관번호 : ");
		insertPanel.add(column);
		theaterid = new JTextField(8);
		insertPanel.add(theaterid);
		
		btnPanel = new JPanel();
		btnPanel.setOpaque(false); 
	    btnPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
	    btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		insertBtn8 = new JButton("삽입");
		
		insertBtn8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String seatID, scheduleID, theaterID;
				seatID = seatid.getText(); scheduleID = screeningid.getText(); theaterID = theaterid.getText();
				
				if(seatID.isEmpty() || scheduleID.isEmpty() || theaterID.isEmpty()) {
					JOptionPane.showMessageDialog(InsertDataScreen.this, "모든 데이터를 삽입하십시오.");
				}
				else {
					int scheduleID2 = Integer.parseInt(scheduleID);
					int theaterID2 = Integer.parseInt(theaterID);
					
					Connection conn = null;
					Statement stmt = null;
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "데이터를 삽입하겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION) {
							Class.forName("com.mysql.cj.jdbc.Driver"); 
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
							System.out.println("DB 연결 완료");
							stmt = conn.createStatement();
							String query = "insert into reserved_seat(seat_id, screening_schedule_id, theater_id)"
									+ " values(" + "'" + seatID + "', '" + scheduleID2 + "', '" + theaterID2 + "');"; 
							
							stmt.executeUpdate(query);
							System.out.println("새로운 예약된 좌석 정보 삽입 완료");
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("JDBC 드라이버 로드 오류");
					} catch (SQLException e2) {
						System.out.println("SQL 실행오류");
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL 실행 오류: " + e2.getMessage(), "경고", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
			                if (stmt != null) stmt.close();
			                if (conn != null) conn.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
					}
				}
			}
			
		});
		
		btnPanel.add(insertBtn8);
		label = new JLabel("    		");
	    btnPanel.add(label);
		add(insertPanel);
		add(btnPanel);
		
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
				mangerSceen.setVisible(true);
			}
        	
        });
	}
}
