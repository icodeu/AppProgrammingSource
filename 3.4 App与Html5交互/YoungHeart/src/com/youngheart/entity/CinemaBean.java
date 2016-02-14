package com.youngheart.entity;

import java.io.Serializable;

public class CinemaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cinemaId;
    private String cinemaName;
    private String cinemaPhotoUrl;
    
    public CinemaBean() {
    	
    }
    
	public String getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getCinemaPhotoUrl() {
		return cinemaPhotoUrl;
	}

	public void setCinemaPhotoUrl(String cinemaPhotoUrl) {
		this.cinemaPhotoUrl = cinemaPhotoUrl;
	}
    
}
