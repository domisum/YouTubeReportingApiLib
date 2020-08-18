package io.domisum.lib.youtubeapilib.reporting.report.actors.impl;

import com.google.api.client.http.GenericUrl;
import com.google.common.base.Charsets;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.report.DownloadableReport;
import io.domisum.lib.youtubeapilib.reporting.report.actors.ReportDownloader;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
public class ReportDownloaderImpl
		implements ReportDownloader
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeReportingApiClientSource authorizedYouTubeReportingApiClientSource;
	
	
	// DOWNLOAD
	@Override
	public String download(YouTubeApiCredentials credentials, DownloadableReport report)
			throws IOException
	{
		var reporting = authorizedYouTubeReportingApiClientSource.getFor(credentials);
		
		var downloadUrl = new GenericUrl(report.getDownloadUrl());
		var outputStream = new ByteArrayOutputStream();
		
		reporting.media().download("").getMediaHttpDownloader().download(downloadUrl, outputStream);
		String reportContent = new String(outputStream.toByteArray(), Charsets.UTF_8);
		
		return reportContent;
	}
	
}