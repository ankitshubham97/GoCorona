package com.ankitshubham.gocorona.ui.news;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ankitshubham.gocorona.R;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        final TextView textView = root.findViewById(R.id.text_news);
        newsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setClickable(true);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "";
                text += "<b>WHO Guildelines</b>";
                text += "<br><a href='https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public/myth-busters'> Myth Busters </a>";
                text += "<br><a href='https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public'> Advice For Public </a>";
                text += "<br><a href='https://www.who.int/emergencies/diseases/novel-coronavirus-2019/situation-reports'> Situation Reports </a>";
                text += "<br><a href='https://www.who.int/emergencies/diseases/novel-coronavirus-2019/events-as-they-happen'> Events As they Happen </a>";
                text += "<br><a href='https://www.mygov.in/covid-19'> India COVID-19 updates </a>";
                text += "<p>";
                text += "<b>Recent News</b>";
                text += "<br><a href='https://www.business-standard.com/article/markets/covid-19-sensex-set-for-biggest-quarterly-fall-nifty-s-worst-in-28-years-120033100444_1.html'> Covid-19: Sensex posts biggest quarterly fall, Nifty's worst in 28 years </a>";
                text += "<br><br><a href='https://www.indiatoday.in/sports/cricket/story/rohit-sharma-donates-80-lakh-coronavirus-fight-pm-cares-kohli-dhoni-1661637-2020-03-31'> Covid-19: Rohit Sharma donates Rs 80 lakh to help India get 'back on its feet' </a>";
                text += "<br><br><a href='https://www.indiatoday.in/technology/news/story/covid-19-airtel-extends-pre-paid-validity-of-low-income-mobile-customers-credits-rs-10-talktime-1661607-2020-03-31'> COVID-19: Airtel extends pre-paid validity of low-income mobile customers, credits Rs 10 talktime  </a>";
                text += "<br><br><a href='https://economictimes.indiatimes.com/news/politics-and-nation/covid-19-declare-travel-history-or-face-legal-action-say-authorities-in-kashmir/articleshow/74908880.cms'> COVID-19: Declare travel history or face legal action, say authorities in Kashmir </a>";
                text += "<br><br><a href='https://www.indiatoday.in/science/story/coronavirus-research-cure-may-lie-in-old-existing-drugs-1661668-2020-03-31'> COVID-19 cure might already exist in old drugs, we're using pieces of coronavirus to find them: Scientist </a>";
                text += "<br><br><a href='https://economictimes.indiatimes.com/news/economy/policy/tax-and-duty-relief-likely-in-package-for-industry/articleshow/74900681.cms'> Covid-19 impact: Tax and duty relief likely in package for industry </a>";
                text += "<br><br><a href='https://thewire.in/health/tracking-covid-19-in-india-use-dip-in-growth-rate-to-ramp-up-public-health-interventions'> COVID-19 in India: Has Window of Opportunity Opened by Dip in Daily Growth Rates Closed? </a>";
                text += "<br><br><a href='https://www.indiatoday.in/coronavirus-outbreak/story/covid-19-shortage-of-ventilators-iit-team-pitches-bag-valve-mask-as-alternative-1661659-2020-03-31'> Covid-19: Shortage of ventilators; IIT team pitches Bag Valve Mask as alternative </a>";

                textView.setText(Html.fromHtml(text));
            }
        });
        return root;
    }
}
