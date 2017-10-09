package swun.iot.entity;


/**
 * TUsers entity. @author MyEclipse Persistence Tools
 */

public class TUsers{

	// Fields

	private String user;
	private String password;//封装输入密码请求参数的属性
	private String repassword;//封装第二次输入密码请求参数的属性
	private String passwordMd5;
	private String name;
	private String email;
	private String phone;
	private String qq;
	private String validateCode;//校验码

	// Constructors

	/** default constructor */
	public TUsers() {
	}

	/** minimal constructor */
	public TUsers(String user, String passwordMd5) {
		this.user = user;
		this.passwordMd5 = passwordMd5;
	}

	/** full constructor */
	public TUsers(String user, String passwordMd5, String name, String email,
			String phone, String qq) {
		this.user = user;
		this.passwordMd5 = passwordMd5;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.qq = qq;
	}

	// Property accessors

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswordMd5() throws Exception {
//		Encrypter.md5Encrypt方法使用MD5算法对密码进行加密
		return swun.iot.common.Encrypter.md5Encrypt(password);
	}

	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	

}