package io.domisum.lib.youtubeapilib.reporting.dependencyinjection.modules;

import com.google.inject.AbstractModule;
import io.domisum.lib.youtubeapilib.reporting.job.actors.JobCreator;
import io.domisum.lib.youtubeapilib.reporting.job.actors.JobLister;
import io.domisum.lib.youtubeapilib.reporting.job.actors.impl.JobCreatorImpl;
import io.domisum.lib.youtubeapilib.reporting.job.actors.impl.JobListerImpl;
import io.domisum.lib.youtubeapilib.reporting.report.actors.ReportDownloader;
import io.domisum.lib.youtubeapilib.reporting.report.actors.ReportLister;
import io.domisum.lib.youtubeapilib.reporting.report.actors.impl.ReportDownloaderImpl;
import io.domisum.lib.youtubeapilib.reporting.report.actors.impl.ReportListerImpl;

public class GuiceModule_YouTubeReportingApiLib
	extends AbstractModule
{
	
	@Override
	protected void configure()
	{
		bind(JobCreator.class).to(JobCreatorImpl.class);
		bind(JobLister.class).to(JobListerImpl.class);
		
		bind(ReportDownloader.class).to(ReportDownloaderImpl.class);
		bind(ReportLister.class).to(ReportListerImpl.class);
	}
	
}
