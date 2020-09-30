package io.domisum.lib.youtubeapilib.reporting;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtubereporting.YouTubeReporting;
import com.google.inject.Singleton;
import io.domisum.lib.youtubeapilib.AuthorizedYouTubeApiClientSource;

@Singleton
public class AuthorizedYouTubeReportingApiClientSource
	extends AuthorizedYouTubeApiClientSource<YouTubeReporting>
{
	
	@Override
	protected YouTubeReporting build(HttpRequestInitializer requestInitializer)
	{
		var builder = new YouTubeReporting.Builder(new NetHttpTransport(), new JacksonFactory(), requestInitializer);
		builder.setApplicationName("YouTubeReportingApiLib");
		
		return builder.build();
	}
	
}
