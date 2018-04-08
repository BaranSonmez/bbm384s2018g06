
public class Branch {
	
	private String name;
	private int quota;
	private long phoneNumber;
	private String city;
	private String district;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	Branch(String name, int quota, long phoneNumber, String city, String district, String address) {
		this.name = name;
		this.quota = quota;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.district = district;
		this.address = address;
	}

}
