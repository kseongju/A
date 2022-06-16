package javastudy;

public class VIPCustomer extends Customer {

	private int agentID; //VIP고객 담당 상담원 아이디
	double saleRatio; //할인율
	
	public VIPCustomer(int customerID, String customerName, int agentID) {
		super(customerID, customerName);
		customerGrade = "VIP"; //고객 등급 VIP
		bonusRatio = 0.05; //보너스 적립 5%
		saleRatio = 0.1; //할인율 10%
		this.agentID = agentID;
	}

	public int calcPrice(int price) {
		bonusPoint += price*bonusRatio;
		return price - (int)(price * saleRatio);
	}
	
	public String showCustomerInfo() {
		return super.showCustomerInfo() + "담당 상담원의 번호는" + agentID + "입니다";
	}
	
	public int getAgentID() {
		return agentID;
	}
}
