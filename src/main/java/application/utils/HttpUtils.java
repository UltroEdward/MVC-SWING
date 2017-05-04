package application.utils;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {

	private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
	private static HttpClient client = null;

	private HttpUtils() {
	}

	static {
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(Constants.CONNECTION_TIMEOUT_SEC * 1000);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(Constants.CONNECTION_REQUEST_TIMEOUT_SEC * 1000);

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());
		client = builder.build();
	}

	public static HttpResponse doGet(String url) {
		return doGet(url, new BasicHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36"));
	}

	public static HttpResponse doGet(String url, Header header) {
		LOG.info("Invoke [GET] to url:" + url);
		HttpGet request = new HttpGet(url);
		request.addHeader(header);

		HttpResponse resp = null;
		try {
			resp = invoke(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resp;
	}

	private static HttpResponse invoke(HttpRequestBase request) throws ClientProtocolException, IOException {
		return client.execute(request);
	}

}
