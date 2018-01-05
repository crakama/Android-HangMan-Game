package com.crakama.hangmandroidclient;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnGameFragListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    static TextView game_word;
    EditText guesses;
    static Button game_button;
    private static Boolean viewable = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnGameFragListener mListener;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Call this from main activity
    public static GameFragment newInstance(String serverReply) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, serverReply);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Fetch reply from above instance, Call a separate function(mParam1) to update edit text
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);


        }
    }
    // TODO: Add edittext
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        this.guesses = (EditText) view.findViewById(R.id.game_guess);
        game_button =
                (Button) view.findViewById(R.id.game_button);
        this.game_word = view.findViewById(R.id.game_word);
        game_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonPressed(v);
            }
        });
        viewable = true;
        return view;
    }
    public static void setGameInfo(final String text) {   //To Do..Transfer to fragment
        if(viewable == true) {
            GameFragment.game_word.setText(text);
        }
    }

    // TODO: pick user guesses and pass to activity--presenter--interactor--server
    public void onButtonPressed(View v) {
        if (mListener != null) {
            mListener.gameBtnClicked(this.guesses.getText().toString());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameFragListener) {
            mListener = (OnGameFragListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGameFragListener {
        // TODO: Update argument type and name
        void gameBtnClicked(String text);

    }
}
