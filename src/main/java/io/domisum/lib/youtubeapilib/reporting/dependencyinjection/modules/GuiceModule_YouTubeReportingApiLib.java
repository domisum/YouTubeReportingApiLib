package io.domisum.lib.youtubeapilib.reporting.dependencyinjection.modules;

import com.google.inject.AbstractModule;
import io.domisum.lib.youtubeapilib.reporting.job.JobCreator;
import io.domisum.lib.youtubeapilib.reporting.job.JobCreatorImpl;
import io.domisum.lib.youtubeapilib.reporting.job.JobLister;
import io.domisum.lib.youtubeapilib.reporting.job.JobListerImpl;
import io.domisum.lib.youtubeapilib.reporting.report.ReportDownloader;
import io.domisum.lib.youtubeapilib.reporting.report.ReportDownloaderImpl;
import io.domisum.lib.youtubeapilib.reporting.report.ReportGetter;
import io.domisum.lib.youtubeapilib.reporting.report.ReportGetterImpl;
import io.domisum.lib.youtubeapilib.reporting.report.ReportLister;
import io.domisum.lib.youtubeapilib.reporting.report.ReportListerImpl;

public class GuiceModule_YouTubeReportingApiLib
	extends AbstractModule
{
	
	@Override
	protected void configure()
	{
		bind(JobCreator.class).to(JobCreatorImpl.class);
		bind(JobLister.class).to(JobListerImpl.class);
		
		bind(ReportDownloader.class).to(ReportDownloaderImpl.class);
		bind(ReportGetter.class).to(ReportGetterImpl.class);
		bind(ReportLister.class).to(ReportListerImpl.class);
	}
	
}
