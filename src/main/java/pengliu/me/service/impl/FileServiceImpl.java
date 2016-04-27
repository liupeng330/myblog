package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengliu.me.service.FileService;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by peng on 16-4-27.
 */
@Service
public class FileServiceImpl extends BaseService implements FileService
{
    private Logger logger = Logger.getLogger(FileServiceImpl.class);

    public List<String> getAllImageNamesFromServer(String basePath)
    {
        File basePathDir = new File(basePath);
        File[] files = basePathDir.listFiles();
        Arrays.sort(files, new Comparator<File>()
        {
            public int compare(File o1, File o2)
            {
                 if(o1.lastModified() > o2.lastModified())
                     return 1;
                if(o1.lastModified() < o2.lastModified())
                    return -1;
                return 0;
            }
        });

        List<String> fileNames = new ArrayList<String>();
        for(File file: basePathDir.listFiles())
        {
            fileNames.add(file.getName());
        }

        return fileNames;
    }
}
