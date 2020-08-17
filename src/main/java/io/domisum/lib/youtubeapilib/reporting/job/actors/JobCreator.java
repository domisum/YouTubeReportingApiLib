package io.domisum.lib.youtubeapilib.reporting.job.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.reporting.job.JobMetadata;

import java.io.IOException;

public interface JobCreator
{
	
	String create(YouTubeApiCredentials credentials, JobMetadata jobMetadata)
			throws IOException;
	
}
