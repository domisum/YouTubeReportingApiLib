package io.domisum.lib.youtubeapilib.reporting.report.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.report.DownloadableReport;

import java.io.IOException;

public interface ReportGetter
{
	
	DownloadableReport get(YouTubeApiCredentials credentials, String jobId, long reportId)
		throws IOException;
	
}
