package pe.edu.utp.catchup.adapters;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.utp.catchup.CatchUpApp;
import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.activities.ArticlesActivity;
import pe.edu.utp.catchup.models.Source;

/**
 * Created by GrupoUTP on 08/07/2017.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    private List<Source> sources;

    public SourcesAdapter() {
    }

    public SourcesAdapter(List<Source> sources) {
        this.sources = sources;
    }


    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_source, parent, false));
    }

    @Override
    public void onBindViewHolder(
            SourcesAdapter.ViewHolder holder, final int position) {
        holder.nameTextView.setText(sources.get(position).getName());
        holder.descriptionTextView.setText(sources.get(position).getDescription());
        holder.logoANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.logoANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.logoANImageView.setImageUrl(sources.get(position).getUrlToSmallLogo());
        holder.sourceConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp.getInstance().setCurrentSource(sources.get(position));
                v.getContext().startActivity(new Intent(v.getContext(), ArticlesActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public List<Source> getSources() {
        return sources;
    }

    public SourcesAdapter setSources(List<Source> sources) {
        this.sources = sources;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView logoANImageView;
        TextView nameTextView;
        TextView descriptionTextView;
        ConstraintLayout sourceConstraintLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            logoANImageView = (ANImageView) itemView.findViewById(R.id.logoANImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            sourceConstraintLayout = (ConstraintLayout) itemView.findViewById(R.id.sourceConstraintLayout);

        }
    }
}
