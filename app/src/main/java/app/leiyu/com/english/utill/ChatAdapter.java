package app.leiyu.com.english.utill;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.*;
import app.leiyu.com.english.R;
import java.util.List;

import cn.jpush.im.android.api.model.Message;

public class ChatAdapter extends BaseAdapter{
    private static String TAG = "ChatAdapter";
    /**
     * 右边显示的布局
     */
    public static final int ITEM_RIGHT = 0;
    /**
     * 中间显示时间
     */
    public static final int ITEM_TIME = 1;
    /**
     * 左边显示的布局
     */
    public static final int ITEM_LEFT = 2;
    private static int ITEM_TOTAL_COUNT = 3;
    private Context mContext;
    private List<Chat> chatList;

    public ChatAdapter(Context mContext, List<Chat> chatList) {
        this.mContext = mContext;
        this.chatList = chatList;
    }

    @Override
    public int getCount() {

        return chatList.size();
    }

    @Override
    public Object getItem(int i) {
        return chatList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 返回多少种Item布局
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return ITEM_TOTAL_COUNT;
    }

    /**
     * 根据position返回相应的Item
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        return chatList.get(position).getType();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        Chat chat=chatList.get(i);

        ViewHolderLeft viewHolderLeft = null;
        ViewHolderRight viewHolderRight = null;
        ViewHolderTime viewHolderTime = null;
        if (view == null) {
            switch (type) {
                case ITEM_LEFT:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_left, null);
                    viewHolderLeft = new ViewHolderLeft();
                    viewHolderLeft.ivLeftImage = (ImageView) view.findViewById(R.id.iv_left_image);
                    viewHolderLeft.tvLeftText = (TextView) view.findViewById(R.id.tv_left_text);
                    viewHolderLeft.ivLeftImage.setImageResource(R.drawable.icon);
                    viewHolderLeft.tvLeftText.setText(chat.getValue());
                    view.setTag(viewHolderLeft);
                    break;
                case ITEM_TIME:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_center, null);
                    viewHolderTime = new ViewHolderTime();
                    viewHolderTime.tvTime = (TextView) view.findViewById(R.id.tv_time);
                    viewHolderTime.tvTime.setText(chat.getValue());
                    view.setTag(viewHolderTime);
                    break;
                case ITEM_RIGHT:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_right, null);
                    viewHolderRight = new ViewHolderRight();
                    viewHolderRight.ivRightImage = (ImageView) view.findViewById(R.id.iv_right_image);
                    viewHolderRight.tvRightText = (TextView) view.findViewById(R.id.tv_right_text);
                    viewHolderRight.ivRightImage.setImageResource(R.drawable.touxiang);
                    viewHolderRight.tvRightText.setText(chat.getValue());
                    view.setTag(viewHolderRight);
                    break;
            }

        } else {
            switch (type) {

                case ITEM_LEFT:
                    viewHolderLeft = (ViewHolderLeft) view.getTag();
                    viewHolderLeft.ivLeftImage.setImageResource(R.drawable.icon);
                    viewHolderLeft.tvLeftText.setText(chat.getValue());
                    break;
                case ITEM_TIME:
                    viewHolderTime = (ViewHolderTime) view.getTag();
                    viewHolderTime.tvTime.setText(chat.getValue());
                    break;
                case ITEM_RIGHT:
                    viewHolderRight = (ViewHolderRight) view.getTag();
                    viewHolderRight.ivRightImage.setImageResource(R.drawable.touxiang);
                    viewHolderRight.tvRightText.setText(chat.getValue());
                    break;
            }

        }


        return view;
    }

    private static class ViewHolderLeft {
        //左边显示头像
        ImageView ivLeftImage;
        //左边显示的文本
        TextView tvLeftText;
    }

    private static class ViewHolderTime {
        //中间显示时间
        TextView tvTime;
    }

    private static class ViewHolderRight {
        //右边显示的头像
        ImageView ivRightImage;
        //右边显示的文本
        TextView tvRightText;
    }
}


