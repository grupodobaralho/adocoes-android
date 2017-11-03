package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
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

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Funcionalidades.ImagePreview.ImagePreviewActivity;
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

    public static final String ARGUMENT_MIDIAS = "midias";

    private static ArrayList<RefMidia> mMidiaIds = new ArrayList<>();
    private static String menorId;

    public static ViewPagerFragment newInstance(Menor menor) {
        menorId = menor.getId();

        final Bundle args = new Bundle();

        args.putParcelableArrayList(ARGUMENT_MIDIAS, new ArrayList<RefMidia>(menor.getMidias()));

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

        final ArrayList<RefMidia> midias = getArguments().getParcelableArrayList(ARGUMENT_MIDIAS);

        if (midias != null) {
            mMidiaIds = midias;
        }

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
        public Object instantiateItem(ViewGroup container, final int position) {

            final View view = mLayoutInflater.inflate(R.layout.viewpager_item, container, false);

            final ImageView imageView = (ImageView) view.findViewById(R.id.item_image);

            final String token = UserBusiness.getInstance().getAccessToken();
            //Talvez seja preciso fazer um filter em mMidiaIds para pegar apenas as fotos. Provavelmente virão refs de vídeos, cartas, etc, junto no campo midias.
            final RefMidia midiaId = mMidiaIds.get(position);
            RestUtil.getMenoresEndPoint().menorMidia(menorId, midiaId.getId(), token).enqueue(new Callback<MenorMidia>() {
                @Override
                public void onResponse(Call<MenorMidia> call, Response<MenorMidia> response) {

                    MenorMidia midia = response.body();

                    if (midia != null) {
                        switch (midiaId.getType()) {
                            case "foto":
                                byte[] imageAsBytes = Base64.decode(midia.getConteudo().getBytes(), Base64.DEFAULT);
                                Bitmap imgBitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                                imageView.setImageBitmap(imgBitmap);
                                break;
                            case "video":
                                imageView.setImageResource(R.drawable.boy_7);
                                break;
                            default:
                                imageView.setImageResource(R.drawable.boy_1);
                                break;
                        }

                    }
                }

                @Override
                public void onFailure(Call<MenorMidia> call, Throwable t) {

                }
            });

            // For testing purpose only!
//            final Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
//            imageView.setImageBitmap(image);

            container.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), ImagePreviewActivity.class);
                    intent.putExtra(ImagePreviewActivity.EXTRA_MIDIA, (Parcelable) midiaId);
                    intent.putExtra(ImagePreviewActivity.EXTRA_MENOR_ID, menorId);
                    startActivity(intent);
                }
            });

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
