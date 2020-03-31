package com.ankitshubham.gocorona.ui.assess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ankitshubham.gocorona.R;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AssessFragment extends Fragment {

    private AssessViewModel assessViewModel;
    private TextView textView;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private RadioGroup radioGroup;
    private ImageButton imageButton1, imageButton2;
    private static int counter = 0;
    private static final int num_questions = 3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        assessViewModel =
                ViewModelProviders.of(this).get(AssessViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assess, container, false);
        final TextView textView = root.findViewById(R.id.text_assess);
//        assessViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        radioButton1 = root.findViewById(R.id.radioButton1);
        radioButton2 = root.findViewById(R.id.radioButton2);
        radioButton3 = root.findViewById(R.id.radioButton3);
        radioButton4 = root.findViewById(R.id.radioButton4);

        radioGroup = root.findViewById(R.id.radioGroup);
        imageButton1 = root.findViewById(R.id.imageButton1);
        imageButton2 = root.findViewById(R.id.imageButton2);

        InputStream inputStream = getResources().openRawResource(R.raw.questions);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        try{
            JSONArray array = new JSONArray(json);
            JSONObject object = array.getJSONObject(counter);
            String q = object.getString("q");
            String o1 = object.getString("o1");
            String o2 = object.getString("o2");
            String o3 = object.getString("o3");
            String o4 = object.getString("o4");
            textView.setText(q);
            radioButton1.setText(o1);
            radioButton2.setText(o2);
            radioButton3.setText(o3);
            radioButton4.setText(o4);
            radioGroup.clearCheck();
            counter++;

        } catch (JSONException e) {
            System.err.println(e.toString());
        }

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                imageButton2.setVisibility(View.VISIBLE);
                radioButton1.setVisibility(View.VISIBLE);
                radioButton2.setVisibility(View.VISIBLE);
                radioButton3.setVisibility(View.VISIBLE);
                radioButton4.setVisibility(View.VISIBLE);
                InputStream inputStream = getResources().openRawResource(R.raw.questions);
                String json = new Scanner(inputStream).useDelimiter("\\A").next();
                try{
                    JSONArray array = new JSONArray(json);
                    JSONObject object = array.getJSONObject(counter);
                    String q = object.getString("q");
                    String o1 = object.getString("o1");
                    String o2 = object.getString("o2");
                    String o3 = object.getString("o3");
                    String o4 = object.getString("o4");
                    textView.setText(q);
                    radioButton1.setText(o1);
                    radioButton2.setText(o2);
                    radioButton3.setText(o3);
                    radioButton4.setText(o4);
                    radioGroup.clearCheck();
                    counter++;

                } catch (JSONException e) {
                    System.err.println(e.toString());
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream inputStream = getResources().openRawResource(R.raw.questions);
                String json = new Scanner(inputStream).useDelimiter("\\A").next();
                if (counter >= num_questions) {
                    radioButton1.setVisibility(View.GONE);
                    radioButton2.setVisibility(View.GONE);
                    radioButton3.setVisibility(View.GONE);
                    radioButton4.setVisibility(View.GONE);
                    textView.setText("The probability of you having Covid-19 is 15%.");
                    imageButton2.setVisibility(View.GONE);
                }
                try{
                    JSONArray array = new JSONArray(json);
                    JSONObject object = array.getJSONObject(counter);
                    String q = object.getString("q");
                    String o1 = object.getString("o1");
                    String o2 = object.getString("o2");
                    String o3 = object.getString("o3");
                    String o4 = object.getString("o4");
                    textView.setText(q);
                    radioButton1.setText(o1);
                    radioButton2.setText(o2);
                    radioButton3.setText(o3);
                    radioButton4.setText(o4);
                    radioGroup.clearCheck();
                    counter++;

                } catch (JSONException e) {
                    System.err.println(e.toString());
                }
            }
        });
        return root;
    }
}
