package io.domisum.lib.youtubeapilib.reporting.report.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.report.DownloadableReport;

import java.io.IOException;
import java.time.Instant;
import java.util.Set;

public interface ReportLister
{
	
	Set<DownloadableReport> listAll(YouTubeApiCredentials credentials, String jobId)
		throws IOException;
	
	Set<DownloadableReport> listCreatedAfter(YouTubeApiCredentials credentials, String jobId, Instant afterThis)
		throws IOException;
	
}
