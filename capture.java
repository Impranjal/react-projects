View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
public static Bitmap getScreenShot(View view) {
       View screenView = view.getRootView();
       screenView.setDrawingCacheEnabled(true);
       Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
       screenView.setDrawingCacheEnabled(false);
       return bitmap;
 }
 
 public static void store(Bitmap bm, String fileName){
    final static String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
    File dir = new File(dirPath);
    if(!dir.exists())
        dir.mkdirs();
    File file = new File(dirPath, fileName);
    try {
        FileOutputStream fOut = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
        fOut.flush();
        fOut.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private void shareImage(File file){
    Uri uri = Uri.fromFile(file);
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_SEND);
    intent.setType("image/*");

    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
    intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
    intent.putExtra(Intent.EXTRA_STREAM, uri);
    try {
        startActivity(Intent.createChooser(intent, "Share Screenshot"));
    } catch (ActivityNotFoundException e) {
        Toast.makeText(context, "No App Available", Toast.LENGTH_SHORT).show();
    }
}
