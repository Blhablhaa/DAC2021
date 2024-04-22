package pojos;


import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="MY_CUSTOMERS")
public class Customer  {
	
	private Integer id;
	private double depositAmt;
	private String email;
	private String name;
	private String password;
	private Date regDate;
	private String role;

	public Customer() {
		System.out.println("in cust constr...");
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="DEPOSIT_AMT")
	public double getDepositAmt() {
		return this.depositAmt;
	}

	public void setDepositAmt(double depositAmt) {
		this.depositAmt = depositAmt;
	}

	@Column(length=20,unique=true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length=20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="REG_DATE")
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	@Column(length=20)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", depositAmt=" + depositAmt + ", email="
				+ email + ", name=" + name + ", password=" + password
				+ ", regDate=" + regDate + ", role=" + role + "]";
	}
	

}