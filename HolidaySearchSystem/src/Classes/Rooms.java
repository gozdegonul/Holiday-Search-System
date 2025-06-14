package Classes;

public class Rooms {

	private int roomId;
	private Hotels hotel;
	private String roomTypeName;
	private double pricePerPerson;
	private int totalRoomCount;
	private String roomFeatures;
	private int capacity;
	private int minAvailableQuantity;

	public int getMinAvailableQuantity() {
		return minAvailableQuantity;
	}

	public void setMinAvailableQuantity(int minAvailableQuantity) {
		this.minAvailableQuantity = minAvailableQuantity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Hotels getHotel() {
		return hotel;
	}

	public void setHotel(Hotels hotel) {
		this.hotel = hotel;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public double getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}

	public int getTotalRoomCount() {
		return totalRoomCount;
	}

	public void setTotalRoomCount(int totalRoomCount) {
		this.totalRoomCount = totalRoomCount;
	}

	public String getRoomFeatures() {
		return roomFeatures;
	}

	public void setRoomFeatures(String roomFeatures) {
		this.roomFeatures = roomFeatures;
	}

}
