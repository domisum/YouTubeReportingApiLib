package io.domisum.lib.youtubeapilib.reporting.report.actors.impl;

import com.google.api.services.youtubereporting.model.ListReportsResponse;
import com.google.api.services.youtubereporting.model.Report;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.report.DownloadableReport;
import io.domisum.lib.youtubeapilib.reporting.report.actors.ReportLister;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
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
				var downloadableReport = parseReport(report);
				reports.add(downloadableReport);
			}
		
		return reports;
	}
	
	private DownloadableReport parseReport(Report report)
	{
		long reportId = Long.parseLong(report.getId());
		String downloadUrl = report.getDownloadUrl();
		
		var startTime = Instant.parse(report.getStartTime());
		var date = LocalDate.ofInstant(startTime, ZoneId.of("UTC"));
		
		var createdTime = Instant.parse(report.getCreateTime());
		
		var downloadableReport = new DownloadableReport(reportId, date, downloadUrl, createdTime);
		return downloadableReport;
	}
	
}
