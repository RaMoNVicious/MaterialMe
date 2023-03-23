package ua.edu.sumdu.materialme;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {
    private final ArrayList<Sport> mSportsData;
    private final Activity mActivity;

    SportsAdapter(Activity activity, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mActivity = activity;
    }

    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder, int position) {
        Sport currentSport = mSportsData.get(position);
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mTitleText;
        private final TextView mInfoText;
        private final ImageView mSportsImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());
            Glide.with(mActivity).load(currentSport.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            Sport currentSport = mSportsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mActivity, DetailActivity.class);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    mActivity,
                    mSportsImage,
                    mActivity.getString(R.string.transition_image)
            );

            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource", currentSport.getImageResource());
            mActivity.startActivity(detailIntent, options.toBundle());
        }
    }
}
