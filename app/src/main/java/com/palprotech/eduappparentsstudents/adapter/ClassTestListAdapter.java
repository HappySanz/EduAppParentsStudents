package com.palprotech.eduappparentsstudents.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.app.AppController;
import com.palprotech.eduappparentsstudents.bean.dashboard.ClassTest;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

/**
 * Created by Admin on 15-05-2017.
 */

public class ClassTestListAdapter extends BaseAdapter{

    private static final String TAG = ClassTestListAdapter.class.getName();
    private final Transformation transformation;
    private Context context;
    private ArrayList<ClassTest> classTests;
    private boolean mSearching = false;
    private boolean mAnimateSearch = false;
    private ArrayList<Integer> mValidSearchIndices =new ArrayList<Integer>();
    private ImageLoader imageLoader = AppController.getInstance().getUniversalImageLoader();

    public ClassTestListAdapter(Context context, ArrayList<ClassTest> classTests) {
        this.context = context;
        this.classTests = classTests;

        transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(0)
                .oval(false)
                .build();
        mSearching = false;
    }

    @Override
    public int getCount() {
        if(mSearching){
            // Log.d("Event List Adapter","Search count"+mValidSearchIndices.size());
            if(!mAnimateSearch){
                mAnimateSearch = true;
            }
            return mValidSearchIndices.size();

        }else{
            // Log.d(TAG,"Normal count size");
            return classTests.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if(mSearching){
            return classTests.get(mValidSearchIndices.get(position));
        }else {
            return classTests.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.class_test_list_item, parent, false);

            holder = new ViewHolder();
            holder.txtClassTestTitle = (TextView) convertView.findViewById(R.id.txtClassTestTitle);
            holder.txtClassTestSubject = (TextView) convertView.findViewById(R.id.txtClassTestSubject);
            holder.txtClassTestDate = (TextView) convertView.findViewById(R.id.txtClassTestDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(mSearching){
            // Log.d("Event List Adapter","actual position"+ position);
            position = mValidSearchIndices.get(position);
            //Log.d("Event List Adapter", "position is"+ position);

        }else{
            Log.d("Event List Adapter","getview pos called"+ position);
        }

        ClassTest classTest = classTests.get(position);

        holder.txtClassTestTitle.setText(classTests.get(position).getHwTitle());
        holder.txtClassTestSubject.setText(classTests.get(position).getHwSubjectName());
        holder.txtClassTestDate.setText(classTests.get(position).getHwTestDate());

        return convertView;
    }

    public void startSearch(String eventName){
        mSearching = true;
        mAnimateSearch = false;
        Log.d("EventListAdapter","serach for event"+eventName);
        mValidSearchIndices.clear();
        for(int i =0; i< classTests.size(); i++){
            String homeWorkTitle = classTests.get(i).getHwTitle();
            if((homeWorkTitle != null) && !(homeWorkTitle.isEmpty())){
                if( homeWorkTitle.toLowerCase().contains(eventName.toLowerCase())){
                    mValidSearchIndices.add(i);
                }

            }

        }
        Log.d("Event List Adapter","notify"+ mValidSearchIndices.size());
        //notifyDataSetChanged();
    }

    public void exitSearch(){
        mSearching = false;
        mValidSearchIndices.clear();
        mAnimateSearch = false;
        // notifyDataSetChanged();
    }

    public void clearSearchFlag(){
        mSearching = false;
    }

    public class ViewHolder {
        public TextView txtClassTestTitle, txtClassTestSubject, txtClassTestDate;
    }

    public boolean ismSearching() {
        return mSearching;
    }

    public int getActualEventPos(int selectedSearchpos){
        if(selectedSearchpos < mValidSearchIndices.size()) {
            return mValidSearchIndices.get(selectedSearchpos);
        }else{
            return 0;
        }
    }
}
