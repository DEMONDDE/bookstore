package cn.domain;

import cn.mapper.DatabaseInsert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * 仅开发使用
 * 对数据库插入信息
 * @author 胡建德
 */
public class FileNameDatabase {

    @Autowired
    private DatabaseInsert databaseInsert;

    /**
     * 插入书籍信息
     */
//    public void insertBook(){
//        String path = "C:\\Users\\78114\\Desktop\\bookcover";
//        File file = new File(path);
//        String[] names = file.list();
//        System.out.println(names[2]);
//        System.out.println(databaseInsert);
//        databaseInsert.insertBook(names);
//    }
}
