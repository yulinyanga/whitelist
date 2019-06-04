package com.trs.zhq.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;


public final class FileUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	public static String OS_SEPARATOR = File.separator;
	
	private static boolean found = false;

	/**
	 * 如果完全匹配某个字符集检测算法, 则该属性保存该字符集的名称. 否则(如二进制文件)其值就为默认值 null, 这时应当查询属性
	 */
	private static String encoding = null;



	/**
	 * @Title: unzip 对zip格式的压缩包进行解压 所需要的jar包是ant.jar
	 * @param
	 * @return_type void
	 * @author Create User At: LJQ
	 * @date Create Date At: 2015-12-8 下午8:28:31
	 */
	public static void unzip(String sourceZip, String destDir) {
			Project p = new Project();
			Expand e = new Expand();
			e.setProject(p);
			e.setSrc(new File(sourceZip));
			e.setOverwrite(false);
			e.setDest(new File(destDir));
			/*
			 * ant下的zip工具默认压缩编码为UTF-8编码， 而winRAR软件压缩是用的windows默认的GBK或者GB2312编码
			 * 所以解压缩时要制定编码格式
			 */
			e.setEncoding(getFilecharset(new File(sourceZip)));
			e.execute();

	}

	//判断编码格式方法
	private static  String getFilecharset(File sourceFile) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1) {
				return charset; //文件编码为 ANSI
			} else if (first3Bytes[0] == (byte) 0xFF
					&& first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE"; //文件编码为 Unicode
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE"; //文件编码为 Unicode big endian
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8"; //文件编码为 UTF-8
				checked = true;
			}
			bis.reset();
			if (!checked) {
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
							// (0x80
							// - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}


	/**
	 * 删除一个文件或者目录
	 *
	 * @param targetPath
	 *            文件或者目录路径
	 * @IOException 当操作发生异常时抛出
	 */
	public static void deleteFile(String targetPath) throws IOException {
		File targetFile = new File(targetPath);
		try {
			if (targetFile.exists() && targetFile.isDirectory()) {
				FileUtils.deleteDirectory(targetFile);
			} else if (targetFile.exists() && targetFile.isFile()) {
				targetFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getJarRootPath() throws FileNotFoundException {
		String path = ResourceUtils.getURL("classpath:").getPath();
		//=> file:/root/tmp/demo-springboot-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/
		//创建File时会自动处理前缀和jar包路径问题  => /root/tmp
		File rootFile = new File(path);
		if(!rootFile.exists()) {
			System.out.println("根目录不存在, 重新创建");
			rootFile = new File("");
			System.out.println("重新创建的根目录: "+rootFile.getAbsolutePath());
		}
		System.out.println("项目根目录: "+rootFile.getAbsolutePath());        //获取的字符串末尾没有分隔符 /
		return rootFile.getAbsolutePath();
	}

}
