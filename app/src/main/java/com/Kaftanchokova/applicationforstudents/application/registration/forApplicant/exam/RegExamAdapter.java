package com.Kaftanchokova.applicationforstudents.application.registration.forApplicant.exam;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class RegExamAdapter  extends FragmentStateAdapter {

    public RegExamAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return(RegExamFragment.newInstance(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}