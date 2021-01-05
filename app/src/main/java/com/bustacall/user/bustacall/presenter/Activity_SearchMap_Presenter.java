package com.bustacall.user.bustacall.presenter;

import com.bustacall.user.bustacall.view.Activity_SearchMap;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapTapi;

/**
 * Created by user on 2016-10-25.
 */

public class Activity_SearchMap_Presenter {
    Activity_SearchMap view;
    TMapData tmapdata; // POI검색, 경로검색 등의 지도데이터를 관리하는 클래스

    public Activity_SearchMap_Presenter(Activity_SearchMap view, TMapTapi tmaptapi) {
        this.view = view;
    }
}
