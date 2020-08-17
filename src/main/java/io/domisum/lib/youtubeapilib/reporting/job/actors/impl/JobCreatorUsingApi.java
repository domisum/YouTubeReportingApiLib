package io.domisum.lib.youtubeapilib.reporting.job.actors.impl;

import com.google.api.services.youtubereporting.model.Job;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.job.JobMetadata;
import io.domisum.lib.youtubeapilib.reporting.job.actors.JobCreator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class JobCreatorUsingApi
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
		String createdJobId = response.getId();
		
		return createdJobId;
	}
	
}
