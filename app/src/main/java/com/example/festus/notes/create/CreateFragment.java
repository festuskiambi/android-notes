package com.example.festus.notes.create;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.festus.notes.R;
import com.example.festus.notes.data.Note;
import com.example.festus.notes.list.ListActivity;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {

    private ViewPager drawablePager;
    private CirclePageIndicator pageIndicator;
    private ImageButton back;
    private ImageButton done;
    private EditText messageInput;

    private PagerAdapter pagerAdapter;






    public CreateFragment() {
    }



    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);

        back = (ImageButton) v.findViewById(R.id.imb_create_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListActivity();
            }
        });

        done = (ImageButton) v.findViewById(R.id.imb_create_done);
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Note note = new Note(
//                        getDate(),
//                        messageInput.getText().toString(),
//                        getDrawableResource(drawablePager.getCurrentItem())
//                );
//                newListItemViewModel.addNewItemToDatabase(listItem);
//
//                startListActivity();
//            }
//        });

        messageInput =  v.findViewById(R.id.edt_create_message);

        drawablePager = (v.findViewById(R.id.vp_create_drawable));

        pagerAdapter = new DrawablePagerAdapter();
        drawablePager.setAdapter(pagerAdapter);

        pageIndicator = v.findViewById(R.id.vpi_create_drawable);
        pageIndicator.setViewPager(drawablePager);

        return v;
    }


    public int getDrawableResource (int pagerItemPosition){
        switch (pagerItemPosition){
            case 0:
                return R.drawable.red_drawable;
            case 1:
                return R.drawable.blue_drawable;
            case 2:
                return R.drawable.green_drawable;
            case 3:
                return R.drawable.yellow_drawable;
            default:
                return 0;
        }
    }

    private void startListActivity() {
        startActivity(new Intent(getActivity(), ListActivity.class));
    }

    private class DrawablePagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ImageView pagerItem = (ImageView) inflater.inflate(R.layout.item_drawable_pager,
                    container,
                    false);

            switch (position) {
                case 0:
                    pagerItem.setImageResource(R.drawable.red_drawable);
                    break;
                case 1:
                    pagerItem.setImageResource(R.drawable.blue_drawable);
                    break;
                case 2:
                    pagerItem.setImageResource(R.drawable.green_drawable);
                    break;
                case 3:
                    pagerItem.setImageResource(R.drawable.yellow_drawable);
                    break;
            }

            container.addView(pagerItem);
            return pagerItem;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    public String getDate() {

        Date currentDate = Calendar.getInstance().getTime();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd/kk:mm:ss");

        return format.format(currentDate);
    }

}
