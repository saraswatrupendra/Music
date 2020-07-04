package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleapp.R;
import com.example.sampleapp.display;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.listitem;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<listitem> list;

    public MyAdapter(Context context, List list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        listitem item=list.get(position);
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        Picasso.get().load(item.getCoverimg()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,desc;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            title=itemView.findViewById(R.id.tile);
            desc=itemView.findViewById(R.id.desc);
            img=itemView.findViewById(R.id.img);
            //chk=itemView.findViewById(R.id.check);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            listitem data=list.get(pos);

           /* if(chk.isChecked()){
                data.setStatus(true);
            }
            else
                data.setStatus(false);*/
            Intent in=new Intent(context, display.class);
            in.putExtra("title",data.getTitle());
            in.putExtra("desc",data.getDesc());
            in.putExtra("image",data.getCoverimg());
            in.putExtra("url",data.getSong());
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(in);
           // Toast.makeText(context,data.getTitle(),Toast.LENGTH_LONG).show();
        }
    }
}
