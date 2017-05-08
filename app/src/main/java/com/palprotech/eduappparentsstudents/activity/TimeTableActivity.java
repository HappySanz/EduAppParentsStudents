package com.palprotech.eduappparentsstudents.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.palprotech.eduappparentsstudents.R;

/**
 * Created by Narendar on 18/04/17.
 */

public class TimeTableActivity extends AppCompatActivity {

    LinearLayout layout_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        AllDynamic();
    }

    public void AllDynamic() {
        layout_all = (LinearLayout) findViewById(R.id.layout_timetable);
        TableLayout layout = new TableLayout(this);
        //str_view = "layout_all";
        layout.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        layout.setPadding(0, 0, 0, 0);

        TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        cellLp.setMargins(6, 0, 0, 0);
        for (int f = 0; f <= 5; f++) {

            TableRow tr = new TableRow(this);

            tr.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            tr.setBackgroundColor(Color.BLACK);
            tr.setPadding(0, 0, 0, 2);

            TableRow.LayoutParams llp = new
                    TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            llp.setMargins(0, 0, 2, 0);//2px right-margin

            //New Cell

            for (int c1 = 0; c1 <= 7; c1++) {

                LinearLayout cell = new LinearLayout(this);
                cell.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                TextView b = new TextView(this);

                String key = Integer.toString(f) + Integer.toString(c1);

//                String col_id = (String) JsonFiles.color_matrix.get(key);

                final String name = "hi";//(String) JsonFiles.color_id.get(col_id).get("name");
                final String code = "hi";//(String) JsonFiles.color_id.get(col_id).get("code");
                final String red = "hi";//(String) JsonFiles.color_id.get(col_id).get("red");
                final String green = "hi";//(String) JsonFiles.color_id.get(col_id).get("green");
                final String blue = "hi";//(String) JsonFiles.color_id.get(col_id).get("blue");
                final String availability = "hi";//(String) JsonFiles.color_id.get(col_id).get("availability");
//                int int_red = Integer.parseInt(red);
//                int int_green = Integer.parseInt(green);
//                int int_blue = Integer.parseInt(blue);

                cell.setBackgroundColor(Color.rgb(255, 144, 134));

                b.setText(name);
                b.setTextSize(10.0f);

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
//                        Intent col_info = new Intent(DynamicStaticGridRange.this, ColourInformation.class);
//                        col_info.putExtra("Key_name", name);
//                        col_info.putExtra("Key_code", code);
//                        col_info.putExtra("Key_red", red);
//                        col_info.putExtra("Key_green", green);
//                        col_info.putExtra("Key_blue", blue);
//                        col_info.putExtra("Key_availability", availability);
//                        DuluxColourActivity.product_sel = "product";
//                        DuluxColourActivity.colour_sel = "selectedcolour";
//                        col_info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        col_info.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(col_info);
                    }
                });
                b.setPressed(true);

                b.setHeight(100);
                b.setWidth(100);
                b.setPadding(0, 0, 4, 0);
                cell.addView(b);
                cell.setLayoutParams(llp);//2px border on the right for the cell

                tr.addView(cell, cellLp);
            } // for
            layout.addView(tr, rowLp);
        }
        // for

        layout_all.addView(layout);

    }
}
