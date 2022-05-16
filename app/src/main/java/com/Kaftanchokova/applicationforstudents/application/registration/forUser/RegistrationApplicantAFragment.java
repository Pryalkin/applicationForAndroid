package com.Kaftanchokova.applicationforstudents.application.registration.forUser;

import static com.Kaftanchokova.applicationforstudents.constants.Constants.*;

import android.content.Intent;
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
import com.Kaftanchokova.applicationforstudents.main.MainActivity;
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
 * Use the {@link RegistrationApplicantAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationApplicantAFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView firstName;
    private TextView middleName;
    private TextView lastName;
    private Button registrationForApplicant;
    private RequestQueue requestQueue;


    public RegistrationApplicantAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationApplicantAFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationApplicantAFragment newInstance(String param1, String param2) {
        RegistrationApplicantAFragment fragment = new RegistrationApplicantAFragment();
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
        View view = inflater.inflate(R.layout.fragment_registration_applicant_a, container, false);
        elementInitialization(view);
        listenerForRegisterButtons();
        return view;
    }

    private void listenerForRegisterButtons() {
        registrationForApplicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstName.getText().toString().equals("")){
                    if (!middleName.getText().toString().equals("")){
                        if (!lastName.getText().toString().equals("")){
                            listenerToRegister();
                        } else Toast.makeText(getActivity(), "Enter a last name", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getActivity(), "Enter a middle name", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "Enter a first name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listenerToRegister() {
        String url = "http://10.0.2.2:8081/user/registrationForApplicant/" + Constants.username;

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
                Toast.makeText(getActivity(), "Вы зарегистрировались как абитуриент!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Войдите в приложение снова!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
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

    private void elementInitialization(View view) {
        firstName = view.findViewById(R.id.textFirstName);
        middleName = view.findViewById(R.id.textMiddleName);
        lastName = view.findViewById(R.id.textLastName);
        registrationForApplicant = view.findViewById(R.id.registrationForApplicant);
    }
}