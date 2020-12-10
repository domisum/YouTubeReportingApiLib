package io.domisum.lib.youtubeapilib.reporting.job;

import com.google.api.services.youtubereporting.model.Job;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.job.model.JobMetadata;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class JobCreatorImpl
	implements JobCreator
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeReportingApiClientSource authorizedYouTubeReportingApiClientSource;
	
	
	// LIST
	@Override
	public String create(YouTubeApiCredentials credentials, JobMetadata jobMetadata)
		throws IOException
	{
		var youTubeReporting = authorizedYouTubeReportingApiClientSource.getFor(credentials);
		
		var job = new Job();
		job.setName(jobMetadata.getName());
		job.setReportTypeId(jobMetadata.getReportType());
		
		var create = youTubeReporting.jobs().create(job);
		var response = create.execute();
		return response.getId();
	}
	
}
