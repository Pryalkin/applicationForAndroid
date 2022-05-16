package com.Kaftanchokova.applicationforstudents.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Kaftanchokova.applicationforstudents.application.ApplicationActivity;
import com.Kaftanchokova.applicationforstudents.R;
import com.Kaftanchokova.applicationforstudents.constants.Constants;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button loginButton;
    private EditText textUsername;
    private EditText textPassword;
    private RequestQueue requestQueue;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        elementInitialization(view);
        listenerForRegisterButtons();
        return view;
    }


    private void elementInitialization(View view) {
        loginButton = view.findViewById(R.id.login);
        textUsername = view.findViewById(R.id.textUsernameLogin);
        textPassword = view.findViewById(R.id.textPasswordLogin);
    }

    private void listenerForRegisterButtons() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textUsername.getText().toString().equals("")){
                    if (!textPassword.getText().toString().equals("")){
                        listenerToLogin();
                    } else Toast.makeText(getActivity(), "Введите password", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "Введите username", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listenerToLogin() {
        String url = "http://10.0.2.2:8081/user/login";

        Map<String, String> params = new HashMap();
        params.put("id", null);
        params.put("userId", null);
        params.put("firstName", null);
        params.put("middleName", null);
        params.put("lastName", null);
        params.put("username", textUsername.getText().toString());
        params.put("password", textPassword.getText().toString());
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
                try {
                    Constants.token = response.getJSONObject("headers").getString("Jwt-Token");
                    Constants.username = response.getString("username");
                    Constants.role = response.getString("role");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textUsername.setText("");
                textPassword.setText("");
                startActivity(new Intent(getActivity(), ApplicationActivity.class));
                Toast.makeText(getActivity(), "Добро пожаловать!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
            }
        })
        {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject jsonResponse = new JSONObject(jsonString);
                    jsonResponse.put("headers", new JSONObject(response.headers));
                    return Response.success(jsonResponse,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonRequest);
    }
}