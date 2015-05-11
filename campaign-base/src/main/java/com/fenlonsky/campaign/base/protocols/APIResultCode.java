package com.fenlonsky.campaign.base.protocols;

public enum APIResultCode {
	SUCCESS(200),
	ERROR(500),
	UNAUTHORITY(413),
	DATAERROR(405);
	
	APIResultCode(int i) {
	}
}
