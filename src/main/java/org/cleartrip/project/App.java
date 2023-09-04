package org.cleartrip.project;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.cleartrip.project.models.Hotel;
import org.cleartrip.project.models.Rating;
import org.cleartrip.project.models.SortHotelsByAv;
import org.cleartrip.project.models.SortRatingsByRatingAsc;
import org.cleartrip.project.models.SortRatingsByRatingDesc;
import org.cleartrip.project.models.User;

public class App {

	// Storage
	static Map<Integer, Hotel> hotels = new HashMap<>();
	static Map<String, User> users = new HashMap<>();
	static int hotelCount = 5;


	public static void main(String[] args) {

		addHotels();

		Scanner sc = new Scanner(System.in);

		boolean exit = false;

		while (!exit) {
			String[] in = sc.nextLine().split(" ");
			if (in[0].equals("EXIT")) {
				exit = true;
			} else if (in[0].equals("LIST_HOTELS")) {
				listHotels();
			} else if (in.length == 5 && in[0].equals("ADD_RATING")) {
				addRating(in[1].trim(), in[2].trim(), in[3].trim(), in[4].trim());
			} else if (in.length == 4 && in[0].equals("GET_RATINGS")) {
				getRatings(in[1].trim(), in[2].trim(), in[3].trim());
			} else if (in.length == 2 && in[0].equals("DESCRIBE_HOTEL")) {
				describeHotel(in[1].trim());
			} else {
				System.out.println("Invalid Command!");
			}

		}

		sc.close();

	}

	private static void listHotels() {
		List<Hotel> plusHotels = new ArrayList<>();
		List<Hotel> normalHotels = new ArrayList<>();

		for (Map.Entry<Integer, Hotel> entry : hotels.entrySet()) {
			if(entry.getValue().isPlus()){
				plusHotels.add(entry.getValue());
			} else{
				normalHotels.add(entry.getValue());
			}
		}
		Collections.sort(plusHotels, new SortHotelsByAv());
		Collections.sort(normalHotels, new SortHotelsByAv());

		System.out.println("Available Hotels: ");
		for (Hotel h : plusHotels) {
			System.out.println("Hotel [id=" + Integer.toString(h.getId()) + "]");
		}
		for (Hotel h : normalHotels) {
			System.out.println("Hotel [id=" + Integer.toString(h.getId()) + "]");
		}
	}

	private static void describeHotel(String hotelIdStr) {
		int hotelId = Integer.parseInt(hotelIdStr);
		if (hotelId < 1 || hotelId > hotelCount) {
			System.out.println("Invalid hotel id!");
			return;

		} else {
			Hotel curr = hotels.get(hotelId);
			System.out.println(curr);
		}

	}

	private static void addHotels() {
		Hotel h1 = new Hotel(1, "Hotel1");
		Hotel h2 = new Hotel(2, "Hotel2", true);
		Hotel h3 = new Hotel(3, "Hotel3");
		Hotel h4 = new Hotel(4, "Hotel4", true);
		Hotel h5 = new Hotel(5, "Hotel5");

		hotels.put(1, h1);
		hotels.put(2, h2);
		hotels.put(3, h3);
		hotels.put(4, h4);
		hotels.put(5, h5);

	}

	private static void addRating(String ratingStr, String userIdStr, String hotelIdStr, String desc) {
		int rating = Integer.parseInt(ratingStr);
		if (rating < 1 || rating > 5) {
			System.out.println("Invalid rating!");
			return;

		}
		int hotelId = Integer.parseInt(hotelIdStr);
		if (hotelId < 1 || hotelId > hotelCount) {
			System.out.println("Invalid hotel id!");
			return;

		}
		Hotel curr = hotels.get(hotelId);
		Map<String, Rating> map = curr.getRatings();
		Rating r;
		int size = map.entrySet().size();
		double av = curr.getAv() * size;
		DecimalFormat dec = new DecimalFormat("#0.0");
		if (map.containsKey(userIdStr)) {
			r = map.get(userIdStr);
			av = Double.parseDouble(dec.format((av - r.getRating() + rating) / (double) size));
			r = new Rating(hotelId, userIdStr, rating, desc);

		} else {
			r = new Rating(hotelId, userIdStr, rating, desc);
			av = Double.parseDouble(dec.format((av + rating) / (double) (size + 1)));
		}
		curr.setAv(av);
		map.remove(userIdStr);
		map.put(userIdStr, r);

	}

	private static void getRatings(String hotelIdStr, String order, String filter) {
		int hotelId = Integer.parseInt(hotelIdStr);
		if (hotelId < 1 || hotelId > hotelCount) {
			System.out.println("Invalid hotel id!");
			return;
		}
		Hotel curr = hotels.get(hotelId);
		Map<String, Rating> map = curr.getRatings();
		List<Rating> ans = new ArrayList<>();

		if (filter.equals("1-2")) {
			for (Map.Entry<String, Rating> entry : map.entrySet()) {
				Rating r = entry.getValue();
				if (r.getRating() > 3) {
					continue;
				} else {
					ans.add(entry.getValue());

				}
			}
		} else if (filter.equals("3-4")) {
			for (Map.Entry<String, Rating> entry : map.entrySet()) {
				Rating r = entry.getValue();
				if (r.getRating() > 4 || r.getRating() < 3) {
					continue;
				} else {
					ans.add(entry.getValue());

				}
			}
		} else if (filter.equals("4-5")) {
			for (Map.Entry<String, Rating> entry : map.entrySet()) {
				Rating r = entry.getValue();
				if (r.getRating() < 4) {
					continue;
				} else {
					ans.add(entry.getValue());

				}
			}
		} else {
			for (Map.Entry<String, Rating> entry : map.entrySet()) {
				ans.add(entry.getValue());
			}
		}

		if (order.equals("RATING_ASC")) {
			Collections.sort(ans, new SortRatingsByRatingAsc());
		} else {
			Collections.sort(ans, new SortRatingsByRatingDesc());
		}
		System.out.println(ans);

	}

}
