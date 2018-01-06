package com.crakama.hangmandroidclient;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by kate on 06/01/2018.
 */

public class WinFragment extends AppCompatDialogFragment {
    private OnWinDialogListener mListener;
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_instructions,null);
        String reply = getArguments().getString("win");


        builder.setView(view).setTitle("A WIN !!!").setMessage(reply).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onWinOKButtonPressed(view, "yes");
            }
        });
        return builder.create();
    }

    public void onWinOKButtonPressed(View v, String s) {
        if (mListener != null) {
            mListener.btnOKClicked(s);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnWinDialogListener) {
            mListener = (OnWinDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnWinDialogListener {
        // TODO: Update argument type and name
        void btnOKClicked(String text);
    }

}