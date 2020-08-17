package io.domisum.lib.youtubeapilib.reporting.job;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class JobMetadata
{
	
	@Getter
	private final String id;
	@Getter
	private final String name;
	@Getter
	private final String reportType;
	
}
