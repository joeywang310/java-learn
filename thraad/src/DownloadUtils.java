
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-05-12 17:12
 */
public class DownloadUtils {
    /**
     * 下载文件到本地
     * @author sun
     * @date 2018年3月25日 上午11:01:05
     * @param urlString
     * @param filename
     * @throws Exception
     */
    public static void download(String urlString, String filename)
            throws Exception {
        URL url = new URL(urlString);// 构造URL
        URLConnection con = url.openConnection();// 打开连接
        InputStream is = con.getInputStream();// 输入流
        String code = con.getHeaderField("Content-Encoding");
        if ((null != code) && code.equals("gzip")) {
            GZIPInputStream gis = new GZIPInputStream(is);
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filename);
            // 开始读取
            while ((len = gis.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            gis.close();
            os.close();
            is.close();
        } else {
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        }
    }

    public static void main(String[] args) {
        try {
//            download("http://www.7-zip.org/a/7z1604.exe", "D:\\迅雷下载/a.exe");
            download("https://partner.95516.com/matchings/images/S2018120409273440548/I201904291739263E75A.jpg", "D:\\迅雷下载/b.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
