package Forms;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import Classes.DatabaseConnection;
import Classes.User;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import java.awt.Component;

public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbHolidayType;
	private JLabel lblUsername;

	public void labelUsername(User user) {
		lblUsername.setText(user.getUsername());
	}

	public void labelUsername(String username) {
		lblUsername.setText(username);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserPage() {
		setTitle("User Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1037, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 1029, 48);
		contentPane.add(panel);

		lblUsername = new JLabel("Username");

		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblUsername.setBounds(914, -3, 127, 51);
		panel.add(lblUsername);
		lblUsername.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = lblUsername.getText();
				UpdateUserPage update = new UpdateUserPage();
				update.getUsername(username);
				update.setVisible(true);
				dispose();

			}
		});

		JLabel lblCompanyName = new JLabel("Elysian Trips");
		lblCompanyName.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblCompanyName.setBackground(Color.GRAY);
		lblCompanyName.setBounds(10, 0, 158, 38);
		panel.add(lblCompanyName);

		JLabel lblMyTrips = new JLabel("My Trips");
		lblMyTrips.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyReservations reservation = new MyReservations();
				reservation.username = lblUsername.getText();
				reservation.setVisible(true);
				dispose();

			}
		});
		lblMyTrips.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblMyTrips.setBounds(810, 1, 72, 47);
		panel.add(lblMyTrips);
		lblMyTrips.setCursor(new Cursor(Cursor.HAND_CURSOR));

		ImageIcon picture = new ImageIcon(getClass().getResource("/images/holidayPicture.jpg"));
		Image img = picture.getImage();
		Image scaledImg = img.getScaledInstance(1029, 511, Image.SCALE_SMOOTH);

		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel lblBeachImage = new JLabel(scaledIcon);
		lblBeachImage.setBounds(0, 43, 1029, 422);
		contentPane.add(lblBeachImage);

		JLabel lblContent = new JLabel("\"Need a break from the everyday — but don’t know where to start?\"\n");

		lblBeachImage.add(lblContent);

		lblContent.setForeground(Color.WHITE);
		lblContent.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblContent.setBounds(165, 174, 700, 25);

		JButton btnStart = new JButton("Start Your Travel");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(100, 100, 1037, 705);
			}
		});

		lblBeachImage.add(btnStart);

		btnStart.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnStart.setBounds(397, 237, 180, 30);
		btnStart.setOpaque(true);
		btnStart.setForeground(Color.WHITE);
		btnStart.setBorderPainted(false);
		btnStart.setBackground(Color.RED);
		btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel lblHolidayType = new JLabel("Holiday Type");
		lblHolidayType.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHolidayType.setBounds(35, 512, 102, 21);
		contentPane.add(lblHolidayType);

		JLabel lblHolidayStartDate = new JLabel("Holiday Start Date");
		lblHolidayStartDate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHolidayStartDate.setBounds(382, 512, 143, 21);
		contentPane.add(lblHolidayStartDate);

		JLabel lblHolidayEndDay = new JLabel("Holiday End Day");
		lblHolidayEndDay.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHolidayEndDay.setBounds(580, 512, 175, 21);
		contentPane.add(lblHolidayEndDay);

		JLabel lblNumberOfPeople = new JLabel("Number of People");
		lblNumberOfPeople.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNumberOfPeople.setBounds(765, 512, 133, 21);
		contentPane.add(lblNumberOfPeople);

		JComboBox cbHolidayDestination = new JComboBox();
		cbHolidayDestination.setFont(new Font("Segoe UI", Font.BOLD, 14));
		cbHolidayDestination.setBounds(207, 573, 133, 25);
		contentPane.add(cbHolidayDestination);

		cbHolidayType = new JComboBox<>();
		cbHolidayType.setFont(new Font("Segoe UI", Font.BOLD, 14));
		cbHolidayType.setBounds(35, 573, 133, 25);
		contentPane.add(cbHolidayType);

		DatabaseConnection db = new DatabaseConnection();

		ArrayList<String> holidayTypes = null;
		try {
			holidayTypes = db.getAllHolidayTypes();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (String type : holidayTypes) {
			cbHolidayType.addItem(type);
		}

		cbHolidayType.addActionListener(e -> {
			String selectedType = cbHolidayType.getSelectedItem().toString();

			try {
				ArrayList<String> destinations = db.getHolidayDestinationsByType(selectedType);
				cbHolidayDestination.removeAllItems();
				for (String dest : destinations) {
					cbHolidayDestination.addItem(dest);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		JLabel lblHolidayDestination = new JLabel("Holiday Destination");
		lblHolidayDestination.setDisplayedMnemonicIndex(1);
		lblHolidayDestination.setBounds(207, 512, 135, 21);
		contentPane.add(lblHolidayDestination);
		lblHolidayDestination.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JComboBox cbNumberPeople = new JComboBox();
		cbNumberPeople.setBounds(784, 573, 94, 25);
		contentPane.add(cbNumberPeople);

		cbNumberPeople.setFont(new Font("Segoe UI", Font.BOLD, 14));
		for (int n = 1; n <= 4; n++) {
			cbNumberPeople.addItem(n);
		}

		DatePicker DateTimeEnd = new DatePicker();
		DateTimeEnd.getComponentDateTextField().setLocation(607, 0);

		DateTimeEnd.getComponentToggleCalendarButton().setFont(new Font("Segoe UI", Font.BOLD, 12));
		DateTimeEnd.getComponentDateTextField().setFont(new Font("Segoe UI", Font.BOLD, 14));
		DateTimeEnd.setBounds(580, 573, 150, 25);
		contentPane.add(DateTimeEnd);
		DateTimeEnd.setEnabled(false);

		DatePicker DateTimeStart = new DatePicker();
		DateTimeStart.getComponentDateTextField().setLocation(393, 0);
		DateTimeStart.addDateChangeListener(new DateChangeListener() {
			@Override
			public void dateChanged(DateChangeEvent event) {
				if (event.getNewDate() != null) {
					DateTimeEnd.setEnabled(true);

					DateTimeEnd.getSettings().setDateRangeLimits(event.getNewDate(), null);
				} else {
					DateTimeEnd.setEnabled(false);
				}
			}
		});

		DateTimeStart.getComponentToggleCalendarButton().setFont(new Font("Segoe UI", Font.BOLD, 12));
		DateTimeStart.getComponentDateTextField().setFont(new Font("Segoe UI", Font.BOLD, 14));
		DateTimeStart.setBounds(382, 573, 150, 25);
		contentPane.add(DateTimeStart);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int holidayTypeId = 0;
				try {
					holidayTypeId = db.getHolidayTypeIdByName(cbHolidayType.getSelectedItem().toString());
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				int destinationId = 0;
				try {
					destinationId = db.getHolidayDestinationIdByName(cbHolidayDestination.getSelectedItem().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				LocalDate startDate = DateTimeStart.getDate();
				LocalDate endDate = DateTimeEnd.getDate();
				int numberofPeople = Integer.parseInt(cbNumberPeople.getSelectedItem().toString());

				boolean available = db.isRoomAvailable(holidayTypeId, destinationId, startDate, endDate,
						numberofPeople);

				if (available) {
					JOptionPane.showMessageDialog(contentPane, "There is a room(s) availiable.");
					HolidaySearchPage search = new HolidaySearchPage();
					search.HolidayType(cbHolidayType.getSelectedItem().toString());
					search.HolidayDestination(cbHolidayDestination.getSelectedItem().toString());
					search.StartDate(DateTimeStart);
					search.EndDate(DateTimeEnd);
					search.numberOfPeople(numberofPeople);
					search.loadHotelsToTableFromLabels();
					search.username = lblUsername.getText();
					search.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "There is no room(s) availiable .", "Not Availibility",
							JOptionPane.WARNING_MESSAGE);
					DateTimeStart.setDate(null);
					DateTimeEnd.setDate(null);
					cbNumberPeople.setSelectedIndex(0);

				}

			}
		});
		btnSearch.setOpaque(true);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSearch.setBorderPainted(false);
		btnSearch.setBackground(Color.RED);
		btnSearch.setBounds(906, 567, 107, 30);
		contentPane.add(btnSearch);

		btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));

		LocalDate today = LocalDate.now();
		DateTimeStart.getSettings().setDateRangeLimits(today, null);
		DateTimeEnd.getSettings().setDateRangeLimits(today, null);

	}

}
