package com.example.beresin_uas_10119120.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beresin_uas_10119120.Database.NoteDatabase;
import com.example.beresin_uas_10119120.Model.Notes;
import com.example.beresin_uas_10119120.Model.NotesClickListener;
import com.example.beresin_uas_10119120.NotesEditActivity;
import com.example.beresin_uas_10119120.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener {

    Context context;
    LayoutInflater inflater;
    List<Notes> notes;
    NotesClickListener listener;

    public NotesAdapter(Context context, List<Notes> notes, NotesClickListener listener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_list, parent, false);
        return new ViewHolder(view);
    }

    private int getRandomColor()
    {
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());

        return colorCode.get(random_color);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String content = notes.get(position).getContent();
        String category = notes.get(position).getCategory();
        String date = notes.get(position).getDate();

        holder.textView_title.setText(title);
        holder.textView_notes.setText(content);
        holder.textView_date.setText(date);

        if(notes.get(position).isPinned()) {
            holder.imageView_pin.setImageResource(R.drawable.ic_round_pin_off);
        }else{
            holder.imageView_pin.setImageResource(0);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {

        CardView notes_container;
        TextView textView_title, textView_notes, textView_date;
        ImageView imageView_pin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notes_container = itemView.findViewById(R.id.notes_container);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_notes = itemView.findViewById(R.id.textView_notes);
            textView_date = itemView.findViewById(R.id.textView_date);
            imageView_pin = itemView.findViewById(R.id.imageView_pin);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), NotesEditActivity.class);
            intent.putExtra("ID",notes.get(getAdapterPosition()).getID());
            view.getContext().startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            showPopup(view.findViewById(R.id.notes_container));
            return true;
        }

        private void showPopup(CardView cardView) {
            PopupMenu popupMenu = new PopupMenu(itemView.getContext(), cardView);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            NoteDatabase database = new NoteDatabase(itemView.getContext());
            switch (item.getItemId()){
                case R.id.pin:
                    if (notes.get(getAdapterPosition()).isPinned()){
                        Log.d(notes.get(getAdapterPosition()).getTitle(), "Log Notes ID");
                        database.pin(notes.get(getAdapterPosition()).getID(), false);
                        Toast.makeText(itemView.getContext(), "Unpinned!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        database.pin(notes.get(getAdapterPosition()).getID(), true);
                        Toast.makeText(itemView.getContext(), "Pinned!", Toast.LENGTH_SHORT).show();
                    }
                    notes.clear();
                    notes.addAll(database.getNotes());
                    notifyDataSetChanged();
                    return true;

                case R.id.delete:
                    database.delete(notes.get(getAdapterPosition()).getID());
                    notes.remove(notes.get(getAdapterPosition()));
                    notifyDataSetChanged();
                    Toast.makeText(itemView.getContext(), "Note is deleted!", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }
    }
}
