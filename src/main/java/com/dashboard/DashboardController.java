package com.dashboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
	@Autowired
	DashboardService dashboardService;

	@RequestMapping("/companies")
	public List<Company> getCompanies() {

		String txtFileName = "transactions.csv";
		List<Grubhub> list = new ArrayList<>();

		List<Company> companies = new ArrayList<>();
		companies.add(new Company("E1", "EAT24", 100.00, 23.56, 76.44));
		companies.add(new Company("Y1", "YELP", 857.00, 52.12, 804.88));
		companies.add(new Company("U1", "Uber eat", 500.0, 19.5, 480.5));
		companies.add(new Company("S1", "seamless", 1000.0, 120.0, 880.0));
		// ID Restaurant Type Date Time Subtotal Delivery Tax Tip Total Commission
		// Processing Fee Withheld Tax

		try (Stream<String> stream = Files.lines(Paths.get(txtFileName))) {
			list = stream.skip(1).map(line -> {
				Grubhub grubhub = new Grubhub();
				String[] str = line.split(",");
				grubhub.setId(str[0]);
				grubhub.setRestaurant(str[1]);
				grubhub.setType(str[2]);
				grubhub.setDate(str[3]);
				grubhub.setTime(str[4]);
				grubhub.setSubtotal(str[5]);
				grubhub.setDelivery(str[6]);
				grubhub.setTax(str[7]);
				grubhub.setTip(str[8]);
				grubhub.setTotal(str[9].replace("$", ""));
				grubhub.setCommission(str[10].replace("$", ""));
				grubhub.setProcessingfee(str[11]);
				grubhub.setWithHeldTax(str[12]);
				return grubhub;
			}).collect(Collectors.toList());

			double totalRevenue = list.stream()
					.mapToDouble(a -> Double.parseDouble(a.getTotal().replace('"', ' ').trim())).sum();
			double totalCommission = list.stream()
					.mapToDouble(a -> Double.parseDouble(a.getCommission().replace('"', ' ').trim())).sum();
			double totalProfit = totalRevenue - totalCommission;
			Company company = new Company("G1", "Grubhub", totalRevenue, totalCommission, totalProfit);
			companies.add(company);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return companies;
	}

	@RequestMapping("/rCompanies")
	public List<Company> getRealCompanies() {

		List<Company> companies = new ArrayList<>();
		companies.add(new Company("E1", "EAT24", 100.00, 23.56, 76.44));
		companies.add(new Company("Y1", "YELP", 857.00, 52.12, 804.88));
		companies.add(new Company("U1", "Uber eat", 500.0, 19.5, 480.5));
		companies.add(new Company("S1", "seamless", 1000.0, 120.0, 880.0));
		companies.add(GrubHubLoginHelper.getCompany());
		return companies;
	}

	@RequestMapping("/rCompanies/{id}")
	public Company getRCompany(@PathVariable String id) {

		List<Company> companies = new ArrayList<>();
		companies.add(new Company("E1", "EAT24", 100.00, 23.56, 76.44));
		companies.add(new Company("Y1", "YELP", 857.00, 52.12, 804.88));
		companies.add(new Company("U1", "Uber eat", 500.0, 19.5, 480.5));
		companies.add(new Company("S1", "seamless", 1000.0, 120.0, 880.0));
		companies.add(GrubHubLoginHelper.getCompany());
		return companies.stream().filter(t -> t.getId().equals(id)).findFirst().get();

	}

	@RequestMapping("/companies/{id}")
	public Company getCompany(@PathVariable String id) {

		String txtFileName = "transactions.csv";
		List<Grubhub> list = new ArrayList<>();

		List<Company> companies = new ArrayList<>();
		companies.add(new Company("E1", "EAT24", 100.00, 23.56, 76.44));
		companies.add(new Company("Y1", "YELP", 857.00, 52.12, 804.88));
		companies.add(new Company("U1", "Uber eat", 500.0, 19.5, 480.5));
		companies.add(new Company("S1", "seamless", 1000.0, 120.0, 880.0));
		// ID Restaurant Type Date Time Subtotal Delivery Tax Tip Total Commission
		// Processing Fee Withheld Tax

		try (Stream<String> stream = Files.lines(Paths.get(txtFileName))) {
			list = stream.skip(1).map(line -> {
				Grubhub grubhub = new Grubhub();
				String[] str = line.split(",");
				grubhub.setId(str[0]);
				grubhub.setRestaurant(str[1]);
				grubhub.setType(str[2]);
				grubhub.setDate(str[3]);
				grubhub.setTime(str[4]);
				grubhub.setSubtotal(str[5]);
				grubhub.setDelivery(str[6]);
				grubhub.setTax(str[7]);
				grubhub.setTip(str[8]);
				grubhub.setTotal(str[9].replace("$", ""));
				grubhub.setCommission(str[10].replace("$", ""));
				grubhub.setProcessingfee(str[11]);
				grubhub.setWithHeldTax(str[12]);
				return grubhub;
			}).collect(Collectors.toList());

			double totalRevenue = list.stream()
					.mapToDouble(a -> Double.parseDouble(a.getTotal().replace('"', ' ').trim())).sum();
			double totalCommission = list.stream()
					.mapToDouble(a -> Double.parseDouble(a.getCommission().replace('"', ' ').trim())).sum();
			double totalProfit = totalRevenue - totalCommission;
			Company company = new Company("G1", "Grubhub", totalRevenue, totalCommission, totalProfit);
			companies.add(company);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return companies.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}
}
