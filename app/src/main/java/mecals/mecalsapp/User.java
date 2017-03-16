package mecals.mecalsapp;

import org.json.JSONObject;

/**
 * Created by Olivier Darchy on 16/03/2017.
 */

public class User {
    private static User m_instance = null;
    private String PUB_ID = null;
    private String NAME = null;
    private String FIRST_NAME = null;
    private String FACILITY_NAME = null;
    private String FACILITY_PUB_ID = null;

    public static User getInstance() {
        if (m_instance == null) {
            m_instance = new User();
        }
        return (m_instance);
    }

    public void setUserData(JSONObject userData){
        JSONObject worker;
        JSONObject facility;

        if (userData != null){
            try {
                worker = userData.getJSONObject("worker_profile");
                facility = userData.getJSONObject("facility");
                this.PUB_ID = worker.get("pub_id").toString();
                this.NAME = worker.get("name").toString();
                this.FIRST_NAME = worker.get("first_name").toString();
                this.FACILITY_NAME = facility.get("name").toString();
                this.FACILITY_PUB_ID = facility.get("pub_id").toString();
            } catch (Exception e) {
                return;
            }
        }
    }

    public String getPubId(){
        return this.PUB_ID;
    }

    public String getName(){
        return this.NAME;
    }

    public String getFirstName(){
        return this.FIRST_NAME;
    }

    public String getFacilityName() {
        return this.FACILITY_NAME;
    }

    public String getFacilityPubId() {
        return this.FACILITY_PUB_ID;
    }
}
