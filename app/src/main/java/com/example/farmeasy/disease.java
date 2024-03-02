package com.example.farmeasy;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farmeasy.ml.ModelUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class disease extends AppCompatActivity {
    TextView disease,result;
    Button btn;
    ImageView img;
    int imageSize=224;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        disease=findViewById(R.id.disease);
        result=findViewById(R.id.result);
        btn=findViewById(R.id.button);
        img=findViewById(R.id.img);
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 100);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialog();
            }
        });
    }
    private void showOptionsDialog() {
        // Use AlertDialog to prompt the user to choose between camera and gallery
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option");
        builder.setItems(new CharSequence[]{"Take Photo", "Choose from Gallery"}, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        checkCameraPermissionAndLaunchCamera();
                        break;
                    case 1:
                        launchGallery();
                        break;
                }
            }
        });
        builder.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkCameraPermissionAndLaunchCamera() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 1);
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
        }
    }
    private void launchGallery() {
        // Launch the gallery to pick an image
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 2); // Use a different request code (e.g., 2) for gallery
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                // Image captured from the camera
                Bitmap image = (Bitmap) data.getExtras().get("data");
                processImage(image);
            } else if (requestCode == 2 && data != null) {
                // Image selected from the gallery
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    processImage(selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void processImage(Bitmap image) {
        //int dim = Math.min(image.getWidth(), image.getHeight());
        //image = ThumbnailUtils.extractThumbnail(image, dim, dim);
        img.setImageBitmap(image);
        image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
        diseaseFun(image);
    }

    private void diseaseFun(Bitmap image) {
        try {
            ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());
            TensorBuffer input1=TensorBuffer.createFixedSize(new int[]{1,224,224,3}, DataType.FLOAT32);
            ByteBuffer byteBuffer=ByteBuffer.allocateDirect(4*imageSize*imageSize*3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValue=new int[imageSize*imageSize];
            image.getPixels(intValue,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());

            int pxl=0;
            for(int i=0;i<imageSize;i++){
                for(int j=0;j<imageSize;j++){
                    int val=intValue[pxl++];
                    byteBuffer.putFloat(((val>>16) & 0xFF) * (1.f/255.f));
                    byteBuffer.putFloat(((val>>8) & 0xFF) * (1.f/255.f));
                    byteBuffer.putFloat((val&0xFF)*(1.f/255.f));
                }
            }
            input1.loadBuffer(byteBuffer);
            ModelUnquant.Outputs outputs=model.process(input1);
            TensorBuffer output0=outputs.getOutputFeature0AsTensorBuffer();

            float[] confidence=output0.getFloatArray();
            int maxPos=0;
            float maxConfidence=0;
            for(int i=0;i<confidence.length;i++){
                if(confidence[i]>maxConfidence){
                    maxConfidence=confidence[i];
                    maxPos=i;
                }
            }
            String[] classes={"LeafBlast","Hispa","Healthy","BrownSpot"};
            result.setText(classes[maxPos]);
            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q="+result.getText())));
                }
            });
            model.close();
        }catch (IOException e){
            //handle
        }
    }
}