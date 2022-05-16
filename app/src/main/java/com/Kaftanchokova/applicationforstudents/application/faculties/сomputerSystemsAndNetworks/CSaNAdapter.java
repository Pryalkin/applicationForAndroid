package com.Kaftanchokova.applicationforstudents.application.faculties.сomputerSystemsAndNetworks;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CSaNAdapter extends FragmentStateAdapter {

    public CSaNAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return(CSaNFragment.newInstance(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
