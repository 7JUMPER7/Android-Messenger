package com.example.messanger.Sidebar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.messanger.R;

public class SidebarFragment extends Fragment {
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "param2";

    private String name;
    private String mParam2;

    public SidebarFragment() { }

    public static SidebarFragment newInstance(String name) {
        SidebarFragment fragment = new SidebarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sidebar, container, false);

        TextView userName = view.findViewById(R.id.nameText);
        userName.setText(name);

        return view;
    }
}