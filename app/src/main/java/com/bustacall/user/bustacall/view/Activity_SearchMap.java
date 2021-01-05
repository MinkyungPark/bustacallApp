package com.bustacall.user.bustacall.view;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bustacall.user.bustacall.AppController;
import com.bustacall.user.bustacall.GpsInfo;
import com.bustacall.user.bustacall.R;
import com.bustacall.user.bustacall.TMapItemAdapter;
import com.bustacall.user.bustacall.dialog.Dialog_Progress;
import com.bustacall.user.bustacall.presenter.Activity_SearchMap_Presenter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapTapi;

import java.util.ArrayList;

/**
 * 출발지, 도착지 검색 페이지
 * Created by user on 2016-10-25.
 */

public class Activity_SearchMap extends BaseActivity implements View.OnClickListener{

    ImageView iv_btnSearch;
    EditText et_search;
    TextView tv_currentpos, tv_backbutton, tv_submitbutton, tv_noresultmsg;
    Activity_SearchMap_Presenter presenter;
    ArrayList<TMapPOIItem> searchList;
    TMapTapi tmaptapi; // 연동 관련 클래스
    TMapItemAdapter adapter;
    ListView listView;
    String selectPlace; // 리스트뷰에서 클릭한 Item의 장소명
    TMapPOIItem selectTmapItem; // 리스트뷰에서 클릭한 Item의 TMapPOIItem 정보 저장
    TMapGpsManager tmapGps=null;
    GpsInfo gpsInfo;
    Intent intent = getIntent();
    AppController app;

