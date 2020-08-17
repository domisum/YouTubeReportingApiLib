package io.domisum.lib.youtubeapilib.reporting.report;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class ReportMetadata
{
	
	@Getter
	private final long id;
	
	@Getter
	private final String type;
	@Getter
	private final String channelId;
	
	@Getter
	private final LocalDate date;
	
}
