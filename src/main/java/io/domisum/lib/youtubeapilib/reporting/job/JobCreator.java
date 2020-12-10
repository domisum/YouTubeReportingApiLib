package io.domisum.lib.youtubeapilib.reporting.job;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.job.model.JobMetadata;

import java.io.IOException;

public interface JobCreator
{
	
	String create(YouTubeApiCredentials credentials, JobMetadata jobMetadata)
		throws IOException;
	
}
