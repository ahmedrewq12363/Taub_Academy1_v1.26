package com.taubacademy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.parse.ParseException;

public class Profile extends android.support.v4.app.Fragment{
    Tutor tutor;
    public Profile() {

    }

    public Profile(Tutor tutor) {
        this.tutor = tutor;
        try {
            this.tutor.fetch();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View profile = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView Name = (TextView) profile.findViewById(R.id.NameOnPRo);
        TextView Email = (TextView) profile.findViewById(R.id.Email);
        final ListView Avail = (ListView) profile.findViewById(R.id.ListAvail);
        final ListView Taught = (ListView) profile.findViewById(R.id.Courses);
        final ListView Feeds = (ListView) profile.findViewById(R.id.Feedbacks);
        TextView Phone = (TextView) profile.findViewById(R.id.Phone);
        TextView Rate = (TextView) profile.findViewById(R.id.RateThis);
        final FloatingActionButton fab = (FloatingActionButton) profile.findViewById(R.id.fab);

        ((Button)profile.findViewById(R.id.textView3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Taught.getVisibility()==View.VISIBLE){
                    Taught.setVisibility(View.GONE);
                }else{
                    Taught.setVisibility(View.VISIBLE);
                }
            }
        });

        ((Button)profile.findViewById(R.id.textView2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Avail.getVisibility()==View.VISIBLE){
                    Avail.setVisibility(View.GONE);
                }else{
                    (Avail).setVisibility(View.VISIBLE);
                }
            }
        });

        ((Button)profile.findViewById(R.id.Feeds)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Feeds.getVisibility()==View.VISIBLE){
                    Feeds.setVisibility(View.GONE);
                    ((View)fab).setVisibility(View.GONE);
                }else{
                    Feeds.setVisibility(View.VISIBLE);

                    ((View)fab).setVisibility(View.VISIBLE);
                }
            }
        });
        /*ParseImageView imagePro = (ParseImageView) profile.findViewById(R.id.imageView);
        ParseFile imageFile = tutor.getPhotoFile();
        if (imageFile != null) {
            imagePro.setParseFile(imageFile);
            imagePro.loadInBackground();
        }*/
        Name.setText(tutor.getName());
        Email.setText(tutor.getEmail());
        Phone.setText(tutor.getPhone());
        Rate.setText("Rate " + tutor.getName() + " :");
        Avail.setAdapter(new AvailableAdapter(getActivity().getBaseContext(), tutor.getAvailableTime()));
        Taught.setAdapter(new TaughtByAdapter(getActivity().getBaseContext(), tutor.getAllCourses()));
        Feeds.setAdapter(new FeedBacksAdapter(getActivity().getBaseContext(), tutor.getFeedbacks()));
        fab.attachToListView(Feeds);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddFeedBackDialog dialog = new AddFeedBackDialog(tutor);
                        dialog.show(getFragmentManager(),"AddingFeedBack");
                    }
                }
        );
        Taught.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        Avail.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        Feeds.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        return profile;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


 }



