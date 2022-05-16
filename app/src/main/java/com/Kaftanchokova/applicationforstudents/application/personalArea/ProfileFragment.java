package com.Kaftanchokova.applicationforstudents.application.personalArea;

import static com.Kaftanchokova.applicationforstudents.constants.Constants.AUTHORIZATION;
import static com.Kaftanchokova.applicationforstudents.constants.Constants.TOKEN_PREFIX;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.Kaftanchokova.applicationforstudents.R;
import com.Kaftanchokova.applicationforstudents.constants.Constants;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static String STR = "Количество экзаменов: ";

    private RequestQueue requestQueue;
    private LinearLayout generalLinearLayout;
    private Context context;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getActivity().getApplicationContext();
        ScrollView scrollView = createAGeneralLayoutScroll(context);
        generalLinearLayout = createAGeneralLinerLayout(context);
        requestRegExam();
        requestForExams();
        scrollView.addView(generalLinearLayout);
        return scrollView;
    }

    private TextView createTextForGrade(Context context) {
        TextView topicForOverview = new TextView(context);
        topicForOverview.setTextColor(Color.BLACK);
        topicForOverview.setTextSize(20);
        topicForOverview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return topicForOverview;
    }

    private ScrollView createAGeneralLayoutScroll(Context context) {
        ScrollView scrollView = new ScrollView(context);
        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.setPadding(30, 50, 30, 30);
        return scrollView;
    }

    private LinearLayout createAGeneralLinerLayout(Context context) {
        LinearLayout generalLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams generalParamsForLinerLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        generalLinearLayout.setOrientation(LinearLayout.VERTICAL);
        generalLinearLayout.setLayoutParams(generalParamsForLinerLayout);
        return generalLinearLayout;
    }

    private LinearLayout createALinearLayoutForExam(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams paramsForLinerLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(paramsForLinerLayout);
        return linearLayout;
    }

    private LinearLayout createALinearLayoutForRegister(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams paramsForLinerLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(paramsForLinerLayout);
        return linearLayout;
    }

    private TextView createTextForItemName(Context context) {
        TextView topicForOverview = new TextView(context);
        topicForOverview.setTextColor(Color.BLACK);
        topicForOverview.setTextSize(20);
        topicForOverview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return topicForOverview;
    }

    private void requestRegExam() {
        String url = "http://10.0.2.2:8081/exams/results/grades/" + Constants.username;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject examResult = new JSONObject();
                JSONObject exam = new JSONObject();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        examResult = response.getJSONObject(i);
                        exam = examResult.getJSONObject("examRegistration");
                        LinearLayout linearLayout = createALinearLayoutForExam(context);
                        TextView textForItemName = createTextForItemName(context);
                        TextView textForGrade = createTextForGrade(context);
                        linearLayout.addView(textForItemName);
                        linearLayout.addView(textForGrade);
                        generalLinearLayout.addView(linearLayout);
                        textForItemName.setText(exam.getString("item") + ": ");
                        textForGrade.setText(examResult.getString("grade"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put(AUTHORIZATION, TOKEN_PREFIX + Constants.token);
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void requestForExams() {
        String url = "http://10.0.2.2:8081/exams/resultRegister/" + Constants.username;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject examRegistration = new JSONObject();
                JSONObject user = new JSONObject();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        examRegistration = response.getJSONObject(i);
                        String item = examRegistration.getString("item");
                        LinearLayout linearLayout = createALinearLayoutForRegister(context);
                        TextView textForRegisterItem = createTextForItemName(context);
                        textForRegisterItem.setText("Вы разегистрированы на: " + item);
                        linearLayout.addView(textForRegisterItem);
                        ImageButton delete = createImageButton(context);
                        linearLayout.addView(delete);
                        generalLinearLayout.addView(linearLayout);
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
                                String urlDel = "http://10.0.2.2:8081/exams/delete/register/" + item + "/" + Constants.username;
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlDel,
                                        response -> {
                                            Toast.makeText(getActivity(), "Регистрация на экзамен удалена!", Toast.LENGTH_SHORT).show();
                                        }
                                        ,
                                        error -> {
                                            Toast.makeText(getActivity(), "Регистрация на экзамен не может быть удалена!", Toast.LENGTH_SHORT).show();
                                        }
                                ){
                                    @Override
                                    public Map<String, String> getHeaders() throws AuthFailureError {
                                        Map<String, String>  params = new HashMap<String, String>();
                                        params.put(AUTHORIZATION, TOKEN_PREFIX + Constants.token);
                                        return params;
                                    }
                                };
                                requestQueue = Volley.newRequestQueue(getContext());
                                requestQueue.add(stringRequest);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put(AUTHORIZATION, TOKEN_PREFIX + Constants.token);
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private ImageButton createImageButton(Context context) {
        ImageButton imageButton = new ImageButton(context);
        imageButton.setImageResource(R.drawable.ic_delete);
        LinearLayout.LayoutParams paramsForLinerLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageButton.setLayoutParams(paramsForLinerLayout);
        return imageButton;
    }

}