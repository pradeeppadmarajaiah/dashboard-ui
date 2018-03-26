package com.dashboard;

public class GrubHubModel {

	/**
	 * 
	 */
	public GrubHubModel() {
	}

	private String customerId;
	private String orderNumber;
	private String grossTotal;
	private String netTotal;
	private String commission;
	private String orderProcessingFees;

	/**
	 * @return the customerId
	 */
	public final String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 * @param orderNumber
	 * @param grossTotal
	 * @param netTotal
	 * @param commission
	 * @param orderProcessingFees
	 */
	public GrubHubModel(String customerId, String orderNumber, String grossTotal, String netTotal, String commission,
			String orderProcessingFees) {
		this.customerId = customerId;
		this.orderNumber = orderNumber;
		this.grossTotal = grossTotal;
		this.netTotal = netTotal;
		this.commission = commission;
		this.orderProcessingFees = orderProcessingFees;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public final void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the orderNumber
	 */
	public final String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public final void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the grossTotal
	 */
	public final String getGrossTotal() {
		return grossTotal;
	}

	/**
	 * @param grossTotal
	 *            the grossTotal to set
	 */
	public final void setGrossTotal(String grossTotal) {
		this.grossTotal = grossTotal;
	}

	/**
	 * @return the netTotal
	 */
	public final String getNetTotal() {
		return netTotal;
	}

	/**
	 * @param netTotal
	 *            the netTotal to set
	 */
	public final void setNetTotal(String netTotal) {
		this.netTotal = netTotal;
	}

	/**
	 * @return the commission
	 */
	public final String getCommission() {
		return commission;
	}

	/**
	 * @param commission
	 *            the commission to set
	 */
	public final void setCommission(String commission) {
		this.commission = commission;
	}

	/**
	 * @return the orderProcessingFees
	 */
	public final String getOrderProcessingFees() {
		return orderProcessingFees;
	}

	/**
	 * @param orderProcessingFees
	 *            the orderProcessingFees to set
	 */
	public final void setOrderProcessingFees(String orderProcessingFees) {
		this.orderProcessingFees = orderProcessingFees;
	}

}
