package com.Kaftanchokova.applicationforstudents.application.registration.forApplicant.exam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.Kaftanchokova.applicationforstudents.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationForExamsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationForExamsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinner1ForItems;
    private Spinner spinner2ForItems;
    private Spinner spinner3ForItems;
    private String[] groupCountries = { "Математика", "Физика", "Английский язык", "Русский язык"};

    public RegistrationForExamsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationForExamsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationForExamsFragment newInstance(String param1, String param2) {
        RegistrationForExamsFragment fragment = new RegistrationForExamsFragment();
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
        View view = inflater.inflate(R.layout.fragment_registration_for_exams, container, false);

        ViewPager2 pager = view.findViewById(R.id.exam);
        FragmentStateAdapter pageAdapter = new RegExamAdapter(getActivity());
        pager.setAdapter(pageAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout_exam);
        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy(){

            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                if (position == 0){
                    tab.setText("Math");
                } else if (position == 1){
                    tab.setText("Ph");
                } else if (position == 2){
                    tab.setText("Eng");
                } else if (position == 3){
                    tab.setText("Rus");
                }
            }
        });
        tabLayoutMediator.attach();



//        spinner1ForItems = view.findViewById(R.id.items1SpinnerText);
//        spinner2ForItems = view.findViewById(R.id.items2SpinnerText);
//        spinner3ForItems = view.findViewById(R.id.items3SpinnerText);
//        ArrayAdapter<String> adapterForItems = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, groupCountries);
//        adapterForItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1ForItems.setAdapter(adapterForItems);
//        spinner2ForItems.setAdapter(adapterForItems);
//        spinner3ForItems.setAdapter(adapterForItems);

        return view;
    }
}