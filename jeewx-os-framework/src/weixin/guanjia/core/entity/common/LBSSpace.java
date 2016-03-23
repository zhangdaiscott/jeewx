package weixin.guanjia.core.entity.common;

public class LBSSpace {

	private String name;
	private String address;
	private Location location;
	private String telphone;
	private String distance;

	public LBSSpace() {
		// TODO Auto-generated constructor stub
	}

	public LBSSpace(String name, Location location, String address,
			String telphone, String distance) {
		super();
		this.address = address;
		this.name = name;
		this.location = location;
		this.telphone = telphone;
		this.distance = distance;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}
