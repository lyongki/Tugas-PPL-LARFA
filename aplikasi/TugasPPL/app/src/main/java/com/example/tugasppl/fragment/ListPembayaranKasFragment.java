package com.example.tugasppl.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.tugasppl.HorizontalScroll;
import com.example.tugasppl.Model.Kas;
import com.example.tugasppl.R;
import com.example.tugasppl.VerticalScroll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListPembayaranKasFragment extends Fragment implements VerticalScroll.ScrollViewListener, HorizontalScroll.ScrollViewListener{

    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    private RelativeLayout relativeLayoutMain;
    private RelativeLayout relativeLayoutA;
    private RelativeLayout relativeLayoutB;
    private RelativeLayout relativeLayoutC;
    private RelativeLayout relativeLayoutD;
    private TableLayout tableLayoutA;
    private TableLayout tableLayoutB;
    private TableLayout tableLayoutC;
    private TableLayout tableLayoutD;
    private TableRow tableRow;
    private TableRow tableRowB;
    private HorizontalScroll horizontalScrollViewB;
    private HorizontalScroll horizontalScrollViewD;
    private VerticalScroll scrollViewC;
    private VerticalScroll scrollViewD;
    private int tableColumnCountB= 0;
    private int tableRowCountC= 0;
    private int jumlahBaris;
    private SharedPreferences preferences;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ArrayList<Kas> dataKas;
    private String url = "http://192.168.2.254/ServiceTugasPPL.php";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_pembayaran_kas, container, false);

        init(root);

        getData();

        return root;
    }

    private void setTable() {
        getScreenDimension();
        initializeRelativeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollViewB.setScrollViewListener(this);
        horizontalScrollViewD.setScrollViewListener(this);
        scrollViewC.setScrollViewListener(this);
        scrollViewD.setScrollViewListener(this);
        addRowToTableA();
        initializeRowForTableB();

        for(int i=0; i<12; i++){
            addColumnsToTableB(Integer.toString(i+1), i);
        }
        for(int i=0; i<jumlahBaris; i++){
            initializeRowForTableD(i);
            addRowToTableC(dataKas.get(i).getNim());
            for(int j=0; j<tableColumnCountB; j++){
                if(j+1>Integer.parseInt(dataKas.get(i).getJumlah()))
                    addColumnToTableAtD(i,0, i+"_"+j);
                else
                    addColumnToTableAtD(i,1, i+"_"+j);
            }
        }

    }

    private void getData() {
        relativeLayoutMain.removeAllViews();
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("response",response);
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length()>0){
                        for(int i = 0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Kas kas = new Kas(jsonObject.getString("nim"), jsonObject.getString("jumlah"));
                            dataKas.add(kas);
                        }
                        jumlahBaris = dataKas.size();
                        setTable();
                    }else {
                        LinearLayout layout = new LinearLayout(getActivity());
                        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        layout.setGravity(Gravity.CENTER);
                        TextView empty = new TextView(getActivity());
                        empty.setText("Tidak ada Pembayaran Kas");
                        empty.setTextSize(24);
                        layout.addView(empty);
                        relativeLayoutMain.addView(layout);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Gagal terhubung ke server",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("flag","get_kas");
                map.put("id_ukm",preferences.getString("id_ukm","0"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(View root) {
        relativeLayoutMain= (RelativeLayout)root.findViewById(R.id.relativeLayoutKasMain);
        preferences = getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getActivity());
        dataKas = new ArrayList<>();
    }

    private void getScreenDimension(){
        WindowManager wm= (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH= size.x;
        SCREEN_HEIGHT = size.y;
    }
    private void initializeRelativeLayout(){
        relativeLayoutA= new RelativeLayout(getActivity());
        relativeLayoutA.setId(R.id.relativeLayoutA);
        relativeLayoutA.setPadding(0,0,0,0);

        relativeLayoutB= new RelativeLayout(getActivity());
        relativeLayoutB.setId(R.id.relativeLayoutB);
        relativeLayoutB.setPadding(0,0,0,0);

        relativeLayoutC= new RelativeLayout(getActivity());
        relativeLayoutC.setId(R.id.relativeLayoutC);
        relativeLayoutC.setPadding(0,0,0,0);

        relativeLayoutD= new RelativeLayout(getActivity());
        relativeLayoutD.setId(R.id.relativeLayoutD);
        relativeLayoutD.setPadding(0,0,0,0);

        relativeLayoutA.setLayoutParams(new RelativeLayout.LayoutParams(300,SCREEN_HEIGHT/20));
        this.relativeLayoutMain.addView(relativeLayoutA);


        RelativeLayout.LayoutParams layoutParamsRelativeLayoutB= new RelativeLayout.LayoutParams(SCREEN_WIDTH- 300, SCREEN_HEIGHT/20);
        layoutParamsRelativeLayoutB.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutA);
        relativeLayoutB.setLayoutParams(layoutParamsRelativeLayoutB);
        this.relativeLayoutMain.addView(relativeLayoutB);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutC= new RelativeLayout.LayoutParams(300, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutC.addRule(RelativeLayout.BELOW, R.id.relativeLayoutA);
        relativeLayoutC.setLayoutParams(layoutParamsRelativeLayoutC);
        this.relativeLayoutMain.addView(relativeLayoutC);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutD= new RelativeLayout.LayoutParams(SCREEN_WIDTH- 300, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.BELOW, R.id.relativeLayoutB);
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutC);
        relativeLayoutD.setLayoutParams(layoutParamsRelativeLayoutD);
        this.relativeLayoutMain.addView(relativeLayoutD);

    }

    private void initializeScrollers(){
        horizontalScrollViewB= new HorizontalScroll(getActivity());
        horizontalScrollViewB.setPadding(0,0,0,0);

        horizontalScrollViewD= new HorizontalScroll(getActivity());
        horizontalScrollViewD.setPadding(0,0,0,0);

        scrollViewC= new VerticalScroll(getActivity());
        scrollViewC.setPadding(0,0,0,0);

        scrollViewD= new VerticalScroll(getActivity());
        scrollViewD.setPadding(0,0,0,0);

        horizontalScrollViewB.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- 300, SCREEN_HEIGHT/20));
        scrollViewC.setLayoutParams(new ViewGroup.LayoutParams(300 ,SCREEN_HEIGHT - (SCREEN_HEIGHT/20)));
        scrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- 300, SCREEN_HEIGHT - (SCREEN_HEIGHT/20) ));
        horizontalScrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- 300, SCREEN_HEIGHT - (SCREEN_HEIGHT/20) ));

        this.relativeLayoutB.addView(horizontalScrollViewB);
        this.relativeLayoutC.addView(scrollViewC);
        this.scrollViewD.addView(horizontalScrollViewD);
        this.relativeLayoutD.addView(scrollViewD);

    }

    private  void initializeTableLayout(){
        tableLayoutA= new TableLayout(getActivity());
        tableLayoutA.setPadding(0,0,0,0);
        tableLayoutB= new TableLayout(getActivity());
        tableLayoutB.setPadding(0,0,0,0);
        tableLayoutB.setId(R.id.tableLayoutB);
        tableLayoutC= new TableLayout(getActivity());
        tableLayoutC.setPadding(0,0,0,0);
        tableLayoutD= new TableLayout(getActivity());
        tableLayoutD.setPadding(0,0,0,0);

        TableLayout.LayoutParams layoutParamsTableLayoutA= new TableLayout.LayoutParams(300, SCREEN_HEIGHT/20);
        tableLayoutA.setLayoutParams(layoutParamsTableLayoutA);
        tableLayoutA.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        this.relativeLayoutA.addView(tableLayoutA);

        TableLayout.LayoutParams layoutParamsTableLayoutB= new TableLayout.LayoutParams(SCREEN_WIDTH -300, SCREEN_HEIGHT/20);
        tableLayoutB.setLayoutParams(layoutParamsTableLayoutB);
        tableLayoutB.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
        this.horizontalScrollViewB.addView(tableLayoutB);

        TableLayout.LayoutParams layoutParamsTableLayoutC= new TableLayout.LayoutParams(300, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        tableLayoutC.setLayoutParams(layoutParamsTableLayoutC);
        tableLayoutC.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
        this.scrollViewC.addView(tableLayoutC);

        TableLayout.LayoutParams layoutParamsTableLayoutD= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tableLayoutD.setLayoutParams(layoutParamsTableLayoutD);
        this.horizontalScrollViewD.addView(tableLayoutD);

    }

    @Override
    public void onScrollChanged(HorizontalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == horizontalScrollViewB){
            horizontalScrollViewD.scrollTo(x,y);
        }
        else if(scrollView == horizontalScrollViewD){
            horizontalScrollViewB.scrollTo(x, y);
        }

    }

    @Override
    public void onScrollChanged(VerticalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == scrollViewC){
            scrollViewD.scrollTo(x,y);
        }
        else if(scrollView == scrollViewD){
            scrollViewC.scrollTo(x,y);
        }
    }

    private void addRowToTableA(){
        tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(300, SCREEN_HEIGHT/20);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getActivity());
        label_date.setText("Pembayaran Kas");
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow.addView(label_date);
        this.tableLayoutA.addView(tableRow);
    }

    private void initializeRowForTableB(){
        tableRowB= new TableRow(getActivity());
        tableRowB.setPadding(0,0,0,0);
        this.tableLayoutB.addView(tableRowB);
    }

    private synchronized void addColumnsToTableB(String text, final int id){
        tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams((SCREEN_WIDTH-300)/3, SCREEN_HEIGHT/20);
        tableRow.setPadding(3,3,3,4);
        tableRow.setLayoutParams(layoutParamsTableRow);
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        TextView label_date = new TextView(getActivity());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        this.tableRow.addView(label_date);
        this.tableRow.setTag(id);
        this.tableRowB.addView(tableRow);
        tableColumnCountB++;
    }

    private synchronized void addRowToTableC(String text){
        TableRow tableRow1= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow1= new TableRow.LayoutParams(300, SCREEN_HEIGHT/20);
        tableRow1.setPadding(3,3,3,4);
        tableRow1.setLayoutParams(layoutParamsTableRow1);
        TextView label_date = new TextView(getActivity());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow1.addView(label_date);
        TableRow tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(300, SCREEN_HEIGHT/20);
        tableRow.setPadding(0,0,0,0);
        tableRow.setLayoutParams(layoutParamsTableRow);
        tableRow.addView(tableRow1);
        this.tableLayoutC.addView(tableRow, tableRowCountC);
        tableRowCountC++;
    }

    private synchronized void initializeRowForTableD(int pos){
        TableRow tableRowB= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, SCREEN_HEIGHT/20);
        tableRowB.setPadding(0,0,0,0);
        tableRowB.setLayoutParams(layoutParamsTableRow);
        this.tableLayoutD.addView(tableRowB, pos);
    }

    private synchronized void addColumnToTableAtD(final int rowPos, int code, String tag){
        TableRow tableRowAdd= (TableRow) this.tableLayoutD.getChildAt(rowPos);
        tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams((SCREEN_WIDTH-300)/3, SCREEN_HEIGHT/20);
        tableRow.setPadding(3,3,3,4);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            tableRow.setBackground(getResources().getDrawable(R.drawable.cell_background));
        else
            tableRow.setBackgroundResource(R.drawable.cell_background);
        tableRow.setLayoutParams(layoutParamsTableRow);
        if(code == 1){
            ImageView check = new ImageView(getActivity());
            Glide.with(getActivity()).load(R.drawable.ic_check).into(check);
            this.tableRow.addView(check);
        }
        tableRow.setTag(tag);

        tableRowAdd.addView(tableRow);
    }
}