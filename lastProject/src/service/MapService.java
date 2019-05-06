package service;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import model.ResListDAO;
import model.domain.ResListDTO;

public class MapService {

	public static ArrayList<JSONObject> getSearchData(String regin, String fishSpecies) throws Exception {
		ArrayList<ResListDTO> alldata = ResListDAO.getall(regin, fishSpecies);
		ArrayList<JSONObject> jsondata = new ArrayList<>();
		for(ResListDTO data : alldata) {
			JSONObject obj = new JSONObject();
			obj.put("fishingName", data.getFishingName());
			obj.put("fishingType", data.getFishingType());
			obj.put("fishingAddress", data.getFishingAddress());
			obj.put("fishingLat", data.getFishingLat());
			obj.put("fishingLng", data.getFishingLng());
			obj.put("fishingNumber", data.getFishingNumber());
			obj.put("fishingSpecies", data.getFishingSpecies());
			obj.put("fishingPriece", data.getFishingPriece());
			jsondata.add(obj);
		}
		return jsondata;
	}
	
}
