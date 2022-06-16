package javastudy;

public class VIPCustomer extends Customer {

	private int agentID; //VIP�� ��� ���� ���̵�
	double saleRatio; //������
	
	public VIPCustomer(int customerID, String customerName, int agentID) {
		super(customerID, customerName);
		customerGrade = "VIP"; //�� ��� VIP
		bonusRatio = 0.05; //���ʽ� ���� 5%
		saleRatio = 0.1; //������ 10%
		this.agentID = agentID;
	}

	public int calcPrice(int price) {
		bonusPoint += price*bonusRatio;
		return price - (int)(price * saleRatio);
	}
	
	public String showCustomerInfo() {
		return super.showCustomerInfo() + "��� ������ ��ȣ��" + agentID + "�Դϴ�";
	}
	
	public int getAgentID() {
		return agentID;
	}
}
