import java.sql.Date;

public class SpecialOffer {
	private String name;
	private int branchId;
	private Date startDate;
	private Date finishDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	SpecialOffer(String name, int branchId, Date startDate, Date finishDate) {
		this.name = name;
		this.branchId = branchId;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}
}
