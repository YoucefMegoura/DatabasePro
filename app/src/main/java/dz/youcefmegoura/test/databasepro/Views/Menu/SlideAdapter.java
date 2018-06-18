package dz.youcefmegoura.test.databasepro.Views.Menu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dz.youcefmegoura.test.databasepro.R;

/**
 * Created by Mékkah on 07/05/2018.
 */

public class SlideAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;

    }

    //Arrays
    public int[] slide_image = {
            R.drawable.title,
            R.drawable.categorie_for_slide,
            R.drawable.crown,
            R.drawable.jeton

    };
    public String[] slide_text = {
            "L'enseignement gratuit de la prononciation des langues étrangères.",
            "L'apprentissage par le jeu. Avance en débloquant de petites unités.",
            "Finis chaque niveau en gagnant des point permettant de debloquer les niveaux suivant ",
            "Gagne des jetons et les utiliser pour l'aide de la prononciation "
    };

    @Override
    public int getCount() {
        return slide_image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView1= (ImageView) view.findViewById(R.id.imageSlide);
        TextView textView = view.findViewById(R.id.textSlide);
        imageView1.setImageResource(slide_image[position]);
        textView.setText(slide_text[position]);
        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
