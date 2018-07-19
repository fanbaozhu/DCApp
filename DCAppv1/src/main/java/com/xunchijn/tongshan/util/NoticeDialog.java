package com.xunchijn.tongshan.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunchijn.tongshan.R;

public class NoticeDialog extends DialogFragment {
    private String mTitle;
    private String mContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        View view = inflater.inflate(com.xunchijn.tongshan.R.layout.dialog_fragment_notice, (ViewGroup) window.findViewById(android.R.id.content), false);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(view);
        return view;
    }

    private void initView(View view) {
        setCancelable(false);
        ImageView viewCancel = view.findViewById(R.id.image_dialog_cancel);
        viewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        viewCancel.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));

        TextView viewTitle = view.findViewById(R.id.text_title);
        setText(mTitle, viewTitle);

        TextView viewContent = view.findViewById(R.id.text_content);
        setText(mContent, viewContent);
    }

    private void setText(String text, TextView view) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        view.setText(text);
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
