package com.mapbox.services.android.navigation.ui.v5.instruction;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.mapbox.api.directions.v5.models.BannerComponents;
import com.mapbox.services.android.navigation.ui.v5.R;

import java.util.List;

public class ExitSignCreator extends NodeCreator<BannerComponentNode, ExitSignVerifier> {
  private String exitNumber;
  private int startIndex;
//  private int endIndex;
  private TextViewUtils textViewUtils;

  ExitSignCreator() {
    super(new ExitSignVerifier());
    textViewUtils = new TextViewUtils();

  }

  @Override
  BannerComponentNode setupNode(BannerComponents components, int index, int startIndex, String
    modifier) {
    if (components.type().equals("exit")) {
//      exitText = components.text();
//      this.startIndex = startIndex;
      return null;
    } else if (components.type().equals("exit-number")) {
      exitNumber = components.text();
      this.startIndex = startIndex;
    }

    return new BannerComponentNode(components, startIndex);
  }

  /**
   * One coordinator should override this method, and this should be the coordinator which populates
   * the textView with text.
   *
   * @param textView             to populate
   * @param bannerComponentNodes containing instructions
   */
  @Override
  void postProcess(TextView textView, List<BannerComponentNode> bannerComponentNodes) {
//    textView.hideExitView();

    if (exitNumber != null) { // matched iOS by ignoring exit text
//      textView.showExitView(exitText + " " + exitNumber);
//      Spannable instructionSpannable = new SpannableString(textView.getText());

      LayoutInflater inflater = (LayoutInflater) textView.getContext().getSystemService(Context
        .LAYOUT_INFLATER_SERVICE);

      TextView exitSignView = (TextView) inflater.inflate(R.layout.exit_sign_view, null);

      exitSignView.setText(exitNumber);



//      textViewUtils.setImageSpan(textView, exitSignView, startIndex, startIndex + exitNumber.length());
      textViewUtils.setImageSpan(textView, textView.getContext().getDrawable(R.drawable.ic_exit_arrow), startIndex, startIndex +
        exitNumber.length(), false);

    }
  }
}
