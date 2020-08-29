package io.domisum.lib.youtubeapilib.reporting.job.actors.impl;

import com.google.api.services.youtubereporting.model.ListJobsResponse;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.AuthorizedYouTubeReportingApiClientSource;
import io.domisum.lib.youtubeapilib.reporting.job.JobMetadata;
import io.domisum.lib.youtubeapilib.reporting.job.actors.JobLister;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class JobListerImpl
		implements JobLister
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeReportingApiClientSource authorizedYouTubeReportingApiClientSource;
	
	
	// LIST
	@Override
	public Set<JobMetadata> listAll(YouTubeApiCredentials credentials)
			throws IOException
	{
		var jobsMetadata = new HashSet<JobMetadata>();
		
		String nextPageToken = null;
		do
		{
			var response = page(credentials, nextPageToken);
			nextPageToken = response.getNextPageToken();
			
			if(response.getJobs() != null)
				for(var job : response.getJobs())
				{
					var jobMetadata = new JobMetadata(job.getId(), job.getName(), job.getReportTypeId());
					jobsMetadata.add(jobMetadata);
				}
		}
		while(nextPageToken != null);
		
		return jobsMetadata;
	}
	
	private ListJobsResponse page(YouTubeApiCredentials credentials, String pageToken)
			throws IOException
	{
		var youTubeReporting = authorizedYouTubeReportingApiClientSource.getFor(credentials);
		
		var list = youTubeReporting.jobs().list();
		list.setPageToken(pageToken);
		var response = list.execute();
		
		return response;
	}
	
}
