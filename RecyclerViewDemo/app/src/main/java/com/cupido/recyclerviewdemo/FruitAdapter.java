package com.cupido.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author: qcq
 * @data: 2017/2/26.
 * @content: RecyclerView 的适配器，继承自RecyclerAdapter，指定泛型为FruitAdapter.ViewHolder。ViewHolder是自定义的内部类
 */


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> list_Fruit ;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_fruit ;
        TextView tv_fruit ;

        /**
         * ViewHolder构造函数
         *
         * @param itemView : RecyclerView 子项的最外层布局
         */
        public ViewHolder(View itemView) {
            super(itemView);
            iv_fruit = (ImageView) itemView.findViewById(R.id.iv_fruitImage);
            tv_fruit = (TextView) itemView.findViewById(R.id.tv_fruitName);
        }
    }

    public FruitAdapter(List<Fruit>fruitList)
    {
        list_Fruit = fruitList ;
    }


    /**
     * 用于创建ViewHolder实例，并将加载出来的布局传入到构造函数中
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fruit,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }


    /**
     * 用于对RecyclerView子项的数据进行赋值，在子项滚动到屏幕内的时候执行
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = list_Fruit.get(position);
        holder.iv_fruit.setImageResource(fruit.getImageId());
        holder.tv_fruit.setText(fruit.getName());
    }

    /**
     * 返回一共有多少子项
     * @return
     */
    @Override
    public int getItemCount() {
        return list_Fruit.size();
    }
}
