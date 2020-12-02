package com.example.team2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
// pasted imports

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {

    private TextView eEventDetails;
    private DatabaseReference reff;
    private Button eCreate;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FacilityRentalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        eCreate = rootView.findViewById(R.id.create);
        eCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarFragment.this.openCreatePage();
            }
        });
        TextView textView = rootView.findViewById(R.id.etEventDetails);
        // TODO: Query event details from database and format string for display
        String event_text = "Monday December 7, 2020\n\tTitle: Soccer Practice\n\tDescription: A bunch of words or somethin\n";
        textView.setText(event_text);
        return rootView;
    }

    public void openCreatePage() {
        Intent intent = new Intent(getActivity(), CreateEventPage.class);
        startActivity(intent);
    }
}