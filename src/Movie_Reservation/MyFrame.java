package Movie_Reservation;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	String member_ID;
	private LoginScreen loginScreen = new LoginScreen(this);
	private ManagerScreen managerScreen = new ManagerScreen(this);
	private MemberScreen memberScreen = new MemberScreen(this);
	private ReservationScreen reservationScreen = new ReservationScreen(this);
	private ReservedScreen reservedScreen;
	
	public MyFrame() {
		setTitle("MyFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		
		add(loginScreen);
		loginScreen.setVisible(true);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}
	
	public void showManagerScreen() {
		loginScreen.setVisible(false);
		memberScreen.setVisible(false);
		reservationScreen.setVisible(false);
		add(managerScreen);
		managerScreen.setVisible(true);
	}
	public void showLoginScreen() {
		managerScreen.setVisible(false);
		memberScreen.setVisible(false);
		reservationScreen.setVisible(false);
		loginScreen.setVisible(true);
	}
	public void showMemberScreen() {
		memberScreen.memberID = member_ID;
		loginScreen.setVisible(false);
		managerScreen.setVisible(false);
		reservationScreen.setVisible(false);
		add(memberScreen);
		memberScreen.setVisible(true);
	}
	public void showReservationScreen() {
		reservationScreen.memberID = member_ID;
		managerScreen.setVisible(false);
		memberScreen.setVisible(false);
		loginScreen.setVisible(false);
		add(reservationScreen);
		reservationScreen.setVisible(true);
	}
	public void showReserved() {
		reservedScreen = new ReservedScreen(this, member_ID);
		managerScreen.setVisible(false);
		memberScreen.setVisible(false);
		loginScreen.setVisible(false);
		reservationScreen.setVisible(false);
		add(reservedScreen);
		reservedScreen.setVisible(true);
	}
}

