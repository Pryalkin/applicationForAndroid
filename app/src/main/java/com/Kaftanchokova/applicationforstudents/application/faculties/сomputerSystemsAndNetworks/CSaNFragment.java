package com.Kaftanchokova.applicationforstudents.application.faculties.сomputerSystemsAndNetworks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Kaftanchokova.applicationforstudents.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CSaNFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CSaNFragment extends Fragment {

    private int pageNumber;

    public static CSaNFragment newInstance(int page) {
        CSaNFragment fragment = new CSaNFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public CSaNFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_csan, container, false);
        TextView textView = view.findViewById(R.id.displayText);
        if (pageNumber == 0){
            textView.setText("Факультет компьютерных систем и сетей: ");
            textView.append("'Кафедра высшей математики'");
        } else if (pageNumber == 1){
            textView.setText("Факультет компьютерных систем и сетей: ");
            textView.append("'Кафедра информатики'");
        } else if (pageNumber == 2){
            textView.setText("Факультет компьютерных систем и сетей: ");
            textView.append("'Кафедра программного обеспечения информационных технологий'");
        } else if (pageNumber == 3){
            textView.setText("Факультет компьютерных систем и сетей: ");
            textView.append("'Кафедра физики'");
        }
        return view;
    }
}