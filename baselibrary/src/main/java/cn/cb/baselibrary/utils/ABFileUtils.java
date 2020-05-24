package cn.cb.baselibrary.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import cn.cb.baselibrary.BaseApplication;

public class ABFileUtils {
    private static final String TAG = "ABFileUtils";

    private static final String cacheFilePathEX = BaseApplication.getContext().getExternalCacheDir() + File.separator + "files" + File.separator;
    private static final String cacheTempFileName = cacheFilePathEX + "temp.json";
    private static File temp = new File(cacheTempFileName);

    private static File webParentFile = BaseApplication.getContext().getDir("webview", Context.MODE_APPEND);
    private static final String DB_COOKIES = "Cookies";
    private static final String DB_COOKIES_PATH = webParentFile + File.separator + DB_COOKIES;

    /**
     * 复制cookie数据库文件
     * 命名为Cookies_asName
     * 路径：同目录下
     *
     * @param asName
     * @return
     */
    public static boolean copyCookieDBAsName(String asName) {
        File cookieDB = new File(DB_COOKIES_PATH);
        File targetDB = new File(DB_COOKIES_PATH + "_" + asName);
        return copyFile(cookieDB, targetDB);
    }

    public static boolean copyCookieFolderAsName(String asName) {
        return copyFolder(webParentFile.getPath(), webParentFile + "_" + asName);
    }

    public static boolean coverSysCookieFolderFromName(String fromName) {
        return copyFolder(webParentFile + "_" + fromName, webParentFile.getPath());
    }

    /**
     * 覆盖cookie数据库文件
     *
     * @param fromName
     * @return
     */
    public static boolean coverSysCookieDBFromName(String fromName) {
        File srcFile = new File(DB_COOKIES_PATH + "_" + fromName);
        File targetFile = new File(DB_COOKIES_PATH);
        return copyFile(srcFile, targetFile);
    }

    public static boolean copyFile(String srcPath, String targetPath) {
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath);
        return copyFile(srcFile, targetFile);
    }

    public static boolean copyFile(File srcFile, File targetFile) {
        try {
            if (srcFile.exists()) {
                if (!targetFile.exists()) {
                    targetFile.getParentFile().mkdir();
                    targetFile.createNewFile();
                }
                try (FileInputStream inputStream = new FileInputStream(srcFile);
                     FileOutputStream outputStream = new FileOutputStream(targetFile)) {
                    byte[] buffer = new byte[1024];
                    int byteRead;
                    while ((byteRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, byteRead);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                //throw new FileNotFoundException("srcPath is not found.");
                Log.e(TAG, "copyFile: srcPath is not found.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 复制文件夹及其中的文件
     *
     * @param oldPath String 原文件夹路径 如：data/user/0/com.test/files
     * @param newPath String 复制后的路径 如：data/user/0/com.test/cache
     * @return <code>true</code> if and only if the directory and files were copied;
     * <code>false</code> otherwise
     */
    public static boolean copyFolder(String oldPath, String newPath) {
        try {
            File newFile = new File(newPath);
            if (!newFile.exists()) {
                if (!newFile.mkdirs()) {
                    Log.e("--Method--", "copyFolder: cannot create directory.");
                    return false;
                }
            }
            File oldFile = new File(oldPath);
            String[] files = oldFile.list();
            File temp;
            for (String file : files) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file);
                } else {
                    temp = new File(oldPath + File.separator + file);
                }

                if (temp.isDirectory()) {   //如果是子文件夹
                    copyFolder(oldPath + "/" + file, newPath + "/" + file);
                } else if (!temp.exists()) {
                    Log.e("--Method--", "copyFolder:  oldFile not exist.");
                    return false;
                } else if (!temp.isFile()) {
                    Log.e("--Method--", "copyFolder:  oldFile not file.");
                    return false;
                } else if (!temp.canRead()) {
                    Log.e("--Method--", "copyFolder:  oldFile cannot read.");
                    return false;
                } else {
                    FileInputStream fileInputStream = new FileInputStream(temp);
                    FileOutputStream fileOutputStream = new FileOutputStream(newPath + "/" + temp.getName());
                    byte[] buffer = new byte[1024];
                    int byteRead;
                    while ((byteRead = fileInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, byteRead);
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeFile(String key, String value) {
        if (!temp.exists()) {
            try {
                temp.getParentFile().mkdirs();
                temp.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        JSONObject object = readTempFile();
        if (object != null) {
            try (PrintStream printStream = new PrintStream(temp)) {
                object.put(key, value);
                printStream.print(object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static String readFileByKey(String key) {
        JSONObject object = readTempFile();
        if (object != null) {
            try {
                String str = object.getString(key);
                return str;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject readTempFile() {
        if (!temp.exists()) {
            try {
                throw new FileNotFoundException(temp.getAbsolutePath() + " is not found");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try (FileInputStream inputStream = new FileInputStream(temp)) {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int b;
            while ((b = inputStream.read(bytes)) != -1) {
                result.write(bytes, 0, b);
            }
            String jsonStr = result.toString("UTF-8");
            if (jsonStr != null && !jsonStr.isEmpty()) {
                return new JSONObject(jsonStr);
            } else {
                return new JSONObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
