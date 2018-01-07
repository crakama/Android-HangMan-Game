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
     * create an instance of this fragment.
     */
public class StartFragment extends Fragment{


    private OnItemClickedListener mListener;
    private EditText connection_ip;
    static TextView connection_info;
    static Button connection_btn;
    private static Boolean viewable = false;

    public StartFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        this.connection_ip = view.findViewById(R.id.connection_ip);
        connection_info = view.findViewById(R.id.connection_info);

        connection_btn =
                view.findViewById(R.id.connection_button);
        connection_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonPressed(v);
            }
        });

        viewable = true;
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View v) {
        if (mListener != null) {
            mListener.connectionBtnClicked(this.connection_ip.getText().toString());
        }
    }

    public static void setConnectionInfo(final String text) {   //To Do..Transfer to fragment
        if(viewable == true) {
            StartFragment.connection_info.setText(text);
        }
    }

    public static void setButtonState(Boolean state){
        connection_btn.setEnabled(state);
    }

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
     */

    public interface OnItemClickedListener {
        // TODO: Update argument type and name
        void connectionBtnClicked(String text);
    }
}



/*https://gist.github.com/butelo/8729891*/
