package ir.geek.parvaneh;

import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItemAdapter;

import java.util.ArrayList;
import java.util.List;

import ir.geek.parvaneh.dataClasses.SportPlanItem;

class spItemAdapter extends DragItemAdapter<Pair<Long, String>, spItemAdapter.ViewHolder> {

    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;

    spItemAdapter(ArrayList<Pair<Long, String>> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;
        mDragOnLongPress = dragOnLongPress;
        setItemList(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        String type = mItemList.get(position).second;
        // ToDo : change codes to get id then show informations
        if (type == "SportPlanItem") {
            SportPlanItem spitem = new SportPlanItem((mItemList.get(position).first).intValue());

            holder.itemView.setTag(mItemList.get(position).first);
            if(spitem.isRest()){
                holder.sportBody.setVisibility(View.GONE);
                if (spitem.getDuration()==5){
                    holder.setRestDuraition(holder.restTimes.get(0));
                } else if (spitem.getDuration()==10){
                    holder.setRestDuraition(holder.restTimes.get(1));
                } else {
                    holder.setRestDuraition(holder.restTimes.get(2));
                }
                holder.restBody.setVisibility(View.VISIBLE);
            }else {
                holder.restBody.setVisibility(View.GONE);

                holder.title.setText(spitem.getTitle());
                holder.title.setFocusable(false);
                holder.title.setBackgroundDrawable(null);

                holder.duration.setText(spitem.getDuration() + "");
                holder.duration.setFocusable(false);
                holder.duration.setBackgroundDrawable(null);
                holder.duration.setPadding(0, 0, 0, 0);

                holder.sportBody.setVisibility(View.VISIBLE);
            }
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for ( int i=0; i<holder.restTimesView.getChildCount();i++){
                    holder.restTimesView.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (view.getId() == holder.restTimesView.getChildAt(2).getId()){
                                // ToDo : (Vahid) Open Dialog
                                holder.setRestDuraition((Button) view);
                            }else{
                                holder.setRestDuraition((Button) view);
                            }
                        }
                    });
                }
                holder.editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!holder.title.isFocusable()) {
                            holder.title.setFocusable(true);
                            holder.title.setFocusableInTouchMode(true);
                            holder.title.requestFocus();
                            holder.title.setClickable(true);
                            holder.title.setBackground(view.getContext().getDrawable(R.drawable.textinputbg));

                            holder.duration.setFocusable(true);
                            holder.duration.setFocusableInTouchMode(true);
                            holder.duration.requestFocus();
                            holder.duration.setBackground(view.getContext().getDrawable(R.drawable.textinputbg));
                            holder.duration.setPadding(10, 2, 10, 2);

                            holder.editBtn.setText(view.getContext().getString(R.string.done));
                        } else {
                            // ToDo : Update spItem with new values
                            holder.title.setFocusable(false);
                            holder.title.setBackground(null);

                            holder.duration.setFocusable(false);
                            holder.duration.setBackground(null);
                            holder.duration.setPadding(0, 0, 0, 0);

                            holder.editBtn.setText(view.getContext().getString(R.string.edit));
                        }
                    }
                });
            }
        };
        new Thread(runnable).run();
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemList.remove(position);
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public long getUniqueItemId(int position) {
        return mItemList.get(position).first;
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {
        RelativeLayout sportBody;
        EditText title;
        Button removeBtn;
        Button editBtn;
        EditText duration;

        RelativeLayout restBody;
        RelativeLayout restTimesView;
        List<Button> restTimes;

        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            sportBody = (RelativeLayout) itemView.findViewById(R.id.sport_body);
            title = (EditText) itemView.findViewById(R.id.sport_title);
            removeBtn = (Button) itemView.findViewById(R.id.remove);
            editBtn = (Button) itemView.findViewById(R.id.edit);
            duration = (EditText) itemView.findViewById(R.id.duration);

            restBody = (RelativeLayout) itemView.findViewById(R.id.rest_body);
            restTimesView = (RelativeLayout) itemView.findViewById(R.id.rest_durs);
            restTimes= new ArrayList<Button>();
            restTimes.add((Button) itemView.findViewById(R.id.rest_dur1));
            restTimes.add((Button) itemView.findViewById(R.id.rest_dur2));
            restTimes.add((Button) itemView.findViewById(R.id.rest_dur3));
        }

        @Override
        public void onItemClicked(View view) {
            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        public void setRestDuraition(Button b){
            for (Button time : restTimes){
                time.setBackground(itemView.getResources().getDrawable(R.drawable.textinputbg));
                time.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.blue_grey));
            }
            b.setBackground(itemView.getResources().getDrawable(R.drawable.selecectedtextinputbg));
            b.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.colorAccent));
        }

    }
}