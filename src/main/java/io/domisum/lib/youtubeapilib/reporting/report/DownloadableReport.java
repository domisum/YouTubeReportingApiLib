package io.domisum.lib.youtubeapilib.reporting.report;

import io.domisum.lib.auxiliumlib.PHR;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class DownloadableReport
{
	
	@Getter
	private final long id;
	@Getter
	private final LocalDate date;
	
	@Getter
	private final String downloadUrl;
	@Getter
	private final Instant createdInstant;
	
	
	// OBJECT
	@Override
	public String toString()
	{
		return PHR.r("{}-({}, created: {})", date, createdInstant);
	}
	
}
