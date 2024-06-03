package Movie_Reservation;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;

public class PrintTable extends JPanel {
	
	JPanel spacePanel, tablePanel, backPanel;
	Font font;
	JLabel tableName, content;
	JButton back, next;
	float fontSize, float_column;
	String column;
	int int_column;
	ManagerScreen screen;
	
	PrintTable(ManagerScreen managerScreen) {
		screen = managerScreen;
		setBackground(Color.white);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		showMemberTable();
	}
	
	private void showMemberTable() {
		
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("member 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
	    Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from member";
			srs = stmt.executeQuery(query);
			
			printData(srs, "member_id", "member_name", "phone_number", "email_address");
			
			System.out.println("select member 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("movie_info 테이블");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				screen.setVisible(true);
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showMovieInfoTable();
			}
        	
        });
	}
	
	private void showMovieInfoTable() {
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("movie_info 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from movie_info";
			srs = stmt.executeQuery(query);
			
			printData(srs, "movie_id", "movie_name", "screening_time", "screening_grade", "director_name", "actor_name", "genre", "movie_introduction", "release_date", "rating_information");
			
			System.out.println("select movie_info 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("screening_schedule 테이블");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showMemberTable();
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showScreening();
			}
        	
        });
	}
	
	private void showScreening() {
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("screening_schedule 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from screening_schedule";
			srs = stmt.executeQuery(query);
			
			printData(srs, "screening_schedule_id", "movie_id", "theater_id", "screening_start_date", "screening_day", "screening_num", "screening_start_time");
			
			System.out.println("select movie_info 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("theater 테이블");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showMovieInfoTable();
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showTheaterTable();
			}
        	
        });
	}
	
	private void showTheaterTable() {
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("theater 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from theater";
			srs = stmt.executeQuery(query);
			
			printData(srs, "theater_id", "number_of_seats", "row_seat", "column_seat", "screening_room_availability");
			
			System.out.println("select thater 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("seat 테이블");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showScreening();
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showSeatTable();
			}
        	
        });
	}
	
	private void showSeatTable() {
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("seat 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from seat";
			srs = stmt.executeQuery(query);
			
			printData(srs, "seat_id", "theater_id", "seat_availability");
			
			System.out.println("select seat 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("reservation 테이블");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showTheaterTable();
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showReservation();
			}
        	
        });
	}
	
	private void showReservation() {
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("reservation 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from reservation";
			srs = stmt.executeQuery(query);
			
			printData(srs, "reservation_id", "payment_method", "payment_status", "payment_amount", "member_id", "payment_date");
			
			System.out.println("select reservation 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("ticket 테이블");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showSeatTable();
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showTicketTable();
			}
        	
        });
	}
	
	private void showTicketTable() {
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("ticket 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from ticket";
			srs = stmt.executeQuery(query);
			
			printData(srs, "ticket_id", "screening_schedule_id", "theater_id", "seat_id", "reservation_id", "issuance_status", "selling_price", "standard_price");
			
			System.out.println("select ticket 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("reserved_seat 테이블");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showReservation();
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showReservedSeat();
			}
        	
        });
	}
	
	private void showReservedSeat() {
		removeAll();
		revalidate();
		repaint();
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
        
        JLabel head = new JLabel("전체 테이블 보기");
        font = head.getFont();
        fontSize = font.getSize() + 20;
        head.setFont(font.deriveFont(fontSize));
        spacePanel.add(head);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
        tablePanel = new JPanel();
        tablePanel.setOpaque(false); 
        tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        
		tableName = new JLabel("reserved_seat 테이블");
		tableName.setForeground(Color.blue);
		font = tableName.getFont();
        fontSize = font.getSize() + 8;
        tableName.setFont(font.deriveFont(fontSize));
        tablePanel.add(tableName);
		add(tablePanel);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); 
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			String query = "select * from reserved_seat";
			srs = stmt.executeQuery(query);
			
			printData2(srs, "reserved_id", "seat_id", "screening_schedule_id", "theater_id");
			
			System.out.println("select reserved_seat 쿼리 실행 완료");
		} catch (ClassNotFoundException e2) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e2) {
			System.out.println("SQL 실행오류");
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
		
		backPanel = new JPanel();
        backPanel.setOpaque(false); 
        backPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(new JLabel("         "));
        back = new JButton("뒤로 가기");
        next = new JButton("없음");
        backPanel.add(back);
        backPanel.add(new JLabel("         "));
        backPanel.add(next);
        add(backPanel);
        
        back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showTicketTable();
			}
        	
        });
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
        	
        });
	}
	
	public void printData(ResultSet srs, String col1, String col2, String col3) throws SQLException {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3); tablePanel.add(content);
		mainPanel.add(tablePanel);
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				column = srs.getString(col1);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				int_column = srs.getInt(col2);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				int_column = srs.getInt(col3);
				content = new JLabel("" + int_column);
				tablePanel.add(content);
			}
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
	
	public void printData(ResultSet srs, String col1, String col2, String col3, String col4) throws SQLException {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3 + " / "); tablePanel.add(content);
		content = new JLabel(col4); tablePanel.add(content);
		mainPanel.add(tablePanel);
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				column = srs.getString(col1);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				column = srs.getString(col2);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				column = srs.getString(col3);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col4.equals("")) {
				column = srs.getString(col4);
				content = new JLabel(column);
				tablePanel.add(content);
			}
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
	
	public void printData2(ResultSet srs, String col1, String col2, String col3, String col4) throws SQLException {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3 + " / "); tablePanel.add(content);
		content = new JLabel(col4); tablePanel.add(content);
		mainPanel.add(tablePanel);
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				column = srs.getString(col2);
				content = new JLabel(column);
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				int_column = srs.getInt(col3);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col4.equals("")) {
				int_column = srs.getInt(col4);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
	
	public void printData(ResultSet srs, String col1, String col2, String col3, String col4, String col5) throws SQLException {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3 + " / "); tablePanel.add(content);
		content = new JLabel(col4 + " / "); tablePanel.add(content);
		content = new JLabel(col5); tablePanel.add(content);
		mainPanel.add(tablePanel);
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				int_column = srs.getInt(col2);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				int_column = srs.getInt(col3);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col4.equals("")) {
				int_column = srs.getInt(col4);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col5.equals("")) {
				int_column = srs.getInt(col5);
				content = new JLabel("" + int_column);
				tablePanel.add(content);
			}
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
	
	public void printData(ResultSet srs, String col1, String col2, String col3, String col4, String col5, String col6) throws SQLException {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3 + " / "); tablePanel.add(content);
		content = new JLabel(col4 + " / "); tablePanel.add(content);
		content = new JLabel(col5 + " / "); tablePanel.add(content);
		content = new JLabel(col6); tablePanel.add(content);
		mainPanel.add(tablePanel);
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				column = srs.getString(col2);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				int_column = srs.getInt(col3);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col4.equals("")) {
				int_column = srs.getInt(col4);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col5.equals("")) {
				column = srs.getString(col5);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col5.equals("")) {
				column = srs.getString(col6);
				content = new JLabel(column);
				tablePanel.add(content);
			}
			
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
	
	public void printData(ResultSet srs, String col1, String col2, String col3, String col4, String col5, String col6, String col7) throws SQLException {
		
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3 + " / "); tablePanel.add(content);
		content = new JLabel(col4 + " / "); tablePanel.add(content);
		content = new JLabel(col5 + " / "); tablePanel.add(content);
		content = new JLabel(col6 + " / "); tablePanel.add(content);
		content = new JLabel(col7); tablePanel.add(content);
		mainPanel.add(tablePanel);
		
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				int_column = srs.getInt(col2);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				int_column = srs.getInt(col3);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col4.equals("")) {
				column = srs.getString(col4);
				content = new JLabel(column + " /");
				tablePanel.add(content);
			}
			if(!col5.equals("")) {
				column = srs.getString(col5);
				content = new JLabel(column + " /");
				tablePanel.add(content);
			}
			if(!col6.equals("")) {
				int_column = srs.getInt(col6);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col7.equals("")) {
				column = srs.getString(col7);
				content = new JLabel(column);
				tablePanel.add(content);
			}
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
	
