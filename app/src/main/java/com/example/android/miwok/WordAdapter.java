package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    // resource id for background color for word list
    private int mColorResourceId;

    // create constructor (matches class name)
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // set miwok textview
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokWordsTextView);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // set default textview
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.englishWordsTextView);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // set image view. find imageview from list_item.xml
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);

        // check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // if image available, display image from resource id
            image.setImageResource(currentWord.getImageResourceId());
            // make view visible
            image.setVisibility(View.VISIBLE);
        } else {
            // or else make view completely gone
            image.setVisibility(View.GONE);
        }

        // set theme color for list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        // play icon id
        //View playIcon = listItemView.findViewById(R.id.play_arrow);

        // find the color that the resource id maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // set background color of the text container view
        textContainer.setBackgroundColor(color);

        // set background color of play icon
        //playIcon.setBackgroundColor(color);

        return listItemView;
    }
}