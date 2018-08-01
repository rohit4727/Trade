package com.iris;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] args) throws InterruptedException {
		Map<String, Map<String, Double>> map = new HashMap<>();
		String[] security = new String[] { "Infosys", "Suzlon", "Larsen", "Sapient", "IRIS", "MERCER", "TCS",
				"BLACKROCK", "SUNLIFE", "AIRTEL" };
		String date = "05-07-2018";
		Time time = Time.valueOf("11:00:00");

		for (int i = 0; i < 3_00_000; i++) {

			if (i % 10 == 0) {
				time.setTime(time.getTime() + 1000);
			}

			Map<String, Double> map2 = map.get(security[i % 10]);
			if (map2 == null) {
				map2 = new HashMap<>();
				map.put(security[i % 10], map2);
			}
			map2.put(date + " " + time, Math.random() * 100);

		}

		map.forEach((String s, Map<String, Double> m) -> {
			m.forEach((String t, Double p) -> {
				System.out.println(s + " " + t + " " + p);
			});
		});
		Thread.sleep(60000);
	}
}