    public TMapItemAdapter getAdapter() {return adapter;}
    public void setAdapter(TMapItemAdapter adapter) {this.adapter = adapter;}
    public TextView getTv_noresultmsg() {return tv_noresultmsg;}
    public void setTv_noresultmsg(TextView tv_noresultmsg) {this.tv_noresultmsg = tv_noresultmsg;}
    public Activity_SearchMap_Presenter getPresenter() {return presenter;}
    public void setPresenter(Activity_SearchMap_Presenter presenter) { this.presenter = presenter;}
    public ListView getListView() {return listView;}
    public void setSearchList(ArrayList<TMapPOIItem> searchList) {this.searchList = searchList;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmap);
        set_Values();
        app = (AppController)getApplicationContext();
        tmapGps = new TMapGpsManager(this);
        tmaptapi = new TMapTapi(this);
        tmaptapi.setSKPMapAuthentication("713a762b-2a5c-3013-81b7-0bf4360d6355");
        tmaptapi.setOnAuthenticationListener(new TMapTapi.OnAuthenticationListenerCallback() {
            /**인증 성공 시*/
            @Override
            public void SKPMapApikeySucceed() {
                Log.d("test", "TMap 사용 가능!");
            }

            /**인증 실패 시*/
            @Override
            public void SKPMapApikeyFailed(String s) {
                Log.d("test", "TMap 사용 안됨 ㅠㅠ!");

            }
        });
    }

    public void set_Values() {
//        presenter = new Activity_SearchMap_Presenter(this, tmaptapi);
        set_Weight();
    }

    public void set_Weight() {
        iv_btnSearch = (ImageView) findViewById(R.id.activity_searchmap_searchicon);
        et_search = (EditText) findViewById(R.id.activity_searchmap_et_search);
        tv_currentpos = (TextView) findViewById(R.id.activity_searchmap_tv_currentpos);
        et_search.setHint("위치 검색");
        tv_backbutton = (TextView) findViewById(R.id.activity_searchmap_backbutton);
        tv_submitbutton = (TextView) findViewById(R.id.activity_searchmap_submitbutton);
        tv_noresultmsg = (TextView) findViewById(R.id.activity_searchmap_no_searchresult_msg);
        listView = (ListView) findViewById(R.id.activity_searchmap_listview);
        searchList = new ArrayList<TMapPOIItem>();
        tv_noresultmsg.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
        iv_btnSearch.setOnClickListener(this);
        tv_backbutton.setOnClickListener(this);
        tv_submitbutton.setOnClickListener(this);
        tv_currentpos.setOnClickListener(this);
        listView.setOnItemClickListener(new ListViewItemClickListener());

        et_search.requestFocus();
        final InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        searchPlace();
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "검색값이 없습니다.", Toast.LENGTH_SHORT).show();
                        return false;
                }
                return true;
            }
        });

    }

    /**리스트 뷰 클릭시 정보 저장 */
    private class ListViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectTmapItem = searchList.get(position);
            et_search.setText(searchList.get(position).getPOIName());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_searchmap_searchicon:
                // 찾기 버튼 클릭
                searchPlace();
                break;
            case R.id.activity_searchmap_backbutton:
                // 뒤로 가기 클릭
                finish();
                break;
            case R.id.activity_searchmap_submitbutton:
                // 설정 완료 클릭
                if (selectTmapItem != null) {
                    Intent result = new Intent();
                    String[] arr = null;
                    try {
                        selectPlace = selectTmapItem.getPOIName();
                        String addrStr = selectTmapItem.getPOIAddress();

                        if(addrStr.contains("null")) {
                            addrStr = addrStr.replaceAll(" null","");
                            Log.d("test",addrStr);
                        }

                        arr = addrStr.split(" ");
                        Log.d("test",arr.toString());
                        Log.d("test",arr.toString());

                    } catch (NullPointerException e) {
                        selectPlace = "설정하기";
                    }
                    result.putExtra("selectPlace", selectPlace.trim());
                    result.putExtra("latitude", selectTmapItem.getPOIPoint().getLatitude());
                    result.putExtra("longitude", selectTmapItem.getPOIPoint().getLongitude());
                    result.putExtra("region",arr[0]);
                    result.putExtra("region_two",arr[1]);
                    this.setResult(RESULT_OK, result);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "선택한 출발지가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.activity_searchmap_tv_currentpos:
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                       nowToreturn();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(Activity_SearchMap.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }
                };
                new TedPermission(this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                        .check();
                break;
        }

    }

    /**
     * 장소를 찾는다
     */
    public void searchPlace() {
        searchList.clear();
        Log.d("text", et_search.getText().toString());

        TMapData tmapdata = new TMapData();
        final ArrayList<TMapPOIItem> arry_list= new ArrayList<>();
        final Dialog_Progress dialog=new Dialog_Progress(this);
        if(this!=null)dialog.show();
        tmapdata.findAllPOI(et_search.getText().toString(), new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> arrayList) {

                for(int i=0;i<arrayList.size();i++){
                    TMapPOIItem item = arrayList.get(i);
                    arry_list.add(item);
                }
                searchList = arry_list;


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                // 해당 작업을 처리함
                                if (searchList != null) {
                                    tv_noresultmsg.setVisibility(View.INVISIBLE);
                                    listView.setVisibility(View.VISIBLE);
                                    adapter = new TMapItemAdapter(searchList);
                                    listView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }).start();
                dialog.dismiss();
            }
        });
    }

    // GPS 설정화면으로 이동
    private void moveConfigGPS() {
        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }

    public void nowToreturn(){
        // GPS 정보를 보여주기 위한 이벤트 클래스 등록
        gpsInfo = new GpsInfo(Activity_SearchMap.this);
        // GPS 사용유무 가져오기
        if (gpsInfo.isGetLocation()) {
            final TMapData tMapData = new TMapData();

            Location location=gpsInfo.getLocation();
            final Double lat = location.getLatitude();
            final Double lon = location.getLongitude();
            final Dialog_Progress dialog=new Dialog_Progress(this);
            if(this!=null)dialog.show();
            tMapData.convertGpsToAddress(lat, lon, new TMapData.ConvertGPSToAddressListenerCallback() {
                @Override
                public void onConvertToGPSToAddress(final String s) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run() {
                                    // 해당 작업을 처리함
                                    et_search.setText(s);
                                    dialog.dismiss();
                                }
                            });
                        }
                    }).start();
                }
            });
        } else {
            // GPS 를 사용할수 없으므로
            gpsInfo.showSettingsAlert();
        }
    }
}
