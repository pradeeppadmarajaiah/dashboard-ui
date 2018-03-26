package com.dashboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVParser {
	public static void main(String[] args) {

		String txtFileName = "transactions.csv";
		List<Grubhub> list = new ArrayList<>();

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

			/*
			 * list.forEach((a) -> { System.out.println(a.getId()); });
			 */
			double totalRevenue = list.stream()
					.mapToDouble(a -> Double.parseDouble(a.getTotal().replace('"', ' ').trim())).sum();
			double totalCommision = list.stream()
					.mapToDouble(a -> Double.parseDouble(a.getCommission().replace('"', ' ').trim())).sum();
			double totalProfit = totalRevenue - totalCommision;

			System.out.println("=====================****Grubhub====Report******=======================");
			System.out.println("                 Total Reveneue  <=======> " + totalRevenue);
			System.out.println("                 Total Commision <=======> " + totalCommision);
			System.out.println("                 Total Profit    <=======> " + totalProfit);
			System.out.println("=====================***************************=======================");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
