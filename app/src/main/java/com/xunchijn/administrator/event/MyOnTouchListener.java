package com.xunchijn.administrator.event;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Administrator on 2016/1/14.
 */
public class MyOnTouchListener implements View.OnTouchListener  {
    private Object obj=null;
    public MyOnTouchListener(Object o){
            obj=o;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        String className = obj.getClass().getName();
        Log.e("obj",obj.toString());

        switch (className.substring(className.lastIndexOf(".")))
        {
            case ".ImageButton":
                if(event.getAction()== MotionEvent.ACTION_DOWN)
                {
                    ((ImageButton)obj).getDrawable().setAlpha(0);
                    ((ImageButton)obj).invalidate();
                }
                else
                {
                    ((ImageButton)obj).getDrawable().setAlpha(255);
                    ((ImageButton)obj).invalidate();
                }
                break;
            case ".AppCompatImageButton":
                if(event.getAction()== MotionEvent.ACTION_DOWN)
                {
                    ((ImageButton)obj).getDrawable().setAlpha(0);
                    ((ImageButton)obj).invalidate();
                }
                else
                {
                    ((ImageButton)obj).getDrawable().setAlpha(255);
                    ((ImageButton)obj).invalidate();
                }
                break;
            case ".Button":
                if(event.getAction()== MotionEvent.ACTION_DOWN)
                {
                    ((Button)obj).setAlpha(0);
                    ((Button)obj).invalidate();
                }
                else
                {
                    ((Button)obj).setAlpha(1);
                    ((Button)obj).invalidate();
                }
                break;
            case ".AppCompatButton":
                if(event.getAction()== MotionEvent.ACTION_DOWN)
                {
                    ((Button)obj).setAlpha(0);
                    ((Button)obj).invalidate();
                }
                else
                {
                    ((Button)obj).setAlpha(1);
                    ((Button)obj).invalidate();
                }
                break;
        }

        return false;
    }
}
