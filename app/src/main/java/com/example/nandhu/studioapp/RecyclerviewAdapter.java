package com.example.nandhu.studioapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

import java.util.List;

/**
 * Created by Nandhu on 14-03-2018.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    List<StudioList> studioLists;
    private Context context;
    private LayoutInflater inflater;
    public RelativeLayout linear;
    public String imageopen;
    RecyclerView recyclerView;

    public RecyclerviewAdapter(Context context, List<StudioList> bus, RecyclerView recyclerView) {
        this.context = context;
        this.studioLists = bus;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recyclerView=recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.single_layout, parent, false);
        return new MyViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final StudioList list = studioLists.get(position);
        holder.caption.setText(list.getCaption());
        Glide.with(context).load(list.getImage()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressBar progress=new ProgressBar(context);
                progress.setVisibility(View.VISIBLE);
                final Dialog dialog;
                // custom dialog
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.image_layout);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                // set the custom dialog components - text, image and button
                final ZoomageView profileopen=(ZoomageView) dialog.findViewById(R.id.myZoomageView);
                Glide.with(context).load(list.getImage()).into(profileopen);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                progress.setVisibility(View.INVISIBLE);
            }
        });
      /*  holder.title.setText("" + list.getTitle());
            holder.desc.setText("" + list.getAuthor());


        holder.authorDialog builder = new Dialog(this);
    builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
    builder.getWindow().setBackgroundDrawable(
        new ColorDrawable(android.graphics.Color.TRANSPARENT));
    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            //nothing;
        }
    });

    ImageView imageView = new ImageView(this);
    imageView.setImageURI(imageUri);
    builder.addContentView(imageView, new RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT));
    builder.show();.setText("" + list.getTime());
        imageopen = list.getImage();*/
            //Picasso.with(context).load(imageopen).into(holder.profileMain);
            //holder.profileMain.setImageURI(Uri.parse(imageopen));


    }

    @Override
    public int getItemCount() {
        return studioLists.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView caption;
        private ImageView image;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            caption = (TextView) itemView.findViewById(R.id.caption);
            image = (ImageView) itemView.findViewById(R.id.image);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            //itemView.setOnClickListener(this);
            //cardView.setOnClickListener(this);
        }


    }
}

