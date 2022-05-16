package com.Kaftanchokova.applicationforstudents.application.registration.forSuperAdmin;

import static com.Kaftanchokova.applicationforstudents.constants.Constants.AUTHORIZATION;
import static com.Kaftanchokova.applicationforstudents.constants.Constants.TOKEN_PREFIX;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
 * Use the {@link ExamResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExamResultsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String[] items = {"Mathematics", "Physics", "English", "Russian"};
    private Spinner spinnerForItem;

    private TextView firstName;
    private TextView middleName;
    private TextView lastName;
    private TextView item;
    private TextView grade;
    private RequestQueue requestQueue;
    private Button register;

    public ExamResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExamResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExamResultsFragment newInstance(String param1, String param2) {
        ExamResultsFragment fragment = new ExamResultsFragment();
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
        View view = inflater.inflate(R.layout.fragment_exam_results, container, false);
        elementInitialization(view);
        spinnerForItem = view.findViewById(R.id.textForExamItemName);
        ArrayAdapter<String> adapterForGroup = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, items);
        adapterForGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForItem.setAdapter(adapterForGroup);
        listenerForButtonExamResult();
        return view;
    }

    private void listenerForButtonExamResult() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemUrl = spinnerForItem.getSelectedItem().toString();
                Toast.makeText(getActivity(), itemUrl, Toast.LENGTH_SHORT).show();
                String gradeUrl = grade.getText().toString();
                String url = "http://10.0.2.2:8081/exams/register/" + itemUrl + "/" + gradeUrl;

                Map<String, String> params = new HashMap();
                params.put("id", null);
                params.put("userId", null);
                params.put("firstName", firstName.getText().toString());
                params.put("middleName", middleName.getText().toString());
                params.put("lastName", lastName.getText().toString());
                params.put("username", null);
                params.put("password", null);
                params.put("email", null);
                params.put("profileImageUrl", null);
                params.put("lastLoginDate", null);
                params.put("lastLoginDateDisplay", null);
                params.put("joinDate", null);
                params.put("role", null);
                params.put("authorities", null);
                params.put("isActive", null);
                params.put("isNotLocked", null);

                JSONObject parameters = new JSONObject(params);

                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), "Выставление оценки прошло успешно!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getActivity(), "Оценка уже выставлена!", Toast.LENGTH_SHORT).show();
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
        register = view.findViewById(R.id.registrationExamResult);
        firstName = view.findViewById(R.id.textForExamFirstName);
        middleName = view.findViewById(R.id.textForExamMiddleName);
        lastName = view.findViewById(R.id.textForExamLastName);
//        item = view.findViewById(R.id.textForExamItemName);
        grade = view.findViewById(R.id.textForExamGrade);
    }
}