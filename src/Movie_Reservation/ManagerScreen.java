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
import javax.swing.JPanel;

public class ManagerScreen extends JPanel {
	JPanel spacePanel, backPanel;
	JLabel right, explainLabel;
	Font font;
	float fontSize;
	JButton initialize, input, delete, alter, print, back;
	InsertDataScreen insertScreen;
	DeleteDataScreen deleteScreen;
	UpdateDataScreen updateScreen;
	PrintTable printTable;
	
	ManagerScreen(MyFrame mf) {
		insertScreen = new InsertDataScreen(this);
		deleteScreen = new DeleteDataScreen(this);
		updateScreen = new UpdateDataScreen(this);
		printTable = new PrintTable(this);
		setBackground(Color.white);
		
		// 수직 방향으로 컴포넌트 배치
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		/*
	 		라벨과 버튼 사이의 간격 두기
		 */
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); 
        add(spacePanel);
		
		right = new JLabel("관리자 모드");
		/*
			권한 라벨 폰트 크기 키우기
		 */
		font = right.getFont();
		fontSize = font.getSize() + 40;
		right.setFont(font.deriveFont(fontSize));
		right.setAlignmentX(CENTER_ALIGNMENT);
		add(right);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100)); 
        add(spacePanel);
        
        /*
         	초기화 버튼 추가하기
         */
		initialize = new JButton("초기화");
		initialize.setAlignmentX(CENTER_ALIGNMENT);
		initialize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				executeInitializeSQL();
			}
			
		});
		add(initialize);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20)); 
        add(spacePanel);
		
        explainLabel = new JLabel("이 버튼을 클릭하면 새로 추가한 데이터들은 전부 지워지고 기존 샘플 데이터만이 남습니다.");
        explainLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(explainLabel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 80)); 
        add(spacePanel);
        
        
        /*
         	삽입 버튼 추가하기
         */
        input = new JButton("삽입");
        input.setAlignmentX(CENTER_ALIGNMENT);
        add(input);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20)); 
        add(spacePanel);
        
        explainLabel = new JLabel("이 버튼을 클릭하면 데이터를 삽입할 수 있습니다.");
        explainLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(explainLabel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 80)); 
        add(spacePanel);
        
        input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mf.add(insertScreen);
				insertScreen.setVisible(true);
			}
        	
        });
        
        /*
         	삭제 버튼 추가하기
         */
        delete = new JButton("삭제");
        delete.setAlignmentX(CENTER_ALIGNMENT);
        add(delete);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20)); 
        add(spacePanel);
        
        explainLabel = new JLabel("이 버튼을 클릭하면 데이터를 삭제할 수 있습니다.");
        explainLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(explainLabel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 80)); 
        add(spacePanel);
        
        delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mf.add(deleteScreen);
				deleteScreen.setVisible(true);
			}
        	
        });
        
        /*
         	변경 버튼 추가하기
         */
        alter = new JButton("변경");
        alter.setAlignmentX(CENTER_ALIGNMENT);
        add(alter);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20)); 
        add(spacePanel);
        
        explainLabel = new JLabel("이 버튼을 클릭하면 데이터를 변경할 수 있습니다.");
        explainLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(explainLabel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 80)); 
        add(spacePanel);
        
        alter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mf.add(updateScreen);
				updateScreen.setVisible(true);
			}
        	
        });
        
        /*
        	모든 테이블 출력 버튼 추가하기
         */
        print = new JButton("전체 테이블 보기");
        print.setAlignmentX(CENTER_ALIGNMENT);
        add(print);
        
        spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20)); 
        add(spacePanel);
        
        explainLabel = new JLabel("이 버튼을 클릭하면 모든 테이블을 볼 수 있습니다.");
        explainLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(explainLabel);
		
		spacePanel = new JPanel();
        spacePanel.setOpaque(false); 
        spacePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 80)); 
        add(spacePanel);
        
        print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mf.add(printTable);
				printTable.setVisible(true);
			}
        	
        });
        
        
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
	
	private void executeInitializeSQL() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root","1234"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			stmt.executeUpdate("drop table if exists movie_info, screening_schedule, ticket, theater, seat, member, reservation, reserved_seat;");
			System.out.println("drop 쿼리 실행 완료");
			stmt.executeUpdate(""
					+ "create table movie_info(\r\n"
					+ "	movie_id int not null auto_increment,\r\n"
					+ "    movie_name varchar(30) not null,\r\n"
					+ "    screening_time time not null,\r\n"
					+ "    screening_grade varchar(30) not null,\r\n"
					+ "    director_name varchar(30) not null,\r\n"
					+ "    actor_name varchar(30) not null,\r\n"
					+ "    genre varchar(30) not null,\r\n"
					+ "    movie_introduction varchar(100) not null,\r\n"
					+ "    release_date date not null,\r\n"
					+ "    rating_information float not null,\r\n"
					+ "    primary key(movie_id)\r\n"
					+ ");");
			System.out.println("create movie_info 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "create table theater(\r\n"
					+ "	theater_id int not null auto_increment,\r\n"
					+ "    number_of_seats int not null,\r\n"
					+ "    row_seat int not null,\r\n"
					+ "    column_seat int not null,\r\n"
					+ "    screening_room_availability tinyint(1) not null,\r\n"
					+ "    primary key(theater_id)\r\n"
					+ ");");
			System.out.println("create theater 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "create table screening_schedule(\r\n"
					+ "	screening_schedule_id int not null auto_increment,\r\n"
					+ "    movie_id int not null,\r\n"
					+ "    theater_id int not null,\r\n"
					+ "    screening_start_date datetime not null,\r\n"
					+ "    screening_day date not null,\r\n"
					+ "    screening_num int not null,\r\n"
					+ "    screening_start_time time not null,\r\n"
					+ "    foreign key(movie_id) references movie_info(movie_id),\r\n"
					+ "    foreign key(theater_id) references theater(theater_id),\r\n"
					+ "    primary key(screening_schedule_id)\r\n"
					+ ");");
			System.out.println("create screening_schedule 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "create table seat(\r\n"
					+ "	seat_id varchar(10) not null,\r\n"
					+ "    theater_id int not null,\r\n"
					+ "    seat_availability tinyint(1) not null,\r\n"
					+ "    foreign key(theater_id) references theater(theater_id),\r\n"
					+ "    primary key(seat_id)\r\n"
					+ ");");
			System.out.println("create seat 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "create table member(\r\n"
					+ "	member_id varchar(50) not null,\r\n"
					+ "    member_name varchar(45) not null,\r\n"
					+ "    phone_number varchar(20) not null,\r\n"
					+ "    email_address varchar(30) not null,\r\n"
					+ "    primary key(member_id)\r\n"
					+ ");");
			System.out.println("create member 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "create table reservation(\r\n"
					+ "	reservation_id int not null auto_increment,\r\n"
					+ "    payment_method varchar(45) not null,\r\n"
					+ "    payment_status tinyint(1) not null,\r\n"
					+ "    payment_amount int not null,\r\n"
					+ "    member_id varchar(50) not null,\r\n"
					+ "    payment_date datetime not null,\r\n"
					+ "    foreign key(member_id) references member(member_id),\r\n"
					+ "    primary key(reservation_id)\r\n"
					+ ");");
			System.out.println("create reservation 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "create table ticket(\r\n"
					+ "	ticket_id int not null auto_increment,\r\n"
					+ "    screening_schedule_id int not null,\r\n"
					+ "    theater_id int not null,\r\n"
					+ "    seat_id varchar(10) not null,\r\n"
					+ "    reservation_id int not null,\r\n"
					+ "    issuance_status tinyint(1) not null,\r\n"
					+ "    selling_price int not null,\r\n"
					+ "    standard_price int not null,\r\n"
					+ "	foreign key(screening_schedule_id) references screening_schedule(screening_schedule_id),\r\n"
					+ "    foreign key(seat_id) references seat(seat_id),\r\n"
					+ "    foreign key(reservation_id) references reservation(reservation_id),\r\n"
					+ "    primary key(ticket_id)\r\n"
					+ ");\r\n");
			System.out.println("create ticket 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "create table reserved_seat (\r\n"
					+ "	reserved_id int not null auto_increment,\r\n"
					+ "	seat_id varchar(10) not null,\r\n"
					+ "    screening_schedule_id int not null,\r\n"
					+ "    theater_id int not null,\r\n"
					+ "    foreign key(seat_id) references seat(seat_id),\r\n"
					+ "    foreign key(screening_schedule_id) references screening_schedule(screening_schedule_id),\r\n"
					+ "    foreign key(theater_id) references theater(theater_id),\r\n"
					+ "    primary key(reserved_id)\r\n"
					+ ");");
			System.out.println("create reserved_seat 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "insert into movie_info values"
					+ "(1, '인터스텔라', '02:00:00', '12세 이상 관람가', '크리스토퍼 놀란', '매튜 맥커너히', 'SF', '우주 여행과 인류의 생존에 관한 이야기', '2024-01-05 00:00:00', 8.5),"
					+ "(2, '어벤져스: 엔드게임', '03:30:00', '15세 이상 관람가', '안소니 루소', '로버트 다우니 주니어', 'SF', '마블 시네마틱 유니버스의 대단원을 마무리 짓는 작품', '2024-02-24 00:00:00', 9.0),"
					+ "(3, '위대한 쇼맨', '03:45:00', '전체 관람가', '마이클 그레이시', '휴 잭맨', '뮤지컬', '영화로 탄생한 위대한 서커스의 이야기', '2024-03-20 00:00:00', 7.8),"
					+ "(4, '캐치 미 이프 유 캔', '02:20:00', '전체 관람가', '스티븐 스필버그', '레오나르도 디카프리오', '드라마', '잊지 못할 인생의 이야기', '2024-04-25 00:00:00', 8.7),"
					+ "(5, '라라랜드', '02:40:00', '전체 관람가', '데이미언 셔젤', '엠마 스톤', '뮤지컬', '꿈을 향한 사랑과 열정의 이야기', '2024-05-07 00:00:00', 8.9),"
					+ "(6, '해리포터와 마법사의 돌', '03:00:00', '전체 관람가', '크리스 콜럼버스', '엠마 왓슨', '판타지', '마법과 모험으로 가득 찬 판타지 세계로 초대합니다', '2024-06-13 00:00:00', 8.1),"
					+ "(7, '타이타닉', '03:00:00', '12세 이상 관람가', '제임스 카메론', '레오나르도 디카프리오', '로맨스', '역사상 가장 비극적인 해상사고 중 하나를 바탕으로 한 로맨틱 드라마', '2024-07-18 00:00:00', 8.4),"
					+ "(8, '아바타', '02:30:00', '12세 이상 관람가', '제임스 카메론', '샘 워싱턴', 'SF', '화려한 비주얼과 메시지를 담은 전세계를 강타한 대작 SF 영화', '2024-08-17 00:00:00', 8.5),"
					+ "(9, '라이언 일병 구하기', '02:00:00', '15세 이상 관람가', '스티븐 스필버그', '톰 행크스', '전쟁', '제2차 세계대전 중의 한 특정 전투를 다룬 영화', '2024-09-24 00:00:00', 8.6),"
					+ "(10, '매트릭스', '03:00:00', '15세 이상 관람가', '라나 워쇼스키', '키아누 리브스', '액션', '가상 현실과 현실 사이의 갈등을 그린 대단히 혁신적인 영화', '2024-10-15 00:00:00', 8.7),"
					+ "(11, '쇼생크 탈출', '02:45:00', '15세 이상 관람가', '프랭크 다라본트', '팀 로빈스', '범죄', '감옥에서의 탈출을 그린 영화로 인생의 다시 시작을 다룬 작품', '2024-11-14 00:00:00', 9.3),"
					+ "(12, '인셉션', '03:00:00', '15세 이상 관람가', '크리스토퍼 놀란', '레오나르도 디카프리오', 'SF', '꿈 속에서의 치밀한 계획과 현실의 경계를 허무는 작전을 다룬 영화', '2024-12-21 00:00:00', 8.8);");
			System.out.println("insert movie_info 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "insert into theater values"
					+ "(1, 50, 10, 5, 1),"
					+ "(2, 40, 10, 4, 1),"
					+ "(3, 45, 9, 5, 1),"
					+ "(4, 60, 10, 6, 1);");
			System.out.println("insert theater 쿼리 실행 완료");
		
			stmt.executeUpdate(""
					+ "INSERT INTO seat "
					+ "VALUES\r\n"
					+ "('1-1', 1, 1), ('1-2', 1, 1), ('1-3', 1, 1), ('1-4', 1, 1), ('1-5', 1, 1), ('1-6', 1, 1), ('1-7', 1, 1), ('1-8', 1, 1), ('1-9', 1, 1), ('1-10', 1, 1),\r\n"
					+ "('1-11', 1, 1), ('1-12', 1, 1), ('1-13', 1, 1), ('1-14', 1, 1), ('1-15', 1, 1), ('1-16', 1, 1), ('1-17', 1, 1), ('1-18', 1, 1), ('1-19', 1, 1), ('1-20', 1, 1),\r\n"
					+ "('1-21', 1, 1), ('1-22', 1, 1), ('1-23', 1, 1), ('1-24', 1, 1), ('1-25', 1, 1), ('1-26', 1, 1), ('1-27', 1, 1), ('1-28', 1, 1), ('1-29', 1, 1), ('1-30', 1, 1),\r\n"
					+ "('1-31', 1, 1), ('1-32', 1, 1), ('1-33', 1, 1), ('1-34', 1, 1), ('1-35', 1, 1), ('1-36', 1, 1), ('1-37', 1, 1), ('1-38', 1, 1), ('1-39', 1, 1), ('1-40', 1, 1),\r\n"
					+ "('1-41', 1, 1), ('1-42', 1, 1), ('1-43', 1, 1), ('1-44', 1, 1), ('1-45', 1, 1), ('1-46', 1, 1), ('1-47', 1, 1), ('1-48', 1, 1), ('1-49', 1, 1), ('1-50', 1, 1),"
					+ ""
					+ "('2-1', 2, 1), ('2-2', 2, 1), ('2-3', 2, 1), ('2-4', 2, 1), ('2-5', 2, 1), ('2-6', 2, 1), ('2-7', 2, 1), ('2-8', 2, 1), ('2-9', 2, 1), ('2-10', 2, 1),\r\n"
					+ "('2-11', 2, 1), ('2-12', 2, 1), ('2-13', 2, 1), ('2-14', 2, 1), ('2-15', 2, 1), ('2-16', 2, 1), ('2-17', 2, 1), ('2-18', 2, 1), ('2-19', 2, 1), ('2-20', 2, 1),\r\n"
					+ "('2-21', 2, 1), ('2-22', 2, 1), ('2-23', 2, 1), ('2-24', 2, 1), ('2-25', 2, 1), ('2-26', 2, 1), ('2-27', 2, 1), ('2-28', 2, 1), ('2-29', 2, 1), ('2-30', 2, 1),\r\n"
					+ "('2-31', 2, 1), ('2-32', 2, 1), ('2-33', 2, 1), ('2-34', 2, 1), ('2-35', 2, 1), ('2-36', 2, 1), ('2-37', 2, 1), ('2-38', 2, 1), ('2-39', 2, 1), ('2-40', 2, 1),"
					+ ""
					+ "('3-1', 3, 1), ('3-2', 3, 1), ('3-3', 3, 1), ('3-4', 3, 1), ('3-5', 3, 1), ('3-6', 3, 1), ('3-7', 3, 1), ('3-8', 3, 1), ('3-9', 3, 1), ('3-10', 3, 1),\r\n"
					+ "('3-11', 3, 1), ('3-12', 3, 1), ('3-13', 3, 1), ('3-14', 3, 1), ('3-15', 3, 1), ('3-16', 3, 1), ('3-17', 3, 1), ('3-18', 3, 1), ('3-19', 3, 1), ('3-20', 3, 1),\r\n"
					+ "('3-21', 3, 1), ('3-22', 3, 1), ('3-23', 3, 1), ('3-24', 3, 1), ('3-25', 3, 1), ('3-26', 3, 1), ('3-27', 3, 1), ('3-28', 3, 1), ('3-29', 3, 1), ('3-30', 3, 1),\r\n"
					+ "('3-31', 3, 1), ('3-32', 3, 1), ('3-33', 3, 1), ('3-34', 3, 1), ('3-35', 3, 1), ('3-36', 3, 1), ('3-37', 3, 1), ('3-38', 3, 1), ('3-39', 3, 1), ('3-40', 3, 1),\r\n"
					+ "('3-41', 3, 1), ('3-42', 3, 1), ('3-43', 3, 1), ('3-44', 3, 1), ('3-45', 3, 1),"
					+ ""
					+ "('4-1', 4, 1), ('4-2', 4, 1), ('4-3', 4, 1), ('4-4', 4, 1), ('4-5', 4, 1), ('4-6', 4, 1), ('4-7', 4, 1), ('4-8', 4, 1), ('4-9', 4, 1), ('4-10', 4, 1),"
					+ "('4-11', 4, 1), ('4-12', 4, 1), ('4-13', 4, 1), ('4-14', 4, 1), ('4-15', 4, 1), ('4-16', 4, 1), ('4-17', 4, 1), ('4-18', 4, 1), ('4-19', 4, 1), ('4-20', 4, 1),\r\n"
					+ "('4-21', 4, 1), ('4-22', 4, 1), ('4-23', 4, 1), ('4-24', 4, 1), ('4-25', 4, 1), ('4-26', 4, 1), ('4-27', 4, 1), ('4-28', 4, 1), ('4-29', 4, 1), ('4-30', 4, 1),\r\n"
					+ "('4-31', 4, 1), ('4-32', 4, 1), ('4-33', 4, 1), ('4-34', 4, 1), ('4-35', 4, 1), ('4-36', 4, 1), ('4-37', 4, 1), ('4-38', 4, 1), ('4-39', 4, 1), ('4-40', 4, 1),\r\n"
					+ "('4-41', 4, 1), ('4-42', 4, 1), ('4-43', 4, 1), ('4-44', 4, 1), ('4-45', 4, 1), ('4-46', 4, 1), ('4-47', 4, 1), ('4-48', 4, 1), ('4-49', 4, 1), ('4-50', 4, 1),\r\n"
					+ "('4-51', 4, 1), ('4-52', 4, 1), ('4-53', 4, 1), ('4-54', 4, 1), ('4-55', 4, 1), ('4-56', 4, 1), ('4-57', 4, 1), ('4-58', 4, 1), ('4-59', 4, 1), ('4-60', 4, 1);\r\n"
					+ "");
			System.out.println("insert seat 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "insert into screening_schedule values "
					+ "(1, 1, 1, '2024-01-05 00:00:00', '2024-01-05', 1, '14:00:00'),"
					+ "(2, 1, 2, '2024-01-05 00:00:00', '2024-01-06', 2, '15:30:00'),"
					+ "(3, 1, 3, '2024-01-05 00:00:00', '2024-01-07', 3, '18:45:00'),"
					+ ""
					+ "(4, 2, 1, '2024-02-24 00:00:00', '2024-02-24', 1, '09:00:00'),"
					+ "(5, 2, 2, '2024-02-24 00:00:00', '2024-02-25', 2, '14:40:00'),"
					+ "(6, 2, 3, '2024-02-24 00:00:00', '2024-02-26', 3, '16:00:00'),"
					+ "(7, 2, 4, '2024-02-24 00:00:00', '2024-02-28', 4, '20:00:00'),"
					+ ""
					+ "(8, 3, 1, '2024-03-20 00:00:00', '2024-03-20', 1, '09:00:00'),"
					+ "(9, 3, 2, '2024-03-20 00:00:00', '2024-03-25', 2, '15:00:00'),"
					+ "(10, 3, 3, '2024-03-20 00:00:00', '2024-04-01', 3, '09:00:00'),"
					+ ""
					+ "(11, 4, 1, '2024-04-25 00:00:00', '2024-04-25', 1, '14:00:00'),"
					+ "(12, 4, 2, '2024-04-25 00:00:00', '2024-04-27', 2, '20:00:00'),"
					+ "(13, 4, 3, '2024-04-25 00:00:00', '2024-05-02', 3, '09:00:00'),"
					+ ""
					+ "(14, 5, 1, '2024-05-07 00:00:00', '2024-05-07', 1, '10:00:00'),"
					+ "(15, 5, 1, '2024-05-07 00:00:00', '2024-05-12', 2, '12:00:00'),"
					+ "(16, 5, 4, '2024-05-07 00:00:00', '2024-05-14', 3, '15:00:00'),"
					+ ""
					+ "(17, 6, 2, '2024-06-13 00:00:00', '2024-06-13', 1, '14:00:00'),"
					+ "(18, 6, 3, '2024-06-13 00:00:00', '2024-06-21', 2, '14:00:00'),"
					+ "(19, 6, 1, '2024-06-13 00:00:00', '2024-06-24', 3, '16:00:00'),"
					+ ""
					+ "(20, 7, 4, '2024-07-18 00:00:00', '2024-07-18', 1, '09:00:00'),"
					+ "(21, 7, 1, '2024-07-18 00:00:00', '2024-07-25', 2, '10:00:00'),"
					+ "(22, 7, 2, '2024-07-18 00:00:00', '2024-07-27', 3, '14:00:00'),"
					+ ""
					+ "(23, 8, 1, '2024-08-17 00:00:00', '2024-08-17', 1, '14:00:00'),"
					+ "(24, 8, 1, '2024-08-17 00:00:00', '2024-08-20', 2, '14:00:00'),"
					+ "(25, 8, 1, '2024-08-17 00:00:00', '2024-08-22', 3, '14:00:00'),"
					+ ""
					+ "(26, 9, 1, '2024-09-24 00:00:00', '2024-09-24', 1, '14:00:00'),"
					+ "(27, 9, 2, '2024-09-24 00:00:00', '2024-09-25', 2, '18:00:00'),"
					+ "(28, 9, 3, '2024-09-24 00:00:00', '2024-09-26', 3, '20:00:00'),"
					+ ""
					+ "(29, 10, 1, '2024-10-15 00:00:00', '2024-10-15', 1, '18:00:00'),"
					+ "(30, 10, 2, '2024-10-15 00:00:00', '2024-10-25', 2, '20:00:00'),"
					+ ""
					+ "(31, 11, 4, '2024-11-14 00:00:00', '2024-11-14', 1, '09:00:00'),"
					+ "(32, 11, 2, '2024-11-14 00:00:00', '2024-11-17', 2, '20:00:00'),"
					+ "(33, 11, 1, '2024-11-14 00:00:00', '2024-11-25', 3, '18:00:00'),"
					+ ""
					+ "(34, 12, 2, '2024-12-21 00:00:00', '2024-12-21', 1, '18:00:00');");
			System.out.println("insert screening_schedule 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "insert into member values"
					+ "('user1', '최명수', '010-9770-6915', 'audtn0099@naver.com'),"
					+ "('user2', '김지원', '010-1234-5678', 'zywon@gmail.com'),"
					+ "('user3', '장경준', '010-1231-3565', 'abcde@gmail.com'),"
					+ "('user4', '김보미', '010-6787-3454', 'sdfasd@gmail.com'),"
					+ "('user5', '최나영', '010-4145-5676', 'dfgddf@gmail.com'),"
					+ "('user6', '최지영', '010-4564-2345', 'asfsdf@gmail.com'),"
					+ "('user7', '최정득', '010-5676-0898', 'asdggd@gmail.com'),"
					+ "('user8', '최인종', '010-3454-2342', 'sdfsd@gmail.com'),"
					+ "('user9', '김지원', '010-5678-2222', 'erwee@gmail.com'),"
					+ "('user10', '박수용', '010-1453-1236', 'qweqwe@gmail.com'),"
					+ "('user11', '양강현', '010-5556-4534', 'fdgdfg@gmail.com'),"
					+ "('user12', '김지원', '010-4356-8964', 'asd@gmail.com');");
			System.out.println("insert member 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "insert into reservation values"
					+ "(1, '카드', 1, 32000, 'user1', '2024-01-01'),"
					+ "(2, '카드', 1, 16000, 'user1', '2024-01-02');");
			System.out.println("insert reservation 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "insert into ticket values"
					+ "(1, 1, 1, '1-1', 1, 1, 16000, 16000),"
					+ "(2, 1, 1, '1-2', 1, 1, 16000, 16000),"
					+ "(3, 2, 2, '2-3', 2, 1, 16000, 16000);");
			System.out.println("insert ticket 쿼리 실행 완료");
			
			stmt.executeUpdate(""
					+ "insert into reserved_seat(seat_id, screening_schedule_id, theater_id) values "
					+ "('1-1', 1, 1), ('1-2', 1, 1), ('2-3', 2, 2);");
			System.out.println("insert reserved_seat 쿼리 실행 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
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
