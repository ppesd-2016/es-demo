package sample.freemarker;

public class Company {

	private String code;
	private String name;
	private String url;
	private String email;
	private String sector;
	private String regnumber;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getRegnumber() {
		return regnumber;
	}
	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}
	public Company(String code, String name, String url, String email,
			String sector, String regnumber) {
		super();
		this.code = code;
		this.name = name;
		this.url = url;
		this.email = email;
		this.sector = sector;
		this.regnumber = regnumber;
	}
	
	
	
	
	
}
