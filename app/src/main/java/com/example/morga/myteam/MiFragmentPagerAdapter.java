package com.example.morga.myteam;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;


public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String  tabTitles[] = new String[] {
            "Equipo", "Entreno", "Partido"
    };
    String token;

    public MiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem (int position) {

        Fragment f = null;

        switch(position) {
            case 0:
                f = FragmentEquipo.newInstance();
                break;

            case 1:
                f = FragmentEntreno.newInstance();
                break;

            case 2:
                f = FragmentPartido.newInstance();
                break;
        }

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
