package model.domain;

public class ResListDTO {
	private String fishingName;
	private String fishingType;
	private String fishingAddress;
	private double fishingLat;
	private double fishingLng;
	private String fishingNumber;
	private String fishingSpecies;
	private String fishingPriece;
	public ResListDTO() {	}
	public ResListDTO(String fishingName, String fishingType, String fishingAddress, double fishingLat,
			double fishingLng, String fishingNumber, String fishingSpecies, String fishingPriece) {
		this.fishingName = fishingName;
		this.fishingType = fishingType;
		this.fishingAddress = fishingAddress;
		this.fishingLat = fishingLat;
		this.fishingLng = fishingLng;
		this.fishingNumber = fishingNumber;
		this.fishingSpecies = fishingSpecies;
		this.fishingPriece = fishingPriece;
	}
	public String getFishingName() {
		return fishingName;
	}
	public void setFishingName(String fishingName) {
		this.fishingName = fishingName;
	}
	public String getFishingType() {
		return fishingType;
	}
	public void setFishingType(String fishingType) {
		this.fishingType = fishingType;
	}
	public String getFishingAddress() {
		return fishingAddress;
	}
	public void setFishingAddress(String fishingAddress) {
		this.fishingAddress = fishingAddress;
	}
	public double getFishingLat() {
		return fishingLat;
	}
	public void setFishingLat(double fishingLat) {
		this.fishingLat = fishingLat;
	}
	public double getFishingLng() {
		return fishingLng;
	}
	public void setFishingLng(double fishingLng) {
		this.fishingLng = fishingLng;
	}
	public String getFishingNumber() {
		return fishingNumber;
	}
	public void setFishingNumber(String fishingNumber) {
		this.fishingNumber = fishingNumber;
	}
	public String getFishingSpecies() {
		return fishingSpecies;
	}
	public void setFishingSpecies(String fishingSpecies) {
		this.fishingSpecies = fishingSpecies;
	}
	public String getFishingPriece() {
		return fishingPriece;
	}
	public void setFishingPriece(String fishingPriece) {
		this.fishingPriece = fishingPriece;
	}
	
}
