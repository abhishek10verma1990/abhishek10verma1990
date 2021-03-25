package com.npst.upi.portal.merchant.config;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
@Configuration
public class HttpClientConfig {
	
	@Autowired
	private RestErrorHandler errorHandler;

	@Bean
	public RestTemplate restTemplate(@Value("${http.connection.timeout:10000}") int connectionTimeout,
			@Value("${http.connect.timeout:10000}") int connectTimeout,
			@Value("${http.read.timeout:10000}") int readTimeout,
			@Value("${http.max.connection:100}") int maxConnection,
			@Value("${http.default.max.per.route:100}") int defaultMaxPerRoute) {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(errorHandler);
		PoolingHttpClientConnectionManager cm = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			 builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			 SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
			 Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
			            .register("http", new PlainConnectionSocketFactory()) 
			            .register("https", sslConnectionSocketFactory)
			            .build(); 
			 
			 cm = new PoolingHttpClientConnectionManager(registry);
			 cm.setMaxTotal(maxConnection);
			 cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
			 CloseableHttpClient httpclient = HttpClients.custom()
			            .setSSLSocketFactory(sslConnectionSocketFactory)
			            .setConnectionManager(cm)
			            .build();

			HttpComponentsClientHttpRequestFactory requestFactory =
			        new HttpComponentsClientHttpRequestFactory();
			
			requestFactory.setConnectionRequestTimeout(connectionTimeout);
			requestFactory.setConnectTimeout(connectTimeout);
			requestFactory.setReadTimeout(readTimeout);

			requestFactory.setHttpClient(httpclient);
			
			restTemplate.setRequestFactory(requestFactory);
			return restTemplate;
		} catch(Exception e) {
			
		} finally {
		
		}
		
		return restTemplate;
	}

}
