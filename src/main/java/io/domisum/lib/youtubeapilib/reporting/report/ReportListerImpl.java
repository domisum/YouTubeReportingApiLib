package io.domisum.lib.youtubeapilib.reporting.report;

import com.google.api.services.youtubereporting.model.ListReportsResponse;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.report.model.DownloadableReport;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ReportListerImpl
	implements ReportLister
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeReportingApiClientSource authorizedYouTubeReportingApiClientSource;
	
	
	// LIST
	@Override
	public Set<DownloadableReport> listAll(YouTubeApiCredentials credentials, String jobId)
		throws IOException
	{
		return list(credentials, jobId, null);
	}
	
	@Override
	public Set<DownloadableReport> listCreatedAfter(YouTubeApiCredentials credentials, String jobId, Instant createdAfter)
		throws IOException
	{
		return list(credentials, jobId, createdAfter);
	}
	
	private Set<DownloadableReport> list(YouTubeApiCredentials credentials, String jobId, Instant createdAfter)
		throws IOException
	{
		var reports = new HashSet<DownloadableReport>();
		
		var reporting = authorizedYouTubeReportingApiClientSource.getFor(credentials);
		String nextPageToken = null;
		do
		{
			var list = reporting.jobs().reports().list(jobId);
			if(createdAfter != null)
				list.setCreatedAfter(createdAfter.toString());
			list.setPageToken(nextPageToken);
			
			var response = list.execute();
			
			var reportsBatch = parseReports(response);
			reports.addAll(reportsBatch);
			nextPageToken = response.getNextPageToken();
		}
		while(nextPageToken != null);
		
		return reports;
	}
	
	private Set<DownloadableReport> parseReports(ListReportsResponse response)
	{
		var reports = new HashSet<DownloadableReport>();
		if(response.getReports() != null)
			for(var report : response.getReports())
			{
				var downloadableReport = ReportGetterImpl.parseReport(report);
				reports.add(downloadableReport);
			}
		
		return reports;
	}
	
}
