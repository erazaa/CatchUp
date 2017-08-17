package pe.edu.utp.catchup.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import org.w3c.dom.Text;

import java.util.List;

import pe.edu.utp.catchup.CatchUpApp;
import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.activities.ArticleActivity;
import pe.edu.utp.catchup.models.Article;

/**
 * Created by GrupoUTP on 15/07/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<Article> articles;

    public ArticlesAdapter(List<Article> articles) {
        this.articles = articles;
    }

    public ArticlesAdapter() {
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.card_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, final int position) {
        holder.titleTextView.setText(articles.get(position).getTitle());
        holder.pictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.pictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.pictureANImageView.setImageUrl(articles.get(position).getUrlToImage());
        holder.articleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp.getInstance().setCurrentArticle(articles.get(position));
                v.getContext().startActivity(new Intent(v.getContext(), ArticleActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView pictureANImageView;
        TextView titleTextView;
        CardView articleCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            pictureANImageView = (ANImageView) itemView.findViewById(R.id.pictureANImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            articleCardView = (CardView) itemView.findViewById(R.id.articleCardView);
        }
    }
}
