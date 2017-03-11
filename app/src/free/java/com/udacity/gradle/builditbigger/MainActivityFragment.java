package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.breunig.jeff.jokesdisplay.DisplayJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointAsyncTask.EndPointCallback {

    public MainActivityFragment() {
    }

    ProgressBar progressBar = null;
    public boolean testFlag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);


        // Set onClickListener for the button
        Button button = (Button) root.findViewById(R.id.joke_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        progressBar = (ProgressBar) root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);


        return root;
    }

    public void getJoke(){

        new EndpointAsyncTask().setCallbackListener(this).execute();
    }

    @Override
    public void onComplete(String result) {
        if (result != null) {
            launchDisplayJokeActivity(result);
        } else {
            Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.GONE);
    }

    public void launchDisplayJokeActivity(String joke) {
        if (!testFlag) {
            Context context = getActivity();
            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(context.getString(R.string.joke_extra), joke);
            //Toast.makeText(context, loadedJoke, Toast.LENGTH_LONG).show();
            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
    }
}
