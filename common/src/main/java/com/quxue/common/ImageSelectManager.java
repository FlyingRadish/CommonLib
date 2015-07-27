package com.quxue.common;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by houxg on 2015/5/18.
 */
public class ImageSelectManager {

    String path;
    String name;
    File tempFile;

    public ImageSelectManager(String path, String name) {
        this.path = path;
        this.name = name;
    }

    /**
     * 生成指定的相册选择Intent,当SDK_INT小于19（KITKAT）时，可以直接得到输出的图片，当大于等于19时，
     * 需要调用getImagePathFromUriForKitkat()方法，得到图片的真实地址，然后调用getCropIntent()送去裁切
     *
     * @param scalX   裁切框比例X
     * @param scalY   裁切框比例Y
     * @param outputX 输出尺寸X
     * @param outputY 输出尺寸Y
     * @return 相册选择Intent
     */
    public Intent getSelectIntent(int scalX, int scalY, int outputX, int outputY) {
        Uri tempUri = getTempFileUri();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //for under Kitka
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", scalX);
        intent.putExtra("aspectY", scalY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    /**
     * 生成裁切Intent
     *
     * @param scalX   裁切框比例X
     * @param scalY   裁切框比例Y
     * @param outputX 输出尺寸X
     * @param outputY 输出尺寸Y
     * @return 裁切Intent
     */
    public Intent getCropIntent(Uri imageUri, int scalX, int scalY, int outputX, int outputY) {
        Uri tempUri = getTempFileUri();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", scalX);
        intent.putExtra("aspectY", scalY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    /**
     * 得到使用相机拍摄的Intent，拍摄结果输出到tempFile
     *
     * @return 拍摄Intent
     */
    public Intent getCameraIntent() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        Uri tempUri = getTempFileUri();

        intent.putExtra("output", tempUri);
        return intent;
    }

    public File getOutputFile() {
        return tempFile;
    }

    private Uri getTempFileUri() {
        tempFile = new File(path, name);
        if (tempFile.exists()) {
            return Uri.fromFile(tempFile);
        }
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(tempFile);
    }

    /**
     * 从缓存得到Bitmap
     *
     * @param contentResolver contentResolver
     * @return 获取到的Bitmap，如果为null则获取失败
     */
    public Bitmap getBitmapFromUri(ContentResolver contentResolver) {
        Uri temp = getTempFileUri();
        if (temp == null) {
            return null;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(temp, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 针对KITKAT或以上的版本，需要专门取出图片的真实地址
     *
     * @param context context
     * @param uri     返回result的intent.getData()
     * @return 图片的真实地址
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getImagePathFromUriForKitkat(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private String getDataColumn(Context context, Uri uri, String selection,
                                 String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
