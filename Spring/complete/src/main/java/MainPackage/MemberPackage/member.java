package MainPackage.MemberPackage;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class member {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String statue; //This is for user's statue. It can be banned, owner, active, inactive, non-member.
	private String status; //It can be gold, platinum, standard.
	private String mail;
	private int referenceNumber; //This is for a member's friend count that came with member.
	private int branchAuthority; //This count for user's entered another branches except member's registered branch
	private Date age;
	private Date startDate;
	private Date endDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBranchAuthority() {
		return branchAuthority;
	}
	public void setBranchAuthority(int branchAuthority) {
		this.branchAuthority = branchAuthority;
	}
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
		this.age = age;
	}
	
	member (){
		
	}
	
	member(String name, String surname, String username, String password, String statue, String status, String mail, int referenceNumber, int branchAuthority, Date age, Date startDate, Date endDate){
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.statue = statue;
		this.status = status;
		this.mail = mail;
		this.referenceNumber = referenceNumber;
		this.branchAuthority = branchAuthority;
		this.age = age;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	member(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
}
