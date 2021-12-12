package ru.profitsw2000.firebasefirestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FootballClubAdapter extends BaseAdapter {

    List<FootballClub> league	;
    Context context	;
    LayoutInflater layoutInflater;

    public FootballClubAdapter(Context context, List<FootballClub> league) {
        this.context = context	;
        this.league = league	;
    }

    @Override
    public int getCount() {
        return league.size();
    }

    @Override
    public Object getItem(int position) {
        return league.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClubViewHolder clubViewHolder   ;

        if (convertView == null){
            layoutInflater = layoutInflater.from(this.context)  ;
            convertView = layoutInflater.inflate(R.layout.item_view, null)  ;

            clubViewHolder = new ClubViewHolder()   ;
            clubViewHolder.textViewClubName = (TextView)convertView.findViewById(R.id.item_club)  ;
            clubViewHolder.textViewCity = (TextView)convertView.findViewById(R.id.item_city) ;
            clubViewHolder.textViewCountry = (TextView)convertView.findViewById(R.id.item_country)   ;
            clubViewHolder.imageLogoFC = (ImageView)convertView.findViewById(R.id.logo_img_list_item)   ;

            convertView.setTag(clubViewHolder);
        } else {
            clubViewHolder = (ClubViewHolder) convertView.getTag()  ;
        }

        final FootballClub footballClub = league.get(position);

        clubViewHolder.textViewClubName.setText(footballClub.getClub());
        clubViewHolder.textViewCity.setText(footballClub.getCity());
        clubViewHolder.textViewCountry.setText(footballClub.getCountry());
        Picasso.get().load(footballClub.getFc_logo()).into(clubViewHolder.imageLogoFC);
        //((ImageView)convertView.findViewById(R.id.logo_img_list_item)).setImageResource(R.drawable.common_google_signin_btn_icon_dark);

        return convertView;
    }

    private static class ClubViewHolder {
        public TextView textViewClubName    ;
        public TextView textViewCity    ;
        public TextView textViewCountry    ;
        public ImageView imageLogoFC    ;
    }
}
