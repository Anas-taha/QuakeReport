package com.example.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


public class earthquake_adapter extends ArrayAdapter<earthquake> {


    public earthquake_adapter(@NonNull Context context, @NonNull ArrayList<earthquake> earthquakes) {
        super(context,  0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquakes_info, parent, false);
        }

        earthquake currentEarthQuake = getItem(position);

        TextView degreeView = (TextView) listItemView.findViewById(R.id.degree);
        DecimalFormat formatter = new DecimalFormat("0.00");

        String output = formatter.format(currentEarthQuake.getmDegree());
        degreeView.setText(output);
        String originalPlace = currentEarthQuake.getmCity();
        String separator="of";
        String primaryString="";
        String settoff="";


        if (originalPlace.contains(separator)){
            String[] parts = originalPlace.split(separator);
            primaryString  = parts[1];
            settoff        = parts[0]+" "+separator;
        }
        else {
            settoff = "near of";
            primaryString = originalPlace;
        }
        TextView cityView = (TextView) listItemView.findViewById(R.id.city);
        cityView.setText(primaryString);

        TextView setoff = (TextView) listItemView.findViewById(R.id.seprated_string);
        setoff.setText(settoff);

        Date date = new Date(currentEarthQuake.getMtimeInms());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        String formatedDate = formatDate(date);
        dateView.setText(formatedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formatedTime = formatTime(date);
        timeView.setText(formatedTime);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) degreeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getmDegree());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


}







