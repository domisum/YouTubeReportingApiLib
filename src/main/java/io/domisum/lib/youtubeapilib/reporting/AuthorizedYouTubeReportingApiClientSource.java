package io.domisum.lib.youtubeapilib.reporting;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtubereporting.YouTubeReporting;
import io.domisum.lib.youtubeapilib.AuthorizedYouTubeApiClientSource;

public class AuthorizedYouTubeReportingApiClientSource
		extends AuthorizedYouTubeApiClientSource<YouTubeReporting>
{
	
	@Override
	protected YouTubeReporting build(HttpRequestInitializer requestInitializer)
	{
		return new YouTubeReporting(new NetHttpTransport(), new JacksonFactory(), requestInitializer);
	}
	
}
