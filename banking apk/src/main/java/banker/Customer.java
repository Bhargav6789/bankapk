package banker;

public class Customer {
    private int accountnumber;
    private String fullName;
    private String idProof;
    private String dateOfBirth;
    private String email;
    private String mobileNumber;
    private String gender;
    private String typeOfAccount;
    private double initialDeposit;
    private double balance;

    // Constructor, getters, setterss
    public Customer() {
        // Default constructor
    }

    public int getaccountnumber() {
        return accountnumber;
    }

    public void setCustomerId(int customerId) {
        this.accountnumber = accountnumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

	public void setAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		
	}

	public void setDob(String dob) {
		// TODO Auto-generated method stub
		
	}
	
}
