package jp.co.sample.emp_management.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class RegisterEmployeeForm {
	/** 従業員名 */
	@NotBlank
	private String name;
	/** 画像 */
	//@NotEmpty
	private MultipartFile image;
	/** 性別 */
	@NotEmpty
	private String gender;
	/** 入社日 */
	@NotBlank
	private String hireDate;
	/** メールアドレス */
	@NotBlank
	private String mailAddress;
	/** 郵便番号 */
	@NotBlank
	@Pattern(regexp = "^[0-9] {7}",message = "ハイフンなしの郵便番号形式にしてください")
	private String zipCode;
	/** 住所 */
	@NotBlank
	private String address;
	/** 電話番号 */
	@NotBlank
	private String telephone;
	/** 給料 */
	@NotNull
	private Integer salary;
	/** 特性 */
	@NotBlank
	private String characteristics;
	/** 扶養人数 */
	@NotNull
	private Integer dependentsCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Integer getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "RegisterEmployeeForm [name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate="
				+ hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address
				+ ", telephone=" + telephone + ", salary=" + salary + ", characteristics=" + characteristics
				+ ", dependentsCount=" + dependentsCount + "]";
	}

}
