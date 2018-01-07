package com.crakama.hangmandroidclient.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.crakama.hangmandroidclient.R;


public class InstructionsFragment extends AppCompatDialogFragment {
    private OnDialogListener mListener;
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_instructions,null);
        String reply = getArguments().getString("reply");


        builder.setView(view).setTitle("Game Instructions").setMessage(reply).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onOKButtonPressed(view, "yes");
            }
        });
        return builder.create();
    }

    public void onOKButtonPressed(View v, String s) {
        if (mListener != null) {
            mListener.btnOKClicked(s);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDialogListener) {
            mListener = (OnDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnDialogListener {
        // TODO: Update argument type and name
        void btnOKClicked(String text);
    }

}
