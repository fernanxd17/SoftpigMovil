package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.Softpig.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PDFActivity extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView = (PDFView) findViewById(R.id.pdfView);
    }
}
