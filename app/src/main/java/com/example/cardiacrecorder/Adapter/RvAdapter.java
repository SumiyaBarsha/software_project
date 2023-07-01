package com.example.cardiacrecorder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacrecorder.EachData;
import com.example.cardiacrecorder.R;

public class RvAdapter extends ListAdapter<EachData,RvAdapter.ViewHolder> {

    private final Context context;
    private final RequestListener listener;

    public RvAdapter(Context context, RequestListener listener) {
        super(diffCallback);
        this.context = context;
        this.listener = listener;
    }

    private final static DiffUtil.ItemCallback<EachData> diffCallback = new DiffUtil.ItemCallback<EachData>() {
        @Override
        public boolean areItemsTheSame(@NonNull EachData oldItem, @NonNull EachData newItem) {
            return oldItem.isIdSame(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull EachData oldItem, @NonNull EachData newItem) {
            return oldItem.isFullySame(newItem);
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EachData curItem = getItem(position);

        holder.tvDate.setText(curItem.getDate());
        holder.tvTime.setText(curItem.getTime());
        holder.tvSysPressure.setText(context.getString(R.string.sys_ph,curItem.getSysPressure()));
        holder.tvDysPressure.setText(context.getString(R.string.sys_ph,curItem.getDysPressure()));
        holder.tvHeartRate.setText(context.getString(R.string.heart_rate_ph,curItem.getHeartRate()));
        holder.tvComment.setText(curItem.getComment());
        holder.tvStatus.setText(curItem.getStatus());

        holder.tvDelete.setOnClickListener(view-> listener.onDeleteRequest(getItem(holder.getAdapterPosition())));
        holder.tvEdit.setOnClickListener(view-> listener.onEditRequest(getItem(holder.getAdapterPosition())));
        holder.itemView.setOnClickListener(view -> listener.onShowRequest(getItem(holder.getAdapterPosition())));

    }

    public interface RequestListener{
        void onShowRequest(EachData data);
        void onEditRequest(EachData data);
        void onDeleteRequest(EachData data);
    }

    /**
     * ViewHolder Class
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvDate, tvTime, tvSysPressure, tvDysPressure, tvHeartRate, tvComment, tvStatus;
        private final ImageButton tvEdit, tvDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvSysPressure = itemView.findViewById(R.id.tvSysPressure);
            tvDysPressure = itemView.findViewById(R.id.tvDysPressure);
            tvHeartRate = itemView.findViewById(R.id.tvHeartRate);
            tvComment = itemView.findViewById(R.id.tvComment);
            tvStatus = itemView.findViewById(R.id.tvStatus);

            tvEdit = itemView.findViewById(R.id.tvEdit);
            tvDelete = itemView.findViewById(R.id.tvDelete);

        }
    }

}
