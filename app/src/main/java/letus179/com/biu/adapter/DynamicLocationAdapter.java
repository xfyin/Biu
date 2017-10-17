package letus179.com.biu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;

import java.util.List;

import letus179.com.biu.R;

public class DynamicLocationAdapter extends BaseAdapter {
    private List<PoiInfo> poiInfoList;
    private int checkPosition;

    private Context context;

    public DynamicLocationAdapter(List<PoiInfo> poiInfoList, int checkPosition, Context context) {
        this.poiInfoList = poiInfoList;
        this.checkPosition = checkPosition;
        this.context = context;
    }

    public DynamicLocationAdapter(int checkPosition) {
        this.checkPosition = checkPosition;
    }

    public void setCheckposition(int checkPosition) {
        this.checkPosition = checkPosition;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return poiInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return poiInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.dynamic_location_item, null);

            holder.textView = (TextView) convertView.findViewById(R.id.dynamic_location_item_loc);
            holder.textAddress = (TextView) convertView.findViewById(R.id.dynamic_location_item_detail);
            holder.imageLl = (ImageView) convertView.findViewById(R.id.dynamic_location_no_show_img);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.i("mybaidumap", "name地址是：" + poiInfoList.get(position).name);
        Log.i("mybaidumap", "address地址是：" + poiInfoList.get(position).address);

        holder.textView.setText(poiInfoList.get(position).name);
        holder.textAddress.setText(poiInfoList.get(position).address);
        if (checkPosition == position) {
            holder.imageLl.setVisibility(View.VISIBLE);
        } else {
            holder.imageLl.setVisibility(View.GONE);
        }


        return convertView;
    }

    class ViewHolder {
        TextView textView;
        TextView textAddress;
        ImageView imageLl;
    }

}