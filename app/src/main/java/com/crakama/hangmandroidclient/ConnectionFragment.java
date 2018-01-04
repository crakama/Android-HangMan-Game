package com.crakama.hangmandroidclient;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.TextView;
=======
>>>>>>> de414d6... Update Model View Presenter


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConnectionFragment.OnItemClickedListener} interface
 * to handle interaction events.
 * Use the {@link ConnectionFragment#//newConnectionInstance} factory method to
 * create an instance of this fragment.
 */
<<<<<<< HEAD
public class ConnectionFragment extends Fragment{


    private OnItemClickedListener mListener;
    private EditText connection_ip;
    static TextView connection_info;
    static Button connection_btn;
    private static Boolean viewable = false;
=======
public class ConnectionFragment extends Fragment {


    private OnItemClickedListener mListener;
    EditText connection_ip;
>>>>>>> de414d6... Update Model View Presenter

    public ConnectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
<<<<<<< HEAD
=======
     *
     * @paramparam1 Parameter 1.
     * @paramparam2 Parameter 2.
     * @return A new instance of fragment ConnectionFragment.
>>>>>>> de414d6... Update Model View Presenter
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
=======
     /*   if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
>>>>>>> de414d6... Update Model View Presenter
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        this.connection_ip = (EditText) view.findViewById(R.id.connection_ip);
<<<<<<< HEAD
        this.connection_info = view.findViewById(R.id.connection_info);

        connection_btn =
                (Button) view.findViewById(R.id.connection_button);
        connection_btn.setOnClickListener(new View.OnClickListener() {
=======
        final Button button =
                (Button) view.findViewById(R.id.connection_button);
        button.setOnClickListener(new View.OnClickListener() {
>>>>>>> de414d6... Update Model View Presenter
            public void onClick(View v) {
                onButtonPressed(v);
            }
        });
<<<<<<< HEAD

        viewable = true;
=======
>>>>>>> de414d6... Update Model View Presenter
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View v) {
        if (mListener != null) {
            mListener.connectionBtnClicked(this.connection_ip.getText().toString());
        }
    }

<<<<<<< HEAD
    public static void setConnectionInfo(final String text) {   //To Do..Transfer to fragment
        if(viewable == true) {
            ConnectionFragment.connection_info.setText(text);
        }
    }

    public static void setButtonState(Boolean state){
        connection_btn.setEnabled(state);
    }
=======
>>>>>>> de414d6... Update Model View Presenter

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickedListener) {
            mListener = (OnItemClickedListener) context;
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
    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }
    /**
     * Callback Interface
<<<<<<< HEAD
     * This interface must be implemented by activities that contain this fragment to allow an
     * interaction in this fragment to be communicated to the activity and potentially other
     * fragments contained in that activity.
     */

=======
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     */
>>>>>>> de414d6... Update Model View Presenter
    public interface OnItemClickedListener {
        // TODO: Update argument type and name
        void connectionBtnClicked(String text);
    }
}



/*https://gist.github.com/butelo/8729891*/
