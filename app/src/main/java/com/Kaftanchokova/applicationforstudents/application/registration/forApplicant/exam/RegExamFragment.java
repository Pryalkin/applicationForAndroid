package com.Kaftanchokova.applicationforstudents.application.registration.forApplicant.exam;

import static com.Kaftanchokova.applicationforstudents.constants.Constants.AUTHORIZATION;
import static com.Kaftanchokova.applicationforstudents.constants.Constants.TOKEN_PREFIX;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Kaftanchokova.applicationforstudents.R;
import com.Kaftanchokova.applicationforstudents.constants.Constants;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegExamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegExamFragment extends Fragment {

    private int pageNumber;
    private TextView info;
    private TextView item;
    private Button registrationItem;
    private RequestQueue requestQueue;
    private String[] items = {"Mathematics", "Physics", "English", "Russian"};

    public static RegExamFragment newInstance(int page) {
        RegExamFragment fragment = new RegExamFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public RegExamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg_exam, container, false);

        elementInitialization(view);
        listenerForRegisterExam();
        return view;
    }

    private void listenerForRegisterExam() {
        registrationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2:8081/exams/register/" + Constants.username;

                Map<String, String> params = new HashMap();
                params.put("id", null);
                params.put("item", items[pageNumber]);
                params.put("user", null);
                params.put("registrationDate", null);

                JSONObject parameters = new JSONObject(params);

                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), "Вы успешно зарегистрировались на экзамен!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getActivity(), "Вы уже зарегистрированы на этот экзамен!", Toast.LENGTH_SHORT).show();
                        //TODO: handle failure
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
                requestQueue.add(jsonRequest);
            }
        });
    }

    private void elementInitialization(View view) {
        registrationItem = view.findViewById(R.id.registrationExam);
        info = view.findViewById(R.id.textInfo);
        item = view.findViewById(R.id.textItem);
        if (pageNumber == 0){
            item.setText("Здесь информация о экзамене");
            info.setText("Математика");
        } else if (pageNumber == 1){
            item.setText("Здесь информация о экзамене");
            info.setText("Физика");
        } else if (pageNumber == 2){
            item.setText("Здесь информация о экзамене");
            info.setText("Английский язык");
        } else if (pageNumber == 3){
            item.setText("Здесь информация о экзамене");
            info.setText("Руский язык");
        }
    }
}