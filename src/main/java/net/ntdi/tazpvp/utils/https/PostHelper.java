package net.ntdi.tazpvp.utils.https;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PostHelper {
    public HttpURLConnection connection;
    public URL url;
    public PostHelper(String url, String jsonInput) throws IOException {
        this.url = new URL(url);
        connection = (HttpURLConnection) this.url.openConnection();

        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()){
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }
}
