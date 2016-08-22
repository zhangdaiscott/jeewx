package weixin.p3.oauth2.pojo.templateMsg;

public class MsgDataPojo{
	private String name;
	private String color;
	public MsgDataPojo(String name,String color){
		this.color = color;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
