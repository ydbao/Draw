package pb.tuyademo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PB on 2016/4/20.
 */
public class ButtonView extends FrameLayout {

    private int imagenotPressed = 0;
    private int imagePressed = 0;
    private boolean pressed = false;

    public ButtonView(Context context) {
        super(context);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Bind(R.id.image)
    ImageView buttonView;

    @OnClick(R.id.image)
    void onClick() {
        if (pressed) {
            buttonView.setImageResource(imagePressed);
            pressed = false;
        } else {
            buttonView.setImageResource(imagenotPressed);
            pressed = true;
        }
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.button_view, this);
        ButterKnife.bind(this);

        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ButtonView);
            for (int i = 0; i < array.getIndexCount(); i++) {
                int index = array.getIndex(i);
                switch (index) {
                    case R.styleable.ButtonView_bv_not_pressed:
                        imagenotPressed = array.getResourceId(index, 0);
                        break;
                    case R.styleable.ButtonView_bv_pressed:
                        imagePressed = array.getResourceId(index, 0);
                        break;
                }
            }
            array.recycle();
        }
        if (imagenotPressed != 0) {
            buttonView.setImageResource(imagenotPressed);
        }
    }

}
