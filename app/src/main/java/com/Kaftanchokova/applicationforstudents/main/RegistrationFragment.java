package com.Kaftanchokova.applicationforstudents.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Kaftanchokova.applicationforstudents.R;
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
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button registerButton;
    private EditText textUsername;
    private EditText textEmail;
    private EditText textPassword1;
    private EditText textPassword2;
    private RequestQueue requestQueue;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        elementInitialization(view);
        listenerForRegisterButtons();
        return view;
    }

    private void elementInitialization(View view) {
        registerButton = view.findViewById(R.id.registration);
        textUsername = view.findViewById(R.id.textUsernameReg);
        textEmail = view.findViewById(R.id.textEmail);
        textPassword1 = view.findViewById(R.id.textPassword1Reg);
        textPassword2 = view.findViewById(R.id.textPassword2Reg);
    }

    private void listenerForRegisterButtons() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textUsername.getText().toString().equals("")){
                    if (!textEmail.getText().toString().equals("")){
                        if (!textPassword1.getText().toString().equals("")){
                            if (!textPassword2.getText().toString().equals("")){
                                if (textPassword1.getText().toString().equals(textPassword2.getText().toString())){
                                    listenerToRegister();
                                } else Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                            } else Toast.makeText(getActivity(), "Enter a second password", Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(getActivity(), "Enter a password", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getActivity(), "Enter a email", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "Enter a username", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listenerToRegister() {
        String url = "http://10.0.2.2:8081/user/register";

        Map<String, String> params = new HashMap();
        params.put("id", null);
        params.put("userId", null);
        params.put("firstName", null);
        params.put("middleName", null);
        params.put("lastName", null);
        params.put("username", textUsername.getText().toString());
        params.put("password", textPassword1.getText().toString());
        params.put("email", textEmail.getText().toString());
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
                Toast.makeText(getActivity(), "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                textUsername.setText("");
                textEmail.setText("");
                textPassword1.setText("");
                textPassword2.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonRequest);
    }
}