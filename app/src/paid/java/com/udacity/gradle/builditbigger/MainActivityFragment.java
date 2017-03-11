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

import com.breunig.jeff.jokesdisplay.DisplayJokeActivity;
public class MainActivityFragment extends Fragment implements EndpointAsyncTask.EndPointCallback {

    @BindView(R.id.joke_progressbar) ProgressBar mProgressBar;
    @BindView(R.id.joke_button) Button mButton;

    public MainActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        ButterKnife.bind(view);

        mButton.setOnClickListener((View v) -> {
            mProgressBar.setVisibility(View.VISIBLE);
            getJoke();
        });

        hideProgressBar();
        return view;
    }

    public void getJoke(){

        new EndpointAsyncTask().setCallbackListener(this).execute();
    }

    @Override
    public void onComplete(String result) {
        if (result != null) {
            launchDisplayJokeActivity(result);
        } else {
            Toast.makeText(getContext(), getString(R.string.joke_error), Toast.LENGTH_LONG).show();
        }
        hideProgressBar();
    }

    public void launchDisplayJokeActivity(String joke) {
        Context context = getActivity();
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.putExtra(context.getString(R.string.joke_extra), joke);
        context.startActivity(intent);
    }

    private void showProgressBar() { mProgressBar.setVisibility(View.VISIBLE); }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}