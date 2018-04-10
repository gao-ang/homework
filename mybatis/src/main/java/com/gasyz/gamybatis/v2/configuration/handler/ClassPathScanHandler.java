package com.gasyz.gamybatis.v2.configuration.handler;

import com.gasyz.gamybatis.v2.exception.FileCastClassException;
import com.gasyz.gamybatis.v2.exception.PathConvertURIException;

import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gaoang on 2018/4/10.
 */
public class ClassPathScanHandler {

    private ClassLoader classLoader = ClassPathScanHandler.class.getClassLoader();//默认使用的类加载器

    Set<Class<?>> mappers = new HashSet<Class<?>>();

    public ClassPathScanHandler(String scanPath) {
        this.doScanPackageClassesByFile(scanPath);
    }

    /**
     * 扫描包内的.class文件
     * @param scanPath
     */
    private void doScanPackageClassesByFile(final String scanPath) {
        String scanPathReplace = scanPath.replace('.', '/');
        URI uri = null;
        try {
             uri = classLoader.getResource(scanPathReplace).toURI();
        } catch (Exception e) {
            throw new PathConvertURIException("扫描包路径转换异常",e);
        }
        File file = new File(uri);
        File[] files = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (!file.isDirectory()) {
                    String name = file.getName();
                    if (name.endsWith(".class")) {
                        Class<?> clazz = convertFileToClass(file, scanPath);
                        mappers.add(clazz);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 扫到的文件转换为class
     * @param file
     * @param scanPath
     * @return
     */
    private Class<?> convertFileToClass(File file,String scanPath) {
        String classReference = scanPath+"."+file.getName().replace(".class","");
        try {
            return Class.forName(classReference);
        } catch (ClassNotFoundException e) {
            throw new FileCastClassException("文件转换class异常",e);
        }
    }

    public Set<Class<?>> getMappers() {
        return mappers;
    }
}
