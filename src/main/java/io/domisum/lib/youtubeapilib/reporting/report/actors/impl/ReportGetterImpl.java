package io.domisum.lib.youtubeapilib.reporting.report.actors.impl;

import com.google.api.services.youtubereporting.model.Report;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.report.DownloadableReport;
import io.domisum.lib.youtubeapilib.reporting.report.actors.ReportGetter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ReportGetterImpl
	implements ReportGetter
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeReportingApiClientSource authorizedYouTubeReportingApiClientSource;
	
	
	// LIST
	@Override
	public DownloadableReport get(YouTubeApiCredentials credentials, String jobId, long reportId)
		throws IOException
	{
		var reporting = authorizedYouTubeReportingApiClientSource.getFor(credentials);
		
		var report = reporting.jobs().reports().get(jobId, reportId+"").execute();
		return parseReport(report);
	}
	
	public static DownloadableReport parseReport(Report report)
	{
		long reportId = Long.parseLong(report.getId());
		String downloadUrl = report.getDownloadUrl();
		
		var startTime = Instant.parse(report.getStartTime());
		var date = LocalDate.ofInstant(startTime, ZoneOffset.UTC);
		
		var createdTime = Instant.parse(report.getCreateTime());
		
		return new DownloadableReport(reportId, date, downloadUrl, createdTime);
	}
	
}
