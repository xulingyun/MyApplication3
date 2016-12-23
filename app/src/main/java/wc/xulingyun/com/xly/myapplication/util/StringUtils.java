/*
 * Copyright (C) 2012 YIXIA.COM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wc.xulingyun.com.xly.myapplication.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wc.xulingyun.com.xly.myapplication.dao.LrcDao;

public class StringUtils {
	public static String join(Object[] elements, CharSequence separator) {
		return join(Arrays.asList(elements), separator);
	}

	public static String join(Iterable<? extends Object> elements, CharSequence separator) {
		StringBuilder builder = new StringBuilder();

		if (elements != null) {
			Iterator<? extends Object> iter = elements.iterator();
			if (iter.hasNext()) {
				builder.append(String.valueOf(iter.next()));
				while (iter.hasNext()) {
					builder.append(separator).append(String.valueOf(iter.next()));
				}
			}
		}

		return builder.toString();
	}

	public static String fixLastSlash(String str) {
		String res = str == null ? "/" : str.trim() + "/";
		if (res.length() > 2 && res.charAt(res.length() - 2) == '/')
			res = res.substring(0, res.length() - 1);
		return res;
	}

	public static int convertToInt(String str) throws NumberFormatException {
		int s, e;
		for (s = 0; s < str.length(); s++)
			if (Character.isDigit(str.charAt(s)))
				break;
		for (e = str.length(); e > 0; e--)
			if (Character.isDigit(str.charAt(e - 1)))
				break;
		if (e > s) {
			try {
				return Integer.parseInt(str.substring(s, e));
			} catch (NumberFormatException ex) {
//				Log.e("convertToInt", ex);
				throw new NumberFormatException();
			}
		} else {
			throw new NumberFormatException();
		}
	}
	/*
	*参数是以毫秒为单位的。
	*
	 */
	public static String generateTime(long time) {
		int totalSeconds = (int) (time / 1000);
		int seconds = totalSeconds % 60;
		int minutes = (totalSeconds / 60) % 60;
		int hours = totalSeconds / 3600;
		return hours > 0 ? String.format(Locale.CHINA,"%02d:%02d:%02d", hours, minutes, seconds) : String.format(Locale.CHINA,"%02d:%02d", minutes, seconds);
	}

	public static ArrayList<LrcDao> parseLrcSong(String path){
		ArrayList<LrcDao> lrcDaoArrayList = new ArrayList<>();
//		File f = new File(path.replace(".mp3", ".lrc"));
		File f = new File(path);
		if(!f.exists()){
			return null;
		}
		//创建一个文件输入流对象
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);

			String s = "";
			while((s = br.readLine()) != null) {
				s = s.replace("[", "");
				s = s.replace("]", "@");
				String splitLrcData[] = s.split("@");
				LrcDao lrcDao;
				if(splitLrcData.length > 1) {
					int temp = time2Str(splitLrcData[0]);
					if(temp!=-1&&!splitLrcData[1].equals("")){
						lrcDao = new LrcDao();
						lrcDao.setTime(temp);
						lrcDao.setContent(splitLrcData[1]);
						lrcDaoArrayList.add(lrcDao);
					}
				}
			}
		} catch (Exception e) {
			lrcDaoArrayList = null;
			e.printStackTrace();
		}finally {
			try {
				if(br!=null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(isr!=null)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fis!=null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return lrcDaoArrayList;
		}
	}
	private static int time2Str(String timeStr) {
		timeStr = timeStr.replace(":", ".");
		timeStr = timeStr.replace(".", "@");
		String timeData[] = timeStr.split("@"); //将时间分隔成字符串数组
		//分离出分、秒并转换为整型
		try {
			int minute = Integer.parseInt(timeData[0]);
			int second = Integer.parseInt(timeData[1]);
			int millisecond = Integer.parseInt(timeData[2]);
			//计算上一行与下一行的时间转换为毫秒数
			int currentTime = (minute * 60 + second) * 1000 + millisecond * 10;
			return currentTime;
		}catch (Exception exception){
			return -1;
		}
	}
	public static boolean isChinese(String str) {
		String regex = "[\\u4e00-\\u9fa5]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
}
