package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by Matheus on 07/09/2017.
 */

public class ViewPagerFragment extends Fragment {

    private static final String ARGUMENT_MIDIAS = "midias";

    private int[] mMidiasMenor;

// TODO: Criar versão do método abaixo que use um Bundle para setar mMenores no fragment

    public static ViewPagerFragment newInstance(Menor menor) {
        final Bundle args = new Bundle();
//        args.putStringArrayList(ARGUMENT_MIDIAS, menor.getMidias());
        final ArrayList<String> stringList = new ArrayList<>();

        args.putStringArrayList(ARGUMENT_MIDIAS, stringList);
        final ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Fazer as paradas do delegate

        //

        // Receive Menores from api here

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        final Activity activity = getActivity();
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ImagePagerAdapter(activity));
        return view;
    }

    class ImagePagerAdapter extends PagerAdapter {

        private LayoutInflater mLayoutInflater;

        public ImagePagerAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // TODO: Change layout file to apropriate layout item
            final View view = mLayoutInflater.inflate(R.layout.viewpager_item, container, false);

            final ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
            Drawable carta = getResources().getDrawable(R.drawable.carta, null);

            imageView.setImageDrawable(carta);

            // Get view's components here, just like a ViewHolder


            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
