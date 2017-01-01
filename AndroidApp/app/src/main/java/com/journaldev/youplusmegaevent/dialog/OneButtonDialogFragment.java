package com.journaldev.youplusmegaevent.dialog;
import android.app.Activity;
import android.support.v4.app.DialogFragment;

/**
 * Created by lenovo on 9/22/2016.
 */
public class OneButtonDialogFragment extends DialogFragment {


    public void setListener(ISimpleAlertDialogButtonListener listener) {
        this.mListener = listener;
    }

    public interface ISimpleAlertDialogButtonListener {
        void onSimpleAlertDialogButtonClicked();
    }

    ISimpleAlertDialogButtonListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mListener == null && activity instanceof ISimpleAlertDialogButtonListener) {
            mListener = (ISimpleAlertDialogButtonListener) activity;
        }

    }
}