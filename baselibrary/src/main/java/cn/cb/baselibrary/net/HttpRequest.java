package cn.cb.baselibrary.net;

public class HttpRequest {

    /*public static void getHomeList(ResponseCallback callback) {
        String url = BuildConfig.HOST + "/getWaterMeterApp?type=home&companyCode=" + WMApplication.getUser().getCompanycode();
        RequestMode.getRequest(url, null, callback, HomeHttpBean.class);
    }

    public static void getCommunityList(ResponseCallback callback, String caliber) {
        String url = BuildConfig.HOST + "/getWaterMeterApp?" +
                "type=community" +
                "&companyCode=" + WMApplication.getUser().getCompanycode() +
                "&devCaliber=" + caliber;
        RequestMode.getRequest(url, null, callback, CommunityBean.class);
    }

    public static void getBuildingList(ResponseCallback callback, String caliber, String communityId, String latLngList) {
        String url = BuildConfig.HOST + "/getWaterMeterApp?type=building" +
                "&companyCode=" + WMApplication.getUser().getCompanycode() +
                "&devCaliber=" + caliber +
                "&latLngList=" + latLngList +
                "&communityId=" + communityId;
        RequestMode.getRequest(url, null, callback, BuildingBean.class);
    }

    public static void getFloorList(ResponseCallback callback, String caliber, String communityId, String buildingId, String latitude, String longitude) {
        String url = BuildConfig.HOST + "/getWaterMeterApp?type=floor" +
                "&companyCode=" + WMApplication.getUser().getCompanycode() +
                "&devCaliber=" + caliber +
                "&communityId=" + communityId +
                "&buildingId=" + buildingId +
                "&latitude=" + latitude +
                "&longitude=" + longitude;
        RequestMode.getRequest(url, null, callback, FloorItemBean.class);
    }

    public static void registerDev(ResponseCallback callback, Map<String, String> param) {
        String url = BuildConfig.HOST + "/getWaterMeterApp?type=registerDev";
        RequestParams params = new RequestParams();
        params.put("registerParam", param.toString());
        RequestMode.postRequest(url, params, callback, HttpBean.class);
    }

    public static void getSpecsList(ResponseCallback callback) {
        String url = BuildConfig.HOST + "/getWaterMeterApp?" +
                "companyCode=" + WMApplication.getUser().getCompanycode() +
                "&type=devSpecs";
        RequestMode.getRequest(url, null, callback, SpecsBean.class);
    }*/
}
