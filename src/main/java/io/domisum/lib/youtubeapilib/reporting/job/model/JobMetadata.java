package io.domisum.lib.youtubeapilib.reporting.job.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class JobMetadata
{
	
	@Getter
	private final String id;
	@Getter
	private final String name;
	@Getter
	private final String reportType;
	
}
