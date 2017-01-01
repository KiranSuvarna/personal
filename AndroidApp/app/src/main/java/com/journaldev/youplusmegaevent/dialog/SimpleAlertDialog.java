package com.journaldev.youplusmegaevent.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.journaldev.youplusmegaevent.R;

/**
 * Created by lenovo on 9/22/2016.
 */
public class SimpleAlertDialog extends OneButtonDialogFragment {

    public static final String TAG = SimpleAlertDialog.class.getSimpleName();

    private static final String TEXT_RESOURCE_KEY = "TEXT_RESOURCE_KEY";
    private static final String TITLE_RESOURCE_KEY = "TITLE_RESOURCE_KEY";
    private static final String BUTTON_RESOURCE_KEY = "BUTTON_RESOURCE_KEY";
    private static final String TEXT_STRING_KEY =  "TEXT_STRING_KEY";

    public static SimpleAlertDialog newInstance(@StringRes int text, @StringRes int title, @StringRes int button) {
        SimpleAlertDialog locationServicesDialog = new
                SimpleAlertDialog();
        Bundle args = new Bundle();
        args.putInt(TEXT_RESOURCE_KEY, text);
        args.putInt(TITLE_RESOURCE_KEY, title);
        args.putInt(BUTTON_RESOURCE_KEY, button);
        locationServicesDialog.setArguments(args);
        return locationServicesDialog;
    }

    public static SimpleAlertDialog newInstance(String descr, @StringRes int button, ISimpleAlertDialogButtonListener listener) {
        SimpleAlertDialog locationServicesDialog = new
                SimpleAlertDialog();
        Bundle args = new Bundle();
        args.putString(TEXT_STRING_KEY, descr);
        args.putInt(BUTTON_RESOURCE_KEY, button);
        locationServicesDialog.setArguments(args);
        locationServicesDialog.setListener(listener);
        return locationServicesDialog;
    }

    public static SimpleAlertDialog newInstance(String descr) {
        SimpleAlertDialog locationServicesDialog = new
                SimpleAlertDialog();
        Bundle args = new Bundle();
        args.putString(TEXT_STRING_KEY, descr);
        locationServicesDialog.setArguments(args);
        return locationServicesDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog m_dialog = new Dialog(getActivity());
        m_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);





        final View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_simple,
                        (ViewGroup) getActivity().getWindow().getDecorView(), false);
        int textRes = getArguments().getInt(TEXT_RESOURCE_KEY, -1);
        if (textRes != -1) {
            ((TextView) view.findViewById(R.id.message)).setText(textRes);
        }
        String text = getArguments().getString(TEXT_STRING_KEY, null);
        if (text != null) {
            ((TextView) view.findViewById(R.id.message)).setText(text);
        }
        int textRes1 = getArguments().getInt(TITLE_RESOURCE_KEY, -1);
        if (textRes1 != -1) {
            ((TextView) view.findViewById(R.id.title)).setText(textRes1);
        } else {
            view.findViewById(R.id.title).setVisibility(View.GONE);
            view.findViewById(R.id.divider).setVisibility(View.GONE);
        }
        int textRes2 = getArguments().getInt(BUTTON_RESOURCE_KEY, -1);
        if (textRes2 != -1) {
            ((TextView) view.findViewById(R.id.button)).setText(textRes2);
        }

        m_dialog.setContentView(view);
        Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), android.R.color.transparent));
        m_dialog.getWindow().setBackgroundDrawable(drawable);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null) {
                    mListener.onSimpleAlertDialogButtonClicked();
                }
            }
        });
        return m_dialog;
    }

}