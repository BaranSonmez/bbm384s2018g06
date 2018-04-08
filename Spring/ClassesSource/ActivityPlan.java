
public class ActivityPlan {
	private int moveId;
	private int memberId;
	private int sets;
	private boolean status;
	public int getMoveId() {
		return moveId;
	}
	public void setMoveId(int moveId) {
		this.moveId = moveId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	ActivityPlan(int moveId, int memberId, int sets, boolean status){
		this.moveId = moveId;
		this.memberId = memberId;
		this.sets = sets;
		this.status = status;
	}

}
