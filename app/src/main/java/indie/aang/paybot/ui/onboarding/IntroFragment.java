package indie.aang.paybot.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import butterknife.BindView;
import butterknife.ButterKnife;
import indie.aang.paybot.R;
import indie.aang.paybot.ui.base.BaseFragment;

public class IntroFragment extends BaseFragment {

    View ROOT_VIEW;

    @BindView(R.id.title)
    TextView titletextView;
    @BindView(R.id.description)
    TextView descriptionTextView;
    @BindView(R.id.image)
    ImageView imageView;





    private int mPage;

    public static IntroFragment newInstance(String title, String description, @DrawableRes int imageRes, int page) {
        IntroFragment frag = new IntroFragment();
        Bundle b = new Bundle();
        b.putString("title", title);
        b.putString("description", description);
        b.putInt("image", imageRes);
        b.putInt("page", page);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getArguments().containsKey("title"))
            throw new RuntimeException("Fragment must contain a TITLE argument!");
        if (!getArguments().containsKey("description"))
            throw new RuntimeException("Fragment must contain a DESCRIPTION argument!");
        if (!getArguments().containsKey("image"))
            throw new RuntimeException("Fragment must contain a IMAGE argument!");
        if (!getArguments().containsKey("page"))
            throw new RuntimeException("Fragment must contain a PAGE argument!");


        mPage = getArguments().getInt("page");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout resource file
        ROOT_VIEW = getActivity().getLayoutInflater().inflate(R.layout.fragment_layout_intro_one, container, false);
        UNBINDER = ButterKnife.bind(this, ROOT_VIEW);
        // Set the current page index as the View's tag (useful in the PageTransformer)
        ROOT_VIEW.setTag(mPage);

        titletextView.setText(getArguments().getString("title"));
        descriptionTextView.setText(getArguments().getString("description"));
        imageView.setImageResource(getArguments().getInt("image"));



        return ROOT_VIEW;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the background color of the root view to the color specified in newInstance()


     }

}