public void printData(ResultSet srs, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8) throws SQLException {
		
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3 + " / "); tablePanel.add(content);
		content = new JLabel(col4 + " / "); tablePanel.add(content);
		content = new JLabel(col5 + " / "); tablePanel.add(content);
		content = new JLabel(col6 + " / "); tablePanel.add(content);
		content = new JLabel(col7 + " / "); tablePanel.add(content);
		content = new JLabel(col8); tablePanel.add(content);
		mainPanel.add(tablePanel);
		
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col4.equals("")) {
				column = srs.getString(col4);
				content = new JLabel(column + " /");
				tablePanel.add(content);
			}
			if(!col5.equals("")) {
				int_column = srs.getInt(col5);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col6.equals("")) {
				int_column = srs.getInt(col6);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col7.equals("")) {
				int_column = srs.getInt(col7);
				content = new JLabel("" + int_column + " /");
				tablePanel.add(content);
			}
			if(!col8.equals("")) {
				int_column = srs.getInt(col8);
				content = new JLabel("" + int_column);
				tablePanel.add(content);
			}
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
	
	public void printData(ResultSet srs, String col1, String col2, String col3, String col4, String col5, String col6, String col7, 
			String col8, String col9, String col10) throws SQLException {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        tablePanel = new JPanel();
		tablePanel.setOpaque(false); 
		tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        content = new JLabel(col1 + " / "); tablePanel.add(content);
		content = new JLabel(col2 + " / "); tablePanel.add(content);
		content = new JLabel(col3 + " / "); tablePanel.add(content);
		content = new JLabel(col4 + " / "); tablePanel.add(content);
		content = new JLabel(col5 + " / "); tablePanel.add(content);
		content = new JLabel(col6 + " / "); tablePanel.add(content);
		content = new JLabel(col7 + " / "); tablePanel.add(content);
		content = new JLabel(col8 + " / "); tablePanel.add(content);
		content = new JLabel(col9 + " / "); tablePanel.add(content);
		content = new JLabel(col10); tablePanel.add(content);
		mainPanel.add(tablePanel);
		
		while(srs.next()) {
			tablePanel = new JPanel();
			tablePanel.setOpaque(false); 
			tablePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
			
			if(!col1.equals("")) {
				int_column = srs.getInt(col1);
				content = new JLabel("" + int_column + " / ");
				tablePanel.add(content);
			}
			if(!col2.equals("")) {
				column = srs.getString(col2);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col3.equals("")) {
				column = srs.getString(col3);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col4.equals("")) {
				column = srs.getString(col4);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col5.equals("")) {
				column = srs.getString(col5);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col6.equals("")) {
				column = srs.getString(col6);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col7.equals("")) {
				column = srs.getString(col7);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col8.equals("")) {
				column = srs.getString(col8);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col9.equals("")) {
				column = srs.getString(col9);
				content = new JLabel(column + " / ");
				tablePanel.add(content);
			}
			if(!col10.equals("")) {
				float_column = srs.getFloat(col10);
				content = new JLabel("" + float_column + "");
				tablePanel.add(content);
			}
			mainPanel.add(tablePanel);
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scrollPane);
	}
}
