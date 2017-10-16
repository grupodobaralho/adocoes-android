package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.MenorMidia;
import br.pucrs.ages.adocoes.Model.RefMidia;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Matheus on 07/09/2017.
 */

public class ViewPagerFragment extends Fragment {

    private static final String ARGUMENT_MIDIAS = "midias";

    private static ArrayList<String> mMidiaIds;
    private static String menorId;

    public static ViewPagerFragment newInstance(Menor menor) {
        menorId = menor.getId();

        final Bundle args = new Bundle();

        mMidiaIds = new ArrayList<>();
        for (RefMidia refMidia : menor.getRefMidias()) {
            mMidiaIds.add(refMidia.getId());
        }

        args.putStringArrayList(ARGUMENT_MIDIAS, mMidiaIds);

        final ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
            return mMidiaIds.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            final View view = mLayoutInflater.inflate(R.layout.viewpager_item, container, false);

            final ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
            Drawable carta = getResources().getDrawable(R.drawable.carta, null);



//            imageView.setImageDrawable(carta);


            // Talvez seja preciso fazer um filter em mMidiaIds para pegar apenas as fotos. Provavelmente virão refs de vídeos, cartas, etc, junto no campo midias.
            RestUtil.getMenoresEndPoint().menorMidia(menorId, mMidiaIds.get(position), "Bearer anonymous").enqueue(new Callback<MenorMidia>() {
                @Override
                public void onResponse(Call<MenorMidia> call, Response<MenorMidia> response) {
                    MenorMidia midia = response.body();
                    if (midia != null) {
                        byte[] imageAsBytes = Base64.decode(midia.getConteudo().getBytes(), Base64.DEFAULT);
                        Bitmap imgBitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                        imageView.setImageBitmap(imgBitmap);
                    }
                }

                @Override
                public void onFailure(Call<MenorMidia> call, Throwable t) {

                }
            });



            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
