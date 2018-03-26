package com.dashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

	private List<Company> companies = new ArrayList<>(Arrays.asList(new Company("G1", "Grubhub", 100.2, 20.00, 80.2),
			new Company("U1", "Uber eat", 500.0, 19.5, 480.5)));

	public List<Company> getAllCompanies() {
		return companies;
	}

	public Company getCompany(String id) {

		return companies.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}

}
