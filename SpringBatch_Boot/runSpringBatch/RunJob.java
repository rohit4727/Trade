import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RunJob {

	private static String restApiUrl = "";

	public static void main(String[] args) {

		if (args.length == 0 || args[0].isEmpty()) {
			throw new RuntimeException(
					"Failed : Please provide spring batch rest api url as first argument and job is as secound argument.");
		} else {
			restApiUrl = args[0];
		}
		if (args.length < 2 || args[1].isEmpty()) {
			throw new RuntimeException("Failed : Please provide job id as secound argument.");
		}
		System.out.println(restApiUrl);
		restApiUrl = restApiUrl.replace("{jobId}", args[1]);

		System.out.println(restApiUrl);
		try {

			URL url = new URL(restApiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
