package pb.tuyademo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zanlabs.widget.infiniteviewpager.InfinitePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TuyaPagerAdapter extends InfinitePagerAdapter {

    private Context context;
    private ViewHolder holder;
    private List<TuyaModel> list = new ArrayList<>();

    private boolean tuya = false;

    public TuyaPagerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TuyaModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void undo() {
    }

    public void redo() {
    }

    public void clean() {
    }

    public void setStart(boolean tuya) {
        this.tuya = tuya;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.pager_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        TuyaModel tuyaModel = list.get(position);

        if (tuyaModel.isChange()) {
            holder.tuyaView.changeColor(tuyaModel.getColor());
            holder.tuyaView.changeStrokeWidth(tuyaModel.getWidth());
            tuyaModel.setChange(false);
        }

        holder.txtPage.setText(position + "");

        holder.tuyaView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return tuya;
            }
        });

        return convertView;
    }

    public static class ViewHolder {

        TuyaView tuyaView;
        TextView txtPage;

        public ViewHolder(View view) {
            tuyaView = (TuyaView) view.findViewById(R.id.tuya);
            txtPage = (TextView) view.findViewById(R.id.txt_page);
        }
    }
}
