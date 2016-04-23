package pb.tuyademo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private boolean brush = false;
    private boolean tuya = false;

    private int whichColor = 0;
    private int whichStrokeWidth = 0;

    private List<TuyaModel> list = new ArrayList<>();

    private InfiniteViewPager mViewPager;
    private TuyaPagerAdapter pagerAdapter;

    @Bind(R.id.selection_menu)
    LinearLayout mSelectionMenuView;
    @Bind(R.id.btn_menu)
    ImageView btnMenu;
    @Bind(R.id.brush)
    ImageView btnBrush;
    @Bind(R.id.show)
    ImageView btnShow;
    @Bind(R.id.color)
    ImageView btnColor;
    @Bind(R.id.delete)
    ImageView btnDelete;
    @Bind(R.id.back)
    ImageView btnBack;
    @Bind(R.id.next)
    ImageView btnNext;

    @Bind(R.id.pdfView)
    PDFView pdfView;

    @Bind(R.id.tuya)
    TuyaView tuyaView;

    @OnClick(R.id.btn_menu)
    void onMenu() {
        if (tuya) {
            btnMenu.setImageResource(R.drawable.paint);
            tuya = false;
//            pagerAdapter.setStart(true);
//            mViewPager.setNoScroll(false);
            tuyaView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            btnDelete.setClickable(false);
            btnNext.setClickable(false);
            btnBack.setClickable(false);
            btnBrush.setClickable(false);
            btnColor.setClickable(false);
        } else {
            btnMenu.setImageResource(R.drawable.paint_in);
            tuya = true;
//            pagerAdapter.setStart(false);
//            mViewPager.setNoScroll(true);
           tuyaView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            btnDelete.setClickable(true);
            btnNext.setClickable(true);
            btnBack.setClickable(true);
            btnBrush.setClickable(true);
            btnColor.setClickable(true);
        }
    }

    @OnClick(R.id.hide)
    void onHide() {
        MenuShow();
        btnShow.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.show)
    void onShow() {
        MenuShow();
        btnShow.setVisibility(View.GONE);
    }

    @OnClick(R.id.back)
    void onBack() {
        tuyaView.undo();
//        pagerAdapter.undo();
    }

    @OnClick(R.id.next)
    void onNext() {
        tuyaView.redo();
//        pagerAdapter.redo();
    }

    @OnClick(R.id.brush)
    void onBrush() {
        if (brush) {
            btnBrush.setImageResource(R.mipmap.ic_brush_pressed);
            brush = false;
        } else {
            btnBrush.setImageResource(R.mipmap.ic_brush);
            brush = true;
        }

        Dialog mDialog = new AlertDialog.Builder(this)
                .setTitle("笔刷设置")
                .setSingleChoiceItems(new String[]{"细", "中", "粗"}, whichStrokeWidth, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        int i = mViewPager.getCurrentItem();

                        switch (which) {
                            case 0: {
                                whichStrokeWidth = 0;
                                PaintManager.saveStroke(MainActivity.this, 5.0f);
                                tuyaView.changeStrokeWidth(5.0f);
//                                list.get(i).setWidth(5.0f);
                                break;
                            }
                            case 1: {
                                whichStrokeWidth = 1;
                                PaintManager.saveStroke(MainActivity.this, 15.0f);
                                tuyaView.changeStrokeWidth(15.0f);
//                                list.get(i).setWidth(15.0f);
                                break;
                            }
                            case 2: {
                                whichStrokeWidth = 2;
                                PaintManager.saveStroke(MainActivity.this, 30.0f);
                                tuyaView.changeStrokeWidth(30.0f);
//                                list.get(i).setWidth(30.0f);
                                break;
                            }
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        btnBrush.setImageResource(R.mipmap.ic_brush);
                        brush = true;
//                        list.get(mViewPager.getCurrentItem()).setChange(true);
//                        pagerAdapter.notifyDataSetChanged();
                    }
                })
                .create();
        mDialog.show();
    }

    @OnClick(R.id.color)
    void onColor() {
        Dialog mDialog = new AlertDialog.Builder(this)
                .setTitle("颜色设置")
                .setSingleChoiceItems(new String[]{"红色", "绿色", "黑色"}, whichColor, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

//                        int i = mViewPager.getCurrentItem();

                        switch (which) {
                            case 0: {
                                PaintManager.saveColor(MainActivity.this, Color.RED);
                                tuyaView.changeColor(Color.RED);
                                whichColor = 0;
                                btnColor.setColorFilter(Color.RED);
//                                list.get(i).setColor(Color.RED);
                                break;
                            }
                            case 1: {
                                PaintManager.saveColor(MainActivity.this, Color.GREEN);
                                tuyaView.changeColor(Color.GREEN);
                                whichColor = 1;
                                btnColor.setColorFilter(Color.GREEN);
//                                list.get(i).setColor(Color.GREEN);
                                break;
                            }
                            case 2: {
                                PaintManager.saveColor(MainActivity.this, Color.BLUE);
                                tuyaView.changeColor(Color.BLACK);
                                whichColor = 2;
                                btnColor.setColorFilter(Color.BLACK);
//                                list.get(i).setColor(Color.BLACK);
                                break;
                            }
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
//                        list.get(mViewPager.getCurrentItem()).setChange(true);
//                        pagerAdapter.notifyDataSetChanged();
                    }
                })
                .create();
        mDialog.show();
    }

    @OnClick(R.id.delete)
    void onDelete() {
        tuyaView.removeAllPaint();
//        pagerAdapter.clean();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaintManager.savePaint(this, Color.RED, 5.0f);

        init();

//        initData();

//        initViewPager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PaintManager.clean(this);
    }

    private void initData() {
        list.add(new TuyaModel());
        list.add(new TuyaModel());
        list.add(new TuyaModel());
        list.add(new TuyaModel());
        list.add(new TuyaModel());
    }

    private void initViewPager() {
//        mViewPager = (InfiniteViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new TuyaPagerAdapter(this);
        pagerAdapter.setData(list);
        pagerAdapter.setStart(true);
        mViewPager.setAdapter(pagerAdapter);
    }

    private void init() {
        ButterKnife.bind(this);
        btnMenu.setImageResource(R.drawable.paint);
        tuyaView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        btnDelete.setClickable(false);
        btnNext.setClickable(false);
        btnBack.setClickable(false);
        btnBrush.setClickable(false);
        btnColor.setClickable(false);
        btnColor.setColorFilter(Color.RED);

        String pdfName = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS) + "/" + "bp_team148.pdf";
        File file = new File(pdfName);
        pdfView.fromFile(file)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                .load();
    }


    private void MenuShow() {
        if (View.GONE == mSelectionMenuView.getVisibility()) {
            mSelectionMenuView.setVisibility(View.VISIBLE);
            mSelectionMenuView.startAnimation(
                    AnimationUtils.loadAnimation(this, R.anim.footer_appear));
        } else {
            mSelectionMenuView.setVisibility(View.GONE);
            mSelectionMenuView.startAnimation(
                    AnimationUtils.loadAnimation(this, R.anim.footer_disappear));
        }
    }
}
