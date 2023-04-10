package com.driver.services;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

@Service
public class GraphHopperMatrixApiExample {

    public void getDistanceAndTime(double startLat, double startLong, double endLat, double endLong) throws Exception {
        // Define the API URL with origin and destination points, and API key
        String apiUrl = String.format("https://graphhopper.com/api/1/matrix?from_point=%f,%f&to_point=%f,%f&type=json&profile=car&out_array=weights&out_array=times&out_array=distances&key=de06056d-de02-400d-944d-a6859fac4325",
                startLat,startLong,endLat,endLong);
        // Send a GET request to the API URL
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Get the response content as a string
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        String response = responseBuilder.toString();

        // Extract the distance and time from the response
        int[][] distances = extractArray(response, "\"distances\"");
        int[][] times = extractArray(response, "\"times\"");

        // Print the distances and times arrays
        if (distances != null && times != null) {
            int disLen = Arrays.deepToString(distances).length();
            int timeLen = Arrays.deepToString(times).length();
            System.out.println(disLen+"  "+timeLen+" Distance And Time Length");
            System.out.println("Distances: " + (Integer.parseInt(Arrays.deepToString(distances).substring(2,disLen-2)))/1000);
            System.out.println("Times: " + (Integer.parseInt(Arrays.deepToString(times).substring(2,timeLen-2)))/3600);
        }

        // Close the connection and reader
        connection.disconnect();
        reader.close();
    }

    private static int[][] extractArray(String response, String arrayName) {
        int[][] array = null;
        int startIndex = response.indexOf(arrayName);
        if (startIndex >= 0) {
            startIndex += arrayName.length() + 2;
            int endIndex = response.indexOf("]", startIndex) + 1;
            String arrayString = response.substring(startIndex, endIndex);
            String[] rows = arrayString.split("\\],\\[");
            array = new int[rows.length][];
            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
                array[i] = new int[cols.length];
                for (int j = 0; j < cols.length; j++) {
                    array[i][j] = Integer.parseInt(cols[j]);
                }
            }
        }
        return array;
    }
}
