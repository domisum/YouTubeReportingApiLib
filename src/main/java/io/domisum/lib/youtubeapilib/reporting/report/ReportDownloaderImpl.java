package io.domisum.lib.youtubeapilib.reporting.report;

import com.google.api.client.http.GenericUrl;
import com.google.common.base.Charsets;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.report.model.DownloadableReport;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
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
		return outputStream.toString(Charsets.UTF_8);
	}
	
}
