package io.domisum.lib.youtubeapilib.reporting.job.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.job.JobMetadata;

import java.io.IOException;
import java.util.Set;

public interface JobLister
{
	
	Set<JobMetadata> listAll(YouTubeApiCredentials credentials)
			throws IOException;
	
}
