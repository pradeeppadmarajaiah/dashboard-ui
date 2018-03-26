package com.dashboard;

public class Company {

	private String id;
	private String name;
	private Double totalRevenue;
	private Double totalCommission;
	private Double totalProfit;

	/**
	 * 
	 */
	public Company() {
	}

	/**
	 * @param id
	 * @param name
	 * @param totalRevenue
	 * @param totalCommission
	 * @param totalProfit
	 */
	public Company(String id, String name, Double totalRevenue, Double totalCommission, Double totalProfit) {
		this.id = id;
		this.name = name;
		this.totalRevenue = totalRevenue;
		this.totalCommission = totalCommission;
		this.totalProfit = totalProfit;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the totalRevenue
	 */
	public final Double getTotalRevenue() {
		return totalRevenue;
	}

	/**
	 * @param totalRevenue
	 *            the totalRevenue to set
	 */
	public final void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	/**
	 * @return the totalCommission
	 */
	public final Double getTotalCommission() {
		return totalCommission;
	}

	/**
	 * @param totalCommission
	 *            the totalCommission to set
	 */
	public final void setTotalCommission(Double totalCommission) {
		this.totalCommission = totalCommission;
	}

	/**
	 * @return the totalProfit
	 */
	public final Double getTotalProfit() {
		return totalProfit;
	}

	/**
	 * @param totalProfit
	 *            the totalProfit to set
	 */
	public final void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", totalRevenue=" + totalRevenue + ", totalCommission="
				+ totalCommission + ", totalProfit=" + totalProfit + "]";
	}

}
