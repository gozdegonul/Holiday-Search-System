package Classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseConnection {

	// get connection
	public Connection getConnected() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/voyagevista", "root", "Bozcaada2004");
	}

	// check username and password for login
	public boolean Login(String username, String password) throws SQLException {
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			return rs.next();

		}
	}

	//Save user
	public void saveUser(User user) throws SQLException {
		String query = "INSERT INTO users VALUES (?, ?, ?, ?, ? ,?)";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getName());
		ps.setString(3, user.getSurname());
		ps.setString(4, user.getGender());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPassword());
		ps.executeUpdate();

	}

	// check email is taken before
	public boolean isEmailRegistered(String email) throws SQLException {
		String query = "SELECT COUNT(*) FROM users WHERE email = ?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1) > 0;
		}

		return false;
	}

	// check username is taken before
	public boolean isUsernameRegistered(String username) throws SQLException {
		String query = "SELECT COUNT(*) FROM users WHERE username = ?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1) > 0;
		}

		return false;
	}

	// get all holiday types
	public ArrayList<String> getAllHolidayTypes() throws SQLException {
		ArrayList<String> holidayTypes = new ArrayList<>();

		String query = "SELECT DISTINCT type_name FROM holidaytypes";

		try (Connection conn = getConnected();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				holidayTypes.add(rs.getString("type_name"));
			}
		}

		return holidayTypes;
	}

	// get holiday types id from holiday type name for get destinations
	public int getHolidayTypeIdByName(String typeName) throws SQLException {
		String query = "SELECT holiday_type_id FROM holidaytypes WHERE type_name = ?";

		try (Connection conn = getConnected(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, typeName);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("holiday_type_id");
			} else {
				throw new SQLException("Type not found: " + typeName);
			}
		}
	}

	//Convert Destination name to ID
	public int getHolidayDestinationIdByName(String destinationName) throws SQLException {
		String query = "SELECT destination_id FROM destinations WHERE destination_name = ?";

		try (Connection conn = getConnected(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, destinationName);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("destination_id");
			} else {
				throw new SQLException("Type not found: " + destinationName);
			}
		}
	}

	// get all hotels destinations related with hotel types

	public ArrayList<String> getHolidayDestinationsByType(String typeName) throws SQLException {
		ArrayList<String> destinations = new ArrayList<>();

		int typeId = getHolidayTypeIdByName(typeName);

		String query = "SELECT DISTINCT destination_name FROM destinations WHERE holiday_type_id = ?";

		try (Connection conn = getConnected(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, typeId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				destinations.add(rs.getString("destination_name"));
			}
		}

		return destinations;
	}
	
	//Convert Hotel name to ID
	public int getHotelIdByName(String hotelName) throws SQLException {
		String query = "SELECT hotel_id FROM hotels WHERE hotel_name = ?";
		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, hotelName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("hotel_id");
			} else {
				throw new SQLException("Hotel not found: " + hotelName);
			}
		}
	}

	//Convert Room name to ID
	public int getRoomIdByName(String RoomName) throws SQLException {
		String query = "SELECT room_id FROM rooms WHERE room_type_name = ?";
		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, RoomName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("room_id");
			} else {
				throw new SQLException("Room not found: " + RoomName);
			}
		}
	}

	//Get RoomId by using RoomType and HotelID
	public int getRoomIdByHotelIdAndRoomType(int hotelId, String roomTypeName) throws SQLException {
		String query = "SELECT room_id FROM rooms WHERE hotel_id = ? AND room_type_name = ?";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, hotelId);
			ps.setString(2, roomTypeName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("room_id");
			} else {
				return -1;
			}
		}
	}

	//Get user personal information
	public User getUserInfo(String username) {
		String query = "SELECT * FROM users WHERE username = ?";
		User user = null;

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	//Check Room availability

	public boolean isRoomAvailable(int holidayTypeId, int destinationId, LocalDate startDate, LocalDate endDate,
			int numberOfPeople) {
		String query = "SELECT r.room_id " + "FROM rooms r " + "JOIN hotels h ON r.hotel_id = h.hotel_id "
				+ "JOIN destinations d ON h.destination_id = d.destination_id "
				+ "JOIN holidaytypes ht ON d.holiday_type_id = ht.holiday_type_id "
				+ "JOIN roomavailability ra ON r.room_id = ra.room_id " + "WHERE ht.holiday_type_id = ? "
				+ "AND d.destination_id = ? " + "AND ra.available_date BETWEEN ? AND ? " + "AND r.capacity >= ? "
				+ "GROUP BY r.room_id " + "HAVING MIN(ra.available_quantity) >= 1 " + "LIMIT 1";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, holidayTypeId);
			ps.setInt(2, destinationId);
			ps.setDate(3, java.sql.Date.valueOf(startDate));
			ps.setDate(4, java.sql.Date.valueOf(endDate));
			ps.setInt(5, numberOfPeople);

			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//get rooms by using hotelId and roomAvailibity
	public List<Rooms> getRoomsByHotelIdAndAvailability(int hotelId, LocalDate startDate, LocalDate endDate,
			int numberOfPeople) {
		String query = "SELECT r.room_id, r.room_type_name, r.room_features, r.price_per_person, r.capacity, "
				+ "MIN(ra.available_quantity) AS min_available_quantity " + "FROM rooms r "
				+ "JOIN roomavailability ra ON r.room_id = ra.room_id " + "WHERE r.hotel_id = ? "
				+ "AND r.capacity >= ? " + "AND ra.available_date BETWEEN ? AND ? "
				+ "GROUP BY r.room_id, r.room_type_name, r.room_features, r.price_per_person, r.capacity "
				+ "ORDER BY r.price_per_person ASC";

		List<Rooms> rooms = new ArrayList<>();

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, hotelId);
			ps.setInt(2, numberOfPeople);
			ps.setDate(3, java.sql.Date.valueOf(startDate));
			ps.setDate(4, java.sql.Date.valueOf(endDate));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Rooms room = new Rooms();
				room.setRoomId(rs.getInt("room_id"));
				room.setRoomTypeName(rs.getString("room_type_name"));
				room.setRoomFeatures(rs.getString("room_features"));
				room.setPricePerPerson(rs.getDouble("price_per_person"));
				room.setCapacity(rs.getInt("capacity"));
				room.setMinAvailableQuantity(rs.getInt("min_available_quantity"));

				rooms.add(room);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rooms;
	}

	//Fill the room informations
	public void fillRoomLabels(List<Rooms> rooms, LocalDate startDate, LocalDate endDate, int numberOfPeople,
			JLabel lblRoom1, JLabel lblRoomInfo1, JLabel lblRoomCount1, JLabel lblPrice1, JLabel lblRoom2,
			JLabel lblRoomInfo2, JLabel lblRoomCount2, JLabel lblPrice2, JButton btnBuyOption1, JButton btnBuyOption2) {

		lblRoom1.setText("");
		lblRoomInfo1.setText("");
		lblRoomCount1.setText("");
		lblPrice1.setText("");
		lblRoom2.setText("");
		lblRoomInfo2.setText("");
		lblRoomCount2.setText("");
		lblPrice2.setText("");

		long nights = ChronoUnit.DAYS.between(startDate, endDate);

		for (int i = 0; i < 2; i++) {
			if (rooms.size() > i) {
				Rooms room = rooms.get(i);

				if (i == 0) {
					lblRoom1.setText(room.getRoomTypeName());
					lblRoomInfo1.setText(room.getRoomFeatures());
					if (room.getMinAvailableQuantity() >= 1) {
						lblRoomCount1.setText("Left Rooms: " + room.getMinAvailableQuantity());

						double totalPrice = room.getPricePerPerson() * numberOfPeople * nights;
						lblPrice1.setText(String.format("%.2f €", totalPrice));
						btnBuyOption1.setEnabled(true);
					} else {
						lblRoomCount1.setText("Not Available");
						lblPrice1.setText("Not Available");
						btnBuyOption1.setEnabled(false);
					}
				} else if (i == 1) {
					lblRoom2.setText(room.getRoomTypeName());
					lblRoomInfo2.setText(room.getRoomFeatures());
					if (room.getMinAvailableQuantity() >= 1) {
						lblRoomCount2.setText("Left Rooms: " + room.getMinAvailableQuantity());
						double totalPrice = room.getPricePerPerson() * numberOfPeople * nights;
						lblPrice2.setText(String.format("%.2f €", totalPrice));
						btnBuyOption2.setEnabled(true);
					} else {
						lblRoomCount2.setText("Not Available");
						lblPrice2.setText("Not Available");
						btnBuyOption2.setEnabled(false);
					}
				}
			}
		}
	}

	//get reservations from user
	public boolean insertReservation(String username, int holidayDestination, int hotelId, int roomId,
			LocalDate startDate, LocalDate endDate, int numberOfPeople, double totalPrice) {

		String query = "INSERT INTO reservations (username, destination_id, hotel_id , room_id,  start_date, end_date, number_of_people, total_price) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ? ,?)";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, username);
			ps.setInt(2, holidayDestination);
			ps.setInt(3, hotelId);
			ps.setInt(4, roomId);
			ps.setDate(5, java.sql.Date.valueOf(startDate));
			ps.setDate(6, java.sql.Date.valueOf(endDate));
			ps.setInt(7, numberOfPeople);
			ps.setDouble(8, totalPrice);
			int rowsAffected = ps.executeUpdate(); // Burada executeUpdate kullanılmalı
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
    
	//decrease roomAvailibility after insert reservation
	public void decreaseRoomAvailability(int roomId, LocalDate startDate, LocalDate endDate) {
		String query = "UPDATE roomavailability " + "SET available_quantity = available_quantity - 1 "
				+ "WHERE room_id = ? " + "AND available_date BETWEEN ? AND ? " + "AND available_quantity > 0";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, roomId);
			ps.setDate(2, java.sql.Date.valueOf(startDate));
			ps.setDate(3, java.sql.Date.valueOf(endDate));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Get upcoming reservations by using username 
	public void loadUpcomingReservations(String username, JTable reservationTable) {
		LocalDate today = LocalDate.now();

		String query = "SELECT r.reservation_id, h.hotel_name, rm.room_type_name, r.start_date, r.end_date, r.number_of_people, r.total_price "
				+ "FROM reservations r " + "JOIN hotels h ON r.hotel_id = h.hotel_id "
				+ "JOIN rooms rm ON r.room_id = rm.room_id " + "WHERE r.username = ? AND r.start_date >= ? "
				+ "ORDER BY r.start_date ASC";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, username);
			ps.setDate(2, java.sql.Date.valueOf(today));

			ResultSet rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();
			model.setRowCount(0);

			while (rs.next()) {
				int reservationId = rs.getInt("reservation_id");
				String hotelName = rs.getString("hotel_name");
				String roomType = rs.getString("room_type_name");
				Date startDate = rs.getDate("start_date");
				Date endDate = rs.getDate("end_date");
				int numberOfPeople = rs.getInt("number_of_people");
				double totalPrice = rs.getDouble("total_price");

				model.addRow(new Object[] { reservationId, hotelName, roomType, startDate, endDate, numberOfPeople,
						String.format("%.2f €", totalPrice) });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//get past reservations by using username
	public void loadPastReservations(String username, JTable reservationTable) {
		LocalDate today = LocalDate.now();

		String query = "SELECT r.reservation_id, h.hotel_name, rm.room_type_name, r.start_date, r.end_date, r.number_of_people, r.total_price "
				+ "FROM reservations r " + "JOIN hotels h ON r.hotel_id = h.hotel_id "
				+ "JOIN rooms rm ON r.room_id = rm.room_id " + "WHERE r.username = ? AND r.end_date < ? "
				+ "ORDER BY r.start_date DESC";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, username);
			ps.setDate(2, java.sql.Date.valueOf(today));

			ResultSet rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();
			model.setRowCount(0);

			while (rs.next()) {
				int reservationId = rs.getInt("reservation_id");
				String hotelName = rs.getString("hotel_name");
				String roomType = rs.getString("room_type_name");
				Date startDate = rs.getDate("start_date");
				Date endDate = rs.getDate("end_date");
				int numberOfPeople = rs.getInt("number_of_people");
				double totalPrice = rs.getDouble("total_price");

				model.addRow(new Object[] { reservationId, hotelName, roomType, startDate, endDate, numberOfPeople,
						String.format("%.2f €", totalPrice) });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Delete reservation(s) and increase the roomAvailibility by using reservationId
	public void deleteReservationAndIncreaseRoomAvailability(int reservationId) {
		String selectQuery = "SELECT room_id, start_date, end_date FROM reservations WHERE reservation_id = ?";
		String deleteQuery = "DELETE FROM reservations WHERE reservation_id = ?";
		String updateAvailabilityQuery = "UPDATE roomavailability SET available_quantity = available_quantity + 1 "
				+ "WHERE room_id = ? AND available_date BETWEEN ? AND ?";

		try (Connection conn = getConnected()) {
			conn.setAutoCommit(false);

			try (PreparedStatement psSelect = conn.prepareStatement(selectQuery);
					PreparedStatement psDelete = conn.prepareStatement(deleteQuery);
					PreparedStatement psUpdate = conn.prepareStatement(updateAvailabilityQuery)) {
				
				psSelect.setInt(1, reservationId);
				ResultSet rs = psSelect.executeQuery();

				if (rs.next()) {
					int roomId = rs.getInt("room_id");
					LocalDate startDate = rs.getDate("start_date").toLocalDate();
					LocalDate endDate = rs.getDate("end_date").toLocalDate();

					
					psUpdate.setInt(1, roomId);
					psUpdate.setDate(2, java.sql.Date.valueOf(startDate));
					psUpdate.setDate(3, java.sql.Date.valueOf(endDate));
					psUpdate.executeUpdate();
				}

			
				psDelete.setInt(1, reservationId);
				psDelete.executeUpdate();

				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Update user information
	public void updateUser(String username, String name, String surname, String password, String gender) {
		String query = "UPDATE users SET name = ?, surname = ?,password = ?, gender = ? WHERE username = ?";

		try (Connection conn = getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, password);
			ps.setString(4, gender);
			ps.setString(5, username);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	}


