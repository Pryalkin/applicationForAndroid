package com.Kaftanchokova.applicationforstudents.application.faculties.сomputerSystemsAndNetworks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Kaftanchokova.applicationforstudents.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComputerSystemsAndNetworksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComputerSystemsAndNetworksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComputerSystemsAndNetworksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FacultiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComputerSystemsAndNetworksFragment newInstance(String param1, String param2) {
        ComputerSystemsAndNetworksFragment fragment = new ComputerSystemsAndNetworksFragment();
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

        View view = inflater.inflate(R.layout.fragment_computer_systems_and_networks, container, false);
        getActivity().findViewById(R.id.bottomNav).setVisibility(View.VISIBLE);

        ViewPager2 pager = view.findViewById(R.id.csan);
        FragmentStateAdapter pageAdapter = new CSaNAdapter(getActivity());
        pager.setAdapter(pageAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy(){

            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                if (position == 0){
                    tab.setText("ВМ");
                } else if (position == 1){
                    tab.setText("И");
                } else if (position == 2){
                    tab.setText("ПОИТ");
                } else if (position == 3){
                    tab.setText("Ф");
                }
            }
        });
        tabLayoutMediator.attach();

        return view;
    }
}