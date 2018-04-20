package ir.geek.parvaneh;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;

import ir.geek.parvaneh.dataClasses.SportPlanItem;

public class DragListFragment extends Fragment {


    private ArrayList<Integer> ids;

    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;

    public static DragListFragment newInstance() {
        return new DragListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drag_list_layout, container, false);
        mDragListView = (DragListView) view.findViewById(R.id.drag_list_view);
        mDragListView.getRecyclerView().setVerticalScrollBarEnabled(true);
        mDragListView.setDragListListener(new DragListView.DragListListenerAdapter() {
            @Override
            public void onItemDragStarted(int position) {
                Toast.makeText(mDragListView.getContext(), "Start - position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                if (fromPosition != toPosition) {
                    Toast.makeText(mDragListView.getContext(), "End - position: " + toPosition, Toast.LENGTH_SHORT).show();
                    // ToDo : Update SportPanItem SET order = toposition where order = from postition
                    // Bug : If it go to offline mode in this page , it doesn't work correctly

                }
            }
        });

        mItemArray = new ArrayList<>();
        if(getArguments()!=null){
            if(getArguments().containsKey("ids")){
                ids = getArguments().getIntegerArrayList("ids");
                for (int id:ids) {
                    mItemArray.add(new Pair<>((long) id, getArguments().getString("type") ));
                }
            }
        }


        setupListRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setupListRecyclerView() {
        mDragListView.setLayoutManager(new LinearLayoutManager(getContext()));
        spItemAdapter listAdapter = new spItemAdapter(mItemArray, R.layout.sport_plan_item, R.id.drag_handle, false);
        mDragListView.setAdapter(listAdapter, true);
        mDragListView.setCanDragHorizontally(false);
    }
}

