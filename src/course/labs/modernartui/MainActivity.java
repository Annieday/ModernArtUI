package course.labs.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int INITIAL_COLOR = 255;

	private TextView colorView1;
	private TextView colorView2;
	private TextView colorView3;
	private TextView colorView4;
	private TextView colorView5;
	private SeekBar colorBar;
	private SeekBar lineBar;

	protected static final String MOMA_URI = "http://www.moma.org/";
	private Builder mBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBuilder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
		colorView1 = (TextView) findViewById(R.id.colorView1);
		colorView2 = (TextView) findViewById(R.id.colorView2);
		colorView3 = (TextView) findViewById(R.id.colorView3);
		colorView4 = (TextView) findViewById(R.id.colorView4);
		colorView5 = (TextView) findViewById(R.id.colorView5);

		colorBar = (SeekBar) findViewById(R.id.colorChangeSeekBar);
		lineBar = (SeekBar) findViewById(R.id.lineChangeSeekBar);
		colorBar.setMax(255);
		lineBar.setMax(5);
		drawColor(0);
		colorBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				drawColor(progress);

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});
		lineBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				changeLine(2 + progress);

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void changeLine(int margin) {
		ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) colorView1
				.getLayoutParams();
		ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams) colorView2
				.getLayoutParams();
		ViewGroup.MarginLayoutParams mlp3 = (ViewGroup.MarginLayoutParams) colorView3
				.getLayoutParams();
		ViewGroup.MarginLayoutParams mlp4 = (ViewGroup.MarginLayoutParams) colorView4
				.getLayoutParams();
		mlp1.setMargins(margin, margin, margin, margin);
		mlp2.setMargins(margin, margin, margin, margin);
		mlp3.setMargins(margin, margin, margin, margin);
		mlp4.setMargins(margin, margin, margin, margin);
		colorView1.setLayoutParams(mlp1);
		colorView2.setLayoutParams(mlp2);
		colorView3.setLayoutParams(mlp3);
		colorView4.setLayoutParams(mlp4);

	}

	private void drawColor(int color) {

		colorView1.setBackgroundColor(Color.rgb(INITIAL_COLOR, color, 0));
		colorView2.setBackgroundColor(Color.rgb(color, 0, INITIAL_COLOR));
		colorView3.setBackgroundColor(Color.rgb(0, INITIAL_COLOR, color));
		colorView4.setBackgroundColor(Color.rgb(INITIAL_COLOR, INITIAL_COLOR,
				color));
		colorView5.setBackgroundColor(Color.rgb(255 - color, INITIAL_COLOR,
				255 - color));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.more_info) {
			moreInformationBuilderSet();
			AlertDialog dialog = mBuilder.show();
			TextView messageView = (TextView) dialog
					.findViewById(android.R.id.message);
			messageView.setGravity(Gravity.CENTER);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void moreInformationBuilderSet() {

		// TODO Auto-generated method stub
		mBuilder.setIcon(R.drawable.ic_launcher)
				.setMessage(R.string.dialog_more_information_text)
				.setPositiveButton("Visit MOMA",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intent = new Intent(Intent.ACTION_VIEW,
										Uri.parse(MOMA_URI));
								startActivity(intent);
							}
						})
				.setNegativeButton("Not Now",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									int id) {
								dialog.dismiss();
							}
						});

	}
}
