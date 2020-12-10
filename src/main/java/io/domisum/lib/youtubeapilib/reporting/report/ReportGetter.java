package io.domisum.lib.youtubeapilib.reporting.report;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.report.model.DownloadableReport;

import java.io.IOException;

public interface ReportGetter
{
	
	DownloadableReport get(YouTubeApiCredentials credentials, String jobId, long reportId)
		throws IOException;
	
}
