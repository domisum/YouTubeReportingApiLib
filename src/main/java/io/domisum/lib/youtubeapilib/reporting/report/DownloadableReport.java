package io.domisum.lib.youtubeapilib.reporting.report;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
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
	
}
