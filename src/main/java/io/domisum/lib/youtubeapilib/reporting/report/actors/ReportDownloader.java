package io.domisum.lib.youtubeapilib.reporting.report.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.report.DownloadableReport;

import java.io.IOException;

public interface ReportDownloader
{
	
	String download(YouTubeApiCredentials credentials, DownloadableReport report)
		throws IOException;
	
}
