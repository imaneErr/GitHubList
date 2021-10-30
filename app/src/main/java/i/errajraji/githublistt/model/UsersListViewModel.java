package i.errajraji.githublistt.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import i.errajraji.githublistt.R;


public class UsersListViewModel extends ArrayAdapter<GitUser> {
    private int resource;
    private Object URL;
    private Object url;

    public UsersListViewModel(@NonNull Context context, int resource, List<GitUser> data){
        super(context,resource,data);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(resource, parent, false);

        CircleImageView imageView =listViewItem.findViewById(R.id.imageView);
        TextView textView = listViewItem.findViewById(R.id.textView);
        TextView textView1 = listViewItem.findViewById(R.id.textView2);

        textView.setText(getItem(position).username);
        textView1.setText(String.valueOf(getItem(position).score));


        try{
            URL url =new URL(getItem(position).avatarUrl);
            
          Bitmap bitmap =  BitmapFactory.decodeStream(url.openStream());
            imageView.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return listViewItem;
    }
}
